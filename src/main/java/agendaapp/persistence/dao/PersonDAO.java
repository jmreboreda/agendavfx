package agendaapp.persistence.dao;



import agendaapp.persistence.vo.PersonVO;
import agendaapp.persistence.vo.PhoneVO;
import agendaapp.utilities.HibernateUtil;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.List;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class PersonDAO {
    
    private SessionFactory sessionFactory;
    private Session session;
    
    public static String FIND_PERSON_BY_ID = "FROM PersonVO WHERE id = :code";
    public static String FIND_ALL_PERSON_IN_ALPHABETICAL_ORDER = "FROM PersonVO ORDER BY apellido1, apellido2, nombre";  
    public static String FIND_ALL_PERSON_BY_NAME_PATTERN_IN_ALPHABETICAL_ORDER =
            "FROM PersonVO WHERE LOWER(apellido1) LIKE :code OR LOWER(apellido2) LIKE :code OR LOWER(nombre) LIKE :code ORDER BY apellido1, apellido2, nombre";
    public static String FIND_PERSON_BY_STRICT_NAME = "FROM PersonVO WHERE apellido1 = :code1 AND apellido2 = :code2 AND nombre = :code3";

    public static String FIND_PHONE_BY_PHONE_NUMBER = "FROM PhoneVO WHERE phoneNUmber = :phoneNumber";

    public static String UPDATE_PERSON_BY_NEW_PHONE = "";

    public PersonDAO() {
    }
    
     public static class PersonDAOFactory {
        
        private static PersonDAO personDAO;
        
        public static PersonDAO getInstance() {
            if(personDAO == null) {
                personDAO = new PersonDAO(HibernateUtil.retrieveGlobalSession());
            }
            return personDAO;
        }
        
    }
     
    public PersonDAO(Session session) {
        this.session = session;
    }

    public Integer createPerson(PersonVO personVO) {

        try {
            session.beginTransaction();
            session.save(personVO);
            session.getTransaction().commit();
        }
        catch (org.hibernate.exception.ConstraintViolationException cve){

            String message = "El teléfono " + personVO.getPhoneVOS();
            showMessageDialog(null, message,"Errores detectados",WARNING_MESSAGE);
            System.out.println(cve.getMessage());
            personVO.setId(-1);
        }
            return personVO.getId();
    }

    public Integer createNewPersonWithPhone(PersonVO personVO){

        session.beginTransaction();
        session.save(personVO);
        session.getTransaction().commit();
        
        return personVO.getId();
        
    }

    public PersonVO addNewPhoneToPerson(PersonVO personVO){

        session.beginTransaction();
        session.merge(personVO);
        session.getTransaction().commit();

        return personVO;

    }

    public Integer createPhone(String phoneNumber){

        PhoneVO phoneVO = new PhoneVO();
        phoneVO.setPhoneNumber(phoneNumber);

        session.beginTransaction();
        session.save(phoneVO);
        session.getTransaction().commit();

        return phoneVO.getId();
    }

    public Integer findPhoneByPhoneNumber(String phoneNumber){

        List<PhoneVO> phoneVOList;
        Integer id = null;

        Query query = session.createQuery(FIND_PHONE_BY_PHONE_NUMBER);
        query.setParameter("phoneNumber", phoneNumber);
        phoneVOList = query.getResultList();
        for(PhoneVO phoneVO : phoneVOList) {
            id = phoneVO.getId();
        }

        return id;

    }

    public PersonVO findPersonById(PersonVO personVO){

        Integer personId = personVO.getId();
        Query query = session.createQuery(FIND_PERSON_BY_ID);
        query.setParameter("code", personId);
        PersonVO pVO = (PersonVO) query.getSingleResult();

        return pVO;
    }
     
    public List<PersonVO> findAllPersonInAlphabeticalOrder(){
        
        Query query = session.createQuery(FIND_ALL_PERSON_IN_ALPHABETICAL_ORDER);
        List<PersonVO> personVOList = query.getResultList();
        
        return personVOList;
    }

    public List<PersonVO> findAllPersonByNamePatternInAlphabeticalOrder(String nameLetters){

        Query query = session.createQuery(FIND_ALL_PERSON_BY_NAME_PATTERN_IN_ALPHABETICAL_ORDER);
        query.setParameter("code", "%" + nameLetters.toLowerCase() + "%");
        List<PersonVO> personVOList = query.getResultList();

        return personVOList;
    }

    public Integer findPersonByStrictName(PersonVO personVO){

        List<PersonVO> personVOList;
        Integer personId = null;

        Query query = session.createQuery(FIND_PERSON_BY_STRICT_NAME)
            .setParameter("code1", personVO.getApellido1())
            .setParameter("code2", personVO.getApellido2())
            .setParameter("code3", personVO.getNombre());

            personVOList = query.getResultList();
            for(PersonVO pVO : personVOList){
                personId = pVO.getId();
            }


        return personId;

    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
