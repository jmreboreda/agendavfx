package agendaapp.persistence.dao.phone;

import agendaapp.persistence.dao.BaseDAO;
import agendaapp.persistence.mapper.PhoneMapper;
import agendaapp.persistence.vo.PhoneVO;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class PhoneDAO extends BaseDAO<PhoneVO,Integer>{

	public PhoneVO findByPhoneNumber(String phoneNumber) {
		final Query query = session.getNamedQuery(PhoneVO.FIND_PHONE_BY_PHONE_NUMBER)
				.setParameter("phoneNumber", phoneNumber);

		final PhoneVO phoneVO = (PhoneVO) query.getSingleResult();

		try {
			return phoneVO;
		}catch(NoResultException e){
			return null;
		}
	}

}
