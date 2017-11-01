package agendaapp.persistence.dao.phone;

import agendaapp.persistence.dao.BaseDAO;
import agendaapp.persistence.vo.PhoneVO;

import javax.persistence.Query;

public class PhoneDAO extends BaseDAO<PhoneVO,Integer>{

	public PhoneVO findByPhoneNumber(String phoneNumber) {
		final Query query = session.createQuery(PhoneVO.FIND_PHONE_BY_PHONE_NUMBER)
				.setParameter("phoneNumber", phoneNumber);

		final PhoneVO phoneVO = (PhoneVO) query.getSingleResult();
		return phoneVO;
	}

}
