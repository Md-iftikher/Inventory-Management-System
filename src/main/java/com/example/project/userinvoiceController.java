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
import java.io.IOException;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.apache.pdfbox.pdmodel.font.PDType1Font.*;

public class userinvoiceController {
        @FXML
        private AnchorPane userDashboardView2;
        @FXML
        private AnchorPane userDashboardView1;
        @FXML
        private AnchorPane invoiceviewinthescreen;
        @FXML
        private Button moveToDashHome_button;

        @FXML
        private Label invoicediscountPrice;

        @FXML
        private Label invoiceToatalPrice;

        @FXML
        private Label priceOfproduct1;

        @FXML
        private Label priceOfproduct2;

        @FXML
        private Label priceOfproduct3;

        @FXML
        private Label priceOfproduct4;

        @FXML
        private Label priceOfproduct5;

        @FXML
        private Label priceOfproduct6;

        @FXML
        private Label priceOfproduct7;

        @FXML
        private Label produc1;

        @FXML
        private Label produc2;

        @FXML
        private Label produc3;

        @FXML
        private Label produc4;

        @FXML
        private Label produc5;

        @FXML
        private Label produc6;

        @FXML
        private Label produc7;
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
        private TableColumn<StockableProduct, Double> invoice_Tableview_Product_Discount;

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
        private Button generateInvoiceButton;
        @FXML
        private Label priceAfterDiscount;
        @FXML
        private Label ProductNotFoundError;

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
                spiner=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1,0);
                productQuantity.setValueFactory(spiner);
        }
        public void ordershowspinervalue(){
                int qutity=productQuantity.getValue();
        }


        @FXML
        private ObservableList<StockableProduct> invoiceProducts = FXCollections.observableArrayList();
        @FXML
        public void addproductshowlistdata() {

                invoice_Tableview_Product_Discount.setCellValueFactory(cellData -> {
                        if (cellData.getValue() instanceof StockableProduct) {
                                double discount = ((StockableProduct) cellData.getValue()).getDiscount();
                                return new SimpleDoubleProperty(discount).asObject();
                        } else {
                                return new SimpleDoubleProperty(0.0).asObject();
                        }
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
                invoice_Tableview_Product_Genre.setCellValueFactory(cellData->{
                        String Genre;
                        if(cellData.getValue() instanceof Game){
                                Genre=((Game) cellData.getValue()).getGenre();
                        }
                        else if(cellData.getValue() instanceof Music){
                                Genre=((Music) cellData.getValue()).getGenre();
                        }
                        else if(cellData.getValue() instanceof Movie){
                                Genre=((Movie) cellData.getValue()).getGenre();
                        }
                        else {
                                Genre="";
                        }
                        return new SimpleStringProperty(Genre);
                });
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
                                                        }
                                                        catch (Exception e){
                                                                System.out.println(e);
                                                        }
                                                }
                                                break;
                                        }
                                }
                        }
                        br.close();
                        if(!found){
                                ProductNotFoundError.setText("Product Not Found.Please Give Correct Information.");
                        }
                        else {
                                addproductshowlistdata();
                                TotalPriceOFOrder.setText("$ " + currentInvoice.calculatePriceWithoutDiscount());
                                priceAfterDiscount.setText("$ "+currentInvoice.calculateDiscountedPrice());
                                ProductNotFoundError.setText("");
                                updateAfteraddingStockInFile(fileName,productName,-1); //stock is reduing by 1.
                        }
                } catch (Exception e) {
                        System.out.println(e);
                        e.printStackTrace();
                }
        }
        public void reset(){

                Product_name.clear();
                product_id.clear();
                TotalPriceOFOrder.setText("$");
                priceAfterDiscount.setText("$");
                PaymentAmount.clear();
                invoiceProducts.clear();
                currentInvoice.resetInvoice();

        }
        @FXML
        private void removeProductFromList() {
                StockableProduct selectedProduct = invoiceTableView.getSelectionModel().getSelectedItem();
                String Product_name=null; String fileName=null;
                if (selectedProduct != null) {
                        Product remoedproduct=null;
                        invoiceTableView.getItems().remove(selectedProduct);
                        invoiceProducts.remove(selectedProduct);
                        if (selectedProduct instanceof Game) {
                                remoedproduct=new Game(selectedProduct.getName(),selectedProduct.getProductId(),selectedProduct.getPrice(),selectedProduct.getGenre(),selectedProduct.getYearPublished(),selectedProduct.getDiscount(),selectedProduct.getNumberOfItemsStocked(),selectedProduct.getDeveloper());
                                 Product_name = remoedproduct.getName();
                                 fileName = "Game.txt";
                        } else if (selectedProduct instanceof Music) {
                                 remoedproduct=new Music(selectedProduct.getName(),selectedProduct.getProductId(),selectedProduct.getPrice(),selectedProduct.getGenre(),selectedProduct.getYearPublished(),selectedProduct.getDiscount(),selectedProduct.getNumberOfItemsStocked(),selectedProduct.getArtistName());
                                Product_name = remoedproduct.getName();
                                fileName = "Music.txt";
                        } else if (selectedProduct instanceof Movie) {
                                remoedproduct=new Movie(selectedProduct.getName(),selectedProduct.getProductId(),selectedProduct.getPrice(),selectedProduct.getGenre(),selectedProduct.getYearPublished(),selectedProduct.getDiscount(),selectedProduct.getNumberOfItemsStocked(),selectedProduct.getDirector());
                                Product_name = remoedproduct.getName();
                                fileName = "Movie.txt";
                        }
                        if(remoedproduct!=null) {
                                currentInvoice.removeProduct(remoedproduct);
                                if(Product_name!=null && fileName!=null) {
                                        updateAfteraddingStockInFile(fileName, Product_name, 1);
                                }
                                else{
                                        System.err.println("file name is null.");
                                }
                                TotalPriceOFOrder.setText("$ " + currentInvoice.calculatePriceWithoutDiscount());
                                priceAfterDiscount.setText("$ "+currentInvoice.calculateDiscountedPrice());
                        }
                        else{
                                System.out.println("remove product is null");
                        }
                } else {
                       System.out.println("No product selected for removal.");
                }
        }
        @FXML
        public void updateAfteraddingStockInFile(String fileName, String productName,int num) {
                 int  addOrRemove=num;
                ArrayList<String> lines = new ArrayList();
                try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                        String line;
                        String currentProductName = null;

                        while ((line = br.readLine()) != null) {
                                if (line.startsWith("Name: ")) {
                                        currentProductName = line.substring("Name: ".length());
                                }
                                if (line.startsWith("No of Items Stocked: ") && currentProductName != null && currentProductName.equals(productName)) {
                                        int currentStock = Integer.parseInt(line.substring("No of Items Stocked: ".length()));

                                        if (currentStock > 0) {
                                                currentStock=currentStock+addOrRemove;
                                        }

                                        line = "No of Items Stocked: " + currentStock;
                                        currentProductName = null;
                                }
                                lines.add(line);
                        }
                } catch (IOException e) {
                        System.err.println("Error updating stock count in the file: " + e.getMessage());
                        return;
                }
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                        for (String updatedLine : lines) {
                                bw.write(updatedLine);
                                bw.newLine();
                        }
                        System.out.println("Stock count updated successfully.");
                } catch (IOException e) {
                        System.err.println("Error writing to the file: " + e.getMessage());
                }
        }


        @FXML
        public void printInvoice() throws NoSuchFieldException, IllegalAccessException {
                System.out.println(currentInvoice.calculatePriceWithoutDiscount());
                System.out.println(currentInvoice.calculateDiscountedPrice());
                System.out.println(currentInvoice.getInvoice());
                saveTotalsToFile();
                ArrayList<Product> itemsList = currentInvoice.getItems();
                int size = itemsList.size();

                for (int i = 0; i < size; i++) {
                        Product product = itemsList.get(i);
                        Label nameLabel = (Label) getClass().getDeclaredField("produc" + (i + 1)).get(this);
                        Label priceLabel = (Label) getClass().getDeclaredField("priceOfproduct" + (i + 1)).get(this);

                        if (nameLabel != null && priceLabel != null) {
                                nameLabel.setText(product.getName());
                                priceLabel.setText("$ "+product.getPrice());
                                invoiceToatalPrice.setText("Total Price: $ "+currentInvoice.calculatePriceWithoutDiscount());
                                invoicediscountPrice.setText("Discounted Price: $ "+currentInvoice.calculateDiscountedPrice());

                        }
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
        @FXML
        private TotalTracker totalTracker = new TotalTracker();
        @FXML
        public void saveTotalsToFile() {
                totalTracker.addToTotalIncome(currentInvoice.calculateDiscountedPrice());
                totalTracker.addToTotalProductsSold(invoiceProducts.size());
                ArrayList<String> lines = new ArrayList<>();

                try (BufferedReader reader = new BufferedReader(new FileReader("totals.txt"))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                                lines.add(line);
                        }
                } catch (IOException e) {
                        System.out.println(e);
                }

                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dateFormat.format(currentDate);

                int dateLineIndex = -1;
                for (int i = 0; i < lines.size(); i++) {
                        if (lines.get(i).startsWith("Date: " + formattedDate)) {
                                dateLineIndex = i;
                                break;
                        }
                }

                if (dateLineIndex != -1) {
                        if (dateLineIndex + 2 < lines.size()) {
                                lines.set(dateLineIndex + 1, "Total Income: " + totalTracker.getTotalIncome());
                                lines.set(dateLineIndex + 2, "Total Products Sold: " + totalTracker.getTotalProductsSold());
                        } else {
                                System.err.println("Invalid file format - Missing data lines.");
                        }
                } else {
                        lines.add("Date: " + formattedDate);
                        lines.add("Total Income: " + totalTracker.getTotalIncome());
                        lines.add("Total Products Sold: " + totalTracker.getTotalProductsSold());
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("totals.txt"))) {
                        for (String line : lines) {
                                writer.write(line);
                                writer.newLine();
                        }
                        System.out.println(" saved to file.");
                } catch (IOException e) {
                        System.out.println(e);
                }
        }
        public void SwitchForm(ActionEvent event) throws NoSuchFieldException, IllegalAccessException {   //for swinting scene .
                if (event.getSource() == moveToDashHome_button) {
                        userDashboardView2.setVisible(true);
                        userDashboardView1.setVisible(true);
                        invoiceviewinthescreen.setVisible(false);
                        reset();

                } else if (event.getSource() == PaymentButton) {
                        invoiceviewinthescreen.setVisible(true);
                        userDashboardView2.setVisible(false);
                        userDashboardView1.setVisible(false);
                        printInvoice();
                }
        }
        @FXML
        public void generateInvoice() {


        }




}
