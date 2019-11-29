import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class HomeController extends Main {

    private static Stage error_window = new Stage();
    @FXML private Label ErrorLabel;
    @FXML private Button okButton;



    public void buttomLesson1(ActionEvent event) throws Exception{
        current_lesson = 1;
        Parent tutorialLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/Lesson1.fxml"));
        Scene tutorialScene = new Scene(tutorialLayout);
        window.setScene(tutorialScene);
    }

    public void buttomLesson2(ActionEvent event) throws Exception{
        if(ok_lesson2) {
            current_lesson = 2;
            Parent tutorialLayout = FXMLLoader.load(getClass().getResource("Fxml_file/Lesson2.fxml"));
            Scene tutorialScene = new Scene(tutorialLayout);
            window.setScene(tutorialScene);
        }
        else{
            setError_window(0);
        }
    }

    public void buttomLesson3(ActionEvent event) throws Exception{
        if (ok_lesson3) {
            current_lesson = 3;
            Parent tutorialLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/Lesson3.fxml"));
            Scene tutorialScene = new Scene(tutorialLayout);
            window.setScene(tutorialScene);
        }
        else{
            setError_window(1);
        }
    }


    private void setError_window(Integer i) throws IOException {
            error_window.setX(230);
            error_window.setY(300);
            error_window.setWidth(200);
            error_window.setHeight(200);
            error_window.setResizable(false);
            error_window.setAlwaysOnTop(true);
            Parent simulatorLayout = FXMLLoader.load(getClass().getResource("Fxml_file/ErrorLesson.fxml"));
            Scene simulatorScene = new Scene(simulatorLayout);
            error_window.setScene(simulatorScene);
            error_window.show();
    }


    public void closeErrorWindow(ActionEvent event){
        error_window.close();
    }

}
