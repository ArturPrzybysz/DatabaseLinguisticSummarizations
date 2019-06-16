package ksrGut.logic.qualityMeasures;

import ksrGut.logic.summaries.Summary;
import ksrGut.logic.summaries.SummaryWithQualifier;
import ksrGut.logic.summaries.quantifier.QuantifierRelativity;

public class T5 {
    public String getName() {
        return "Length of a summary";
    }

    public static double getValue(Summary summary) {
        return 2 * Math.pow(0.5, summary.getSummarizerSets().size());
    }
}
