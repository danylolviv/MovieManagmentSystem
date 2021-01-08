package sample.Gui.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Gui.Models.MovieModel;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddPathViewController implements Initializable {
    @FXML
    private AnchorPane anchorid;
    @FXML
    private TextField pathToMovie;
    @FXML
    private TextField yearField;
    @FXML
    private DatePicker releaseDateField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextField movieTitleField;

    private MovieModel mModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addMovie(){
        String title = movieTitleField.getText();
        String year = yearField.getText();
        LocalDate date = releaseDateField.getValue();
        //String category
        String description = descriptionField.getText();
        String moviePath = pathToMovie.getText();

        mModel.addMovie(title,year, date, description, moviePath);

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

    public void openIt(ActionEvent actionEvent)  {
        addMovie();
    }
}
