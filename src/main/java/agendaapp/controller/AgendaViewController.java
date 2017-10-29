package agendaapp.controller;


import agendaapp.dto.PersonDTO;
import agendaapp.dto.PhoneDTO;
import agendaapp.manager.PersonManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private TableColumn<PhoneDTO, String> phoneTypeColumn;


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

        final int selectedIdx = personWhoMeetPatternList.getSelectionModel().getSelectedIndex();
        final int selectedPersonId = personDTOList.get(selectedIdx).getId();
        List<PersonDTO> personDTOList = manager.findPersonById(selectedPersonId);
        List<PhoneDTO> phoneDTOList = new ArrayList<>();
        for(PhoneDTO phoneDTO : personDTOList.get(0).getPhoneDTOS()){
            phoneDTOList.add(phoneDTO);
        }

        ObservableList<PhoneDTO> phoneDTOObservableList = FXCollections.observableList(phoneDTOList);
        phonesTable.setEditable(true);
        phonesTable.getColumns().clear();
        phonesTable.getColumns().addAll(phoneNumberColumn, phoneTypeColumn);
        phonesTable.setItems(phoneDTOObservableList);
        phonesTable.refresh();
    }

    public void onExit(){

        System.exit(0);
    }
}
