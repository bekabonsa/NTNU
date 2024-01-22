package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private Label welcomeText;
    Button btnScene1, btnScene2;
    @FXML
    void onHelloButtonClick(ActionEvent event) {
        Stage mainWindow = (Stage) welcomeText.getScene().getWindow();
        String title = welcomeText.getText();
        mainWindow.setTitle("oko");
    }

    public void loginButtonClicked(){
        Parent root = FXMLLoader.load(getClass().getResource("hello-view2.fxml"));
        Stage window = (Stage) btnScene1.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));

    }

}
