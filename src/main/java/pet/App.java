package pet;

import javafx.scene.layout.AnchorPane;
import pet.entity.*;
import javafx.stage.Modality;
import pet.service.*;
import pet.util.HibernateUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jeasy.random.EasyRandom;

import java.io.IOException;
import java.sql.SQLException;

public class App extends Application {

    public static void main(String[] args) throws SQLException {
//        ClientService service = new ClientService();
//        service.add(new Client());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/pet/menu.fxml"));
        primaryStage.setTitle("Mening Birinchi Programmam");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void setRoot(String fxml) throws IOException {
        Stage stage = new Stage();
        mainStage = new Stage();
        scene = new Scene(loadFXML(fxml));
        mainStage.setScene(scene);
        mainStage.initModality(Modality.APPLICATION_MODAL);
        mainStage.setResizable(false);
        mainStage.showAndWait();
    }
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static Stage mainStage;
    public static Scene scene;

    public static class Fill{
        public static void fillFielsds() throws SQLException {
//            ClientService clientService = new ClientService();
//            MachineService machineService = new MachineService();
//            MachineMenu mc = new MachineMenu();
//            mc.fillTables();
//
//            HibernateUtil.shutdown();
//
//            OrderService orderService = new OrderService();
//            OrderTypeService orderTypeService = new OrderTypeService();
//            QualityService qualityService = new QualityService();
//            QualityTypeService qualityTypeService = new QualityTypeService();
//
//
//            Client client = clientService.getById(7);
//            Machine machine = machineService.getById(1);
//            OrderType orderType = orderTypeService.getById(10);
//            Quality quality = qualityService.getById(1);
//            QualityType qualityType = qualityTypeService.getById(11);
//            Orders order = getOrder();
//
//
//            QualityType qualityType = getQualityType();
//            Quality quality = getQuality();
//
//
//            order.setClient(client);
//            order.setMachine(machine);
//            order.setOrderType(orderType);
//            order.setQuality(quality);
//            order.setQualityType(qualityType);
//
//            machine.getOrders().add(order);
//            client.getOrders().add(order);
//            orderType.getOrders().add(order);
//            quality.getOrders().add(order);
//            qualityType.getOrders().add(order);
//
//            quality.setType(qualityType);
//            qualityTypeService.add(qualityType);
//            qualityService.add(quality);
//
//            orderService.add(order);
            HibernateUtil.shutdown();
        }

//        public static Client getClient(){
            Client client = new Client();
//            client.setName("Birinchi");
//            return client;
//        }
//        public static Machine getMachine(){
////        Machine machine = new Machine();
//            EasyRandom easyRandom = new EasyRandom();
//            Machine machine = easyRandom.nextObject(Machine.class);
////        machine.setId(0);
////        machine.setNumber(1);
////        machine.setDentities(400);
////        machine.setNorm(0);
////        machine.setSpeed(140);
////        machine.setWidth(5);
//            return machine;
//        }
//        public static Orders getOrder(){
//            EasyRandom easyRandom = new EasyRandom();
//            Orders orders = easyRandom.nextObject(Orders.class);
//            return orders;
//        }
//        public static OrderType getOrderType(){
//            EasyRandom easyRandom = new EasyRandom();
//            OrderType o = easyRandom.nextObject(OrderType.class);
//            return o;
//        }
//        public static Quality getQuality(){
//            EasyRandom easyRandom = new EasyRandom();
//            Quality o = easyRandom.nextObject(Quality.class);
//            return o;
//        }
//        public static QualityType getQualityType(){
//            EasyRandom easyRandom = new EasyRandom();
//            QualityType o = easyRandom.nextObject(QualityType.class);
//            return o;
//        }
    }
}
