package agendaapp.mapper;



import agendaapp.persistence.vo.PersonVO;
import agendaapp.persistence.vo.PhoneVO;
import agendaapp.dto.PersonDTO;
import agendaapp.dto.PhoneDTO;

import java.util.HashSet;
import java.util.Set;

public class PersonMapper {


    public PersonMapper() {
    }

    public PersonVO proccesPersonDTOVO(PersonDTO personDTO){

        Set<PhoneVO> phoneVOS = new HashSet<>();
        for(PhoneDTO phoneDTO : personDTO.getPhoneDTOS()){
            PhoneVO phoneVO = proccessPhoneDTOVO(phoneDTO);
            phoneVOS.add(phoneVO);
        }

        PersonVO personVO = new PersonVO();
            personVO.setId(personDTO.getId());
            personVO.setApellido1(personDTO.getApellido1());
            personVO.setApellido2(personDTO.getApellido2());
            personVO.setNombre(personDTO.getNombre());
            personVO.setPhoneVOS(phoneVOS);

        return personVO;

    }


    public PersonDTO proccessPersonVODTO(PersonVO personVO){

        Set<PhoneDTO> phoneDTOS = new HashSet<>();

        PersonDTO personDTO = PersonDTO.create()
                .withApellido1(personVO.getApellido1())
                .withApellido2(personVO.getApellido2())
                .withNombre(personVO.getNombre())
                .build();
        personDTO.setId(personVO.getId());
        for(PhoneVO phVO : personVO.getPhoneVOS()){
           PhoneDTO phDTO = new PhoneDTO();
           phDTO.setId(phVO.getId());
           phDTO.setPhoneNumber(phVO.getPhoneNumber());
           phoneDTOS.add(phDTO);
        }

        personDTO.setPhoneDTOS(phoneDTOS);

        return personDTO;
    }

    public PersonVO proccessIdToVO(Integer personId){

        PersonVO personVO = new PersonVO();
        personVO.setId(personId);

        return personVO;

    }

    public PhoneVO proccessPhoneDTOVO(PhoneDTO phoneDTO){

        PhoneVO phoneVO = new PhoneVO();
        phoneVO.setId(phoneDTO.getId());
        phoneVO.setPhoneNumber(phoneDTO.getPhoneNumber());

        return phoneVO;
    }

    public PhoneDTO proccessPhoneVODTO(PhoneVO phoneVO){

        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setId(phoneVO.getId());
        phoneDTO.setPhoneNumber(phoneVO.getPhoneNumber());
        System.out.println("PhoneDTO -> id = " + phoneDTO.getId() + " phoneNumber = "
        + phoneDTO.getPhoneNumber());

        return phoneDTO;
    }

}
