package pet;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {
    @FXML
    private static Scene scene;
    @FXML
    private MenuItem CLients;
    @FXML
    private MenuItem Machines;
    @FXML
    private MenuItem Orders;
    @FXML
    private MenuItem OrdersTypes;
    @FXML
    private MenuItem Qualitys;
    @FXML
    private MenuItem QualityTypes;
    @FXML
    private AnchorPane anchorman;

    @FXML
    public void initialize() {
    }



    public void openClientsMenu() throws IOException {
        App.setRoot("controller/clientmenu");

    }
    public void openMachineMenu() throws IOException {
        App.setRoot("controller/machinemenu");
    }
    public void openOrderTypeMenu() throws IOException {
        App.setRoot("controller/ordertypemenu");
    }
    public void openQualityMenu() throws IOException {
        App.setRoot("controller/qualitymenu");
    }
    public void openQualityTypeMenu() throws IOException {
        App.setRoot("controller/qualitytypemenu");
    }
    public void openOrdersMenu() throws IOException {
        Stage stage = new Stage();
        App.mainStage = new Stage();
        Scene scene = new Scene(App.loadFXML("controller/ordersmenu"));
        App.mainStage.setScene(scene);
        App.mainStage.initModality(Modality.APPLICATION_MODAL);
//        App.mainStage.setResizable(false);
        App.mainStage.showAndWait();
//        App.setRoot("controller/ordersmenu");
    }
    public void openDailyProductionMenu() throws IOException {
//        App.setRoot("controller/dailyproductionmenu");
        Stage stage = new Stage();
        App.mainStage = new Stage();
        Scene scene = new Scene(App.loadFXML("controller/dailyproductionmenu"));
        App.mainStage.setScene(scene);
        App.mainStage.initModality(Modality.APPLICATION_MODAL);
//        App.mainStage.setResizable(false);
        App.mainStage.showAndWait();
//        App.setRoot("controller/ordersmenu");
    }

}