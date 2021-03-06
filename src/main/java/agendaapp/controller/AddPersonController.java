package agendaapp.controller;

import agendaapp.dto.PersonDTO;
import agendaapp.dto.PhoneDTO;
import agendaapp.manager.PersonManager;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.*;
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

    private PersonManager manager = new PersonManager();



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

        if(isNameDupicate(personDTO)){
            System.out.println("Ya existe una persona con ese nombre");
            return;
        }

        Integer phoneId = existPhoneNumber(personDTO);
        if(phoneId == null){
            personCreate(personDTO);
        }
        else{
            personCreateAndUpdate(personDTO, phoneId);
        }

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

    private Boolean isNameDupicate(PersonDTO personDTO){

        Boolean isNameDuplicated = true;

        Integer id = manager.findPersonByStrictName(personDTO);
        if(id == null){
            isNameDuplicated = false;
        }

        return isNameDuplicated;
    }

    public Integer existPhoneNumber(PersonDTO personDTO){

        String phoneNumber = null;

        for(PhoneDTO phoneDTO : personDTO.getPhoneDTOS()) {
            phoneNumber = phoneDTO.getPhoneNumber();
        }

        Integer phoneID = manager.findPhoneByPhoneNumber(phoneNumber);

        return phoneID;
    }

    public Integer personCreate(PersonDTO personDTO){

        Integer personId = manager.createPerson(personDTO);
        System.out.println("Se ha creado una persona: \n"
                + personDTO.toString() + " [id: " + personId + "]");

        return personId;
    }

    public void personCreateAndUpdate(PersonDTO personDTO, Integer phoneId) {

        personDTO.getPhoneDTOS().clear();
        Integer personId = personCreate(personDTO);
        personDTO.setId(personId);

        Set<PhoneDTO> phoneDTOS = new HashSet<>();
        PhoneDTO phDTO = new PhoneDTO();
        phDTO.setId(phoneId);
        phDTO.setPhoneNumber(tfPhoneNumber.getText());
        Set<PersonDTO> personDTOS = new HashSet<>();
        personDTOS.add(personDTO);
        phDTO.setPersonDTOS(personDTOS);
        phoneDTOS.add(phDTO);
        personDTO.setPhoneDTOS(phoneDTOS);

        manager.addNewPhoneToPerson(personDTO);
    }

    public void clearFormData(){

        tfApellido1.clear();
        tfApellido2.clear();
        tfNombre.clear();
        tfPhoneNumber.clear();
    }
}
