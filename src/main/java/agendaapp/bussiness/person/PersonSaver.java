package agendaapp.bussiness.person;

import agendaapp.bussiness.phone.PhoneChecker;
import agendaapp.dto.PersonDTO;
import agendaapp.dto.PhoneDTO;
import agendaapp.persistence.dao.DAOFactory;
import agendaapp.persistence.dao.person.PersonDAO;
import agendaapp.persistence.mapper.PersonMapper;
import agendaapp.persistence.vo.PersonVO;

import java.util.Collections;
import java.util.Iterator;

public class PersonSaver {

	private final PersonDAO personDAO = DAOFactory.getPersonDAO();
	private final PersonChecker personChecker = new PersonChecker();
	private final PhoneChecker phoneChecker = new PhoneChecker();

	public void savePerson(PersonDTO personDTO) {

		final boolean personExists = personChecker.existsWithSameCompleteName(personDTO);
		if(personExists) {
			throw new IllegalArgumentException("personExists person with same complete name");
		}

		Iterator<PhoneDTO> personsIt = personDTO.getPhoneDTOS().iterator();
		if(personsIt.hasNext()) {
			PhoneDTO phoneDTO = personsIt.next();
			boolean exists = phoneChecker.existsPhoneNumber(phoneDTO.getPhoneNumber());

			if(exists) {
				final PersonVO personVO = PersonMapper.mapToVO(personDTO);
				personVO.setPhoneVOS(Collections.emptySet());
				personDAO.persist(personVO);


			} else {

			}
		}

	}
}
