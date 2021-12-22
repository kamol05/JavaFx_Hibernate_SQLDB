package pet.controller;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import pet.App;
import pet.entity.Machine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pet.service.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineMenu {
    private MachineService machineService = new MachineService();
    @FXML
    private TableView<Machine> tableView;
    @FXML
    private TableColumn<Machine,Integer> dentities;
    @FXML
    private TableColumn<Machine,Integer> number;
    @FXML
    private TableColumn<Machine,Integer> speed;
    @FXML
    private TableColumn<Machine,Integer> width;
    @FXML
    Button add;
    @FXML
    Button edit;
    @FXML
    Button back;

    private List<Machine> machineList = new ArrayList<>();
    private ObservableList<Machine> machineObservable;

    @FXML
    public void initialize() {
        fillTables();
    }

    @FXML
    public void fillTables(){
        number.setCellValueFactory(new PropertyValueFactory<>("id"));
        dentities.setCellValueFactory(new PropertyValueFactory<>("dentities"));
        speed.setCellValueFactory(new PropertyValueFactory<>("speed"));
        width.setCellValueFactory(new PropertyValueFactory<>("width"));
        try {
            machineList = machineService.getAll();
        } catch (SQLException throwables) {
        }
        machineObservable = FXCollections.observableArrayList(machineList);
        tableView.setItems(machineObservable);
    }

    @FXML public void setBack(){
        App.mainStage.close();
    }

    @FXML
    public void createMachine() throws SQLException, IOException {
        Machine machine = new Machine();
        machine.setId(machineList.size()+1);
        boolean isButtonClicked = getMachineFromForm(machine);
        if (isButtonClicked){
            machineService.add(machine);
            fillTables();
        }
    }
    @FXML
    public void editMachine() throws SQLException, IOException {
        Machine machine = tableView.getSelectionModel().getSelectedItem();
        if (machine != null){
            boolean isButtonClicked = getMachineFromForm(machine);
            if (isButtonClicked){
                machineService.update(machine);
                fillTables();
            }
        }else {
            System.out.println("Select the Machine");
        }
    }

    public boolean getMachineFromForm(Machine machine) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addmachine.fxml"));
        AnchorPane page = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(page);
        stage.setScene(scene);
        AddMachine machineSave = loader.getController();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        machineSave.setStage(stage);
        machineSave.setMachine(machine);
        stage.showAndWait();
        return machineSave.isButtonClicked();
    }


}
