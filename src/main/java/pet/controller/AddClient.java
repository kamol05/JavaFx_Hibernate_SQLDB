package pet.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import pet.entity.*;

@Getter
@Setter
public class AddClient {

    private Stage stage;
    private Client client;
    private boolean buttonClicked = false;

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
    public void handleConfirmButton(){
        try{
            client.setId(Integer.parseInt(id.getText()));
            client.setName(name.getText());
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Errorcha Qays7idir qatorda harf yoki belgi bor");
            alert.show();
        }
        buttonClicked = true;
        stage.close();
    }

    @FXML
    public void handleCancelButton(){
        stage.close();
    }

    public void setClient(Client client) {
        this.client = client;
        this.id.setText(String.valueOf(client.getId()));
        id.setEditable(false);
        this.name.setText(client.getName());
    }
}