package agendaapp.persistence.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Phone", uniqueConstraints = {
        @UniqueConstraint(
                name = "UNIQUE_PHONE_NUMBER",
                columnNames = {"PHONENUMBER"}
        )
})
@NamedQueries({
        @NamedQuery(
                name = PhoneVO.FIND_PHONE_BY_PHONE_NUMBER,
                query = " select p from PhoneVO as p " +
                        " where p.phoneNumber = :phoneNumber "
        )
})
public class PhoneVO implements Serializable {
    
    private Integer id;
    private String phoneNumber;
    private Set<PersonVO> personVOS = new HashSet<>();

    public static final String FIND_PHONE_BY_PHONE_NUMBER = "PhoneVO.FIND_PHONE_BY_PHONE_NUMBER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @ManyToMany(mappedBy="phoneVOS")
    public Set<PersonVO> getPersonVOS() {
        return personVOS;
    }

    public void setPersonVOS(Set<PersonVO> personVOS) {
        this.personVOS = personVOS;
    }

}