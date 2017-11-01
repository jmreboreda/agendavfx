package agendaapp.bussiness.person;

import agendaapp.dto.PersonDTO;
import agendaapp.persistence.dao.DAOFactory;
import agendaapp.persistence.dao.person.PersonDAO;
import agendaapp.persistence.mapper.PersonMapper;
import agendaapp.persistence.vo.PersonVO;

public class PersonSaver {

	private final PersonDAO personDAO = DAOFactory.getPersonDAO();
	private final PersonChecker personChecker = new PersonChecker();

	public void savePerson(PersonDTO personDTO) {

		final boolean exists = personChecker.existsWithSameCompleteName(personDTO);
		if(exists) {
			throw new IllegalArgumentException("exists person with same complete name");
		}

		final PersonVO personVO = PersonMapper.mapToVO(personDTO);
		personDAO.persist(personVO);
	}

}
