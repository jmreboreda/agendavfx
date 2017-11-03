package agendaapp.persistence.mapper;

import agendaapp.dto.PersonDTO;
import agendaapp.dto.PhoneDTO;
import agendaapp.persistence.vo.PersonVO;
import agendaapp.persistence.vo.PhoneVO;

import java.util.HashSet;
import java.util.Set;

public class PhoneMapper {

	public static PhoneVO mapToVO(PhoneDTO phoneDTO){

		PhoneVO phoneVO = new PhoneVO();
		phoneVO.setId(phoneDTO.getId());
		phoneVO.setPhoneNumber(phoneDTO.getPhoneNumber());
		//phoneVO.setPersonVO(PersonMapper.mapToVO(phoneDTO.getPersonDTO()));
		return phoneVO;
	}

	public static PhoneDTO mapToDTO(PhoneVO phoneVO){

		PersonDTO personDTOIdOnly = PersonDTO.create()
				.withId(phoneVO.getId())
				.build();

		PhoneDTO phoneDTO = PhoneDTO.create()
				.withId(phoneVO.getId())
				.withPhoneNumber(phoneVO.getPhoneNumber())
				.withPersonDTO(personDTOIdOnly)
				.build();

		return phoneDTO;
	}

	public static Set<PhoneDTO> mapVOSToDTOS(Set<PhoneVO> phoneVOS){

		Set<PhoneDTO> phoneDTOS = new HashSet<>();
		for(PhoneVO phoneVO : phoneVOS){
			//PhoneDTO phoneDTO = new PhoneDTO();
			PhoneDTO phoneDTO = PhoneDTO.create()
					.withId(phoneVO.getId())
					.withPhoneNumber(phoneVO.getPhoneNumber())
					.withPersonDTO(PersonMapper.mapToDTO(phoneVO.getPersonVO()))
					.build();
			phoneDTOS.add(phoneDTO);
		}

		return phoneDTOS;
	}


}
