package com.java.ci.fuzzylogic.component;

public class LinearUp implements MembershipFunction
    {
        public double start;
        public double end;

        public LinearUp(double start, double end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public double membershipDegree(double x) {
            if (x <= start)
                return 0;
            else if (x < end)
                return (x - start) / (end - start);
            else
                return 1;
        }
    }
