package agendaapp.persistence.dao.phone;

import agendaapp.dto.PhoneDTO;
import agendaapp.persistence.dao.BaseDAO;
import agendaapp.persistence.mapper.PhoneMapper;
import agendaapp.persistence.vo.PersonVO;
import agendaapp.persistence.vo.PhoneVO;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

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
