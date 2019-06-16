package ksrGut.logic.qualityMeasures;

import ksrGut.logic.summaries.Summary;

public class T7 {
    public static String getName() {
        return "Degree of quantifier cardinality";
    }

    public static double getValue(Summary summary) {
        return 1 - summary.getQuantifier().getCardinalityRatio();
    }
}
