package ksrGut.logic.summaries;

import ksrGut.logic.CrispSet;
import ksrGut.logic.FuzzySet;
import ksrGut.logic.characteristicFunction.CharacteristicFunction;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LinguisticVariable {
    private String name;
    private Map<String, CharacteristicFunction> nameToFunction;
    private CrispSet universe;

    public LinguisticVariable(String name, Map<String, CharacteristicFunction> nameToFunction, CrispSet universe) {
        this.name = name;
        this.nameToFunction = nameToFunction;
        this.universe = universe;
    }

    public FuzzySet fuzzySetPerLabel(String characteristicFunctionLabel) {
        return new FuzzySet(this.universe, nameToFunction.get(characteristicFunctionLabel));
    }

    public CrispSet getUniverse() {
        return universe;
    }

    public String getName() {
        return name;
    }

    public Set<String> getCharacteristicFunctionsLabels() {
        return nameToFunction.keySet();
    }

    public List<String> getCharacteristicFunctionsLabelsWithName() {
        return nameToFunction.keySet().stream().map(s1 -> s1 + " " + name).collect(Collectors.toList());
    }

    public void addFunction(String name, CharacteristicFunction function) {
        nameToFunction.put(name, function);
    }
}
