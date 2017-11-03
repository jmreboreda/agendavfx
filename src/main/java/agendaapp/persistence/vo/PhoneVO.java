package agendaapp.persistence.vo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "PHONE")
@NamedQueries({
        @NamedQuery(
                name = PhoneVO.FIND_PHONE_BY_PHONE_NUMBER,
                query = " select p from PhoneVO as p " +
                        " where p.phoneNumber = :phoneNumber "
        )
})
public class PhoneVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String phoneNumber;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PERSONID")
    private PersonVO personVO;

    public static final String FIND_PHONE_BY_PHONE_NUMBER = "PhoneVO.FIND_PHONE_BY_PHONE_NUMBER";
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PersonVO getPersonVO() {
        return personVO;
    }

    public void setPersonVO(PersonVO personVO) {
        this.personVO = personVO;
    }

}