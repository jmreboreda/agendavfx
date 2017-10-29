package agendaapp.manager;

import agendaapp.dto.PersonDTO;
import agendaapp.dto.PhoneDTO;
import agendaapp.persistence.vo.PersonVO;
import agendaapp.persistence.vo.PhoneVO;
import agendaapp.mapper.PersonMapper;
import agendaapp.persistence.dao.PersonDAO;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonManager {

    public PersonManager() {
    }

    public List<PersonDTO> findAllFromNamePatternInAlphabeticalOrder(String namePattern) {

        List<PersonDTO> personDTOList = new ArrayList<>();
        PersonMapper mapper = new PersonMapper();

        PersonDAO personDAO = PersonDAO.PersonDAOFactory.getInstance();
        List<PersonVO> personVOList = personDAO.findAllPersonByNamePatternInAlphabeticalOrder(namePattern);
        for (PersonVO personVO : personVOList) {
            Set<PhoneDTO> phoneDTOS = new HashSet<>();
            for (PhoneVO phoneVO : personVO.getPhoneVOS()) {
                PhoneDTO phoneDTO = mapper.proccessPhoneVODTO(phoneVO);
                phoneDTOS.add(phoneDTO);
            }

            PersonDTO personDTO = PersonDTO.create()
                    .withId(personVO.getId())
                    .withApellido1(personVO.getApellido1())
                    .withApellido2(personVO.getApellido2())
                    .withNombre(personVO.getNombre())
                    .withPhones(phoneDTOS)
                    .build();

            personDTOList.add(personDTO);
        }

        return personDTOList;
    }
}
