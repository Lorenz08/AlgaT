import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


//usiamo tre metodi diversi per poter cambiare la variabile current_lesson (usando quindi 3 file fxml simili anzichè uno solo)
public class HomeController extends Main {



    public void buttonLesson1() throws Exception {
            current_lesson = 1;
            Parent tutorialLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/Lessons/Lesson1.fxml"));
            Scene tutorialScene = new Scene(tutorialLayout);
            window.setScene(tutorialScene);
    }


    public void buttonLesson2() throws Exception{
            if (ok_lesson2) {
                current_lesson = 2;
                Parent tutorialLayout = FXMLLoader.load(getClass().getResource("Fxml_file/Lessons/Lesson2.fxml"));
                Scene tutorialScene = new Scene(tutorialLayout);
                window.setScene(tutorialScene);
            } else {
                setError_window(1);
            }
    }

    public void buttonLesson3() throws Exception{
            if (ok_lesson3) {
                current_lesson = 3;
                Parent tutorialLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/Lessons/Lesson3.fxml"));
                Scene tutorialScene = new Scene(tutorialLayout);
                window.setScene(tutorialScene);
            } else {
                setError_window(2);
            }
    }


    public void buttonExercise1() throws Exception {
        if(ok_exercise1){
            current_exercise = 1;
            Parent exerciseLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/Test/Esercitazione_1.fxml"));
            Scene exerciselScene = new Scene(exerciseLayout);
            window.setScene(exerciselScene);
        }
        else{
            setError_window(0);
        }
    }

    public void buttonExercise2() throws Exception{
        if(ok_exercise2 && ok_lesson2) {
            current_exercise = 2;
            Parent exerciseLayout = FXMLLoader.load(getClass().getResource("Fxml_file/Test/Esercitazione_1.fxml"));
            Scene exerciselScene = new Scene(exerciseLayout);
            window.setScene(exerciselScene);
        }
        else{
            setError_window(0);
        }
    }







    public void buttonExercise1(ActionEvent event) throws Exception {
        if(ok_exercise1){
            current_exercise = 1;
            Parent exerciseLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/Test/Esercitazione_1.fxml"));
            Scene exerciselScene = new Scene(exerciseLayout);
            window.setScene(exerciselScene);
        }
        else{
            setError_window(0);
        }
    }

    public void buttonExercise2(ActionEvent event) throws Exception{
        if(ok_exercise2 && ok_lesson2) {
            current_exercise = 2;
            Parent exerciseLayout = FXMLLoader.load(getClass().getResource("Fxml_file/Test/Esercitazione_1.fxml"));
            Scene exerciselScene = new Scene(exerciseLayout);
            window.setScene(exerciselScene);
        }
        else{
            setError_window(0);
        }
    }

    public void buttonExercise3(ActionEvent event) throws Exception{
        if (ok_exercise3 && ok_lesson3) {
            current_exercise = 3;
            Parent exerciseLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/Test/Esercitazione_1.fxml"));
            Scene exerciselScene = new Scene(exerciseLayout);
            window.setScene(exerciselScene);
        }
        else{
            setError_window(1);
        }
    }


}
