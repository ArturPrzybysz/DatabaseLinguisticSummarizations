package ksrGut.logic.qualityMeasures;

import ksrGut.logic.FuzzySet;
import ksrGut.logic.summaries.Summary;
import ksrGut.logic.summaries.SummaryWithQualifier;

import java.util.List;

public class T9 {
    public static String getName() {
        return "Degree of qualifier imprecision";
    }

    public static double getValue(Summary summary) {
        if (!(summary instanceof SummaryWithQualifier)) {
            return 0;
        }
        List<FuzzySet> qualifiers = ((SummaryWithQualifier) summary).getQualifierSets();
        double piFuzziness = qualifiers.stream().mapToDouble(FuzzySet::getDegreeOfFuzzinessForFunction).reduce(1, (a, b) -> a * b);
        return 1 - Math.pow(piFuzziness, 1.0 / (double) qualifiers.size());
    }

}
