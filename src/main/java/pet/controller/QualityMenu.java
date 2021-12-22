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
import lombok.Getter;
import lombok.Setter;
import pet.App;
import pet.entity.Quality;
import pet.service.QualityService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QualityMenu {
    private QualityService qualityService = new QualityService();
    @FXML
    private TableView<Quality> tableView;
    @FXML
    private TableColumn<Quality,Integer> id;
    @FXML
    private TableColumn<Quality,String> name;
    @FXML
    private TableColumn<Quality,String> qualityType;

    @FXML
    Button add;
    @FXML
    Button edit;
    @FXML
    Button back;

    private List<Quality> qualityList = new ArrayList<>();
    private ObservableList<Quality> qualityObservable;

    @FXML
    public void initialize() {
        fillTables();
    }

    @FXML
    public void fillTables(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        qualityType.setCellValueFactory(new PropertyValueFactory<>("type"));
        try {
            qualityList = qualityService.getAll();
        } catch (SQLException throwables) {
        }
        qualityObservable = FXCollections.observableArrayList(qualityList);
        tableView.setItems(qualityObservable);
    }

    @FXML public void setBack(){
        App.mainStage.close();
    }

    @FXML
    public void createQuality() throws SQLException, IOException {
        Quality quality = new Quality();
        quality.setId(qualityList.size()+1);
        boolean isButtonClicked = getQualityFromForm(quality);
        if (isButtonClicked){
            qualityService.add(quality);
            fillTables();
        }
    }
    @FXML
    public void editQuality() throws SQLException, IOException {
        Quality quality = tableView.getSelectionModel().getSelectedItem();
        if (quality != null){
            boolean isButtonClicked = getQualityFromForm(quality);
            if (isButtonClicked){
                qualityService.update(quality);
                fillTables();
            }
        }else {
            System.out.println("Select the Quality");
        }
    }

    public boolean getQualityFromForm(Quality quality) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addquality.fxml"));
        AnchorPane page = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(page);
        stage.setScene(scene);
        AddQuality qualitySave = loader.getController();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        qualitySave.setStage(stage);
        qualitySave.setQuality(quality);
        stage.showAndWait();
        return qualitySave.isButtonClicked();
    }
}
