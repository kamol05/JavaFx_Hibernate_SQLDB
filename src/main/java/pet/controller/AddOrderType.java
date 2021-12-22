package pet.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import pet.entity.OrderType;

@Getter
@Setter
public class AddOrderType {
    private Stage stage;
    private OrderType orderType;
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
            orderType.setId(Integer.parseInt(id.getText()));
            orderType.setDescription(description.getText());
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

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
        this.id.setText(String.valueOf(orderType.getId()));
        id.setEditable(false);
        this.description.setText(orderType.getDescription() == null ? "" : orderType.getDescription());
    }
}

