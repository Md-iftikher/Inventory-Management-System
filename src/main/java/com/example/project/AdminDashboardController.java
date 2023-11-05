package com.example.project;

import com.example.project.mainbackend.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminDashboardController {
    @FXML
    private Button Admin_dash_Order_back_user_Sceren_for_order;
    @FXML
    private Button admin_inventoryINfo_Button;
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
    private TableView<ProductDataForTableView> addProduct_TableView;

    @FXML
    private TableColumn<ProductDataForTableView, String> addProduct_Table_col_Productname;

    @FXML
    private TableColumn<ProductDataForTableView, String> addProduct_Table_col_devloper_artist_director;

    @FXML
    private TableColumn<ProductDataForTableView, Double> addProduct_Table_col_discount;

    @FXML
    private TableColumn<ProductDataForTableView, String> addProduct_Table_col_genre;

    @FXML
    private TableColumn<ProductDataForTableView, Integer> addProduct_Table_col_id;

    @FXML
    private TableColumn<ProductDataForTableView, Double> addProduct_Table_col_price;

    @FXML
    private TableColumn<ProductDataForTableView, Integer> addProduct_Table_col_NumOfItemStocked;

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
    private StockableProduct sp;
    @FXML
    private Game game;
    @FXML
    private Music music;
    @FXML
    private Movie movie;
    @FXML
    private ObservableList<ProductDataForTableView> productDataList = FXCollections.observableArrayList();
    @FXML
    public void adproductshowlistdata() {
        //addproductDataList=addproductlist();

        addProduct_Table_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProduct_Table_col_Productname.setCellValueFactory(new PropertyValueFactory<>("productName"));
        addProduct_Table_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        addProduct_Table_col_devloper_artist_director.setCellValueFactory(new PropertyValueFactory<>("developerArtistDirector"));
        addProduct_Table_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        addProduct_Table_col_discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        addProduct_Table_col_NumOfItemStocked.setCellValueFactory(new PropertyValueFactory<>("numOfItemStocked"));

        //addProduct_TableView.setItems(addproductDataList);
        addProduct_TableView.setItems(productDataList);

    }






    @FXML
    public void intializeproductType(){ //product type intilize in Combobox.
        String[] producttypelist={"Movie","Game","Music"};
        List<String> listT=new ArrayList<>();
        for(String data:producttypelist){
            listT.add(data);
        }
        ObservableList listData= FXCollections.observableArrayList(listT);
        addProduct_tyoe.setItems(listData);

        addProduct_Change_label_with_type.setText("Director:");
        addProduct_Table_col_devloper_artist_director.setText("Devloper");
        handleProductTypeChange();

    }
    @FXML
    public void handleProductTypeChange() {
        String selectedType = (String) addProduct_tyoe.getValue();
        if (selectedType != null) {
            if (selectedType.equals("Movie")) {
                addProduct_Change_label_with_type.setText("Director:");
                addProduct_Table_col_devloper_artist_director.setText("Director");
            } else if (selectedType.equals("Game")) {
                addProduct_Change_label_with_type.setText("Developer:");
                addProduct_Table_col_devloper_artist_director.setText("Devloper");
            } else if (selectedType.equals("Music")) {
                addProduct_Change_label_with_type.setText("Artist:");
                addProduct_Table_col_devloper_artist_director.setText("Artist");
            }
        }
    }

      ///adding product
      @FXML
      public void addProduct() {
          String selectedCategory = (String)addProduct_tyoe.getValue();
          String productName = addProduct_name.getText();
          int productId = Integer.parseInt(addProductId.getText());
          double price = Double.parseDouble(addProduct_price.getText());
          String genre = addProduct_genre.getText();
          int yearOfPublish = Integer.parseInt(addProduct_Year_of_publish.getText());
          double discount = Double.parseDouble(addProduct_Discount.getText());
          int stocks = Integer.parseInt(addProduct_Stocks.getText());
          String developer_artist_director = addProduct_change_texbox.getText();
          try {
              if (selectedCategory.equals("Game")) {
                  adproductshowlistdata();
                  game = new Game(productName, productId, price, genre, yearOfPublish, discount, stocks, developer_artist_director);
                  productDataList.add(new ProductDataForTableView(game.getProductId(), game.getName(), game.getGenre(), game.getDeveloper(),game.getYearPublished(),game.getPrice(), game.getDiscount(), game.getNumberOfItemsStocked()));
                  Inventory.getInstance().addItem(game);
                  updateProductInfoInFile(game);

              } else if (selectedCategory.equals("Music")) {
                  adproductshowlistdata();
                  music = new Music(productName, productId, price, genre, yearOfPublish, discount, stocks, developer_artist_director);
                  productDataList.add(new ProductDataForTableView(music.getProductId(), music.getName(), music.getGenre(), music.getArtistName(),music.getYearPublished(), music.getPrice(), music.getDiscount(), music.getNumberOfItemsStocked()));
                  Inventory.getInstance().addItem(music);
                  updateProductInfoInFile(music);

              } else if (selectedCategory.equals("Movie")) {
                  adproductshowlistdata();
                  movie = new Movie(productName, productId, price, genre, yearOfPublish, discount, stocks, developer_artist_director);
                  productDataList.add(new ProductDataForTableView(movie.getProductId(), movie.getName(), movie.getGenre(), movie.getDirector(),movie.getYearPublished(), movie.getPrice(), movie.getDiscount(), movie.getNumberOfItemsStocked()));
                  Inventory.getInstance().addItem(movie);
                  updateProductInfoInFile(movie);

              }

              adproductshowlistdata();
          } catch (Exception e) {
              System.out.println(e);
          }
      }
    public void updateProductInfoInFile(StockableProduct sp) {
        String fileName = null;
        String productInfo = "";

        if (sp instanceof Game) {
            fileName = "Game.txt";
            Game game = (Game) sp;
            productInfo = "Name: " + game.getName() + "\n" +
                    "Product Id: " + game.getProductId() + "\n" +
                    "Price: " + game.getPrice() + "\n" +
                    "Genre: " + game.getGenre() + "\n" +
                    "Year of Publish: " + game.getYearPublished() + "\n" +
                    "Discount: " + game.getDiscount() + "\n" +
                    "No of Items Stocked: " + game.getNumberOfItemsStocked() + "\n" +
                    "Developer: " + game.getDeveloper() + "\n";
        } else if (sp instanceof Music) {
            fileName = "Music.txt";
            Music music=(Music) sp;
            productInfo = "Name: " + music.getName() + "\n" +
                    "Product Id: " + music.getProductId() + "\n" +
                    "Price: " + music.getPrice() + "\n" +
                    "Genre: " + music.getGenre() + "\n" +
                    "Year of Publish: " + music.getYearPublished() + "\n" +
                    "Discount: " + music.getDiscount() + "\n" +
                    "No of Items Stocked: " + music.getNumberOfItemsStocked() + "\n" +
                    "Artist: " + music.getArtistName() + "\n";

        } else if (sp instanceof Movie) {
            fileName = "Movie.txt";
            Movie movie=(Movie) sp;
            productInfo = "Name: " + movie.getName() + "\n" +
                    "Product Id: " + movie.getProductId() + "\n" +
                    "Price: " + movie.getPrice() + "\n" +
                    "Genre: " + movie.getGenre() + "\n" +
                    "Year of Publish: " + movie.getYearPublished() + "\n" +
                    "Discount: " + movie.getDiscount() + "\n" +
                    "No of Items Stocked: " + movie.getNumberOfItemsStocked() + "\n" +
                    "Director: " + movie.getDirector()+ "\n";

        }

        File file = new File(fileName);
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
            pw.println(productInfo);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //clearing input fields
    @FXML
    private void clearInputFields() {
        addProduct_name.clear();
        addProductId.clear();
        addProduct_genre.clear();
        addProduct_change_texbox.clear();
        addProduct_Year_of_publish.clear();
        addProduct_price.clear();
        addProduct_Discount.clear();
        addProduct_Stocks.clear();
    }



    @FXML
    public void SwitchForm(ActionEvent event) {   //for swinting scene .
        if (event.getSource() == adminDashHome_button){
            home_form.setVisible(true);
            addProduct_Form.setVisible(false);
        }
        else if (event.getSource() == admin_addproduct_Button){
            home_form.setVisible(false);
            addProduct_Form.setVisible(true);
            intializeproductType();
            clearInputFields();
        } else if (event.getSource() == Admin_dash_Order_back_user_Sceren_for_order) {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText(" For Order. Do you Want To Go User Loing Screen? ");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
                    Parent root = fxmlLoader.load();
                    Scene scene = new Scene(root, 600, 400);
                    String css = this.getClass().getResource("adminDashboardDesign.css").toExternalForm();
                    scene.getStylesheets().add(css);
                    Stage currentStage = (Stage) Admin_dash_Order_back_user_Sceren_for_order.getScene().getWindow();
                    currentStage.setScene(scene);
                    currentStage.show();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if(event.getSource() == admin_inventoryINfo_Button) {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText(" Do you Want to GO inventory? ");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InventoryView.fxml"));
                    Parent root = fxmlLoader.load();
                    Scene scene = new Scene(root, 1100, 600);
                    String css = this.getClass().getResource("inventory.css").toExternalForm();
                    scene.getStylesheets().add(css);
                    Stage currentStage = (Stage) admin_inventoryINfo_Button.getScene().getWindow();
                    currentStage.setScene(scene);
                    currentStage.show();
                }

            }
            catch (Exception e){
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
    @FXML
    public void updateProductInFile(ActionEvent event) {
        try {
            String selectedCategory = (String)addProduct_tyoe.getValue();


            String productName = addProduct_name.getText();
            int productId = Integer.parseInt(addProductId.getText());
            double price = Double.parseDouble(addProduct_price.getText());
            String genre = addProduct_genre.getText();
            int yearOfPublish = Integer.parseInt(addProduct_Year_of_publish.getText());
            double discount = Double.parseDouble(addProduct_Discount.getText());
            int stocks = Integer.parseInt(addProduct_Stocks.getText());
            String developer_artist_director = addProduct_change_texbox.getText();

            String fileName = null;
            if (selectedCategory.equals("Game")) {
                fileName = "Game.txt";
            } else if (selectedCategory.equals("Movie")) {
                fileName = "Movie.txt";
            } else if (selectedCategory.equals("Music")) {
                fileName = "Music.txt";
            }

            File file = new File(fileName);
            File tempFile = new File("temp.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Name: ")) {
                    String currentProductName = line.substring("Name: ".length());
                    int currentProductId = Integer.parseInt(br.readLine().substring("Product Id: ".length()));
                    //write all current product then try it will work.

                    if (currentProductId == productId && currentProductName.equals(productName)) {

                        bw.write("Name: " + productName);
                        bw.newLine();
                        bw.write("Product Id: " + productId);
                        bw.newLine();
                        bw.write("Price: " + price);
                        bw.newLine();
                        bw.write("Genre: " + genre);
                        bw.newLine();
                        bw.write("Year of Publish: " + yearOfPublish);
                        bw.newLine();
                        bw.write("Discount: " + discount);
                        bw.newLine();
                        bw.write("No of Items Stocked: " + stocks);
                        bw.newLine();
                        if (selectedCategory.equals("Game")) {
                            bw.write("Developer: " + developer_artist_director);
                            bw.newLine();
                        } else if (selectedCategory.equals("Movie")) {
                            bw.write("Director: " + developer_artist_director);
                            bw.newLine();
                        } else if (selectedCategory.equals("Music")) {
                            bw.write("Artist: " + developer_artist_director);
                            bw.newLine();
                        }
                        found = true;
                    } else {

                        bw.write(line);
                        bw.newLine();
                    }
                } else {

                    bw.write(line);
                    bw.newLine();
                }
            }

            br.close();
            bw.close();

            if (found) {

                if (file.delete()) {
                    if (!tempFile.renameTo(file)) {
                        System.err.println("Failed to rename the temp file.");
                    }
                } else {
                    System.err.println("Failed to delete the original file.");
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Product updated successfully.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Product with ID " + productId + " and name " + productName + " not found.");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid input. Please enter valid product ID and values.");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}


