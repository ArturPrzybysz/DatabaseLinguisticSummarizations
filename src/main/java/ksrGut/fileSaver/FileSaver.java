package ksrGut.fileSaver;

import ksrGut.logic.summaries.Summary;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileSaver {
    static void save(Summary summary, Map<String, Double> qualityValues, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(summary.toString());
            for (String key : qualityValues.keySet()) {
                writer.write(String.format("%s = %s\n", key, qualityValues.get(key)));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void save(List<Summary> summaries, Map<String, Double> qualityValues, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (Summary s : summaries) {
                writer.write(s.toString());
                for (String key : qualityValues.keySet()) {
                    writer.write(String.format("%s = %s\n", key, qualityValues.get(key)));
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
