package agendaapp.controller;


import agendaapp.bussiness.PersonCreator;
import agendaapp.dto.PersonDTO;
import agendaapp.dto.PhoneDTO;
import agendaapp.manager.PersonManager;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    private Button btAddPhone;
    @FXML
    private TableView<PhoneDTO> phonesTable;
    @FXML
    private TableColumn phoneNumberColumn;
    //phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<PhoneDTO,String>("phoneNumber"))
    @FXML
    private TableColumn idColumn;
    //id.setCellValueFactory(new PropertyValueFactory<PhoneDTO,String>("id"))
    @FXML
    private javafx.scene.control.Button btAddPerson;

    final ContextMenu contextMenu = new ContextMenu();
    final MenuItem itemAddPhone = new MenuItem("Añadir teléfono");
    final MenuItem itemDeletePhone = new MenuItem("Eliminar teléfono");


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
            ObservableList rowsInTable = phonesTable.getItems();
            for(int i = 0; i < rowsInTable.size(); ++i){
                phonesTable.getItems().remove(0);
            }
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

        }
        phonesTable.setEditable(true);
        phonesTable.setItems(phoneDTOObservableList);

        BooleanBinding booleanBind = Bindings.isEmpty(phoneDTOObservableList);
        btAddPhone.disableProperty().bind(booleanBind);
    }

    public void OnAddPerson(MouseEvent event){

        Parent root = null;

        Stage stage = new Stage();
        try {
                root = FXMLLoader.load(AddPersonController.class.getResource("/agendaapp/view/AddPerson.fxml"));
        }
        catch(Exception e){
            System.out.println("Error");
        }

        stage.setScene(new Scene(root));
        stage.setTitle("Añadir persona a la agenda");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node)event.getSource()).getScene().getWindow() );
        stage.show();
    }

    public void onAddNewPhoneToPerson(ActionEvent event){

        Parent root = null;

        Stage stage = new Stage();
        try {
            root = FXMLLoader.load(AddPersonController.class.getResource("/agendaapp/view/AddNewPhoneToPerson.fxml"));
        }
        catch(Exception e){
            System.out.println("Error");
        }

        stage.setScene(new Scene(root));
        stage.setTitle("Añadir teléfono");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node)event.getSource()).getScene().getWindow() );
        stage.show();
    }





//        final int selectedListIdx = personWhoMeetPatternList.getSelectionModel().getSelectedIndex();
//        final int selectedPersonId = personDTOList.get(selectedListIdx).getId();
//        List<PersonDTO> personDTOList = manager.findPersonById(selectedPersonId);
//
//        PersonCreator personCreator = new PersonCreator();
//        personCreator.



    public void onDeletePersonsPhone(){

    }

    public void onExit(){

        System.exit(0);
    }
}
