package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AdminLogincontroller {
    @FXML
    private Button admininlogin_bt;

    @FXML
    private PasswordField adminpassword;

    @FXML
    private TextField adminusername;

    @FXML
    private Button close;
    @FXML
    private Label loginMassagelebel;
    private String userFile = "admininfo.txt";
    @FXML
    public void Loginf(ActionEvent event){
        try {
            if (adminusername.getText().isBlank() == false && adminpassword.getText().isBlank() == false) {
                logincheck();
            } else {
                loginMassagelebel.setText("Please Enter Username and Password");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void logincheck() {
        String enteredUsername = adminusername.getText();
        String enteredPassword = adminpassword.getText();
        boolean loginSuccessful = false;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(userFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 2) {
                    String username = userData[0];
                    String password = userData[1];
//                    String firstname = userData[2];
//                    String lastname = userData[3];
                    if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                        loginSuccessful = true;
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error reading user information from the file: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error closing the file: " + e.getMessage());
                }
            }
        }

        if (loginSuccessful) {
            loginMassagelebel.setText("Login successful!");
            openadminDashboard();

        } else {
            loginMassagelebel.setText("Invalid Login. Please Try again.");
        }
    }





    @FXML
    public void close_back_to_login(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 600, 400);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void openadminDashboard(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 600, 400);
            Stage currentStage = (Stage)admininlogin_bt.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }



}
