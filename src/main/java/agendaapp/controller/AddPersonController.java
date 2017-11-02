package agendaapp.controller;

import agendaapp.bussiness.person.PersonChecker;
import agendaapp.bussiness.person.PersonSaver;
import agendaapp.bussiness.phone.PhoneChecker;
import agendaapp.dto.PersonDTO;
import agendaapp.dto.PhoneDTO;
import agendaapp.persistence.vo.PhoneVO;
import agendaapp.utilities.MessageDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.lang3.Validate;

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

        Set<PhoneDTO> phoneDTOS = new HashSet<>();

        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setId(null);
        phoneDTO.setPhoneNumber(tfPhoneNumber.getText());
        phoneDTOS.add(phoneDTO);

        PersonDTO personDTO = PersonDTO.create()
                .withId(null)
                .withApellido1(tfApellido1.getText())
                .withApellido2(tfApellido2.getText())
                .withNombre(tfNombre.getText())
                .withPhones(phoneDTOS)
                .build();

        Boolean existPhoneNumber = phoneChecker.existsPhoneNumber(tfPhoneNumber.getText());
        if(existPhoneNumber){
//            phoneDTO.setId(existPhoneNumber.getId());
//            phoneDTOS.clear();
//            phoneDTOS.add(phoneDTO);
        }

        personSaver.savePerson(personDTO);

        MessageDialog message = new MessageDialog();
        message.newPersonAdded(personDTO, tfPhoneNumber.getText());

        clearFormData();
    }

    public void OnExit(){
        Stage stage = (Stage) btSalir.getScene().getWindow();
        stage.close();
    }

    private void validateNotEmptyData() {
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
