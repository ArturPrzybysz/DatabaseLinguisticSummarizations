package ksrGut.logic.qualityMeasures;

import ksrGut.logic.CrispSet;
import ksrGut.logic.FuzzySet;
import ksrGut.logic.summaries.ConjunctionType;
import ksrGut.logic.summaries.Summary;
import ksrGut.logic.summaries.SummaryWithQualifier;
import ksrGut.logic.summaries.quantifier.QuantifierRelativity;

public class T1 {
    public String getName() {
        return "Degree of truth";
    }

    public static double getValue(Summary summary) {
        double operationResult = r(summary);
        if (summary.getQuantifier().relativity == QuantifierRelativity.RELATIVE && !(summary instanceof SummaryWithQualifier)) {
            operationResult /= summary.getSubjectsCount();
        }
        return summary.getQuantifier().function.applyRaw(operationResult);

    }

    private static double r(Summary summary) {
        if (summary instanceof SummaryWithQualifier) {
            return getRWithQualifier(summary);
        } else {
            return getR(summary);
        }
    }

    private static double getRWithQualifier(Summary summary) {
        FuzzySet summarizer = summary.getWholeSummarizer();
        FuzzySet qualifier = ((SummaryWithQualifier) summary).getWholeQualifier();
        double a = summarizer.conjunction(summary.getNormPair(), ConjunctionType.AND, qualifier).cardinality();
        double b = qualifier.cardinality();
        return a / b;
    }

    private static double getR(Summary summary) {
        return summary.getWholeSummarizer().cardinality();
    }
}
