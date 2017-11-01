package agendaapp.bussiness.phone;

import agendaapp.persistence.dao.DAOFactory;
import agendaapp.persistence.dao.phone.PhoneDAO;
import agendaapp.persistence.vo.PhoneVO;

public class PhoneChecker {

	final PhoneDAO phoneDAO = DAOFactory.getPhoneDAO();

	public Boolean existsPhoneNumber(String phoneNumber) {
		final PhoneVO byPhoneNumber = phoneDAO.findByPhoneNumber(phoneNumber);
		return byPhoneNumber != null;
	}

}
