package agendaapp.controller;


import agendaapp.bussiness.person.PersonFinder;
import agendaapp.dto.PersonDTO;
import agendaapp.dto.PhoneDTO;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AgendaViewController implements Initializable{

    private List<PersonDTO> personDTOList;

    private PersonFinder personFinder = new PersonFinder();

    @FXML
    private TextField tfPersonNamePattern;
    @FXML
    private ListView personWhoMeetPatternList;//Importante!! parametriza el listView ListView<Whatever>
    @FXML
    private Button btAddPhone;
    @FXML
    private TableView<PhoneDTO> phonesTable;
    @FXML
    private TableColumn phoneNumberColumn;
    @FXML
    private TableColumn idColumn;
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
            personDTOList = personFinder.findAllFromNamePatternInAlphabeticalOrder(patternLetters);
            personWhoMeetPatternList.setItems(FXCollections.observableList(personDTOList));
        }
        else{
            personWhoMeetPatternList.getItems().clear();
            ObservableList rowsInTable = phonesTable.getItems();
            // CACA DE LA VACA => forEach
            //edit: no sólo es caca de la vaca, sino que estás borrando una lista iterándola!!
            // para eso tienes clear()
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

        //TODO eso se puede hacer mejor
        final int selectedListIdx = personWhoMeetPatternList.getSelectionModel().getSelectedIndex();
        final int selectedPersonId = personDTOList.get(selectedListIdx).getId();

        PersonFinder personFinder = new PersonFinder();
        PersonDTO personDTO = personFinder.findPersonById(selectedPersonId);

        List<PhoneDTO> phoneDTOList = new ArrayList<>();
        for(PhoneDTO phoneDTO : personDTO.getPhoneDTOS()){
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

    //TODO cambiar este callBack para que empiece en lowecase
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


    public void onDeletePersonsPhone(){

    }

    public void onExit(){
        System.exit(0);
    }
}
