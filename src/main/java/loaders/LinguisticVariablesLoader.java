package loaders;

import ksrGut.data.ColumnMap;
import ksrGut.logic.CrispSet;
import ksrGut.logic.characteristicFunction.CharacteristicFunction;
import ksrGut.logic.characteristicFunction.FallingFunction;
import ksrGut.logic.characteristicFunction.RisingFunction;
import ksrGut.logic.characteristicFunction.TriangularFunction;
import ksrGut.logic.summaries.LinguisticVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinguisticVariablesLoader {
    public static List<LinguisticVariable> load(CrispSet universe) {
        List<LinguisticVariable> variables = new ArrayList<>();

        Map<String, CharacteristicFunction> map = new HashMap<>();
        map.put("not", new FallingFunction(ColumnMap.fat, 0, 2));
        map.put("a bit", new TriangularFunction(ColumnMap.fat, 1, 4, 9));
        map.put("medium", new TriangularFunction(ColumnMap.fat, 6, 20, 30));
        map.put("very", new RisingFunction(ColumnMap.fat, 20, 40));
        variables.add(new LinguisticVariable("fat", map, universe));

        map = new HashMap<>();
        map.put("not", new FallingFunction(ColumnMap.alcohol, 0, 1));
        map.put("a bit", new TriangularFunction(ColumnMap.alcohol, 0.5, 2, 3.5));
        map.put("medium", new TriangularFunction(ColumnMap.alcohol, 2, 16, 25));
        map.put("strong", new RisingFunction(ColumnMap.alcohol, 20, 25));
        variables.add(new LinguisticVariable("alcoholic", map, universe));

        map = new HashMap<>();
        map.put("not", new FallingFunction(ColumnMap.vitaminE, 0, 1));
        map.put("medium", new TriangularFunction(ColumnMap.vitaminE, 0.5, 15, 25));
        map.put("very", new RisingFunction(ColumnMap.vitaminE, 17, 30));
        variables.add(new LinguisticVariable("vitamin E", map, universe));

        map = new HashMap<>();
        map.put("weak", new FallingFunction(ColumnMap.caffeine, 0, 4));
        map.put("medium", new TriangularFunction(ColumnMap.caffeine, 0.5, 80, 150));
        map.put("strong", new RisingFunction(ColumnMap.caffeine, 130, 170));
        variables.add(new LinguisticVariable("caffeine", map, universe));

        map = new HashMap<>();
        map.put("none", new FallingFunction(ColumnMap.calcium, 0, 2));
        map.put("some", new TriangularFunction(ColumnMap.calcium, 1, 30, 50));
        map.put("medium", new TriangularFunction(ColumnMap.calcium, 35, 350, 500));
        map.put("a lot", new RisingFunction(ColumnMap.calcium, 450, 550));
        variables.add(new LinguisticVariable("calcium", map, universe));

        map = new HashMap<>();
        map.put("none", new FallingFunction(ColumnMap.cholesterol, 0, 2));
        map.put("some", new TriangularFunction(ColumnMap.cholesterol, 1, 20, 40));
        map.put("medium", new TriangularFunction(ColumnMap.cholesterol, 20, 80, 160));
        map.put("a lot", new RisingFunction(ColumnMap.cholesterol, 150, 200));
        variables.add(new LinguisticVariable("cholesterol", map, universe));

        map = new HashMap<>();
        map.put("none", new FallingFunction(ColumnMap.energy, 0, 100));
        map.put("some", new TriangularFunction(ColumnMap.energy, 60, 200, 400));
        map.put("medium", new TriangularFunction(ColumnMap.energy, 250, 800, 1600));
        map.put("a lot", new RisingFunction(ColumnMap.energy, 1500, 1700));
        variables.add(new LinguisticVariable("energy", map, universe));

        map = new HashMap<>();
        map.put("none", new FallingFunction(ColumnMap.iron, 0, 3));
        map.put("some", new TriangularFunction(ColumnMap.iron, 2, 7, 15));
        map.put("medium", new TriangularFunction(ColumnMap.iron, 9, 16, 25));
        map.put("a lot", new RisingFunction(ColumnMap.iron, 20, 28));
        variables.add(new LinguisticVariable("iron", map, universe));

        map = new HashMap<>();
        map.put("none", new FallingFunction(ColumnMap.magnesium, 0, 5));
        map.put("some", new TriangularFunction(ColumnMap.magnesium, 2, 40, 100));
        map.put("medium", new TriangularFunction(ColumnMap.magnesium, 70, 140, 200));
        map.put("a lot", new RisingFunction(ColumnMap.magnesium, 180, 200));
        variables.add(new LinguisticVariable("magnesium", map, universe));

        map = new HashMap<>();
        map.put("none", new FallingFunction(ColumnMap.moisture, 0, 4));
        map.put("some", new TriangularFunction(ColumnMap.moisture, 2, 20, 30));
        map.put("medium", new TriangularFunction(ColumnMap.moisture, 20, 60, 90));
        map.put("a lot", new RisingFunction(ColumnMap.moisture, 85, 90));
        variables.add(new LinguisticVariable("moisture", map, universe));

        map = new HashMap<>();
        map.put("none", new FallingFunction(ColumnMap.protein, 0, 3));
        map.put("some", new TriangularFunction(ColumnMap.protein, 1, 5, 10));
        map.put("medium", new TriangularFunction(ColumnMap.protein, 7, 12, 20));
        map.put("a lot", new RisingFunction(ColumnMap.protein, 15, 20));
        variables.add(new LinguisticVariable("protein", map, universe));

        map = new HashMap<>();
        map.put("none", new FallingFunction(ColumnMap.starch, 0, 3));
        map.put("some", new TriangularFunction(ColumnMap.starch, 0, 20, 40));
        map.put("medium", new TriangularFunction(ColumnMap.starch, 30, 40, 50));
        map.put("a lot", new RisingFunction(ColumnMap.starch, 45, 50));
        variables.add(new LinguisticVariable("starch", map, universe));

        map = new HashMap<>();
        map.put("none", new FallingFunction(ColumnMap.sugars, 0, 3));
        map.put("some", new TriangularFunction(ColumnMap.sugars, 0, 20, 40));
        map.put("medium", new TriangularFunction(ColumnMap.sugars, 30, 40, 50));
        map.put("a lot", new RisingFunction(ColumnMap.sugars, 45, 50));
        variables.add(new LinguisticVariable("sugar", map, universe));

        map = new HashMap<>();
        map.put("none", new FallingFunction(ColumnMap.vitaminA, 0, 50));
        map.put("some", new TriangularFunction(ColumnMap.vitaminA, 0, 300, 500));
        map.put("medium", new TriangularFunction(ColumnMap.vitaminA, 300, 800, 1100));
        map.put("a lot", new RisingFunction(ColumnMap.vitaminA, 900, 1100));
        variables.add(new LinguisticVariable("vitamin A", map, universe));

        map = new HashMap<>();
        map.put("none", new FallingFunction(ColumnMap.vitaminC, 0, 50));
        map.put("some", new TriangularFunction(ColumnMap.vitaminC, 0, 300, 500));
        map.put("medium", new TriangularFunction(ColumnMap.vitaminC, 300, 800, 1100));
        map.put("a lot", new RisingFunction(ColumnMap.vitaminC, 900, 1100));
        variables.add(new LinguisticVariable("vitamin C", map, universe));

        return variables;
    }
}
