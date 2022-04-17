package com.example;
/*
Put header here


 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {

    @FXML
    // fx:id="chart"
    private BarChart<String, Number> chart; // Value injected by FXMLLoader

    @FXML
    private Label label;
    
    @FXML
    private Button calculateButton;

    @FXML
    private TextField balance;

    @FXML
    private TextField apy;

    @FXML
    private TextField years;

    @FXML
    private void calculate(ActionEvent event) {
        try {
            double balanceValue = Double.parseDouble(balance.getText());
            double apyValue = Double.parseDouble(apy.getText());
            double yearsValue = Double.parseDouble(years.getText());
            double total = balanceValue * Math.pow((1 + apyValue / 100), yearsValue);
            label.setText(String.format("$%.2f", total));
            
            chart.getData().clear();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Balance");
            for (int i = 0; i <= yearsValue; i++) {
                series.getData().add(new XYChart.Data<>(String.valueOf(i), balanceValue * Math.pow((1 + apyValue / 100), i)));
            }
            chart.getData().add(series);
        }
        finally {
            // do nothing
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Make balance, apy, and years text fields numeric only
        balance.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                balance.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        apy.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                apy.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        years.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                years.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
