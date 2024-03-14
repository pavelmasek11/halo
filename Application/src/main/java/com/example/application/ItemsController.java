package com.example.application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ItemsController {

    @FXML
    private TextField Nazev;

    @FXML
    private TextField incomeGoal;

    @FXML
    private TextField incomeSaved;

    private MujModel model;
    public void setMujModel(MujModel model) {
        this.model = model;
    }

    public void handlePridatZaznam(ActionEvent event) {
        Item item1 = new Item(Nazev.getText(), Double.parseDouble(incomeGoal.getText()),Double.parseDouble(incomeSaved.getText()));
        model.pridejZaznam().add(item1);
    }



}







