package com.example.project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 450);
            String css = this.getClass().getResource("adminDashboardDesign.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setTitle("application");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}