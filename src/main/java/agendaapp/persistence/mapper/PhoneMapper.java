package agendaapp.persistence.mapper;

import agendaapp.dto.PhoneDTO;
import agendaapp.persistence.vo.PhoneVO;

public class PhoneMapper {

	public static PhoneVO mapToVO(PhoneDTO phoneDTO){

		PhoneVO phoneVO = new PhoneVO();
		phoneVO.setId(phoneDTO.getId());
		phoneVO.setPhoneNumber(phoneDTO.getPhoneNumber());

		return phoneVO;
	}

	public static PhoneDTO mapToDTO(PhoneVO phoneVO){

		PhoneDTO phoneDTO = new PhoneDTO();
		phoneDTO.setId(phoneVO.getId());
		phoneDTO.setPhoneNumber(phoneVO.getPhoneNumber());

		return phoneDTO;
	}


}
