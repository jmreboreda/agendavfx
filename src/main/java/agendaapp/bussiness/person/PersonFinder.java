package agendaapp.bussiness.person;

import agendaapp.dto.PersonDTO;
import agendaapp.persistence.dao.DAOFactory;
import agendaapp.persistence.dao.person.PersonDAO;
import agendaapp.persistence.mapper.PersonMapper;
import agendaapp.persistence.vo.PersonVO;

import java.util.ArrayList;
import java.util.List;

public class PersonFinder {

	private final PersonDAO personDAO = DAOFactory.getPersonDAO();

	public PersonDTO findPersonById(Integer id) {
		final PersonVO personVO = personDAO.findPersonById(id);
		return PersonMapper.mapToDTO(personVO);
	}

	public List<PersonDTO> findAllFromNamePatternInAlphabeticalOrder(String namePattern) {
		final List<PersonVO> allPersonVOs = personDAO.findAllPersonFromNamePatternInAlphabeticalOrder(namePattern);

		final List<PersonDTO> allPersonDTOs = new ArrayList<>();
		for(PersonVO vo : allPersonVOs) {
			final PersonDTO dto = PersonMapper.mapToDTO(vo);
			allPersonDTOs.add(dto);
		}
		return allPersonDTOs;
	}

	public PersonDTO findPersonByStrictName(PersonDTO personDTO) {
		final PersonVO personByStrictName = personDAO.findPersonByStrictName(personDTO.getNombre(), personDTO.getApellido1(), personDTO.getApellido2());
		return PersonMapper.mapToDTO(personByStrictName);
	}

}