package com.java.ci.fuzzylogic.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InferenceEngine {
        
	List<Rule> allRules = new ArrayList<>();
	List<String> allFuzzySets = new ArrayList<>();
    Map<String, FuzzySet> allFuzzySetDetails = new HashMap<>();

    public void addNewRule(Rule rule) {
         allRules.add(rule);
    }
    public void addFuzzySet(FuzzySet set) {
         allFuzzySets.add(set.setName);
         allFuzzySetDetails.put(set.setName, set);
    }        
    public void execute(FuzzySet site) {
         site.infer(allRules);
    }
}
