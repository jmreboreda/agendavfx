package agendaapp.controller;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AgendaViewController {

    private static final Logger logger = LoggerFactory.getLogger(AgendaViewController.class);

    @FXML
    private TextField tfPersonNamePattern;


    @FXML
    public String onChangeInPersonNamePattern(final Event event){

        String patternLetters = tfPersonNamePattern.getText();

        System.out.println(patternLettersa);

        logger.info(patternLetters);

        return patternLetters;

    }

    public void onExit(){

    }

}
