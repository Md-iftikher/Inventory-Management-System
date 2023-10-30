package com.example.project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class regesterController {
    @FXML
    private Button closeButton,RegisterButton;
    @FXML
    private Label confirmPasswordMessage,registrationmessage;
    @FXML
    private PasswordField setPasswordTextfield,ConfirmPasswordTextfield;
    @FXML
    private TextField FirstnameTextfield,lastnameTextfield,usernameTextfield;

    public void RegisterButton(ActionEvent event){
        if(setPasswordTextfield.getText().equals(ConfirmPasswordTextfield.getText())){
            StoreingUserData();
            registrationmessage.setText("User Registered Successfully.");
            KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.seconds(2), e -> close_back_to_login(event));
            Timeline timeline = new Timeline(keyFrame);
            timeline.play();
        }
        else {
            confirmPasswordMessage.setText("Password Does not match.");
        }
    }

    private void StoreingUserData() {
        String firstname=FirstnameTextfield.getText();
        String lastname=lastnameTextfield.getText();
        String username= usernameTextfield.getText();
        String password=setPasswordTextfield.getText();

        String filename="user_info.txt";

        try(BufferedWriter writer=new BufferedWriter(new FileWriter(filename,true))) {
            writer.write(username+","+password+","+firstname+","+lastname);
            writer.newLine();
            writer.flush();
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println(e);
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

}
