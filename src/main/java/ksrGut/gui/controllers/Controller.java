package ksrGut.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import ksrGut.ProjectPaths;
import ksrGut.logic.CrispSet;
import ksrGut.logic.summaries.LinguisticVariable;
import ksrGut.logic.summaries.quantifier.Quantifier;
import loaders.LinguisticVariablesLoader;
import loaders.QuantifiersLoader;
import tech.tablesaw.api.Table;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private CrispSet universe;
    private ObservableList<LinguisticVariable> variables;
    @FXML
    private ListView<LinguisticVariable> variablesView;
    @FXML
    public ListView<Quantifier> quantifiersView;
    private ObservableList<Quantifier> quantifiers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Table table = Table.read().file(ProjectPaths.dataPath.toString());
            universe = new CrispSet(table);
            variables = FXCollections.observableArrayList(LinguisticVariablesLoader.load(universe));
            quantifiers = FXCollections.observableArrayList(QuantifiersLoader.load());
            quantifiersView = new ListView<>(quantifiers);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void generateSummaries(ActionEvent actionEvent) {
        quantifiersView.refresh();
    }

    public void saveToFile(ActionEvent actionEvent) {
    }

    public void moveToFuzzySetsWindow(ActionEvent actionEvent) {
    }
}
