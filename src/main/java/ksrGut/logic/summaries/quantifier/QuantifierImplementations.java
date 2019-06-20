package ksrGut.logic.summaries.quantifier;

import ksrGut.logic.characteristicFunction.FallingFunction;
import ksrGut.logic.characteristicFunction.RisingFunction;
import ksrGut.logic.characteristicFunction.TriangularFunction;

public class QuantifierImplementations {

    // RELATIVE
    public static Quantifier HARDLY_ANY() {
        return new Quantifier("hardly_any", QuantifierRelativity.RELATIVE,
                new TriangularFunction(null, 0, 0.1, 0.2));
    }

    public static Quantifier SOME() {
        return new Quantifier("some", QuantifierRelativity.RELATIVE,
                new TriangularFunction(null, 0.1, 0.2, 0.4));
    }

    public static Quantifier MORE_THAN_HALF() {
        return new Quantifier("more_than_a_half", QuantifierRelativity.RELATIVE,
                new RisingFunction(null, 0.5, 0.6));
    }

    public static Quantifier MOST() {
        return new Quantifier("most", QuantifierRelativity.RELATIVE,
                new RisingFunction(null, 0.7, 1.0));
    }

    public static Quantifier LEAST() {
        return new Quantifier("least", QuantifierRelativity.RELATIVE,
                new FallingFunction(null, 0.0, 0.5));
    }

    // ABSOLUTE
    public static Quantifier ABOUT_0() {
        return new Quantifier("about_0", QuantifierRelativity.ABSOLUTE,
                new TriangularFunction(null, -1.0, 0, 1));
    }

    public static Quantifier ABOUT_5() {
        return new Quantifier("about_5", QuantifierRelativity.ABSOLUTE,
                new TriangularFunction(null, 3, 5, 7));
    }

    public static Quantifier ABOUT_25() {
        return new Quantifier("about_25", QuantifierRelativity.ABSOLUTE,
                new TriangularFunction(null, 20, 25, 30));
    }

    public static Quantifier ABOUT_100() {
        return new Quantifier("about_100", QuantifierRelativity.ABSOLUTE,
                new TriangularFunction(null, 85, 100, 115));
    }

    public static Quantifier ABOUT_500() {
        return new Quantifier("about_500", QuantifierRelativity.ABSOLUTE,
                new TriangularFunction(null, 450, 500, 550));
    }

    public static Quantifier ABOUT_1000() {
        return new Quantifier("about_1000", QuantifierRelativity.ABSOLUTE,
                new TriangularFunction(null, 850, 1000, 1150));
    }

    public static Quantifier ABOUT_N(double N) {
        return new Quantifier("about_" + N, QuantifierRelativity.ABSOLUTE,
                new TriangularFunction(null, N * 0.9, N, N * 1.1));

    }
}
