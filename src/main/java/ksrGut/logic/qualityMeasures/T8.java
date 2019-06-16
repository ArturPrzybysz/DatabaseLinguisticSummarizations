package ksrGut.logic.qualityMeasures;

import ksrGut.logic.FuzzySet;
import ksrGut.logic.summaries.Summary;

import java.util.List;

public class T8 {
    public static String getName() {
        return "Degree of summarizer cardinality";
    }

    public static double getValue(Summary summary) {
        List<FuzzySet> summarizers = summary.getSummarizerSets();
        double piCardinality = summarizers.stream().mapToDouble(FuzzySet::getCardinalityRatio).reduce(1, (a, b) -> a * b);
        return 1 - Math.pow(Math.E, Math.log(piCardinality) / summarizers.size());
    }

}
