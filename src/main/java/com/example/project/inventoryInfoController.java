package com.example.project;

import com.example.project.mainbackend.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class inventoryInfoController {
    @FXML
    private RadioButton Movie_radio_button;

    @FXML
    private RadioButton MusicRadio_Button;

    @FXML
    private TableView<ProductDataForTableView> ShowallProduct_TableView;

    @FXML
    private TableColumn<ProductDataForTableView, String> product_Table_col_Productname;

    @FXML
    private TableColumn<ProductDataForTableView, Integer> Product_Table_col_NumOfItemStocked;

    @FXML
    private TableColumn<ProductDataForTableView, String> Product_Table_col_devloper_artist_director;

    @FXML
    private TableColumn<ProductDataForTableView, Double> Product_Table_col_discount1;

    @FXML
    private TableColumn<ProductDataForTableView, String> Product_Table_col_genre;

    @FXML
    private TableColumn<ProductDataForTableView, Integer> Product_Table_col_id;

    @FXML
    private TableColumn<ProductDataForTableView, Double> Product_Table_col_price;
    @FXML
    private TableColumn<ProductDataForTableView, Integer> Product_Table_col_publishedYEar;

    @FXML
    private Label adminUserName;

    @FXML
    private Button back_to_home;

    @FXML
    private Label changetextAfterClikingRadioButton;

    @FXML
    private RadioButton game_radio_button;

    @FXML
    private Button moveToDashHome_button;

    @FXML
    private ToggleGroup radio_button;

    @FXML
    private Button user_Sceren_for_order;
    @FXML
    private Game game;
    private ObservableList<ProductDataForTableView> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        radio_button.selectToggle(game_radio_button);

        Product_Table_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        product_Table_col_Productname.setCellValueFactory(new PropertyValueFactory<>("productName"));
        Product_Table_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        Product_Table_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        Product_Table_col_devloper_artist_director.setCellValueFactory(new PropertyValueFactory<>("developerArtistDirector"));
        Product_Table_col_NumOfItemStocked.setCellValueFactory(new PropertyValueFactory<>("numOfItemStocked"));
        Product_Table_col_discount1.setCellValueFactory(new PropertyValueFactory<>("discount"));
        Product_Table_col_publishedYEar.setCellValueFactory(new PropertyValueFactory<>("Year_of_Publish"));


        radio_button.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == game_radio_button) {
                changetextAfterClikingRadioButton.setText("All Product Of Game");
                Product_Table_col_devloper_artist_director.setText("Devloper");
                populateGameTableview();
            } else if (newValue == Movie_radio_button) {
                changetextAfterClikingRadioButton.setText("All Product Of Movie");
                Product_Table_col_devloper_artist_director.setText("Director");
                populateMovieTableview();
            } else if (newValue == MusicRadio_Button) {
                changetextAfterClikingRadioButton.setText("All Product Of Music");
                Product_Table_col_devloper_artist_director.setText("Artist");
                populateMusicTableview();
            }
        });
    }
    @FXML
    private void populateMovieTableview() {
        data.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("Movie.txt"))) {
            String line;
            ProductDataForTableView product = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name:")) {
                    if (product != null) {
                        data.add(product);
                    }

                    product = new ProductDataForTableView();
                    product.setProductName(line.substring(6).trim());
                } else if (product != null) {
                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String property = parts[0];
                        String value = parts[1];

                        switch (property) {
                            case "Product Id":
                                product.setId(Integer.parseInt(value));
                                break;
                            case "Price":
                                product.setPrice(Double.parseDouble(value));
                                break;
                            case "Genre":
                                product.setGenre(value);
                                break;
                            case "Director":
                                product.setDeveloperArtistDirector(value);
                                break;
                            case "Discount":
                                product.setDiscount(Double.parseDouble(value));
                                break;
                            case "No of Items Stocked":
                                product.setNumOfItemStocked(Integer.parseInt(value));
                                break;
                            case "Year of Publish":
                                product.setYear_of_Publish(Integer.parseInt(value));
                                break;
                        }
                    }
                }
            }

            if (product != null) {
                data.add(product);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        ShowallProduct_TableView.setItems(data);
    }
    @FXML
    private void populateMusicTableview() {

        data.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("Music.txt"))) {
            String line;
            ProductDataForTableView product = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name:")) {
                    if (product != null) {
                        data.add(product);
                    }

                    product = new ProductDataForTableView();
                    product.setProductName(line.substring(6).trim());
                } else if (product != null) {
                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String property = parts[0];
                        String value = parts[1];

                        switch (property) {
                            case "Product Id":
                                product.setId(Integer.parseInt(value));
                                break;
                            case "Price":
                                product.setPrice(Double.parseDouble(value));
                                break;
                            case "Genre":
                                product.setGenre(value);
                                break;
                            case "Artist":
                                product.setDeveloperArtistDirector(value);
                                break;
                            case "Discount":
                                product.setDiscount(Double.parseDouble(value));
                                break;
                            case "No of Items Stocked":
                                product.setNumOfItemStocked(Integer.parseInt(value));
                                break;
                            case "Year of Publish":
                                product.setYear_of_Publish(Integer.parseInt(value));
                                break;
                        }
                    }
                }
            }

            if (product != null) {
                data.add(product);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        ShowallProduct_TableView.setItems(data);
    }
    @FXML
    private void populateGameTableview() {
        data.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("Game.txt"))) {
            String line;
            ProductDataForTableView product = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name:")) {
                    if (product != null) {
                        data.add(product);
                    }

                    product = new ProductDataForTableView();
                    product.setProductName(line.substring(6).trim());
                } else if (product != null) {
                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String property = parts[0];
                        String value = parts[1];

                        switch (property) {
                            case "Product Id":
                                product.setId(Integer.parseInt(value));
                                break;
                            case "Price":
                                product.setPrice(Double.parseDouble(value));
                                break;
                            case "Genre":
                                product.setGenre(value);
                                break;
                            case "Developer":
                                product.setDeveloperArtistDirector(value);
                                break;
                            case "Discount":
                                product.setDiscount(Double.parseDouble(value));
                                break;
                            case "No of Items Stocked":
                                product.setNumOfItemStocked(Integer.parseInt(value));
                                break;
                            case "Year of Publish":
                                product.setYear_of_Publish(Integer.parseInt(value));
                                break;
                        }
                    }
                }
            }

            if (product != null) {
                data.add(product);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        ShowallProduct_TableView.setItems(data);
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
    @FXML
    public void setBack_to_home(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1100, 600);
            String css = this.getClass().getResource("adminDashboardDesign.css").toExternalForm();
            scene.getStylesheets().add(css);
            Stage currentStage = (Stage)moveToDashHome_button.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void logout_From_inventory_toHome(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1100, 600);
            String css = this.getClass().getResource("adminDashboardDesign.css").toExternalForm();
            scene.getStylesheets().add(css);
            Stage currentStage = (Stage)back_to_home.getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
