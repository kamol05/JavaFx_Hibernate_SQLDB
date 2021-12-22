package pet.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import pet.entity.QualityType;

@Getter
@Setter
public class AddQualityType {
    private Stage stage;
    private QualityType qualityType;
    private boolean buttonClicked = false;
    @FXML
    private Label idLine;

    @FXML
    private Label descriptionLine;

    @FXML
    private TextField id;

    @FXML
    private TextField description;

    @FXML
    private Button save;
    @FXML
    private Button back;

    @FXML
    public void handleConfirmButton(){
        try{
            qualityType.setId(Integer.parseInt(id.getText()));
            qualityType.setDescription(description.getText());
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

    public void setQualityType(QualityType qualityType) {
        this.qualityType = qualityType;
        this.id.setText(String.valueOf(qualityType.getId()));
        id.setEditable(false);
        this.description.setText(qualityType.getDescription());

    }
}
