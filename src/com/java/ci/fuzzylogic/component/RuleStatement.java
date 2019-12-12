package com.java.ci.fuzzylogic.component;

public class RuleStatement
{
    private FuzzySet variable;
    private String variableValue;
    private String operator;

    public RuleStatement(FuzzySet variable, String operator, String value) {
        this.variable = variable;
        this.variableValue = value;
        this.operator = operator;
    }

    public FuzzySet getVariableReference() {
        return variable;
    }

    public String getVariableValue() {
        return variableValue;
    }
}
