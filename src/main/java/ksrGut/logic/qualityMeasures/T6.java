package ksrGut.logic.qualityMeasures;

import ksrGut.logic.summaries.Summary;

public class T6 {
    public static String getName(){
        return "Degree of quantifier imprecision (T6)";
    }

    public static double getValue(Summary summary) {
        return 1 - summary.getQuantifier().getDegreeOfFuzziness();
    }

}
