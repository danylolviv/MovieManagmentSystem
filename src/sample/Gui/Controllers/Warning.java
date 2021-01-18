package sample.Gui.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Warning {

    @FXML
    private Button close;

    public void closeWindow() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
