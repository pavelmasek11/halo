package com.example.application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class OverviewController implements Initializable {




    @FXML
    private BorderPane bp;
    @FXML
    private TextArea incomeGoal;
    @FXML
    private BarChart<String, Number> chart;
    @FXML
    private TextArea incomeSaved;
    @FXML
    private TableColumn<Item, String> totalName;

    @FXML
    private TableColumn<Item, Double> totalIncome;
    @FXML
    private TableColumn<Item, Double> totalSaved;

    @FXML
    private TableView<Item> tableView;


    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;
    @FXML
    private AnchorPane contentArea;

    private Connection databaseConnection;

    private MujModel model;
    private Main main;




    public void setMujModel(MujModel model) {
        this.model = model;
    }
    public void setMain(Main main) {
        this.main = main;
    }

    // konstruktor kontrolleru s vytvorenim instance model
    public OverviewController() {
        // Připojení k databázi při vytvoření instance
        Connection databaseConnection = MujModel.connection();
        this.model = new MujModel(databaseConnection);
        model.loadDataFromDatabase();
    }

    // Inicializace kontrolleru po načtení FXML
    @FXML
    private void initialize() {
        updatePrehled();
    }

    // Aktualizace přehledového FXML s celkovým příjmem a výdajem
    private void updatePrehled() {
        totalIncome.setText(String.valueOf(model.getCelkova_castka()));
        totalSaved.setText(String.valueOf(model.getNasetrena_castka()));

    }

    public void setDatabaseConnection(Connection connection) {
        this.databaseConnection = connection;
    }

    @FXML
    private void openprijem(ActionEvent event) {
        openPrijemWindow();
    }

    private void openPrijemWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lib/Items.fxml"));

            Parent prehledPane = loader.load();



            ItemsController itemsController = loader.getController();
            itemsController.setMujModel(model);
         //   itemsController.setDatabaseConnection(databaseConnection);

            contentArea.getChildren().setAll(prehledPane.getChildrenUnmodifiable());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void openprehled(ActionEvent event) {
        openPrehledmWindow();
    }

    private void openPrehledmWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lib/Overview.fxml"));

            Parent prehledPane = loader.load();


            OverviewController overviewController = loader.getController();
            overviewController.setMujModel(model);

            contentArea.getChildren().setAll(prehledPane.getChildrenUnmodifiable());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void opencalc(ActionEvent event) {
        openCalcWindow();
    }

    private void openCalcWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lib/Calc.fxml"));

            Parent prehledPane = loader.load();


            CalcController calcController = loader.getController();
            calcController.setMujmodel(model); // Předávání instance MujModel do ItemsController

            contentArea.getChildren().setAll(prehledPane.getChildrenUnmodifiable());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        ObservableList<Item> initialData(){
        Item i1 = new Item( "Hodinky",1500,56);
        return FXCollections.<Item> observableArrayList(i1);
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        totalName.setCellValueFactory(new PropertyValueFactory<Item, String>("Nazev"));
        totalIncome.setCellValueFactory(new PropertyValueFactory<Item, Double>("celkova_castka"));
        totalSaved.setCellValueFactory(new PropertyValueFactory<Item, Double>("nasetrena_castka"));

        tableView.setItems(initialData());
    }


}

