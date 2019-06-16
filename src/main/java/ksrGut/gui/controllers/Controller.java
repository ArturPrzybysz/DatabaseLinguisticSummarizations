package ksrGut.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ksrGut.ProjectPaths;
import ksrGut.logic.CrispSet;
import ksrGut.logic.norms.NormPairImplementations;
import ksrGut.logic.summaries.ConjunctionType;
import ksrGut.logic.summaries.LinguisticVariable;
import ksrGut.logic.summaries.Summary;
import ksrGut.logic.summaries.SummaryWithQualifier;
import ksrGut.logic.summaries.quantifier.Quantifier;
import loaders.LinguisticVariablesLoader;
import loaders.QuantifiersLoader;
import tech.tablesaw.api.Table;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    ListView<String> variablesView;
    @FXML
    ListView<String> generatedSummarizer;
    @FXML
    ListView<Quantifier> quantifiersView;
    
    @FXML
    TableView<SummaryPOJO> summariesView;
    @FXML
    CheckBox t1CheckBox;
    @FXML
    CheckBox t2CheckBox;
    @FXML
    CheckBox t3CheckBox;
    @FXML
    CheckBox t4CheckBox;
    @FXML
    CheckBox t5CheckBox;
    @FXML
    CheckBox t6CheckBox;
    @FXML
    CheckBox t7CheckBox;
    @FXML
    CheckBox t8CheckBox;
    @FXML
    CheckBox t9CheckBox;
    @FXML
    CheckBox t10CheckBox;
    @FXML
    CheckBox t11CheckBox;


    private ObservableList<String> nameToFunction;
    private ObservableList<Quantifier> quantifiers;
    private ObservableList<String> generatedSummarizerStrings;
    private ObservableList<SummaryPOJO> summaryPOJOS;

    private CrispSet universe;
    private List<LinguisticVariable> variables;
    private char currentlyChosenQW = 'S';

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            joinAndInitLists();
            initTable();
            setCallbacks();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initTable() {
        summariesView.setItems(summaryPOJOS);
        summariesView.getColumns().clear();
        TableColumn summaryColumn = new TableColumn<>("Summary");
        summaryColumn.setVisible(true);
        summaryColumn.setCellValueFactory(new PropertyValueFactory<>("summary"));
        summariesView.getColumns().add(summaryColumn);

        for (int i = 1; i <= 11; i++) {
            TableColumn t = new TableColumn<>(String.format("T[%d]", i));
            t.setVisible(true);
            t.setCellValueFactory(new PropertyValueFactory<>("t" + i));
            summariesView.getColumns().add(t);
        }
    }

    private boolean[] tMask() {
        boolean[] mask = new boolean[11];
        mask[0] = t1CheckBox.isSelected();
        mask[1] = t2CheckBox.isSelected();
        mask[2] = t3CheckBox.isSelected();
        mask[3] = t4CheckBox.isSelected();
        mask[4] = t5CheckBox.isSelected();
        mask[5] = t6CheckBox.isSelected();
        mask[6] = t7CheckBox.isSelected();
        mask[7] = t8CheckBox.isSelected();
        mask[8] = t9CheckBox.isSelected();
        mask[9] = t10CheckBox.isSelected();
        mask[10] = t11CheckBox.isSelected();
        return mask;
    }

    private void joinAndInitLists() throws IOException {
        Table table = Table.read().file(ProjectPaths.dataPath.toString());
        universe = new CrispSet(table);
        variables = LinguisticVariablesLoader.load(universe);
        List<String> functionNames = new ArrayList<>();
        for (LinguisticVariable lv : variables) {
            functionNames.addAll(lv.getCharacteristicFunctionsLabelsWithName());
        }

        nameToFunction = FXCollections.observableArrayList(functionNames);
        quantifiers = FXCollections.observableArrayList(QuantifiersLoader.load());
        generatedSummarizerStrings = FXCollections.observableArrayList(new ArrayList<>());
        summaryPOJOS = FXCollections.observableArrayList(new ArrayList<>());

        quantifiersView.setItems(quantifiers);
        variablesView.setItems(nameToFunction);
        generatedSummarizer.setItems(generatedSummarizerStrings);
    }

    private void setCallbacks() {
        quantifiersView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                Quantifier quantifier = quantifiersView.getSelectionModel().getSelectedItem();
                generatedSummarizerStrings.add("Q: " + quantifier.toString());
            }
        });

        variablesView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                String element = variablesView.getSelectionModel().getSelectedItem();
                generatedSummarizerStrings.add(currentlyChosenQW + ": " + element);
            }
        });

        generatedSummarizer.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                String element = generatedSummarizer.getSelectionModel().getSelectedItem();
                generatedSummarizerStrings.remove(element);
            }
        });
    }

    public void generateSummary(ActionEvent actionEvent) {
        try {
            Summary summary = parseSummaryFromGUI();
            SummaryPOJO sp = new SummaryPOJO(summary, tMask());
            summaryPOJOS.add(sp);
            summariesView.refresh();
            generatedSummarizerStrings.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(ActionEvent actionEvent) {

    }

    public void addSand() {
        generatedSummarizerStrings.add(currentlyChosenQW + ": AND");
    }

    public void addSor() {
        generatedSummarizerStrings.add(currentlyChosenQW + ": OR");
    }

    public void switchQualifier() {
        if (currentlyChosenQW == 'S') {
            currentlyChosenQW = 'W';
        } else {
            currentlyChosenQW = 'S';

        }
    }

    private Summary parseSummaryFromGUI() {
        List<ConjunctionType> quantifierConjunction = new ArrayList<>();
        Quantifier quantifier = null;

        List<ConjunctionType> qualifierConjunction = new ArrayList<>();
        List<LinguisticVariable> qualifierVariables = new ArrayList<>();
        List<String> qualifierFNames = new ArrayList<>();

        List<ConjunctionType> summarizerConjunction = new ArrayList<>();
        List<LinguisticVariable> summarizerVariables = new ArrayList<>();
        List<String> summarizerFNames = new ArrayList<>();

        for (String element : generatedSummarizerStrings) {
            String firstLetter = element.substring(0, 1);
            switch (firstLetter) {
                case "Q":
                    if (element.equals("Q: AND")) {
                        quantifierConjunction.add(ConjunctionType.AND);
                    } else if (element.equals("Q: OR")) {
                        quantifierConjunction.add(ConjunctionType.OR);
                    } else {
                        String[] array = element.split(" ");

                        for (Quantifier q : this.quantifiers) {
                            if (q.toString().equals(array[array.length - 1])) {
                                quantifier = q;
                                break;
                            }
                        }
                    }
                    break;
                case "W":
                    if (element.equals("W: AND")) {
                        qualifierConjunction.add(ConjunctionType.AND);
                    } else if (element.equals("W: OR")) {
                        qualifierConjunction.add(ConjunctionType.OR);
                    } else {
                        String[] array = element.split(" ");
                        for (LinguisticVariable lv : this.variables) {
                            if (array[array.length - 1].equals(lv.getName())) {
                                qualifierVariables.add(lv);
                                qualifierFNames.add(array[array.length - 2]);
                                break;
                            }
                        }
                    }
                    break;
                case "S":
                    if (element.equals("S: AND")) {
                        summarizerConjunction.add(ConjunctionType.AND);
                    } else if (element.equals("S: OR")) {
                        summarizerConjunction.add(ConjunctionType.OR);
                    } else {
                        String[] array = element.split(" ");
                        for (LinguisticVariable lv : this.variables) {
                            if (array[array.length - 1].equals(lv.getName())) {
                                summarizerVariables.add(lv);
                                summarizerFNames.add(array[array.length - 2]);
                                break;
                            }
                        }
                    }
                    break;
            }
        }

        if (qualifierVariables.isEmpty()) {
            return new Summary("products", summarizerVariables, summarizerFNames, quantifier, summarizerConjunction,
                    NormPairImplementations.minMaxNormPair());
        } else {
            return new SummaryWithQualifier("products", summarizerVariables, summarizerFNames, quantifier, summarizerConjunction,
                    NormPairImplementations.minMaxNormPair(),
                    qualifierVariables, qualifierFNames, qualifierConjunction);
        }
    }
}
