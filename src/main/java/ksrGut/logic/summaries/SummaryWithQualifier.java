package ksrGut.logic.summaries;

import ksrGut.logic.FuzzySet;
import ksrGut.logic.norms.NormPair;
import ksrGut.logic.summaries.quantifier.Quantifier;

import java.util.ArrayList;
import java.util.List;

public class SummaryWithQualifier extends Summary {
    private List<LinguisticVariable> qualifiers;
    private List<String> qualifierNames;
    private List<ConjunctionType> qualifierConjunctions;

    private List<FuzzySet> qualifierSets;

    public SummaryWithQualifier(String subject,
                                List<LinguisticVariable> summarizers,
                                List<String> summarizersNames,
                                Quantifier quantifier,
                                List<ConjunctionType> summarizerConjunctions,
                                NormPair normPair,
                                List<LinguisticVariable> qualifiers,
                                List<String> qualifierNames,
                                List<ConjunctionType> qualifierConjunctions) {
        super(subject, summarizers, summarizersNames, quantifier, summarizerConjunctions, normPair);
        this.qualifiers = qualifiers;
        this.qualifierNames = qualifierNames;
        this.qualifierConjunctions = qualifierConjunctions;
    }

    private List<FuzzySet> getQualifierSets() {
        if (qualifierSets == null) {
            qualifierSets = new ArrayList<>();
            for (int i = 0; i < qualifiers.size(); i++) {
                qualifierSets.add(qualifiers.get(i).fuzzySetPerLabel(qualifierNames.get(i)));
            }
        }
        return qualifierSets;
    }

    public FuzzySet getWholeQualifier() {
        List<FuzzySet> qualifiers = getQualifierSets();
        FuzzySet qualifier = qualifiers.get(0);
        for (int i = 1; i < qualifiers.size(); i++) {
            qualifier = qualifier.conjunction(this.normPair, qualifierConjunctions.get(i), qualifiers.get(i + 1));
        }
        return qualifier;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (quantifier != null) {
            sb.append(quantifier.getName()).append(" ");
        }
        sb.append(subject).append(" WHICH HAVE/ARE ");
        toStringHelper(sb, qualifierNames, qualifiers, qualifierConjunctions);

        sb.append("ARE/HAVE ");
        toStringHelper(sb, summarizersNames, summarizers, conjunctions);
        return sb.toString();
    }

}
