package ksrGut.logic.norms;

import ksrGut.data.Tuple;
import ksrGut.logic.FuzzySet;
import ksrGut.logic.summaries.ConjunctionType;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class NormPair {
    public NormPair(Norm andNorm, Norm orNorm) {
        this.andNorm = andNorm;
        this.orNorm = orNorm;
    }

    private Norm andNorm;
    private Norm orNorm;

    public FuzzySet apply(ConjunctionType conjunctionType, FuzzySet a, FuzzySet b) {
        if (a.getSpace() != b.getSpace()) throw new IllegalArgumentException();
        Map<Tuple, Double> resultElements;
        if (conjunctionType.equals(ConjunctionType.AND))
            resultElements = a.getSpace().getElements().stream()
                    .collect(Collectors.toMap(
                            t -> t,
                            t -> andNorm.apply(a.getElements().get(t), b.getElements().get(t))
                    ));
        else
            resultElements = a.getSpace().getElements().stream()
                    .collect(Collectors.toMap(
                            t -> t,
                            t -> orNorm.apply(a.getElements().get(t), b.getElements().get(t))
                    ));

        return new FuzzySet(a.getSpace(), resultElements);
    }
}
