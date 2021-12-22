package pet.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.controlsfx.control.textfield.TextFields;
import pet.entity.DailyProduction;
import pet.entity.Machine;
import pet.entity.Orders;
import pet.service.MachineService;
import pet.service.OrderService;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class AddDailyProduction {
    private Stage stage;
    private Orders order;
    private Machine machine;
    private Date date;
    private DailyProduction dailyProduction;
    private boolean buttonClicked = false;
    private final OrderService orderService = new OrderService();
    private final MachineService machineService = new MachineService();


    public void setDate(){
        LocalDate localDate = datePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        date = Date.from(instant);
    }
    public void setOrder(){ order = (Orders) orderCombo.getSelectionModel().getSelectedItem();}

    public void setMachine(){ machine = (Machine) machineCombo.getSelectionModel().getSelectedItem(); }

    public void handleConfirmButton(){
        try{
            LocalDate localDate = datePicker.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date data = Date.from(instant);

            dailyProduction.setDate(data);
            dailyProduction.setOrder(order);
            this.order.setMachine(machine);

            dailyProduction.setSpeed(Integer.parseInt(speed.getText()));
            dailyProduction.setWorkedSteps(Integer.parseInt(workedSteps.getText()));
            dailyProduction.setWorkedm2(Integer.parseInt(workedm2.getText()));
            dailyProduction.setNorma(Integer.parseInt(norma.getText()));
            dailyProduction.setPercentage(Integer.parseInt(percentage.getText()));
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Errorcha Qaysidir qatorda harf yoki belgi bor");
            alert.show();
        }
        buttonClicked = true;
        stage.close();
    }

    public void setDailyProduction(DailyProduction dailyProduction) {
        this.dailyProduction = dailyProduction;

        if (dailyProduction.getDate() != null){
            Instant instant = dailyProduction.getDate().toInstant();
            LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            this.datePicker.setValue(localDate);
        }
        if (dailyProduction.getOrder() != null){
            this.order = dailyProduction.getOrder();
            this.machine = this.order.getMachine();
            dailyProduction.setMachine(String.valueOf(this.machine));
            orderCombo.getSelectionModel().select(order);
            machineCombo.getSelectionModel().select(machine);
        }
        this.speed.setText(String.valueOf(dailyProduction.getSpeed()));
        this.workedSteps.setText(String.valueOf(dailyProduction.getWorkedSteps()));
        this.workedm2.setText(String.valueOf(dailyProduction.getWorkedm2()));
        this.norma.setText(String.valueOf(dailyProduction.getNorma()));
        this.percentage.setText(String.valueOf(dailyProduction.getPercentage()));
    }

    public void fillCombos() throws SQLException {
        orderObservableList.addAll(orderService.getAll());
        orderCombo.setItems(orderObservableList);
        orderCombo.setEditable(true);
        TextFields.bindAutoCompletion(orderCombo.getEditor(), orderCombo.getItems());
        machineObservableList.addAll(machineService.getAll());
        machineCombo.setItems(machineObservableList);
    }
    public void initialize() throws SQLException { fillCombos(); }

    public void handleCancelButton(){ stage.close(); }
    @FXML
    private List<Orders> orderList,machineList;
    @FXML
    private ObservableList<Orders> orderObservableList = FXCollections.observableList(new ArrayList<>());
    @FXML
    private ObservableList<Machine> machineObservableList = FXCollections.observableList(new ArrayList<>());
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox orderCombo;
    @FXML
    private ComboBox machineCombo;
    @FXML
    private Label speedLine;
    @FXML
    private Label workedStepsLine;
    @FXML
    private Label workedm2Line;
    @FXML
    private Label normLineLine;
    @FXML
    private Label percentageLine;
    @FXML
    private TextField speed;
    @FXML
    private TextField workedSteps;
    @FXML
    private TextField workedm2;
    @FXML
    private TextField norma;
    @FXML
    private TextField percentage;
    @FXML
    private Button save;
    @FXML
    private Button back;

}
