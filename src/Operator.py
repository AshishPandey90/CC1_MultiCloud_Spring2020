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
        
def calculator(list,accuracy_factor):
    if len(list) == 0:
        return -1,-1
    bucket=[0 for c in range(accuracy_factor)]
    total=[0 for c in range(accuracy_factor)]
    
    hold=1/accuracy_factor-0.01
    totalcount=0
    flag2=total[:]

    for num in list:
        num=Convertnumber(num, accuracy_factor)
        if(num<10):
            i = math.floor(num/(10/accuracy_factor))
        else:
            i = accuracy_factor - 1
        bucket[i] = bucket[i] + 1
        total[i] = total[i] + num
    while hold>0:
        partamount=0
        breakflag=0
        flag=[str(c+1) for c in range(accuracy_factor)]
        flag2=[0 for c in range(accuracy_factor)]
        #print(flag)
        for j in range(accuracy_factor):
            if bucket[j]/len(list)<hold:
                flag[j]='0'
        tmp=''.join(flag)
        tmp=tmp.split('0')
        for item in tmp:
            if not(item == ''):
                partamount = partamount + 1
        for chuck in tmp:
            if len(chuck)>0:
                chuckn=[c for c in chuck]
                chuck_count=0
                chuck_id=[]
                for inde in chuckn:
                    index=int(inde)
                    index = index - 1
                    chuck_id.append(index)
                    chuck_count = chuck_count + bucket[index]
                if partamount == 1 and chuck_count/len(list)>=0.5:
                    for i in chuck_id:
                        flag2[i]=1
                    breakflag = 1
                    break
                elif chuck_count/len(list)>(1/partamount):
                    for i in chuck_id:
                        flag2[i]=1
                        breakflag = 1
                    break
                elif len(chuck) == accuracy_factor:
                    flag2=[1 for c in range(accuracy_factor)]
                    breakflag = 1
                    break
        if(breakflag == 1):
            break
        hold = hold - 0.01
    
    flag=[0 for c in range(accuracy_factor)]
    if flag2[0] == 0 and flag2[1] == 0:
        bucket[0] = 0
    else:
        flag[0] = 1
    for k in range(1,accuracy_factor-1):
        if flag2[k] == 0 and flag2[k-1] == 0 and flag2[k+1] == 0:
            bucket[k] = 0
        else:
            flag[k] = 1
    

    if flag2[accuracy_factor-1] == 0 and flag2[accuracy_factor-2] == 0: 
        bucket[accuracy_factor-1] = 0
    else:
        flag[accuracy_factor-1] = 1
    

    for l in range(accuracy_factor):
        totalcount = totalcount + total[l]*flag[l]
    mean_value=totalcount/sum(bucket)
    standard_diviation=0
    for k in range(accuracy_factor):
        standard_diviation = ((2*k+1-mean_value)**2)*bucket[k] + standard_diviation
    #print(bucket,flag)
    standard_diviation=(standard_diviation/sum(bucket))**0.5
    return mean_value,standard_diviation
   
def Opertor(rulesfile,accuracy_factor):
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
    awslist=["aws",calculator(awsperformancelist, accuracy_factor),calculator(awsagilitylist, accuracy_factor),calculator(awscostlist, accuracy_factor),calculator(awssecuritylist, accuracy_factor)]
    genilist=["geni",calculator(geniperformancelist, accuracy_factor),calculator(geniagilitylist, accuracy_factor),calculator(genicostlist, accuracy_factor),calculator(genisecuritylist, accuracy_factor)]
    mulist=["mu",calculator(muperformancelist, accuracy_factor),calculator(muagilitylist, accuracy_factor),calculator(mucostlist, accuracy_factor),calculator(musecuritylist, accuracy_factor)]
    return awslist,genilist,mulist
    
if __name__=="__main__":
    '''
    Opertor(rulesfile,accuracy_factor,dropout_factor)
    rulesfile:json file contains rules
    accuracy_factor: amount of values for each property
        eg: if the value only has 'good' and 'bad', then it's 2
    output order is performance(mean,standard_deviation), agility(mean,standard_deviation), cost(mean,standard_deviation), sercuirty(mean,standard_deviation)
    '''
    print(Opertor('rules.json',5))
