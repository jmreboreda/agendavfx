package agendaapp.persistence.vo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Person", uniqueConstraints = {
		@UniqueConstraint(
				name = "UNIQUE_STRICT_NAME",
				columnNames = {"NOMBRE", "APELLIDO1", "APELLIDO2"}
		)
})
@NamedQueries({
		@NamedQuery(
				name = PersonVO.FIND_ALL_PERSON_BY_NAME_PATTERN_IN_ALPHABETICAL_ORDER,
				query = " select p from PersonVO as p " +
						" where lower(p.apellido1) like lower(:code) " +
						" or lower(p.apellido2) like lower(:code) " +
						" or lower(p.nombre) like lower(:code) "+
						" order by p.apellido1, p.apellido2, p.nombre "
		),
		@NamedQuery(
				name = PersonVO.FIND_PERSON_BY_STRICT_NAME,
				query = " select p from PersonVO as p " +
						" where p.apellido1 = :apellido1 " +
						" and p.apellido2 = :apellido2 " +
						" and p.nombre = :nombre"
		)
})
public class PersonVO implements Serializable {

	public static final String FIND_ALL_PERSON_BY_NAME_PATTERN_IN_ALPHABETICAL_ORDER = "PersonVO.FIND_ALL_PERSON_BY_NAME_PATTERN_IN_ALPHABETICAL_ORDER";

	public static final String FIND_PERSON_BY_STRICT_NAME = "PersonVO.FIND_PERSON_BY_STRICT_NAME";

	private Integer id;
    private String apellido1;
    private String apellido2;
    private String nombre;
    private Set<PhoneVO> phoneVOS;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PersonPhone", joinColumns = { @JoinColumn(name = "personId") }, inverseJoinColumns = { @JoinColumn(name = "phoneId") })
    public Set<PhoneVO> getPhoneVOS() {
        return phoneVOS;
    }

    public void setPhoneVOS(Set<PhoneVO> phoneVOS) {
        this.phoneVOS = phoneVOS;}

    @Override
    public String toString(){
        return apellido1 + " " + apellido2 + ", " + nombre;
    }

}