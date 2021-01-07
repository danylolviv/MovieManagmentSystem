package sample.Gui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoriesController implements Initializable {
    public AnchorPane anchorid;
    public TextField pathToMovie;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }

    private File randomFile;

    public void findFile(ActionEvent actionEvent) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("OpenFileDialog");
        Stage stage = (Stage) anchorid.getScene().getWindow();
        File fl = fileChooser.showOpenDialog(stage);
        randomFile = fl;



        if (fl != null){
            System.out.println("Path : " + fl.getPath() );
            pathToMovie.setText(fl.getPath());
        }

    }

    public void openIt(ActionEvent actionEvent) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(randomFile);
    }
}
