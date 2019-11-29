import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    public static Stage window;
    private static Stage new_window = new Stage();
    public static Integer current_lesson = 0;
    public static boolean b = true;
    public static boolean ok_lesson2 = false;
    public static boolean ok_lesson3 = false;




    public static void main(String[] args) {
        try {
            launch(args);
        }
        catch (Exception io)  {
            io.printStackTrace();
        }
    }


    public void start(Stage stage) throws Exception{
        window = stage;
        window.setTitle("AlgaT");
        window.setX(30);
        window.setY(150);
        window.setWidth(600);
        window.setHeight(500);
        window.setResizable(false);
        window.setOnCloseRequest(event -> new_window.close());
        Parent initialLayout = FXMLLoader.load(getClass().getResource("Fxml_file/InitialScene.fxml"));
        Scene initialScene = new Scene(initialLayout);
        window.setScene(initialScene);
        window.show();
    }


    public void simulatorButton(ActionEvent event) throws Exception {
        if (b){
            new_window.setX(650);
            new_window.setY(150);
            new_window.setWidth(600);
            new_window.setHeight(500);
            new_window.setResizable(false);
            new_window.setOnCloseRequest(event1 -> b = true);
            Parent simulatorLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/SimulatorScene.fxml"));
            Scene simulatorScene = new Scene(simulatorLayout);
            new_window.setScene(simulatorScene);
            new_window.show();
            b = false;
        }
        else {
            new_window.close();
            b = true;
        }
    }


    public void homeButton(ActionEvent event) throws Exception {
        Parent simulatorLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/InitialScene.fxml"));
        Scene simulatorScene = new Scene(simulatorLayout);
        window.setScene(simulatorScene);
    }

}

