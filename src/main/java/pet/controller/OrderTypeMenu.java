package pet.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pet.App;
import pet.entity.*;
import pet.service.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderTypeMenu {
    private OrderTypeService orderTypeService = new OrderTypeService();
    @FXML
    private TableView<OrderType> tableView;
    @FXML
    private TableColumn<OrderType,Integer> number;
    @FXML
    private TableColumn<OrderType,String> description;
    @FXML
    Button add;
    @FXML
    Button edit;
    @FXML
    Button back;

    private List<OrderType> orderTypeList = new ArrayList<>();
    private ObservableList<OrderType> orderTypeObservable;

    @FXML
    public void initialize() {
        fillTables();
    }

    @FXML
    public void fillTables(){
        number.setCellValueFactory(new PropertyValueFactory<>("id"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        try {
            orderTypeList = orderTypeService.getAll();
        } catch (SQLException throwables) {
        }
        orderTypeObservable = FXCollections.observableArrayList(orderTypeList);
        tableView.setItems(orderTypeObservable);
    }

    @FXML public void setBack(){
        App.mainStage.close();
    }

    @FXML
    public void createOrderType() throws SQLException, IOException {
        OrderType orderType = new OrderType();
        orderType.setId(orderTypeList.size()+1);
        boolean isButtonClicked = getOrderTypeFromForm(orderType);
        if (isButtonClicked){
            orderTypeService.add(orderType);
            fillTables();
        }
    }
    @FXML
    public void editOrderType() throws SQLException, IOException {
        OrderType orderType = tableView.getSelectionModel().getSelectedItem();
        if (orderType != null){
            boolean isButtonClicked = getOrderTypeFromForm(orderType);
            if (isButtonClicked){
                orderTypeService.update(orderType);
                fillTables();
            }
        }else {
            System.out.println("Select the OrderType");
        }
    }

    public boolean getOrderTypeFromForm(OrderType orderType) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addordertype.fxml"));
        AnchorPane page = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(page);
        stage.setScene(scene);
        AddOrderType orderTypeSave = loader.getController();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        orderTypeSave.setStage(stage);
        orderTypeSave.setOrderType(orderType);
        stage.showAndWait();
        return orderTypeSave.isButtonClicked();
    }
}
