package ksrGut.logic.qualityMeasures;

import ksrGut.logic.FuzzySet;
import ksrGut.logic.summaries.Summary;

public class T4 {
    public static String getName() {
        return "Degree of appropriateness";
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
//private double CalculateT4(LinguisticSummary summarization, double t3)
//        {
//        double mulS = 1.0;
//        foreach (var summarizer in summarization.Summarizers)
//        {
//        double sumG = 0.0;
//        foreach (var tuple in summarization.Data)
//        {
//        sumG += summarizer.CalculateMembership(tuple.Get(summarizer.Column)) > 0 ? 1.0 : 0;
//        }
//        mulS *= (sumG / summarization.Data.Count);
//        }
//        return Math.Abs(mulS - t3);
