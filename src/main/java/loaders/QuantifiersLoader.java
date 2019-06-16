package loaders;

import ksrGut.logic.summaries.quantifier.Quantifier;
import ksrGut.logic.summaries.quantifier.QuantifierImplementations;

import java.util.ArrayList;
import java.util.List;

public class QuantifiersLoader {
    public static List<Quantifier> load() {
        List<Quantifier> quantifiers = new ArrayList<>();

        quantifiers.add(QuantifierImplementations.SOME());
        quantifiers.add(QuantifierImplementations.MOST());
        quantifiers.add(QuantifierImplementations.LEAST());
        quantifiers.add(QuantifierImplementations.HARDLY_ANY());
        quantifiers.add(QuantifierImplementations.MORE_THAN_HALF());
        quantifiers.add(QuantifierImplementations.ABOUT_0());
        quantifiers.add(QuantifierImplementations.ABOUT_5());
        quantifiers.add(QuantifierImplementations.ABOUT_25());
        quantifiers.add(QuantifierImplementations.ABOUT_100());
        quantifiers.add(QuantifierImplementations.ABOUT_500());
        quantifiers.add(QuantifierImplementations.ABOUT_1000());

        return quantifiers;
    }
}
