package ksrGut.gui.controllers;

import javafx.fxml.Initializable;
import ksrGut.ProjectPaths;
import ksrGut.logic.CrispSet;
import ksrGut.logic.summaries.LinguisticVariable;
import ksrGut.logic.summaries.quantifier.Quantifier;
import loaders.LinguisticVariablesLoader;
import loaders.QuantifiersLoader;
import tech.tablesaw.api.Table;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Table table;
    private CrispSet universe;
    private List<LinguisticVariable> variables;
    private List<Quantifier> quantifiers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            table = Table.read().file(ProjectPaths.dataPath.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        universe = new CrispSet(table);
        variables = LinguisticVariablesLoader.load(universe);
        quantifiers = QuantifiersLoader.load();
    }


}
