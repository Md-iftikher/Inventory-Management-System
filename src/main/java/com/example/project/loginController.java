package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class loginController {

    @FXML
    private Button loginButton,adminlogin;
    @FXML
    private Label loginMassagelebel; //Invalid Login. Please Try again.
    @FXML
    private TextField usernameTextfield;
    @FXML
    private TextField enterPasswordField;
    @FXML
    private Button Registrationclick;

    private String userFile = "user_info.txt";
    @FXML
    public void Loginf(ActionEvent event){
        try {
            if (usernameTextfield.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false) {
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
        String enteredUsername = usernameTextfield.getText();
        String enteredPassword = enterPasswordField.getText();
        boolean loginSuccessful = false;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(userFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 4) {
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
            logintouserInvoice();
        } else {
            loginMassagelebel.setText("Invalid Login. Please Try again.");
        }
    }

    private void logintouserInvoice() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userDashboard.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1100, 600);
            String css = this.getClass().getResource("adminlogin.css").toExternalForm();
            scene.getStylesheets().add(css);
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            System.out.println(e);
            e.getCause();
            e.printStackTrace();
        }
    }


    @FXML
    public void handleRegistrationClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registration.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 600, 524);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            System.out.println(e);
            e.getCause();
        }
    }
    @FXML
    public void Adminlogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminLogin.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 600, 450);
            String css = this.getClass().getResource("adminlogin.css").toExternalForm();
            scene.getStylesheets().add(css);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            System.out.println(e);
            e.getCause();
        }
    }




}