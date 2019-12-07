import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class HomeController extends Main {

    public void buttonLesson1() throws Exception {
        current_lesson = 1;
        setTemplate("/Fxml_file/Lessons/Lesson1.fxml");
    }

    public void buttonLesson2() throws Exception{
            if (ok_lesson2) {
                current_lesson = 2;
                setTemplate("Fxml_file/Lessons/Lesson2.fxml");
            } else
                setError_window(1);
    }

    public void buttonLesson3() throws Exception{
            if (ok_lesson3) {
                current_lesson = 3;
                setTemplate("/Fxml_file/Lessons/Lesson3.fxml");
            } else
                setError_window(2);
    }


    public void buttonExercise1() throws Exception {
        if(ok_exercise1){
            current_exercize = 1;
            setTemplate("Fxml_file/Tests/TypeTest_1.fxml");
        }
        else
            setError_window(3);
    }

    public void buttonExercise2() throws Exception{
        if(ok_exercise2 && ok_lesson2) {
            current_exercize = 2;
            setTemplate("Fxml_file/Tests/TypeTest_1.fxml");
        }
        else
            setError_window(4);
    }

    public void buttonExercise3() throws Exception{
        if (ok_exercise3 && ok_lesson3) {
            current_exercize = 3;
            setTemplate("Fxml_file/Tests/TypeTest_1.fxml");
        }
        else
            setError_window(5);
    }

    private void setTemplate(String s) throws java.io.IOException {
        Parent exerciseLayout = FXMLLoader.load(getClass().getResource(s));
        Scene exerciselScene = new Scene(exerciseLayout);
        window.setScene(exerciselScene);
    }

}
