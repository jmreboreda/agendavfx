package agendaapp.bussiness.person;

import agendaapp.bussiness.person.exceptions.PersonAlreadyExistsException;
import agendaapp.dto.PersonDTO;
import agendaapp.persistence.dao.DAOFactory;
import agendaapp.persistence.dao.person.PersonDAO;
import agendaapp.persistence.mapper.PersonMapper;
import agendaapp.persistence.vo.PersonVO;

public class PersonSaver {

	private final PersonDAO personDAO = DAOFactory.getPersonDAO();
	private final PersonChecker personChecker = new PersonChecker();

	public void savePerson(PersonDTO personDTO) throws PersonAlreadyExistsException {

		final boolean personExists = personChecker.existsWithSameCompleteName(personDTO);
		if (personExists) {
			final String errorMessage = "person " + personDTO.toString() + " already exists";
			throw new PersonAlreadyExistsException(errorMessage);
		}

		PersonVO personVO = PersonMapper.mapToVO(personDTO);
		personDAO.persist(personVO);
	}



}