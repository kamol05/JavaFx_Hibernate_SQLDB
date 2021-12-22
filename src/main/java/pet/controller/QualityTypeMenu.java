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
import pet.entity.QualityType;
import pet.service.QualityTypeService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QualityTypeMenu {
    private QualityTypeService qualityTypeService = new QualityTypeService();
    @FXML
    private TableView<QualityType> tableView;
    @FXML
    private TableColumn<QualityType,Integer> id;

    @FXML
    private TableColumn<QualityType,String> description;
    
    @FXML
    Button add;
    @FXML
    Button edit;
    @FXML
    Button back;

    private List<QualityType> qualityTypeList = new ArrayList<>();
    private ObservableList<QualityType> qualityTypeObservable;

    @FXML
    public void initialize() {
        fillTables();
    }

    @FXML
    public void fillTables(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        try {
            qualityTypeList = qualityTypeService.getAll();
        } catch (SQLException throwables) {
        }
        qualityTypeObservable = FXCollections.observableArrayList(qualityTypeList);
        tableView.setItems(qualityTypeObservable);
    }

    @FXML public void setBack(){
        App.mainStage.close();
    }

    @FXML
    public void createQualityType() throws SQLException, IOException {
        QualityType qualityType = new QualityType();
        qualityType.setId(qualityTypeList.size()+1);
        boolean isButtonClicked = getQualityTypeFromForm(qualityType);
        if (isButtonClicked){
            qualityTypeService.add(qualityType);
            fillTables();
        }
    }
    @FXML
    public void editQualityType() throws SQLException, IOException {
        QualityType qualityType = tableView.getSelectionModel().getSelectedItem();
        if (qualityType != null){
            boolean isButtonClicked = getQualityTypeFromForm(qualityType);
            if (isButtonClicked){
                qualityTypeService.update(qualityType);
                fillTables();
            }
        }else {
            System.out.println("Select the QualityType");
        }
    }

    public boolean getQualityTypeFromForm(QualityType qualityType) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addqualityType.fxml"));
        AnchorPane page = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(page);
        stage.setScene(scene);
        AddQualityType qualityTypeSave = loader.getController();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        qualityTypeSave.setStage(stage);
        qualityTypeSave.setQualityType(qualityType);
        stage.showAndWait();
        return qualityTypeSave.isButtonClicked();
    }
}
