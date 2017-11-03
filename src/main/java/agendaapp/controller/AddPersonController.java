package agendaapp.controller;

import agendaapp.bussiness.person.PersonChecker;
import agendaapp.bussiness.person.PersonSaver;
import agendaapp.bussiness.person.exceptions.PersonAlreadyExistsException;
import agendaapp.bussiness.phone.PhoneChecker;
import agendaapp.dto.PersonDTO;
import agendaapp.dto.PhoneDTO;
import agendaapp.utilities.MessageDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.lang3.Validate;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AddPersonController {

    @FXML
    private TextField tfApellido1;
    @FXML
    private TextField tfApellido2;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfPhoneNumber;
    @FXML
    private Button btAceptar;
    @FXML
    private Button btSalir;

    private final PersonSaver personSaver = new PersonSaver();
    private final PersonChecker personChecker = new PersonChecker();
    private final PhoneChecker phoneChecker = new PhoneChecker();

    public void onCreatePerson(){

        validateNotEmptyData();

        final PersonDTO personDTO = retrievePersonDTO();

        try {
            personSaver.savePerson(personDTO);
//            MessageDialog message = new MessageDialog();
//            message.newPersonAdded(personDTO, phoneNumber);
            clearFormData();

        } catch (PersonAlreadyExistsException e) {
            // Así funcionan las excepciones, el saver te informa de que no ha podido
            // crear la persona por una razón concreta, así que ahora desde el controller
            // puedes avisar al usuario con una ventanita o lo que quieras

            // Puedes crear en el MessageDialog un errorMessage o algo así, con un iconito
            // de alerta o alguna chorrada
        }



    }

    private PersonDTO retrievePersonDTO() {
        String phoneNumber = tfPhoneNumber.getText();
        String apellido1 = tfApellido1.getText();
        String apellido2 = tfApellido2.getText();
        String nombre = tfNombre.getText();

        final Set<PhoneDTO> phones = new HashSet<>();
        final PhoneDTO phoneDTO = PhoneDTO.create()
                .withPhoneNumber(phoneNumber)
                .build();

        phones.add(phoneDTO);

        return PersonDTO.create()
                .withNombre(nombre)
                .withApellido1(apellido1)
                .withApellido1(apellido2)
                .withPhones(phones)
                .build();
    }

    public void OnExit(){
        Stage stage = (Stage) btSalir.getScene().getWindow();
        stage.close();
    }

    private void validateNotEmptyData() {
        //Las validaciones de apache commons lanzan IllegalArgumentException,
        // si haces esto tu apli petará por detrás y el user no se enterará

        // Podrías capturar el IllegalArgumentException pero en controller yo creo
        // que es mejor que compruebes a manija si los campos son válidos para
        // mandárselos al saver, y si no lo son muestras una ventanita de error
        Validate.notBlank(tfApellido1.getText(), "Apellido 1 no puede estar vacío");
        Validate.notBlank(tfApellido2.getText(), "Apellido 2 no puede estar vacío");
        Validate.notBlank(tfNombre.getText(), "Nombre no puede estar vacío");
        Validate.notBlank(tfPhoneNumber.getText(), "Teléfono no puede estar vacío");
    }

    public void clearFormData(){
        tfApellido1.clear();
        tfApellido2.clear();
        tfNombre.clear();
        tfPhoneNumber.clear();
    }
}
