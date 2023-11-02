package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class inventoryInfoController {
    @FXML
    private Button Admin_logoutButton;

    @FXML
    private RadioButton Movie_radio_button;

    @FXML
    private RadioButton MusicRadio_Button;

    @FXML
    private TableColumn<?, ?> Product_Table_col_NumOfItemStocked;

    @FXML
    private TableColumn<?, ?> Product_Table_col_devloper_artist_director;

    @FXML
    private TableColumn<?, ?> Product_Table_col_discount;

    @FXML
    private TableColumn<?, ?> Product_Table_col_genre;

    @FXML
    private TableColumn<?, ?> Product_Table_col_id;

    @FXML
    private TableColumn<?, ?> Product_Table_col_price;

    @FXML
    private TableView<?> ShowallProduct_TableView;

    @FXML
    private Label adminUserName;

    @FXML
    private ImageView back_to_home;

    @FXML
    private Label changetextAfterClikingRadioButton;

    @FXML
    private RadioButton game_radio_button;

    @FXML
    private Button moveToDashHome_button;

    @FXML
    private TableColumn<?, ?> product_Table_col_Productname;

    @FXML
    private ToggleGroup radio_button;

    @FXML
    private Button user_Sceren_for_order;



}
