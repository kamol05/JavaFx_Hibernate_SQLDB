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
import pet.entity.DailyProduction;
import pet.entity.Machine;
import pet.entity.Orders;
import pet.service.DailyProductionService;
import pet.service.OrderService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyProductionMenu {
    private DailyProductionService dailyProductionService = new DailyProductionService();
    private OrderService orderService = new OrderService();
    @FXML
    private TableView<DailyProduction> tableView;
    @FXML
    private TableColumn<DailyProduction, Date> date;
    @FXML
    private TableColumn<DailyProduction,Integer> speed;
    @FXML
    private TableColumn<DailyProduction,Integer> workedSteps;
    @FXML
    private TableColumn<DailyProduction,Integer> workedm2;
    @FXML
    private TableColumn<DailyProduction,Integer> norma;
    @FXML
    private TableColumn<DailyProduction,Integer> percentage;
    @FXML
    private TableColumn<DailyProduction,String> order;
    @FXML
    private TableColumn<DailyProduction,String> machine;
    @FXML
    Button add;
    @FXML
    Button edit;
    @FXML
    Button back;

    private List<DailyProduction> dailyProductionList = new ArrayList<>();
    private ObservableList<DailyProduction> dailyProductionObservable;

    @FXML
    public void initialize() {
        fillTables();
    }

    @FXML
    public void fillTables(){
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        speed.setCellValueFactory(new PropertyValueFactory<>("speed"));
        workedSteps.setCellValueFactory(new PropertyValueFactory<>("workedSteps"));
        workedm2.setCellValueFactory(new PropertyValueFactory<>("workedm2"));
        norma.setCellValueFactory(new PropertyValueFactory<>("norma"));
        percentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));

        order.setCellValueFactory(new PropertyValueFactory<>("order"));
        machine.setCellValueFactory(new PropertyValueFactory<>("machine"));
        try {
            dailyProductionList = dailyProductionService.getAll();
        } catch (SQLException throwables) {
        }
        dailyProductionObservable = FXCollections.observableArrayList(dailyProductionList);
        tableView.setItems(dailyProductionObservable);
    }

    @FXML public void setBack(){
        App.mainStage.close();
    }

    @FXML
    public void createDailyProduction() throws SQLException, IOException {
        DailyProduction dailyProduction = new DailyProduction();
        dailyProduction.setId(dailyProductionList.size()+1);
        boolean isButtonClicked = getDailyProductionFromForm(dailyProduction);
        if (isButtonClicked){
            dailyProductionService.add(dailyProduction);
            orderService.update(dailyProduction.getOrder());
            fillTables();
        }
    }
    @FXML
    public void editDailyProduction() throws SQLException, IOException {
        DailyProduction dailyProduction = tableView.getSelectionModel().getSelectedItem();
        if (dailyProduction != null){
            boolean isButtonClicked = getDailyProductionFromForm(dailyProduction);
            if (isButtonClicked){
                dailyProductionService.update(dailyProduction);
                orderService.update(dailyProduction.getOrder());
                fillTables();
            }
        }else {
            System.out.println("Select the DailyProduction");
        }
    }

    public boolean getDailyProductionFromForm(DailyProduction dailyProduction) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("adddailyproduction.fxml"));
        AnchorPane page = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(page);
        stage.setScene(scene);
        AddDailyProduction dailyProductionSave = loader.getController();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        dailyProductionSave.setStage(stage);
        dailyProductionSave.setDailyProduction(dailyProduction);
        stage.showAndWait();
        return dailyProductionSave.isButtonClicked();
    }
}
