package agendaapp.persistence.dao;

import agendaapp.persistence.dao.person.PersonDAO;
import agendaapp.persistence.dao.phone.PhoneDAO;

public class DAOFactory {

	private static PersonDAO personDAO;
	private static PhoneDAO phoneDAO;

	public static PersonDAO getPersonDAO() {
		if(personDAO == null) {
			personDAO = new PersonDAO();
		}
		return personDAO;
	}

	public static PhoneDAO getPhoneDAO() {
		if(phoneDAO == null) {
			phoneDAO = new PhoneDAO();
		}
		return phoneDAO;
	}

}
