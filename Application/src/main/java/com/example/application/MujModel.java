package com.example.application;

import java.sql.*;
import java.util.Collection;

import javafx.scene.control.TableView;

public class MujModel {
    private String Nazev;

    private double celkova_castka;
    private double nasetrena_castka;
    private double remains;

    private Connection connection;
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/jdbc_lib";
    private static final String username = "root";
    private static final String password = "root1234";
    private TableView<Item> tableView;

    public MujModel(Connection connection) {
        this.connection = connection;
        celkova_castka = 0;
        nasetrena_castka = 0;
        remains = 0;

        loadDataFromDatabase();
    }

    public double getCelkova_castka() { return celkova_castka; }

    public double getNasetrena_castka() { return nasetrena_castka; }

    public static Connection connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Chyba pri pripojeni k databazi");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
/*
    private void saveToDatabase(String query, double[] values) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setDouble(i + 1, values[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/



    // nacitani z databaze
    public void loadDataFromDatabase() {
        String query = "SELECT * FROM jdbc_lib.tabulka;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                celkova_castka = resultSet.getDouble("celkovaCastka");
                nasetrena_castka = resultSet.getDouble("nasetrenaCastka");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Collection<Item> pridejZaznam() {
        
    }
}




