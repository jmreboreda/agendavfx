package agendaapp.bussiness.person.exceptions;

public class PersonAlreadyExistsException extends Exception {

    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}
