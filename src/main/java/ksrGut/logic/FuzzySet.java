package ksrGut.logic;

import ksrGut.data.Tuple;
import ksrGut.logic.characteristicFunction.CharacteristicFunction;
import ksrGut.logic.characteristicFunction.ColumnToWidthMap;
import ksrGut.logic.norms.NormPair;
import ksrGut.logic.summaries.ConjunctionType;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FuzzySet {
    private CrispSet space;
    private Map<Tuple, Double> elements;
    private double maxValue;
    private CharacteristicFunction characteristicFunction;

    public double getCharacteristicValue(Tuple tuple) {
        return this.elements.get(tuple);
    }

    public FuzzySet(CrispSet space, CharacteristicFunction characteristicFunction) {
        elements = space.getElements().stream()
                .collect(Collectors.toMap(e -> e, characteristicFunction));
        this.space = space;
        this.maxValue = ColumnToWidthMap.get(characteristicFunction.getColumnName());
        this.characteristicFunction = characteristicFunction;
    }

    public FuzzySet(CrispSet space, Map<Tuple, Double> elements) {
        this.elements = elements;
        this.space = space;
    }

    public double getDegreeOfFuzzinessForElements() {
        return support().getElements().size() / space.getElements().size();
    }

    public double getDegreeOfFuzzinessForFunction() {
        return this.characteristicFunction.getBase() / this.maxValue;
    }

    public double getCardinalityRatioForElements() {
        return cardinality() / space.getElements().size();
    }

    public double getCardinalityRatioForFunction() {
        return this.characteristicFunction.getArea() / this.maxValue;
    }

    FuzzySet complement() {
        Map<Tuple, Double> complementElements = this.elements.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> 1 - e.getValue()));
        return new FuzzySet(space, complementElements);
    }

    public double getHeight() {
        Optional<Double> height = elements.values().stream().max(Double::compareTo);
        return height.orElse(0.0);
    }

    public FuzzySet conjunction(NormPair normPair, ConjunctionType conjunctionType, FuzzySet fuzzySet) {
        return normPair.apply(conjunctionType, this, fuzzySet);
    }

    public CrispSet getSpace() {
        return space;
    }

    public Map<Tuple, Double> getElements() {
        return elements;
    }

    public boolean isNormal() {
        return getHeight() == 1;
    }

    public CrispSet alphaCut(final double alpha) {
        Set<Tuple> filtered = elements.entrySet().stream()
                .filter(e -> e.getValue() >= alpha)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        return new CrispSet(filtered, space);
    }

    public boolean isEmpty() {
        return elements.values().stream()
                .noneMatch(v -> v != 0);
    }

    public double cardinality() {
        return elements.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public CrispSet support() {
        Set<Tuple> filtered = elements.entrySet().stream()
                .filter(e -> e.getValue() > 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        return new CrispSet(filtered, space);
    }

    public CrispSet core() {
        return alphaCut(1);
    }

}
