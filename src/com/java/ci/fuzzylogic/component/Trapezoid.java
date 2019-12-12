package com.java.ci.fuzzylogic.component;

public class Trapezoid implements MembershipFunction {
    public double x0, x1, x2, x3;

    public Trapezoid(double x0, double x1, double x2, double x3) {
        this.x0 = x0;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
    }

    @Override
    public double membershipDegree(double x) {
        if (x <= x0 || x >= x3)
            return 0;
        else if (x >= x1 && x <= x2)
            return 1;
        else if ((x > x0) && (x < x1))
            return (x - x0) / (x1 - x0);
        else
            return (x3 - x) / (x3 - x2);
    }
}
