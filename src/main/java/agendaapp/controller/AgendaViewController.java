package agendaapp.controller;


import agendaapp.dto.PersonDTO;
import agendaapp.manager.PersonManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class AgendaViewController {

    private static final Logger logger = LoggerFactory.getLogger(AgendaViewController.class);

    @FXML
    private TextField tfPersonNamePattern;
    @FXML
    private ListView personWhoMeetPatternList;


    @FXML
    public void onChangeInPersonNamePattern(){

        String patternLetters = tfPersonNamePattern.getText();
        if(!patternLetters.isEmpty()) {

            PersonManager manager = new PersonManager();
            List<PersonDTO> personDTOList = manager.findAllFromNamePatternInAlphabeticalOrder(patternLetters);
            ObservableList observablePersonDTOList = FXCollections.observableList(personDTOList);
            personWhoMeetPatternList.setItems(observablePersonDTOList);
        }
        else{
            personWhoMeetPatternList.getItems().clear();
        }
    }

    public void onExit(){

        System.exit(0);

    }

    //final int selectedIdx = playerList.getSelectionModel().getSelectedIndex();
    //String itemToRemove = playerList.getSelectionModel().getSelectedItem();
}
