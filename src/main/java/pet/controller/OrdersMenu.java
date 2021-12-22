package pet.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pet.App;
import pet.entity.Orders;
import pet.service.OrderService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersMenu {
    private OrderService orderService = new OrderService();
    @FXML
    private TableView<Orders> tableView;
    @FXML
    private TableColumn<Orders, Date> date;
    @FXML
    private TableColumn<Orders,Integer> m2totalline;
    @FXML
    private TableColumn<Orders,Integer> m2forworkline;
    @FXML
    private TableColumn<Orders,Integer> atkline;
    @FXML
    private TableColumn<Orders,Integer> m2weavedline;
    @FXML
    private TableColumn<Orders,Integer> m2leftline;
    @FXML
    private TableColumn<Orders,Integer> daysworkline;
    @FXML
    private TableColumn<Orders,Integer> ordernumberline;
    @FXML
    private TableColumn<Orders,Integer> stocknumberline;
    @FXML
    private TableColumn<Orders,String> willweaveline;

    @FXML
    private TableColumn<Orders,String> clientRow;
    @FXML
    private TableColumn<Orders,String> machineRow;
    @FXML
    private TableColumn<Orders,String> orderTypeRow;
    @FXML
    private TableColumn<Orders,String> qualityRow;
    @FXML
    private TableColumn<Orders,String> qualityTypeRow;

    @FXML
    Button add;
    @FXML
    Button edit;
    @FXML
    Button back;

    private List<Orders> orderList = new ArrayList<>();
    private ObservableList<Orders> orderObservable;

    @FXML
    public void createOrders() throws SQLException, IOException {
        Orders order = new Orders();
        order.setId(orderList.size()+1);
        boolean isButtonClicked = getOrdersFromForm(order);
        if (isButtonClicked){
            orderService.add(order);
            fillTables();
        }
    }
    @FXML
    public void editOrders() throws SQLException, IOException {
        Orders order = tableView.getSelectionModel().getSelectedItem();
        if (order != null){
            boolean isButtonClicked = getOrdersFromForm(order);
            if (isButtonClicked){
                orderService.update(order);
                fillTables();
            }
        }else {
            System.out.println("Select the Orders");
        }
    }

    public boolean getOrdersFromForm(Orders order) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addorder.fxml"));
        AnchorPane page = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(page);
        stage.setScene(scene);
        AddOrders orderSave = loader.getController();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        orderSave.setStage(stage);
        orderSave.setOrders(order);
        stage.showAndWait();
        return orderSave.isButtonClicked();
    }

    @FXML
    public void initialize() {
        fillTables();
    }
    @FXML
    public void fillTables(){
        m2totalline.setCellValueFactory(new PropertyValueFactory<>("m2Total"));
        m2forworkline.setCellValueFactory(new PropertyValueFactory<>("m2ForWork"));
        atkline.setCellValueFactory(new PropertyValueFactory<>("atk"));
        m2weavedline.setCellValueFactory(new PropertyValueFactory<>("m2Weaved"));
        m2leftline.setCellValueFactory(new PropertyValueFactory<>("m2Left"));
        daysworkline.setCellValueFactory(new PropertyValueFactory<>("daysWork"));
        ordernumberline.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        stocknumberline.setCellValueFactory(new PropertyValueFactory<>("stockNumber"));
        willweaveline.setCellValueFactory(new PropertyValueFactory<>("willWeave"));


        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        clientRow.setCellValueFactory(new PropertyValueFactory<>("client"));
        machineRow.setCellValueFactory(new PropertyValueFactory<>("machine"));
        orderTypeRow.setCellValueFactory(new PropertyValueFactory<>("orderType"));
        qualityRow.setCellValueFactory(new PropertyValueFactory<>("quality"));
        qualityTypeRow.setCellValueFactory(new PropertyValueFactory<>("qualityType"));
        try {
            orderList = orderService.getAll();
        } catch (SQLException throwables) {
        }
        orderObservable = FXCollections.observableArrayList(orderList);
        tableView.setItems(orderObservable);
    }
    @FXML
    public void setBack(){
        App.mainStage.close();
    }
}
