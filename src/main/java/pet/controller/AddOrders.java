package pet.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.*;
import org.controlsfx.control.textfield.TextFields;
import pet.entity.*;
import pet.service.*;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Getter
@Setter
public class AddOrders {
    private Stage stage;
    private Date date;
    private Orders orders;
    private Client client;
    private Machine machine;
    private OrderType orderType;
    private Quality quality;
    private QualityType qualityType;
    private boolean buttonClicked = false;
    private final ClientService clientService = new ClientService();
    private final MachineService machineService = new MachineService();
    private final OrderTypeService orderTypeService = new OrderTypeService();
    private final QualityService qualityService = new QualityService();
    private final QualityTypeService qualityTypeService = new QualityTypeService();

    @FXML
    public void handleConfirmButton(){
        try{
            orders.setM2Total(Integer.parseInt(m2Total.getText()));
            orders.setM2ForWork(Integer.parseInt(m2ForWork.getText()));
            orders.setAtk(Integer.parseInt(atk.getText()));
            orders.setM2Weaved(Integer.parseInt(m2Weaved.getText()));
            orders.setM2Left(Integer.parseInt(m2Left.getText()));
            orders.setDaysWork(Integer.parseInt(daysWork.getText()));
            orders.setOrderNumber(Integer.parseInt(orderNumber.getText()));
            orders.setStockNumber(Integer.parseInt(stockNumber.getText()));
            orders.setWillWeave(willWeave.isSelected() ? "HA" : "YO'Q");

            orders.setDate(date);
            orders.setClient(client);
            orders.setMachine(machine);
            orders.setOrderType(orderType);
            orders.setQuality(quality);
            orders.setQualityType(qualityType);
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Errorcha Qaysidir qatorda harf yoki belgi bor");
            alert.show();
        }
        buttonClicked = true;
        stage.close();
    }
    public void setDate(){
        LocalDate localDate = datePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        date = Date.from(instant);
    }

    public void setClient(){
        client = (Client) clientCombo.getSelectionModel().getSelectedItem();
    }
    public void setMachine(){ machine = (Machine) machineCombo.getSelectionModel().getSelectedItem();}
    public void setOrderType(){ orderType = (OrderType) ordertypeCombo.getSelectionModel().getSelectedItem();}
    public void setQuality(){ quality = (Quality) qualityCombo.getSelectionModel().getSelectedItem(); }
    public void setQualityType(){ qualityType = (QualityType) qualityTypeCombo.getSelectionModel().getSelectedItem(); }

    @FXML
    public void handleCancelButton(){
        stage.close();
    }

    public void setOrders(Orders orders) {
        this.orders = orders;

        this.m2Total.setText(String.valueOf(orders.getM2Total()));
        this.m2ForWork.setText(String.valueOf(orders.getM2ForWork()));
        this.atk.setText(String.valueOf(orders.getAtk()));
        this.m2Weaved.setText(String.valueOf(orders.getM2Weaved()));
        this.m2Left.setText(String.valueOf(orders.getM2Left()));
        this.daysWork.setText(String.valueOf(orders.getDaysWork()));
        this.orderNumber.setText(String.valueOf(orders.getStockNumber()));
        this.stockNumber.setText(String.valueOf(orders.getOrderNumber()));
        this.willWeave.setSelected(orders.getWillWeave() != null && orders.getWillWeave().equals("HA"));

        if (orders.getDate() != null){
            Instant instant = orders.getDate().toInstant();
            LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            this.datePicker.setValue(localDate);
        }
        this.client =       orders.getClient() == null ? new Client() : orders.getClient();
        this.machine =      orders.getMachine() == null ? new Machine() : orders.getMachine();
        this.orderType =    orders.getOrderType() == null ? new OrderType() : orders.getOrderType();
        this.quality =      orders.getQuality() == null ? new Quality() : orders.getQuality();
        this.qualityType =  orders.getQualityType() == null ? new QualityType() : orders.getQualityType();

        clientCombo.getSelectionModel().select(client);
        machineCombo.getSelectionModel().select(machine);
        ordertypeCombo.getSelectionModel().select(orderType);
        qualityCombo.getSelectionModel().select(quality);
        qualityTypeCombo.getSelectionModel().select(qualityType);
    }



    @FXML
    public void initialize() throws SQLException {
        fillClients();
        fillMachine();
        fillOrderType();
        fillQuality();
        fillQualityType();
    }
    public void fillClients () throws SQLException {
        clientObservableList.addAll(clientService.getAll());
        clientCombo.setItems(clientObservableList);
        clientCombo.setEditable(true);
        TextFields.bindAutoCompletion(clientCombo.getEditor(), clientCombo.getItems());
    }
    public void fillMachine () throws SQLException {
        machineObservableList.addAll(machineService.getAll());
        machineCombo.setItems(machineObservableList);
//        machineCombo.setEditable(true);
//        TextFields.bindAutoCompletion(machineCombo.getEditor(), machineCombo.getItems());
    }
    public void fillOrderType () throws SQLException {
        orderTypeObservableList.addAll(orderTypeService.getAll());
        ordertypeCombo.setItems(orderTypeObservableList);
//        ordertypeCombo.setEditable(true);
//        TextFields.bindAutoCompletion(ordertypeCombo.getEditor(), ordertypeCombo.getItems());
    }
    public void fillQuality () throws SQLException {
        qualityObservableList.addAll(qualityService.getAll());
        qualityCombo.setItems(qualityObservableList);
        qualityCombo.setEditable(true);
        TextFields.bindAutoCompletion(qualityCombo.getEditor(), qualityCombo.getItems());
    }
    public void fillQualityType () throws SQLException {
        qualityTypeObservableList.addAll(qualityTypeService.getAll());
        qualityTypeCombo.setItems(qualityTypeObservableList);
//        qualityTypeCombo.setEditable(true);
//        TextFields.bindAutoCompletion(qualityTypeCombo.getEditor(), qualityTypeCombo.getItems());
    }
    @FXML
    private List<Client> clientList;
    @FXML
    private List<Machine> machineList;
    @FXML
    private List<OrderType> orderTypeList;
    @FXML
    private List<Quality> qualityList;
    @FXML
    private List<QualityType> qualityTypeList;
    @FXML
    private ObservableList<Client> clientObservableList = FXCollections.observableList(new ArrayList<>());
    @FXML
    private ObservableList<Machine> machineObservableList = FXCollections.observableList(new ArrayList<>());
    @FXML
    private ObservableList<OrderType> orderTypeObservableList = FXCollections.observableList(new ArrayList<>());
    @FXML
    private ObservableList<Quality> qualityObservableList = FXCollections.observableList(new ArrayList<>());
    @FXML
    private ObservableList<QualityType> qualityTypeObservableList = FXCollections.observableList(new ArrayList<>());
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox clientCombo,machineCombo,ordertypeCombo,qualityCombo,qualityTypeCombo;
    @FXML
    private Label m2TotalLine,m2ForWorkLine,atkLine,m2WeavedLine,m2LeftLine,daysWorkLine,orderNumberLine,stockNumberLine,willWeaveLine;
    @FXML
    private TextField m2Total,m2ForWork,atk,m2Weaved,m2Left,daysWork,orderNumber,stockNumber;
    @FXML
    private CheckBox willWeave;
    @FXML
    private Button save,back;
}
