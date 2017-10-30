/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaapp.dto;

import java.util.HashSet;
import java.util.Set;


public class PersonDTO {
 
    private Integer id;
    private final String apellido1;
    private final String apellido2;
    private final String nombre;
    private Set<PhoneDTO> phoneDTOS;

    private PersonDTO(Integer id, String apellido1, String apellido2, String nombre, Set<PhoneDTO> phoneDTOS) {
        this.id = id;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nombre = nombre;
        this.phoneDTOS = phoneDTOS;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<PhoneDTO> getPhoneDTOS() {
        return phoneDTOS;
    }

    public void setPhoneDTOS(Set<PhoneDTO> phoneDTOS) {
        this.phoneDTOS = phoneDTOS;
    }
    
    @Override
    public String toString() {
        return getApellido1() + " " + getApellido2() + ", " + getNombre();
    }

    public static PersonDTO.PersonBuilder create() {
        return new PersonDTO.PersonBuilder();
    }

    public static class PersonBuilder {

        private Integer id;
        private String apellido1;
        private String apellido2;
        private String nombre;
        private Set<PhoneDTO> phoneDTOS = new HashSet<>();

        public PersonDTO.PersonBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public PersonDTO.PersonBuilder withApellido1(String apellido1) {
            this.apellido1 = apellido1;
            return this;
        }

        public PersonDTO.PersonBuilder withApellido2(String apellido2) {
            this.apellido2 = apellido2;
            return this;
        }

        public PersonDTO.PersonBuilder withNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public PersonDTO.PersonBuilder withPhones(Set<PhoneDTO> phoneDTOs) {
            this.phoneDTOS = phoneDTOs;
            return this;
        }

        public PersonDTO build() {
            return new PersonDTO(this.id, this.apellido1, this.apellido2, this.nombre, this.phoneDTOS);
        }
    }
}