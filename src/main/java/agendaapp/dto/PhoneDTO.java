/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaapp.dto;

import java.util.Set;

/**
 *
 * @author jmrb
 */
public class PhoneDTO {
    
    public PhoneDTO() {
 
     }
 
    private Integer id;
    private String phoneNumber;
    private Set<PersonDTO> personDTOS;

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

    public Set<PersonDTO> getPersonDTOS() {
        return personDTOS;
    }

    public void setPersonDTOS(Set<PersonDTO> personDTOS) {
        this.personDTOS = personDTOS;
    }

    @Override
    public String toString(){

        return phoneNumber;
    }
}
