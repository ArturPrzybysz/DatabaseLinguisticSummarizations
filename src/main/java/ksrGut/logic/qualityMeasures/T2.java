package ksrGut.logic.qualityMeasures;

import ksrGut.logic.FuzzySet;
import ksrGut.logic.summaries.Summary;

public class T2 {
    public static String getName() {
        return "Degree of imprecision (T2)";
    }

    public static double getValue(Summary summary) {
        return summary.getSummarizerSets().stream()
                .mapToDouble(FuzzySet::getDegreeOfFuzzinessForFunction)
                .reduce(1, (a, b) -> a * b);
    }
}
