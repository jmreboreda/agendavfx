package agendaapp;

import agendaapp.bussiness.person.PersonSaver;
import agendaapp.dto.PersonDTO;
import agendaapp.dto.PhoneDTO;
import agendaapp.persistence.vo.PersonVO;
import agendaapp.persistence.vo.PhoneVO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/agendaapp/view/AgendaView.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
