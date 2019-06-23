package ksrGut.logic.qualityMeasures;

import ksrGut.logic.FuzzySet;
import ksrGut.logic.summaries.ConjunctionType;
import ksrGut.logic.summaries.Summary;
import ksrGut.logic.summaries.SummaryWithQualifier;

public class T3 {
    public static String getName() {
        return "Degree of coverage (T3)";
    }

    public static double getValue(Summary summary) {
        return t(summary) / h(summary);
    }

    private static double t(Summary summary) {
        if (summary instanceof SummaryWithQualifier) {
            return tWithQualifier(summary);
        } else {
            return tNoQualifier(summary);
        }
    }

    private static double tWithQualifier(Summary summary) {
        FuzzySet qualifier = ((SummaryWithQualifier) summary).getWholeQualifier();
        FuzzySet summarizer = summary.getWholeSummarizer();
        return summarizer.conjunction(summary.getNormPair(), ConjunctionType.AND, qualifier)
                .support().getElements().size();
    }

    private static double tNoQualifier(Summary summary) {
        return summary.getWholeSummarizer().support().getElements().size();
    }

    private static double h(Summary summary) {
        if (summary instanceof SummaryWithQualifier) {
            return hWithQualifier(summary);
        } else {
            return hNoQualifier(summary);
        }
    }

    private static double hNoQualifier(Summary summary) {
        return summary.getSpaceSize();
    }

    private static double hWithQualifier(Summary summary) {
        return ((SummaryWithQualifier) summary).getWholeQualifier().support().getElements().size();
    }
}
