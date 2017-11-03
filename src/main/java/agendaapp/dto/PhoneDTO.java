package agendaapp.dto;

import java.util.Set;

public class PhoneDTO {

    private Integer id;
    private String phoneNumber;
    private PersonDTO personDTO;

    public PhoneDTO(Integer id, String phoneNumber, PersonDTO personDTO) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.personDTO = personDTO;
    }

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

    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    public void setPersonDTO(PersonDTO personDTO) {
        this.personDTO = personDTO;
    }

    @Override
    public String toString(){
        return phoneNumber;
    }

    public static PhoneDTO.PhoneBuilder create() {
        return new PhoneDTO.PhoneBuilder();
    }

    public static class PhoneBuilder {

        private Integer id;
        private String phoneNumber;
        private PersonDTO personDTO;

        public PhoneDTO.PhoneBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public PhoneDTO.PhoneBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public PhoneDTO.PhoneBuilder withPersonDTO(PersonDTO personDTO) {
            this.personDTO = personDTO;
            return this;
        }

        public PhoneDTO build() {
            return new PhoneDTO(this.id, this.phoneNumber, this.personDTO);
        }
    }
}
