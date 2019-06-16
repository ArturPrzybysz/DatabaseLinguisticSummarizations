package ksrGut.data;

import tech.tablesaw.api.Row;

import java.util.HashMap;
import java.util.Map;

public class Tuple {

    String name;
    private Map<String, Double> values = new HashMap<>();

    public double getValue(String keyName) {
        return values.get(keyName);
    }

    public Tuple(Row row) {
        name = row.getString("Sage, dried");

        values.put(ColumnMap.energy, (double) row.getInt("Energy, with dietary fibre (kJ)"));
        values.put(ColumnMap.moisture, row.getDouble("Moisture (g)"));
        values.put(ColumnMap.protein, row.getDouble("Protein (g)"));
        values.put(ColumnMap.fat, row.getDouble("Total fat (g)"));
        values.put(ColumnMap.sugars, row.getDouble("Total sugars (g)"));
        values.put(ColumnMap.starch, row.getDouble("Starch (g)"));
        values.put(ColumnMap.alcohol, row.getDouble("Alcohol (g)"));
        values.put(ColumnMap.vitaminA, (double) row.getInt("Preformed vitamin A (retinol) (µg)"));
        values.put(ColumnMap.vitaminC, (double) row.getInt("Vitamin C (mg)"));
        values.put(ColumnMap.vitaminE, row.getDouble("Vitamin E (mg)"));
        values.put(ColumnMap.calcium, (double) row.getInt("Calcium (Ca) (mg)"));
        values.put(ColumnMap.iron, row.getDouble("Iron (Fe) (mg)"));
        values.put(ColumnMap.magnesium, (double) row.getInt("Magnesium (Mg) (mg)"));
        values.put(ColumnMap.cholesterol, (double) row.getInt("Cholesterol (mg)"));
        values.put(ColumnMap.caffeine, (double) row.getInt("Caffeine (mg)"));
    }
}
