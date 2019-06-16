package ksrGut.logic.qualityMeasures;

import ksrGut.logic.summaries.Summary;

public class T5 {
    public String getName() {
        return "Length of a summary (T5)";
    }

    public static double getValue(Summary summary) {
        return 2 * Math.pow(0.5, summary.getSummarizerSets().size());
    }
}
