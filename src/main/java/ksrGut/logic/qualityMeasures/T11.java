package ksrGut.logic.qualityMeasures;

import ksrGut.logic.summaries.Summary;
import ksrGut.logic.summaries.SummaryWithQualifier;

public class T11 {
    public static String getName() {
        return "Length of qualifier";
    }

    public static double getValue(Summary summary) {
        if (!(summary instanceof SummaryWithQualifier))
            return 1;
        return 2 * Math.pow(0.5, ((SummaryWithQualifier) summary).getQualifierSets().size());

    }

}
