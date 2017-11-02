package agendaapp.utilities;

import agendaapp.dto.PersonDTO;
import javafx.scene.control.Alert;

public class MessageDialog {

    public void newPersonAdded(PersonDTO personDTO, String phoneNumber){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información del sistema");
        alert.setHeaderText(null);
        alert.setContentText("Se ha creado la persona \n"
               + personDTO.toString() + ", con el teléfono " + phoneNumber);

        alert.showAndWait();
    }

    public void existsPerson(PersonDTO personDTO){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información del sistema");
        alert.setHeaderText(null);
        alert.setContentText("Ya existe la persona " + personDTO.toString() + ". \n"
                + "Añádale el nuevo teléfono el registro existente.");

        alert.showAndWait();

    }
}
