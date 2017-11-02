package agendaapp.bussiness.person;

import agendaapp.dto.PersonDTO;
import agendaapp.persistence.dao.DAOFactory;
import agendaapp.persistence.dao.person.PersonDAO;
import agendaapp.persistence.vo.PersonVO;
import org.apache.commons.lang3.Validate;

public class PersonChecker {

	private final PersonDAO personDAO = DAOFactory.getPersonDAO();

	public boolean existsWithSameCompleteName(PersonDTO personDTO) {
		Validate.notNull(personDTO, "personDRO cannot be null");
		final PersonVO personVO = personDAO.findPersonByStrictName(personDTO.getNombre(), personDTO.getApellido1(), personDTO.getApellido2());
		return personVO != null;
	}

}
