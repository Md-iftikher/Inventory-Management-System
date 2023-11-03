package com.example.project;

import com.example.project.mainbackend.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class userinvoiceController {
        @FXML
        private Label DiscountAfterOfer;

        @FXML
        private TextField PaymentAmount;

        @FXML
        private Button PaymentButton;

        @FXML
        private TextField product_id;

        @FXML
        private TextField Product_genre;

        @FXML
        private TextField Product_name;

        @FXML
        private Label TotalPriceOFOrder;

        @FXML
        private Label UserName;

        @FXML
        private Button addProduct_Add_button;

        @FXML
        private Label addProduct_Change_label_with_type;

        @FXML
        private Button addProduct_Reset_button;

        @FXML
        private Button back_to_home;

        @FXML
        private ComboBox<String> choseProduct_type;

        @FXML
        private TableView<StockableProduct> invoiceTableView;

        @FXML
        private TableColumn<StockableProduct, String> invoice_Tableview_Product_Genre;

        @FXML
        private TableColumn<StockableProduct, Integer> invoice_Tableview_Product_Quantity;

        @FXML
        private TableColumn<StockableProduct, String> invoice_Tableview_Product_Type;

        @FXML
        private TableColumn<StockableProduct, String> invoice_Tableview_Product_devloper_artist_director;

        @FXML
        private TableColumn<StockableProduct, String> invoice_Tableview_Product_name;

        @FXML
        private TableColumn<StockableProduct, Double> invoice_Tableview_Product_price_withQuantity;

        @FXML
        private Spinner<Integer> productQuantity;

        @FXML
        private Label quantityTextField;

        @FXML
        private Invoice currentInvoice = new Invoice();

        String[] producttypelist = {"Movie", "Game", "Music"};

        @FXML
        public void initialize() {
                List<String> listT = new ArrayList<>();
                for (String data : producttypelist) {
                        listT.add(data);
                }
                ObservableList<String> listData = FXCollections.observableArrayList(listT);
                choseProduct_type.setItems(listData);
                OrderSpiner();


        }
        @FXML
        private SpinnerValueFactory<Integer>spiner;
        public void OrderSpiner(){
                spiner=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,500,0);
                productQuantity.setValueFactory(spiner);
        }
        public void ordershowspinervalue(){
                int qutity=productQuantity.getValue();
        }


        @FXML
        private ObservableList<StockableProduct> invoiceProducts = FXCollections.observableArrayList();
        @FXML
        public void addproductshowlistdata() {

                invoice_Tableview_Product_Quantity.setCellValueFactory(cellData -> {
                        int quantity = productQuantity.getValue() != null ? productQuantity.getValue() : 0;
                        TableCell<StockableProduct, Integer> cell = new TableCell<StockableProduct, Integer>() {
                                @Override
                                protected void updateItem(Integer item, boolean empty) {
                                        super.updateItem(item, empty);
                                        if (!empty) {
                                                setText(String.valueOf(quantity));
                                        } else {
                                                setText(null);
                                        }
                                }
                        };

                        return new SimpleObjectProperty<>(quantity);
                });

                invoice_Tableview_Product_Type.setCellValueFactory(cellData -> {
                        StockableProduct product = cellData.getValue();
                        if (product instanceof Game) {
                                return new SimpleStringProperty("Game");
                        } else if (product instanceof Music) {
                                return new SimpleStringProperty("Music");
                        } else if (product instanceof Movie) {
                                return new SimpleStringProperty("Movie");
                        } else {
                                return new SimpleStringProperty("");
                        }
                });
                invoice_Tableview_Product_Genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
                invoice_Tableview_Product_devloper_artist_director.setCellValueFactory(cellData -> {
                        String developerArtistDirector;
                        if (cellData.getValue() instanceof Game) {
                                developerArtistDirector = ((Game) cellData.getValue()).getDeveloper();
                        } else if (cellData.getValue() instanceof Music) {
                                developerArtistDirector = ((Music) cellData.getValue()).getArtistName();
                        } else if (cellData.getValue() instanceof Movie) {
                                developerArtistDirector = ((Movie) cellData.getValue()).getDirector();
                        } else {
                                developerArtistDirector = "";
                        }

                        return new SimpleStringProperty(developerArtistDirector);
                });

                invoice_Tableview_Product_name.setCellValueFactory(cellData -> {
                        String productname;
                        if (cellData.getValue() instanceof Game) {
                                productname = ((Game) cellData.getValue()).getName();
                        } else if (cellData.getValue() instanceof Music) {
                                productname = ((Music) cellData.getValue()).getName();
                        } else if (cellData.getValue() instanceof Movie) {
                                productname = ((Movie) cellData.getValue()).getName();
                        } else {
                                productname = "";
                        }
                        return new SimpleStringProperty(productname);
                });

                invoice_Tableview_Product_price_withQuantity.setCellValueFactory(cellData -> {
                        StockableProduct product = cellData.getValue();
                        int quantity = productQuantity.getValue();

                        if (quantity > 0) {
                                double totalPrice = product.getPrice() * quantity;
                                return new SimpleDoubleProperty(totalPrice).asObject();
                        } else {
                                return new SimpleDoubleProperty(0.0).asObject();
                        }
                });

                invoiceTableView.setItems(invoiceProducts);

        }

        @FXML
        public void load_data_in_table(ActionEvent event) {
                try {
                        String selectedCategory = (String) choseProduct_type.getValue();
                        String productName = Product_name.getText();
                        int productId = Integer.parseInt(product_id.getText());
                        StockableProduct producttableview = null;
                        Product product=null;

                        String fileName = null;
                        if (selectedCategory.equals("Game")) {
                                fileName = "Game.txt";
                        } else if (selectedCategory.equals("Movie")) {
                                fileName = "Movie.txt";
                        } else if (selectedCategory.equals("Music")) {
                                fileName = "Music.txt";
                        }

                        File file = new File(fileName);
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line;
                        boolean found=false;

                        while ((line = br.readLine()) != null) {
                                if (line.startsWith("Name: ")) {
                                        String currentProductName = line.substring("Name: ".length());
                                        int currentProductId = Integer.parseInt(br.readLine().substring("Product Id: ".length()));

                                        if (currentProductId == productId && currentProductName.equals(productName)) {
                                                found=true;
                                                String genre = null;
                                                double price = 0.0;
                                                int yearOfPublish = 0;
                                                double discount = 0.0;
                                                int stocks = 0;
                                                String developerArtistDirector = "null";

                                                while ((line = br.readLine()) != null) {
                                                        if (line.startsWith("Genre: ")) {
                                                                genre = line.substring("Genre: ".length());
                                                        } else if (line.startsWith("Price: ")) {
                                                                price = Double.parseDouble(line.substring("Price: ".length()));
                                                        } else if (line.startsWith("Year of Publish: ")) {
                                                                yearOfPublish = Integer.parseInt(line.substring("Year of Publish: ".length()));
                                                        } else if (line.startsWith("Discount: ")) {
                                                                discount = Double.parseDouble(line.substring("Discount: ".length()));
                                                        } else if (line.startsWith("Stocks: ")) {
                                                                stocks = Integer.parseInt(line.substring("Stocks: ".length()));
                                                        } else if (line.startsWith("Developer: ")) {
                                                                developerArtistDirector = line.substring("Developer:".length());
                                                        }else if (line.startsWith("Artist: ")) {
                                                                developerArtistDirector = line.substring("Artist: ".length());
                                                        } else if (line.startsWith("Director: ")) {
                                                                developerArtistDirector = line.substring("Director: ".length());
                                                        } else if (line.startsWith("Name: ")) {
                                                                break;
                                                        }
                                                }

                                                // Create an instance of the appropriate product type based on selectedCategory
                                                if (selectedCategory.equals("Game")) {
                                                        product = new Game(productName,productId, price, genre, yearOfPublish, discount, stocks, developerArtistDirector);
                                                        producttableview=new Game(product.getName(),product.getProductId(),product.getPrice(),product.getGenre(),product.getYearPublished(),product.getDiscount(),product.getNumberOfItemsStocked(),product.getDeveloper());
                                                        invoiceProducts.add(producttableview);

                                                } else if (selectedCategory.equals("Movie")) {
                                                        product = new Movie(productName,productId, price, genre, yearOfPublish, discount, stocks, developerArtistDirector);
                                                        producttableview=new Movie(product.getName(),product.getProductId(),product.getPrice(),product.getGenre(),product.getYearPublished(),product.getDiscount(),product.getNumberOfItemsStocked(),product.getDirector());
                                                        invoiceProducts.add(producttableview);
                                                } else if (selectedCategory.equals("Music")) {
                                                        product = new Music(productName, productId,price, genre, yearOfPublish, discount, stocks, developerArtistDirector);
                                                        producttableview=new Movie(product.getName(),product.getProductId(),product.getPrice(),product.getGenre(),product.getYearPublished(),product.getDiscount(),product.getNumberOfItemsStocked(),product.getArtistName());
                                                        invoiceProducts.add(producttableview);
                                                }

                                                if(product!=null){
                                                        try {
                                                                currentInvoice.addProduct(product);
                                                                System.out.println("product added in invoice");
                                                        }
                                                        catch (Exception e){
                                                                System.out.println(e);
                                                        }
                                                }
                                                break; // Exit the loop since the product has been found
                                        }
                                }
                        }
                        br.close();
                        System.out.println(currentInvoice.calculatePriceWithoutDiscount());
                        TotalPriceOFOrder.setText(" " + currentInvoice.calculatePriceWithoutDiscount());
                        System.out.println(currentInvoice.getLocalDateTime());
                        System.out.println(currentInvoice.getInvoice());
                } catch (Exception e) {
                        System.out.println(e);
                        e.printStackTrace();
                }
        }



        public void logout_From_inventory_toHome() {
                try {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Are You Sure You Want To Logout?");
                        Optional<ButtonType> option = alert.showAndWait();

                        if (option.isPresent() && option.get() == ButtonType.OK) {
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
                                Parent root = fxmlLoader.load();
                                Scene scene = new Scene(root, 600, 450);
                                Stage currentStage = (Stage) back_to_home.getScene().getWindow();
                                currentStage.setScene(scene);
                                currentStage.show();
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}
