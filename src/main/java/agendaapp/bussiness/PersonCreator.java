package agendaapp.bussiness;

import agendaapp.dto.PersonDTO;
import agendaapp.dto.PhoneDTO;
import agendaapp.manager.PersonManager;

import java.util.HashSet;
import java.util.Set;

public class PersonCreator {

    PersonManager manager = new PersonManager();

    public Boolean isNameDupicate(PersonDTO personDTO){

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

    public void personCreateAndUpdate(PersonDTO personDTO, Integer phoneId, String phoneNumber) {

        personDTO.getPhoneDTOS().clear();
        Integer personId = personCreate(personDTO);
        personDTO.setId(personId);

        Set<PhoneDTO> phoneDTOS = new HashSet<>();
        PhoneDTO phDTO = new PhoneDTO();
        phDTO.setId(phoneId);
        phDTO.setPhoneNumber(phoneNumber);
        Set<PersonDTO> personDTOS = new HashSet<>();
        personDTOS.add(personDTO);
        phDTO.setPersonDTOS(personDTOS);
        phoneDTOS.add(phDTO);
        personDTO.setPhoneDTOS(phoneDTOS);

        manager.addNewPhoneToPerson(personDTO);
    }

}
