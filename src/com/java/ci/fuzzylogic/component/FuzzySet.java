package com.java.ci.fuzzylogic.component;

import java.util.*;

public class FuzzySet
{
    private Map<String, MembershipFunction> setMembershipsDetails = new HashMap<>();
    private List<String> setMembers = new ArrayList<>();

    public String setName = "";
    public double minRange = 0;
    public double maxRange = 1;
    public double increment = 0.1;
    public double valPredicted = 0;

    public FuzzySet(String name, double minRange, double maxRange) {
    	this.setName = name;
        this.minRange = minRange;
        this.maxRange = maxRange;
	}
    public FuzzySet(String name, double minRange, double maxRange, double increment) {
        this.setName = name;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.increment = increment;
    }
    public String getName() {
        return setName;
    }
    public MembershipFunction GetMembership(String value) {
        return setMembershipsDetails.get(value);
    }

    public double getRangeVal() {
        return valPredicted;
    }
    public void setRangeVal(double value) {
        valPredicted = value;
    }
    public void membershipWithFunction(String membershipname, MembershipFunction membership) {
        setMembers.add(membershipname);
        setMembershipsDetails.put(membershipname, membership);
    }

    public void infer(List<Rule> rules) {
        Map<String, List<Double>> degrees = new HashMap<>();
        for (int i = 0; i < setMembers.size(); ++i)
        {
            degrees.put(setMembers.get(i), new ArrayList<>());
        }

        for (int i = 0; i < rules.size(); ++i)
        {
            Rule rule = rules.get(i);
            RuleStatement consequent = rule.getConsequent();
            if (consequent.getVariableReference() == this)
            {
            	for (int j = 0; j < rule.getAntecedentCount(); ++j)
                {
                    RuleStatement antecedent = rule.getAntecedent(j);
                    FuzzySet variable = antecedent.getVariableReference();
                    String value = antecedent.getVariableValue();
                    MembershipFunction ms = variable.GetMembership(value);
                    double degreeOfMembership = ms.membershipDegree(variable.getRangeVal());
                    degrees.get(consequent.getVariableValue()).add(degreeOfMembership);
                }
            }
        }

        Map<String, Double> consequent_degrees = getAverage(degrees);
        valPredicted = getAreaCentroid(consequent_degrees);
    }
    public Map<String, Double> getAverage(Map<String, List<Double>> degrees) {
        Map<String, Double> results = new HashMap<>();

        for(String value : degrees.keySet())
        {
            List<Double> de = degrees.get(value);
            double squareSum = 0;
            for (int i = 0; i < de.size(); ++i)
            {
                double v = de.get(i);
                squareSum += v;
            }
            results.put(value, squareSum/de.size());
        }

        return results;
    }

    public double getAreaCentroid(Map<String, Double> degrees) {
    	double sumDegrees = 0;
    	double areaSum = 0;
    	
        for (double x = minRange; x <= maxRange; x += increment) {
            for (int i = 0; i < setMembers.size(); ++i) {
                MembershipFunction ms = setMembershipsDetails.get(setMembers.get(i));
                //degreeAtx will always be less than 1
                double degreeAtx = ms.membershipDegree(x);
                // d1  is root mean sum square can be greater
                double dCalculated = degrees.get(setMembers.get(i));
                double dCurrent = dCalculated > degreeAtx ? degreeAtx : dCalculated;
                areaSum += (x * dCurrent);
                sumDegrees += dCurrent;
            }
        }
        double xCoordinate = areaSum/sumDegrees;
        return xCoordinate;
    }
}
