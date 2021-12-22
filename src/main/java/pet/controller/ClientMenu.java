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
import pet.entity.Client;
import pet.service.ClientService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientMenu {
    
    private ClientService clientService = new ClientService();
    @FXML
    private TableView<Client> tableView;
    @FXML
    private TableColumn<Client,Integer> number;
    @FXML
    private TableColumn<Client,Integer> name;
   
    @FXML
    Button add;
    @FXML
    Button edit;
    @FXML
    Button back;

    private List<Client> clientList = new ArrayList<>();
    private ObservableList<Client> clientObservable;

    @FXML
    public void initialize() {
        fillTables();
    }

    @FXML
    public void fillTables(){
        number.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        try {
            clientList = clientService.getAll();
        } catch (SQLException throwables) {
        }
        clientObservable = FXCollections.observableArrayList(clientList);
        tableView.setItems(clientObservable);
    }

    @FXML public void setBack(){
        App.mainStage.close();
    }

    @FXML
    public void createClient() throws SQLException, IOException {
        Client client = new Client();
        client.setId(clientList.size()+1);
        boolean isButtonClicked = getClientFromForm(client);
        if (isButtonClicked){
            clientService.add(client);
            fillTables();
        }
    }
    @FXML
    public void editClient() throws SQLException, IOException {
        Client client = tableView.getSelectionModel().getSelectedItem();
        if (client != null){
            boolean isButtonClicked = getClientFromForm(client);
            if (isButtonClicked){
                clientService.update(client);
                fillTables();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Iltimoss Clientni tanlang");
            alert.show();
        }
    }

    public boolean getClientFromForm(Client client) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addclient.fxml"));
        AnchorPane page = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(page);
        stage.setScene(scene);
        AddClient clientSave = loader.getController();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        clientSave.setStage(stage);
        clientSave.setClient(client);
        stage.showAndWait();
        return clientSave.isButtonClicked();
    }
}
