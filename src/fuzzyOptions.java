/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
/**
 *
 * @author Martian Lapadatescu
 */
public class fuzzyOptions {
    
    public enum identEnum { VERY_BAD,BAD,NEUTRAL,GOOD,GREAT,EXCELLENT;} 
    public enum inputEnum {PERFORMANCE,AGILITY,COST,SERCURITY;}
    public enum outputEnum{GENI, AWS, MU;}
      
    ArrayList<String> identifiers = new ArrayList<>(Arrays.asList("Very_Bad","Bad", "Neutral, "Good","Great","Excellent"));
    ArrayList<String> inputDim = new ArrayList<>(Arrays.asList("Performance","Agility","Cost","Security"));
    ArrayList<String> outDim  = new ArrayList<>(Arrays.asList("Geni","AWS","Lewis"));
    
      public Map <"Performance",<String>> mapPerf;
      public Map <"Agility", <String>> mapAgil;
      public Map <"Cost", <String>> mapCost;
      public Map <"Security", <String>> mapSecurity;     
    
//    public Map <inputEnum, ArrayList<String>> mapPerf;
//    public Map <inputEnum, ArrayList<String>> mapAgil;
//    public Map <inputEnum, ArrayList<String>> mapCost;
//    public Map <inputEnum, ArrayList<String>> mapSecurity; 
    List<Entry<mapPerf, mapAgil,mapCost,mapSecurity>> Rule = new ArrayList<>();
    List<Entry<Rule>> Rules = new ArrayList<>();
    //
    //Rule.add(new SimpleImmutableEntry<>(mapPerf1,mapAgil1,mapCost1,mapSecurity1));
    //Rules.add(new SimpleImmutableEntry<>(Rule));
    
     static ArrayList<G>  subindentifier(ArrayList<G> arr,enum<E> lBound ,enum<E> rBound)
          {
            return arr.subList(lBound,uBound);
           } ;
     static String randomItem(ArrayList<G>)
                {
                     
                    return G.get( random.nextInt(G.size()) ) ;
                }
     static Rules generateData(int n,identEnum low,identEnum high,outputEnum ...CSP)
        
         {int i;
          String dataScore1,dataScore2,dataScore3,dataScore4;
         
          inputIdentifiers  = subindentifier(identiers,low,high);
          ArrayList<String> desiredProviders = new ArrayList();
          for(outputEnum provider : CSP )
               {
                 desiredProviders.add(provider);
               }
          for (i=0 ; i< n ;i++)
           {
            for (inputEnum p_a_c_s : inputEnum.values())  
             { dataScore+String.valueOf(inputEnum.p_a_c_s)=randomItem(inputIdentifiers);
               
               }  
             mapPerf.put("Performance",dataScore1);
             mapAgil.put("Agility",dataScore2);
             mapCost.put("Cost",dataScore3);) 
             mapSecurity.put("Security",dataScore4); 
             Rule.add(new SimpleImmutableEntry<>(mapPerf,mapAgil,mapCost,mapSecurity);
             Rules.add(new SimpleImmutableEntry<>(Rule));
             Rule.clear();
             mapPerf.clear();
             mapAgil.clear();
             mapCost.clear();
             mapSecurity.clear();        
           }
       // ArrayList<String> subIdentifiers= new ArrayList<String>(al.subList(1, 4));
     System.out.println("SubList stored in ArrayList: "+);
     return Rules;
    }

    
    public static void main(String[]  args){
       
        try {
  //  Block of code to try user input or get frontEnd input
            }
        catch(Exception e) {
  //  Block of code to handle errors
            }
        
        
       Rules rulesGenerated=new Rules<>(); 
       rulesGenerated =generateData(10,NEUTRAL,EXCELLENT,AWS,GENI);
       Stream.of(rulesGenerated.toString())
               .forEach(System.out::println);
       
       // ArrayList<String> subIdentifiers= new ArrayList<String>(al.subList(1, 4));
     
    }
}
}
