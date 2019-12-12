import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.java.ci.fuzzylogic.component.*;
public class FuzzyController {
	public static void main(String[] args) {
		
		InferenceEngine engineInstance = new InferenceEngine();
		
		FuzzySet site = new FuzzySet("CSP", 0, 100, 1);
		site.membershipWithFunction("GENI", new LinearDown(0, 30));
		site.membershipWithFunction("GCP", new Trapezoid(20, 30, 40, 50));
		site.membershipWithFunction("AZURE", new Trapezoid(40, 50, 60, 70));
		site.membershipWithFunction("AWS", new LinearUp(50, 100));
		

		//Performance fuzzy set
		FuzzySet performance=new FuzzySet("Performnace", 0, 10);
		performance.membershipWithFunction("Bad", new LinearDown(0, 2));
		performance.membershipWithFunction("Average", new Trapezoid(0, 1, 3, 8));
		performance.membershipWithFunction("Good", new LinearUp(5, 10));
		
        //Agility fuzzy set
		FuzzySet agility=new FuzzySet("Agility", 0, 10);
		agility.membershipWithFunction("Bad", new LinearDown(0, 2));
		agility.membershipWithFunction("Average", new Trapezoid(0, 2, 4, 8));
		agility.membershipWithFunction("Good", new LinearUp(5, 10));
		
		
		//Cost fuzzy set
		FuzzySet cost=new FuzzySet("Cost", 0, 10);
		cost.membershipWithFunction("Bad", new LinearDown(0, 2));
		cost.membershipWithFunction("Average", new Trapezoid(0, 1, 3, 8));
		cost.membershipWithFunction("Good", new LinearUp(5, 10));
		
		//Security fuzzy set
		FuzzySet security=new FuzzySet("Security", 0, 10);
		security.membershipWithFunction("Bad", new LinearDown(0, 2));
		security.membershipWithFunction("Average", new Trapezoid(0, 2, 4, 8));
		security.membershipWithFunction("Good", new LinearUp(5, 10));
		
		engineInstance.addFuzzySet(performance);
		engineInstance.addFuzzySet(agility);
		engineInstance.addFuzzySet(cost);
		engineInstance.addFuzzySet(security);
		engineInstance.addFuzzySet(site);
		
		List<FuzzySet> allFuzzyFactors = new ArrayList<>();
		allFuzzyFactors.add(0, performance);
		allFuzzyFactors.add(1, agility);
		allFuzzyFactors.add(2, cost);
		allFuzzyFactors.add(3, security);
		allFuzzyFactors.add(4, site);
		
		
		// read rules from the input files and apply to inference engine
		readRuleFileForEngine(allFuzzyFactors, engineInstance , args[4]);
		
		// read crisp input for the input fuzzy sets
		performance.setRangeVal(Double.parseDouble(args[0].split(":")[1]));
		agility.setRangeVal(Double.parseDouble(args[1].split(":")[1]));
		cost.setRangeVal(Double.parseDouble(args[2].split(":")[1]));
		security.setRangeVal(Double.parseDouble(args[3].split(":")[1]));
		
		// get the output membership of the fuzzy set
		engineInstance.execute(site);
	
		System.out.println(site.getName()+ " "+ site.getRangeVal());
		System.out.println();
		System.out.println("GENI: "+site.GetMembership("GENI").membershipDegree(site.getRangeVal()));
		System.out.println("GCP: "+site.GetMembership("GCP").membershipDegree(site.getRangeVal()));
		System.out.println("AZURE: "+site.GetMembership("AZURE").membershipDegree(site.getRangeVal()));
		System.out.println("AWS: "+site.GetMembership("AWS").membershipDegree(site.getRangeVal()));
		
	}

	private static void readRuleFileForEngine(List<FuzzySet> allFuzzyFactors, InferenceEngine engineInstance, String ruleFilePath) {
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(ruleFilePath));
			String line = reader.readLine();
			int count = 0;
			Rule rule = new Rule("Rule" + count);
			while (line != null) {
				//System.out.println(line);
				
				if(line.equals("$")) break;
				if(line.equals("#")) {
					count++;
					engineInstance.addNewRule(rule);
					rule = new Rule("Rule" + count);
				}
				else {
					String[] ruleInstance = line.split(",");
					
					if(ruleInstance[0].equals("performance"))
					rule.addAntecedent(new RuleStatement(allFuzzyFactors.get(0), ruleInstance[1], ruleInstance[2]));
					
					if(ruleInstance[0].equals("agility"))
					rule.addAntecedent(new RuleStatement(allFuzzyFactors.get(1), ruleInstance[1], ruleInstance[2]));
					
					if(ruleInstance[0].equals("cost"))
					rule.addAntecedent(new RuleStatement(allFuzzyFactors.get(2), ruleInstance[1], ruleInstance[2]));
					
					if(ruleInstance[0].equals("security"))
					rule.addAntecedent(new RuleStatement(allFuzzyFactors.get(3), ruleInstance[1], ruleInstance[2]));
					
					if(ruleInstance[0].equals("CSP"))
					rule.setConsequent(new RuleStatement(allFuzzyFactors.get(4), ruleInstance[1], ruleInstance[2]));
					
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
