package ksrGut.gui.controllers;

import com.google.common.primitives.Doubles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import ksrGut.ProjectPaths;
import ksrGut.logic.CrispSet;
import ksrGut.logic.characteristicFunction.FallingFunction;
import ksrGut.logic.characteristicFunction.RectangularFunction;
import ksrGut.logic.characteristicFunction.RisingFunction;
import ksrGut.logic.characteristicFunction.TriangularFunction;
import ksrGut.logic.norms.NormPairImplementations;
import ksrGut.logic.summaries.ConjunctionType;
import ksrGut.logic.summaries.LinguisticVariable;
import ksrGut.logic.summaries.Summary;
import ksrGut.logic.summaries.SummaryWithQualifier;
import ksrGut.logic.summaries.quantifier.Quantifier;
import ksrGut.logic.summaries.quantifier.QuantifierRelativity;
import loaders.LinguisticVariablesLoader;
import loaders.QuantifiersLoader;
import tech.tablesaw.api.Table;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    @FXML
    ListView<String> variablesView;
    @FXML
    ListView<String> variablesView2;
    @FXML
    ListView<Quantifier> quantifiersView;
    @FXML
    ListView<Quantifier> quantifiersView2;
    @FXML
    ListView<String> generatedSummarizer;
    @FXML
    ChoiceBox<String> choiceBoxVariablesFeatures;
    @FXML
    ChoiceBox<String> choiceBoxVariablesCharacteristicFunction;
    @FXML
    ChoiceBox<String> choiceBoxVariablesCharacteristicFunction2;
    @FXML
    TextField variableItemNewName;
    @FXML
    TextField characteristicFunctionData;
    @FXML
    TextField characteristicFunctionData2;
    @FXML
    TextField quantifierName;
    @FXML
    CheckBox relativity;

    @FXML
    ListView<SummaryPOJO> summariesView;
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

    private List<LinguisticVariable> variables;
    private char currentlyChosenQW = 'S';

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            joinAndInitLists();
            setCallbacks();
        } catch (IOException e) {
            e.printStackTrace();
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
        CrispSet universe = new CrispSet(table);
        variables = LinguisticVariablesLoader.load(universe);
        List<String> functionNames = new ArrayList<>();
        for (LinguisticVariable lv : variables) {
            functionNames.addAll(lv.getCharacteristicFunctionsLabelsWithName());
        }

        nameToFunction = FXCollections.observableArrayList(functionNames);
        quantifiers = FXCollections.observableArrayList(QuantifiersLoader.load());
        List<String> x = variables.stream().map(LinguisticVariable::getName).collect(Collectors.toList());
        ObservableList<String> choiceBoxVariableList = FXCollections.observableArrayList(x);
        generatedSummarizerStrings = FXCollections.observableArrayList(new ArrayList<>());
        summaryPOJOS = FXCollections.observableArrayList(new ArrayList<>());
        ObservableList<String> functions = FXCollections.observableArrayList("triangular", "rectangular", "falling", "rising");


        quantifiersView.setItems(quantifiers);
        quantifiersView2.setItems(quantifiers);
        variablesView.setItems(nameToFunction);
        variablesView2.setItems(nameToFunction);
        generatedSummarizer.setItems(generatedSummarizerStrings);
        summariesView.setItems(summaryPOJOS);
        choiceBoxVariablesFeatures.setItems(choiceBoxVariableList);
        choiceBoxVariablesCharacteristicFunction.setItems(functions);
        choiceBoxVariablesCharacteristicFunction2.setItems(functions);
    }

    public void createNewVariableElement(ActionEvent actionEvent) {
        try {
            String chosenVariable = choiceBoxVariablesFeatures.getValue();
            LinguisticVariable lv = variables.stream()
                    .filter(v -> chosenVariable.equals(v.getName()))
                    .findFirst().orElse(null);

            String newName = variableItemNewName.getText();
            if (lv.getCharacteristicFunctionsLabels().contains(newName)) {
                return;
            }
            List<Double> x = new ArrayList<>();
            for (String s : characteristicFunctionData.getText().split(" ")) {
                x.add(Doubles.tryParse(s));
            }
            String chosenFunction = choiceBoxVariablesCharacteristicFunction.getValue();
            switch (chosenFunction) {
                case "triangular":
                    lv.addFunction(newName, new TriangularFunction(lv.getName(), x.get(0), x.get(1), x.get(2)));
                    break;
                case "rectangular":
                    lv.addFunction(newName, new RectangularFunction(lv.getName(), x.get(0), x.get(1)));
                    break;
                case "rising":
                    lv.addFunction(newName, new RisingFunction(lv.getName(), x.get(0), x.get(1)));
                    break;
                case "falling":
                    lv.addFunction(newName, new FallingFunction(lv.getName(), x.get(0), x.get(1)));
                    break;
                default:
                    return;
            }

            nameToFunction.add(newName + " " + lv.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void createQuantifier(ActionEvent actionEvent) {
        QuantifierRelativity quantifierRelativity;
        if (relativity.isSelected()) quantifierRelativity = QuantifierRelativity.RELATIVE;
        else quantifierRelativity = QuantifierRelativity.ABSOLUTE;
        List<Double> x = new ArrayList<>();
        for (String s : characteristicFunctionData2.getText().split(" ")) {
            x.add(Doubles.tryParse(s));
        }
        String quantifierName = this.quantifierName.getText().replace(" ", "_");

        String chosenFunction = choiceBoxVariablesCharacteristicFunction2.getValue();
        switch (chosenFunction) {
            case "triangular":
                quantifiers.add(new Quantifier(quantifierName, quantifierRelativity, new TriangularFunction(null, x.get(0), x.get(1), x.get(2))));
                break;
            case "rectangular":
                quantifiers.add(new Quantifier(quantifierName, quantifierRelativity, new RectangularFunction(null, x.get(0), x.get(1))));
                break;
            case "rising":
                quantifiers.add(new Quantifier(quantifierName, quantifierRelativity, new RisingFunction(null, x.get(0), x.get(1))));
                break;
            case "falling":
                quantifiers.add(new Quantifier(quantifierName, quantifierRelativity, new FallingFunction(null, x.get(0), x.get(1))));
                break;
        }
    }

    public void saveSummaries(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showSaveDialog(null);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
            for (SummaryPOJO item : summaryPOJOS) {
                writer.write(item.toString());
                writer.write('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
