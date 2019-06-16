package ksrGut.logic.summaries;

import ksrGut.logic.FuzzySet;
import ksrGut.logic.norms.NormPair;
import ksrGut.logic.summaries.quantifier.Quantifier;

import java.util.ArrayList;
import java.util.List;

public class Summary {
    protected String subject;
    protected List<LinguisticVariable> summarizers;
    protected List<String> summarizersNames;
    protected List<ConjunctionType> conjunctions;
    protected List<FuzzySet> summarizerSets;
    protected Quantifier quantifier;
    protected NormPair normPair;

    public Summary(String subject,
                   List<LinguisticVariable> summarizers,
                   List<String> summarizersNames,
                   Quantifier quantifier,
                   List<ConjunctionType> summarizerConjunctions,
                   NormPair normPair
    ) {
        if (!inputValid(summarizers, summarizersNames, summarizerConjunctions)) {
            throw new IllegalArgumentException();
        }
        this.subject = subject;
        this.summarizers = summarizers;
        this.quantifier = quantifier;
        this.conjunctions = summarizerConjunctions;
        this.normPair = normPair;
        this.summarizersNames = summarizersNames;
    }

    public List<FuzzySet> getSummarizerSets() {
        if (summarizerSets == null) {
            summarizerSets = new ArrayList<>();
            for (int i = 0; i < summarizers.size(); i++) {
                summarizerSets.add(summarizers.get(i).fuzzySetPerLabel(summarizersNames.get(i)));
            }
        }
        return summarizerSets;
    }


    public FuzzySet getWholeSummarizer() {
        List<FuzzySet> summarizers = getSummarizerSets();
        if (summarizers.size() == 1) return summarizers.get(0);

        FuzzySet summarizer = summarizers.get(0).conjunction(normPair, conjunctions.get(0), summarizers.get(1));

        for (int i = 1; i < conjunctions.size(); i++) {
            summarizer = summarizer.conjunction(normPair, conjunctions.get(i), summarizers.get(i + 1));
        }
        return summarizer;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (quantifier != null) {
            sb.append(quantifier.getName()).append(" ");
        }
        sb.append(subject).append(" ");
        sb.append("IS/HAS ");
        toStringHelper(sb, summarizersNames, summarizers, conjunctions);
        return sb.toString();

    }

    public Quantifier getQuantifier() {
        return quantifier;
    }

    public int getSubjectsCount() {
        return summarizers.get(0).getUniverse().getElements().size();
    }

    private boolean inputValid(List<LinguisticVariable> summarizers,
                               List<String> summarizersNames,
                               List<ConjunctionType> summarizerConjunctions) {
        if (summarizers.size() != summarizersNames.size()) return false;
        if (summarizers.size() == 0) return false;
        return summarizers.size() - 1 == summarizerConjunctions.size();
    }

    static void toStringHelper(StringBuilder sb, List<String> qualifierNames, List<LinguisticVariable> qualifiers, List<ConjunctionType> qualifierConjunctions) {
        sb.append(qualifierNames.get(0)).append(" ").
                append(qualifiers.get(0).getName()).append(" ");
        for (int i = 0; i < qualifierConjunctions.size(); i++) {
            if (qualifierConjunctions.get(i) == ConjunctionType.AND)
                sb.append("AND ");
            else
                sb.append("OR ");

            sb.append(qualifierNames.get(i + 1)).append(" ").
                    append(qualifiers.get(i + 1).getName()).append(" ");
        }
    }

    public NormPair getNormPair() {
        return this.normPair;
    }

    public double getSpaceSize() {
        return summarizers.get(0).getUniverse().getElements().size();
    }
}
