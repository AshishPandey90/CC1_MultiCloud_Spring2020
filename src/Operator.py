import math
import json
from string import punctuation


def Gremmarcheck(sentence,partamount):
    for i in range(partamount):
        if(sentence[i*5+0]!="if"):
            return True
        if(not(sentence[i*5+1] == "performance" or "agility" or "cost" or "security")):
            return True
        if(sentence[i*5+2]!="is"):
            return True
        if(not(sentence[i*5+3] == "verypoor" or "belowaverage" or "average" or "aboveaverage" or "excellent" )):
            return True
        if(i<math.floor(len(sentence)/5)-1):
            if(sentence[i*5+4] == "and"):
                continue
            else:
                return True 
    i = math.floor(len(sentence)/5)-1
    if(not(sentence[i*5+4]=="then")):
        return True
    if(not(sentence[i*5+5]=="csp")):
        return True
    if(not(sentence[i*5+6]== "is")):
        return True
    if(not(sentence[i*5+5] == "aws" or "geni" or "mu")):
        return True
    return False
def Convertnumber(item,accuracy_factor):
    if accuracy_factor == 5:
        if item == 'verypoor':
            return 1
        if item == 'belowaverage':
            return 3
        if item == 'average':
            return 5
        if item == 'aboveaverage':
            return 7
        if item == 'excellent':
            return 9
        
def calculator(list,accuracy_factor,dropout_factor):
    if len(list) == 0:
        return -1
    bucket=[0 for c in range(accuracy_factor)]
    total=[0 for c in range(accuracy_factor)]
    hold=dropout_factor
    count=0
    totalcount=0
    for num in list:
        num=Convertnumber(num, accuracy_factor)
        if(num<10):
            i=math.floor(num/(10/accuracy_factor))
        else:
            i=accuracy_factor-1
        bucket[i] = bucket[i] + 1
        total[i] = total[i] + num
    for j in range(accuracy_factor):
        if bucket[j]/len(list)>hold:
            count = bucket[j] + count
            totalcount = total[j] + totalcount
    return totalcount/count
    
def Opertor(rulesfile,accuracy_factor,dropout_factor):
    f=open(rulesfile)
    data=json.load(f)
    muperformancelist=[]
    muagilitylist=[]
    mucostlist=[]
    musecuritylist=[]
    awsperformancelist=[]
    awsagilitylist=[]
    awscostlist=[]
    awssecuritylist=[]
    geniperformancelist=[]
    geniagilitylist=[]
    genicostlist=[]
    genisecuritylist=[]
    for rule in data['rules']:
        tmp=rule.split()
        sentence=' '.join(tmp)
        sentence=''.join([c for c in sentence if c not in punctuation])
        sentence=sentence.lower()
        sentence=sentence.split()
        if(Gremmarcheck(sentence,4)):
            return 0,0,0,0
        round=math.floor(len(sentence)/5)
        for i in range(round):
            if sentence[5*round+2]=='aws':
                if(sentence[5*i+1]=='performance'):
                    awsperformancelist.append(sentence[i*5+3])
                if(sentence[5*i+1]=='agility'):
                    awsagilitylist.append(sentence[i*5+3])
                if(sentence[5*i+1]=='cost'):
                    awscostlist.append(sentence[i*5+3])
                if(sentence[5*i+1]=='security'):
                    awssecuritylist.append(sentence[i*5+3])
            if sentence[5*round+2]=='geni':
                if(sentence[5*i+1]=='performance'):
                    geniperformancelist.append(sentence[i*5+3])
                if(sentence[5*i+1]=='agility'):
                    geniagilitylist.append(sentence[i*5+3])
                if(sentence[5*i+1]=='cost'):
                    genicostlist.append(sentence[i*5+3])
                if(sentence[5*i+1]=='security'):
                    genisecuritylist.append(sentence[i*5+3]) 
            if sentence[5*round+2]=='mu':
                if(sentence[5*i+1]=='performance'):
                    muperformancelist.append(sentence[i*5+3])
                if(sentence[5*i+1]=='agility'):
                    muagilitylist.append(sentence[i*5+3])
                if(sentence[5*i+1]=='cost'):
                    mucostlist.append(sentence[i*5+3])
                if(sentence[5*i+1]=='security'):
                    musecuritylist.append(sentence[i*5+3])
        awslist=["aws",calculator(awsperformancelist, accuracy_factor, dropout_factor),calculator(awsagilitylist, accuracy_factor, dropout_factor),calculator(awscostlist, accuracy_factor, dropout_factor),calculator(awssecuritylist, accuracy_factor, dropout_factor)]
        genilist=["geni",calculator(geniperformancelist, accuracy_factor, dropout_factor),calculator(geniagilitylist, accuracy_factor, dropout_factor),calculator(genicostlist, accuracy_factor, dropout_factor),calculator(genisecuritylist, accuracy_factor, dropout_factor)]
        mulist=["mu",calculator(muperformancelist, accuracy_factor, dropout_factor),calculator(muagilitylist, accuracy_factor, dropout_factor),calculator(mucostlist, accuracy_factor, dropout_factor),calculator(musecuritylist, accuracy_factor, dropout_factor)]
    return awslist,genilist,mulist
    
if __name__=="__main__":
    '''
    Opertor(rulesfile,accuracy_factor,dropout_factor)
    rulesfile:json file contains rules
    accuracy_factor: amount of values for each property
        eg: if the value only has 'good' and 'bad', then it's 2
    dropout_facotr: ignore the data points buckets that has lesser amount than dropout_factor*total_data_amount
        eg: for a list[0,10,10,10,10,10,10,10], if dropout_factor=0.2, then 0 bucket will be ignored since (1/7)<0.2
    '''
    print(Opertor('rules.json',5,0))


