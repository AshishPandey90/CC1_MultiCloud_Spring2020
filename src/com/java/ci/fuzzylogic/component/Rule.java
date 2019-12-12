package com.java.ci.fuzzylogic.component;

import java.util.ArrayList;
import java.util.List;

public class Rule
    {
        private List<RuleStatement> ruleAntecedents = new ArrayList<>();
        private RuleStatement ruleConsequent = null;
        String ruleName;

        public Rule(String name) {
            ruleName = name;
        }

        public RuleStatement getConsequent() {
            return ruleConsequent;
        }

        public void setConsequent(RuleStatement value) {
            ruleConsequent = value;
        }

        public void addAntecedent(RuleStatement antecedent) {
            ruleAntecedents.add(antecedent);
        }

        public RuleStatement getAntecedent(int index) {
            return ruleAntecedents.get(index);
        }

        public int getAntecedentCount() {
            return ruleAntecedents.size();
        }
    }
