package agendaapp.manager;

import agendaapp.dto.PersonDTO;
import agendaapp.dto.PhoneDTO;
import agendaapp.persistence.mapper.PersonMapper;
import agendaapp.persistence.vo.PersonVO;
import agendaapp.persistence.vo.PhoneVO;
import agendaapp.persistence.dao.PersonDAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonManager {

    PersonMapper mapper = new PersonMapper();

    public PersonManager() {
    }

    public List<PersonDTO> findAllFromNamePatternInAlphabeticalOrder(String namePattern) {

        List<PersonDTO> personDTOList = new ArrayList<>();

        PersonDAO personDAO = PersonDAO.PersonDAOFactory.getInstance();
        List<PersonVO> personVOList = personDAO.findAllPersonFromNamePatternInAlphabeticalOrder(namePattern);
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

    public List<PersonDTO> findPersonById(Integer id){

        PersonDAO personDAO = PersonDAO.PersonDAOFactory.getInstance();
        List<PersonVO> personVOList = personDAO.findPersonById(id);

        Set<PhoneDTO> phoneDTOList = new HashSet<>();
        for(PhoneVO phoneVO : personVOList.get(0).getPhoneVOS()){
            PhoneDTO phoneDTO = new PhoneDTO();
            phoneDTO.setId(phoneVO.getId());
            phoneDTO.setPhoneNumber(phoneVO.getPhoneNumber());
            phoneDTOList.add(phoneDTO);
        }
        List<PersonDTO> personDTOList = new ArrayList<>();
        for(PersonVO personVO : personVOList) {
           PersonDTO personDTO = PersonDTO.create()
                   .withApellido1(personVO.getApellido1())
                   .withApellido2(personVO.getApellido2())
                   .withNombre(personVO.getNombre())
                   .withPhones(phoneDTOList)
                   .build();
           personDTOList.add(personDTO);
        }

        return personDTOList;
    }

    public Integer findPersonByStrictName(PersonDTO personDTO){

        PersonVO personVO = mapper.proccesPersonDTOVO(personDTO);
        PersonDAO personDAO = PersonDAO.PersonDAOFactory.getInstance();
        Integer id = personDAO.findPersonByStrictName(personVO);

        return id;
    }

    public Integer createPerson(PersonDTO personDTO){

        PersonVO personVO = mapper.proccesPersonDTOVO(personDTO);
        PersonDAO personDAO = PersonDAO.PersonDAOFactory.getInstance();
        Integer id = personDAO.createPerson(personVO);

        return id;
    }

    public Integer findPhoneByPhoneNumber(String phoneNumber){

        PersonDAO personDAO = PersonDAO.PersonDAOFactory.getInstance();
        Integer phoneId = personDAO.findPhoneByPhoneNumber(phoneNumber);

        return phoneId;
    }

    public Integer addNewPhoneToPerson(PersonDTO personDTO){

        PersonVO personVO = mapper.proccesPersonDTOVO(personDTO);
        PersonDAO personDAO = PersonDAO.PersonDAOFactory.getInstance();
        PersonVO personVO1 = personDAO.addNewPhoneToPerson(personVO);

        return personVO1.getId();


    }
}
