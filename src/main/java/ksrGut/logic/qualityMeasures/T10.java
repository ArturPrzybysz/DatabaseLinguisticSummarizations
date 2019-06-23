package ksrGut.logic.qualityMeasures;

import ksrGut.logic.FuzzySet;
import ksrGut.logic.summaries.Summary;
import ksrGut.logic.summaries.SummaryWithQualifier;

import java.util.List;

public class T10 {
    public static String getName() {
        return "Degree of qualifier cardinality";
    }

    public static double getValue(Summary summary) {
        if (!(summary instanceof SummaryWithQualifier))
            return 0;

        List<FuzzySet> qualifiers = ((SummaryWithQualifier) summary).getQualifierSets();
        double piCardinality = qualifiers.stream().mapToDouble(FuzzySet::getCardinalityRatioForFunction).reduce(1, (a, b) -> a * b);
        return 1 - Math.pow(piCardinality, 1.0 / (double) qualifiers.size());
    }

}
