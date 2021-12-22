package pet.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import pet.entity.Machine;

@Getter
@Setter
public class AddMachine {
    private Stage stage;
    private Machine machine;
    private boolean buttonClicked = false;
    @FXML
    private Label idLine;
    @FXML
    private Label widthLine;
    @FXML
    private Label dentitiesLine;
    @FXML
    private Label speedLine;
    @FXML
    private Label normLine;
    @FXML
    private TextField id;
    @FXML
    private TextField width;
    @FXML
    private TextField dentities;
    @FXML
    private TextField speed;
    @FXML
    private TextField norm;
    @FXML
    private Button save;
    @FXML
    private Button back;

    @FXML
    public void handleConfirmButton(){
        try{
            machine.setId(Integer.parseInt(id.getText()));
            machine.setDentities(Integer.parseInt(dentities.getText()));
            machine.setSpeed(Integer.parseInt(speed.getText()));
            machine.setWidth(Float.parseFloat(width.getText()));
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

    public void setMachine(Machine machine) {
        this.machine = machine;
        this.id.setText(String.valueOf(machine.getId()));
        id.setEditable(false);
        this.dentities.setText(String.valueOf(machine.getDentities()));
        this.speed.setText(String.valueOf(machine.getSpeed()));
        this.width.setText(String.valueOf(machine.getWidth()));
    }
}