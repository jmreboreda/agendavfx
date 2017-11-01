package agendaapp.persistence.dao.person;


import agendaapp.persistence.dao.BaseDAO;
import agendaapp.persistence.vo.PersonVO;
import org.hibernate.Query;

import java.util.List;


public class PersonDAO extends BaseDAO<PersonVO,Integer> {

    public PersonVO findPersonById(Integer id){
        return super.find(id);
    }

    public List<PersonVO> findAllPersonInAlphabeticalOrder(){
        return super.findAll();
    }

    @SuppressWarnings("unchecked")
    public List<PersonVO> findAllPersonFromNamePatternInAlphabeticalOrder(String nameLetters){
        final String pattern = "%" + nameLetters.toLowerCase() + "%";
        final Query query = session.getNamedQuery(PersonVO.FIND_ALL_PERSON_BY_NAME_PATTERN_IN_ALPHABETICAL_ORDER)
                .setParameter("code", pattern);
        return (List<PersonVO>) query.list();
    }

    public PersonVO findPersonByStrictName(String name, String firstName1, String firstName2){

        final Query query = session.getNamedQuery(PersonVO.FIND_PERSON_BY_STRICT_NAME)
            .setParameter("nombre", name)
            .setParameter("apellido1", firstName1)
            .setParameter("apellido2", firstName2);

        return (PersonVO) query.uniqueResult();
    }

}
