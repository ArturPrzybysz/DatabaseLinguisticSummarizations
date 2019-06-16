package ksrGut.logic.characteristicFunction;

import ksrGut.data.ColumnMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class ColumnToWidthMap {
    private static Map<String, Double> map;

    static double get(String columnName) {
        if (Objects.isNull(map)) {
            initMap();
        }
        return map.get(columnName);
    }

    private static void initMap() {
        map = new HashMap<>();
        map.put(null, 1.0);
        map.put(ColumnMap.moisture, 100.0);
        map.put(ColumnMap.protein, 100.0);
        map.put(ColumnMap.fat, 100.0);
        map.put(ColumnMap.sugars, 100.0);
        map.put(ColumnMap.starch, 100.0);
        map.put(ColumnMap.alcohol, 100.0);
        map.put(ColumnMap.vitaminA, 100000.0);
        map.put(ColumnMap.vitaminC, 10000.0);
        map.put(ColumnMap.vitaminE, 10000.0);
        map.put(ColumnMap.calcium, 10000.0);
        map.put(ColumnMap.iron, 10000.0);
        map.put(ColumnMap.magnesium, 10000.0);
        map.put(ColumnMap.cholesterol, 10000.0);
        map.put(ColumnMap.caffeine, 10000.0);
    }
}
