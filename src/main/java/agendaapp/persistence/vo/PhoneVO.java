/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaapp.persistence.vo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Phone")
public class PhoneVO implements Serializable {
    
    private Integer id;
    private String phoneNumber;
    private Set<PersonVO> personVOS = new HashSet<>();

    public static String FIND_PHONE_BY_PHONE_NUMBER = "FROM PhoneVO WHERE phoneNUmber = :phoneNumber";
    public static final String FIND_PHONE_BY_ID = "FROM PhoneVO WHERE id = :code ";
    
    public PhoneVO() {
 
	}
    
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