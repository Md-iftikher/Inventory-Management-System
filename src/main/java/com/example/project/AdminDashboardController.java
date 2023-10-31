package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Optional;

public class AdminDashboardController {
    @FXML
    private Button Admin_dash_Order_back_user_Sceren_for_order;

    @FXML
    private Button Admin_logoutButton;

    @FXML
    private AnchorPane Admin_main_form;

    @FXML
    private TextField addProductId;

    @FXML
    private Button addProduct_Add_button;

    @FXML
    private Label addProduct_Change_label_with_type;

    @FXML
    private TextField addProduct_Discount;

    @FXML
    private AnchorPane addProduct_Form;

    @FXML
    private Button addProduct_Reset_button;

    @FXML
    private TextField addProduct_Stocks;

    @FXML
    private TableView<?> addProduct_TableView;

    @FXML
    private TableColumn<?, ?> addProduct_Table_col_Productname;

    @FXML
    private TableColumn<?, ?> addProduct_Table_col_devloper_artist_director;

    @FXML
    private TableColumn<?, ?> addProduct_Table_col_discount;

    @FXML
    private TableColumn<?, ?> addProduct_Table_col_genre;

    @FXML
    private TableColumn<?, ?> addProduct_Table_col_id;

    @FXML
    private TableColumn<?, ?> addProduct_Table_col_price;

    @FXML
    private TableColumn<?, ?> addProduct_Table_col_remainingstocks;

    @FXML
    private TextField addProduct_Year_of_publish;

    @FXML
    private TextField addProduct_change_texbox;

    @FXML
    private Button addProduct_delete_button;

    @FXML
    private TextField addProduct_genre;

    @FXML
    private TextField addProduct_name;

    @FXML
    private TextField addProduct_price;

    @FXML
    private Label addProduct_table_label_change_with_type;

    @FXML
    private ComboBox<?> addProduct_tyoe;

    @FXML
    private Button addProduct_update_button;

    @FXML
    private Button adminDashHome_button;

    @FXML
    private Label adminUserName;

    @FXML
    private Button admin_addproduct_Button;

    @FXML
    private Label home_avaibleProducts;

    @FXML
    private AnchorPane home_form;

    @FXML
    private BarChart<?, ?> home_income_chart;

    @FXML
    private Label home_number_order;

    @FXML
    private BarChart<?, ?> home_order_chart;

    @FXML
    private Label home_total_income;
    @FXML
    public void SwitchForm(ActionEvent event) {
        if (event.getSource() == adminDashHome_button){
            home_form.setVisible(true);
            addProduct_Form.setVisible(false);
        }
        else if (event.getSource() == admin_addproduct_Button){
            home_form.setVisible(false);
            addProduct_Form.setVisible(true);
        } else if (event.getSource() == Admin_dash_Order_back_user_Sceren_for_order) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 600, 400);
                Stage currentStage = (Stage) Admin_dash_Order_back_user_Sceren_for_order.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

            @FXML
    public void logout_From_dashboard_TO_admin_login(){
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText(" Are You Sure You Want To Logout? ");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminLogin.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 600, 450);
                String css = this.getClass().getResource("adminlogin.css").toExternalForm();
                scene.getStylesheets().add(css);
                Stage currentStage = (Stage) Admin_logoutButton.getScene().getWindow();
                currentStage.setScene(scene);
                currentStage.show();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}


