package pet.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.controlsfx.control.textfield.TextFields;
import pet.entity.Quality;
import pet.entity.QualityType;
import pet.service.QualityTypeService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AddQuality {
    private Stage stage;
    private Quality quality;
    private QualityType qualityType;
    private boolean buttonClicked = false;
    private final QualityTypeService qualityTypeService = new QualityTypeService();

    @FXML
    private Label idLine;
    @FXML
    private Label nameLine;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private Button save;
    @FXML
    private Button back;
    @FXML
    private ComboBox qualityTypeCombo;
    @FXML
    private List<QualityType> qualityTypeList;
    @FXML
    private ObservableList<QualityType> qualityTypeObservableList = FXCollections.observableList(new ArrayList<>());
    @FXML
    public void initialize() throws SQLException {
        qualityTypeObservableList.addAll(qualityTypeService.getAll());
        qualityTypeCombo.setItems(qualityTypeObservableList);
    }
    @FXML
    public void handleConfirmButton(){
        try{
            quality.setId(Integer.parseInt(id.getText()));
            quality.setName(name.getText());
            quality.setType(qualityType);
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Errorcha Qaysidir qatorda harf yoki belgi bor");
            alert.show();
        }
        buttonClicked = true;
        stage.close();
    }
    @FXML
    public void handleCancelButton(){
        stage.close();
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
        qualityType = quality.getType() == null ? new QualityType() : quality.getType();
        qualityTypeCombo.getSelectionModel().select(qualityType);
        this.id.setText(String.valueOf(quality.getId()));
        id.setEditable(false);
        this.name.setText(quality.getName());
    }

    @FXML
    public void selectQType(){
        qualityType = (QualityType) qualityTypeCombo.getSelectionModel().getSelectedItem();
    }

}
