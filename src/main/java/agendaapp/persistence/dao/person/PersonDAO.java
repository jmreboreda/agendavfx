package agendaapp.persistence.dao.person;


import agendaapp.persistence.dao.BaseDAO;
import agendaapp.persistence.vo.PersonVO;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;


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
        return (List<PersonVO>) query.getResultList();
    }

    public PersonVO findPersonByStrictName(String name, String firstName1, String firstName2){

        final Query query = session.getNamedQuery(PersonVO.FIND_PERSON_BY_STRICT_NAME)
            .setParameter("nombre", name)
            .setParameter("apellido1", firstName1)
            .setParameter("apellido2", firstName2);

        try {
            return (PersonVO) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public PersonVO findPhonesByPersonId(Integer personId){
        final Query query = session.getNamedQuery(PersonVO.FIND_PHONES_BY_PERSONID)
                .setParameter("personId", personId);

        try {
            return (PersonVO) query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
}
