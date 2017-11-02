package agendaapp.bussiness.phone;

import agendaapp.persistence.dao.DAOFactory;
import agendaapp.persistence.dao.phone.PhoneDAO;

public class PhoneCreator {

	private final PhoneDAO phoneDAO = DAOFactory.getPhoneDAO();
}
