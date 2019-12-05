import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class HomeController extends Main {

    public void buttonLesson1() throws Exception {
        current_lesson = 1;
        window.setTitle("AlgaT - Lesson 1");
        Parent tutorialLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/Lessons/Lesson1.fxml"));
        Scene tutorialScene = new Scene(tutorialLayout);
        window.setScene(tutorialScene);
    }

    public void buttonLesson2() throws Exception{
        if (!ok_lesson2) {
            current_lesson = 2;
            window.setTitle("AlgaT - Lesson 2");
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
            window.setTitle("AlgaT - Lesson 3");
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
            window.setTitle("AlgaT - Exercise 1");
            Parent exerciseLayout = FXMLLoader.load(getClass().getResource("Fxml_file/Tests/Esercitazione_1.fxml"));
            Scene exerciselScene = new Scene(exerciseLayout);
            window.setScene(exerciselScene);
        }
        else{
            setError_window(3);
        }
    }

    public void buttonExercise2() throws Exception{
        if(ok_exercise2 && ok_lesson2) {
            current_exercise = 2;
            window.setTitle("AlgaT - Exercise 2");
            Parent exerciseLayout = FXMLLoader.load(getClass().getResource("Fxml_file/Tests/Esercitazione_2.fxml"));
            Scene exerciselScene = new Scene(exerciseLayout);
            window.setScene(exerciselScene);
        }
        else{
            setError_window(4);
        }
    }

    public void buttonExercise3() throws Exception{
        if (ok_exercise3 && ok_lesson3) {
            current_exercise = 3;
            window.setTitle("AlgaT - Exercise 3");
            Parent exerciseLayout = FXMLLoader.load(getClass().getResource("Fxml_file/Tests/Esercitazione_3.fxml"));
            Scene exerciselScene = new Scene(exerciseLayout);
            window.setScene(exerciselScene);
        }
        else{
            setError_window(5);
        }
    }

}
