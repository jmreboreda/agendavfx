package agendaapp.persistence.mapper;

import agendaapp.dto.PersonDTO;
import agendaapp.dto.PhoneDTO;
import agendaapp.persistence.vo.PersonVO;
import agendaapp.persistence.vo.PhoneVO;

import java.util.HashSet;
import java.util.Set;

public class PersonMapper {

    public static PersonVO mapToVO(PersonDTO personDTO){

        PersonVO personVO = new PersonVO();
        personVO.setApellido1(personDTO.getApellido1());
        personVO.setApellido2(personDTO.getApellido2());
        personVO.setNombre(personDTO.getNombre());

        Set<PhoneVO> phoneVOS = new HashSet<>();
        for(PhoneDTO phoneDTO : personDTO.getPhoneDTOS()){
            final PhoneVO phoneVO = PhoneMapper.mapToVO(phoneDTO);
            phoneVO.setPersonVO(personVO);
            phoneVOS.add(phoneVO);
        }

        personVO.setPhoneVOS(phoneVOS);
        return personVO;
    }

    public static PersonDTO mapToDTO(PersonVO personVO){

        Set<PhoneDTO> phoneDTOS = new HashSet<>();

        for(PhoneVO phoneVO : personVO.getPhoneVOS()){
            phoneDTOS.add(PhoneMapper.mapToDTO(phoneVO));
        }

        return PersonDTO.create()
                .withId(personVO.getId())
                .withApellido1(personVO.getApellido1())
                .withApellido2(personVO.getApellido2())
                .withNombre(personVO.getNombre())
                .withPhones(phoneDTOS)
                .build();
    }

}
