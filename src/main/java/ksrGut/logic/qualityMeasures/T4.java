package ksrGut.logic.qualityMeasures;

import ksrGut.logic.FuzzySet;
import ksrGut.logic.summaries.Summary;

public class T4 {
    public static String getName() {
        return "Degree of appropriateness (T4)";
    }

    public static double getValue(Summary summary) {
        double t3 = T3.getValue(summary);
        double mulS = 1;
        for (FuzzySet fs : summary.getSummarizerSets()) {
            mulS *= fs.support().getElements().size() / fs.getSpace().getElements().size();
        }
        return Math.abs(mulS - t3);
    }
}
