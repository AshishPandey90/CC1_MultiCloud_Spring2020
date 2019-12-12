package com.java.ci.fuzzylogic.component;

public class LinearDown implements MembershipFunction {
    public double start;
    public double end;

    public LinearDown(double x0, double x1) {
        this.start = x0;
        this.end = x1;
    }

    public double membershipDegree(double x) {
        if (x <= start)
            return 1;
        else if (x < end)
            return (end - x)/(end - start);
        else
            return 0;
    }
}
