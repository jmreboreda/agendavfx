package agendaapp.controller;


import agendaapp.dto.PersonDTO;
import agendaapp.dto.PhoneDTO;
import agendaapp.manager.PersonManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AgendaViewController implements Initializable{

    private static final Logger logger = LoggerFactory.getLogger(AgendaViewController.class);
    private List<PersonDTO> personDTOList;
    PersonManager manager = new PersonManager();

    @FXML
    private TextField tfPersonNamePattern;
    @FXML
    private ListView personWhoMeetPatternList;
    @FXML
    private TableView<PhoneDTO> phonesTable;
    @FXML
    private TableColumn<PhoneDTO, String> phoneNumberColumn;
    @FXML
    private TableColumn<PhoneDTO, String> id;
    @FXML
    private Button btAddPerson;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                tfPersonNamePattern.requestFocus();
            }
        });
    }

    public void onChangeInPersonNamePattern(){

        String patternLetters = tfPersonNamePattern.getText();
        if(!patternLetters.isEmpty()) {

            personDTOList = manager.findAllFromNamePatternInAlphabeticalOrder(patternLetters);
            ObservableList observablePersonDTOList = FXCollections.observableList(personDTOList);
            personWhoMeetPatternList.setItems(observablePersonDTOList);
        }
        else{
            personWhoMeetPatternList.getItems().clear();
        }
    }

    public void onSelectPerson(){

        String patternLetters = tfPersonNamePattern.getText();
        if(patternLetters.isEmpty()){
            return;
        }

        final int selectedListIdx = personWhoMeetPatternList.getSelectionModel().getSelectedIndex();
        final int selectedPersonId = personDTOList.get(selectedListIdx).getId();
        List<PersonDTO> personDTOList = manager.findPersonById(selectedPersonId);
        List<PhoneDTO> phoneDTOList = new ArrayList<>();
        for(PhoneDTO phoneDTO : personDTOList.get(0).getPhoneDTOS()){
            phoneDTOList.add(phoneDTO);
        }


        ObservableList<PhoneDTO> phoneDTOObservableList = FXCollections.observableList(phoneDTOList);
        for(PhoneDTO phDTO : phoneDTOObservableList) {

            System.out.println("phoneDTOObservableList -> Id: " + phDTO.getId() + ", phoneNumber: " +phDTO.getPhoneNumber());
        }
        phonesTable.setEditable(true);
        //phonesTable.getColumns().clear();
        //phonesTable.getColumns().addAll(phoneNumberColumn, id);
        phonesTable.setItems(phoneDTOObservableList);
    }

    public void OnAddPerson(MouseEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(
                AddPersonController.class.getResource("/agendaapp/view/AddPerson.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("My modal window");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node)event.getSource()).getScene().getWindow() );
        stage.show();
    }

    public void onExit(){

        System.exit(0);
    }
}
