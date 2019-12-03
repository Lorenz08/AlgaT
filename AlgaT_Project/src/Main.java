import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Main extends Application {

    public static Stage window;
    private static Stage new_window = new Stage();
    //"puntatore" alla lezione corrente
    public static Integer current_lesson = 0;
    //"puntatore" all'esercitazione corrente

    public static Integer current_exercise = 0;

    public static boolean b = true;
    public static boolean ok_lesson2 = false;
    public static boolean ok_lesson3 = false;
    public static boolean ok_exercise1 = false;
    public static boolean ok_exercise2 = false;
    public static boolean ok_exercise3 = false;




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
        window.setOnCloseRequest(event -> simulator_window.close());
        window.setOnCloseRequest(event -> error_window.close());
        Parent initialLayout = FXMLLoader.load(getClass().getResource("Fxml_file/InitialScene.fxml"));
        Scene initialScene = new Scene(initialLayout);
        window.setScene(initialScene);
        window.show();
    }

    public void testButton(ActionEvent event) throws Exception {
        Parent simulatorLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/Test/Esercitazione_1.fxml"));
        Scene simulatorScene = new Scene(simulatorLayout);
        window.setScene(simulatorScene);
    }


    public void simulatorButton(ActionEvent event) throws Exception {
        if(!errorWindowIsActive) {
            if (!simulatorIsActive) {
                simulatorIsActive = true;
                simulator_window.setX(650);
                simulator_window.setY(150);
                simulator_window.setWidth(600);
                simulator_window.setHeight(500);
                simulator_window.setResizable(false);
                simulator_window.setOnCloseRequest(event1 -> simulatorIsActive = false);
                Parent simulatorLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/SimulatorScene.fxml"));
                Scene simulatorScene = new Scene(simulatorLayout);
                simulator_window.setScene(simulatorScene);
                simulator_window.show();
            } else {
                simulator_window.close();
                simulatorIsActive = false;
            }
        }
    }


    public void homeButton(ActionEvent event) throws Exception{
        if(!errorWindowIsActive) {
            Parent simulatorLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/InitialScene.fxml"));
            Scene simulatorScene = new Scene(simulatorLayout);
            window.setScene(simulatorScene);
        }
    }

    public void setError_window(Integer i) throws IOException {
        Parent simulatorLayout = FXMLLoader.load(getClass().getResource("Fxml_file/ErrorLesson.fxml"));
        Scene simulatorScene = new Scene(simulatorLayout);
        error_window.setScene(simulatorScene);
        error_window.setX(250);
        error_window.setY(300);
        error_window.setWidth(220);
        error_window.setHeight(220);
        error_window.setTitle("Error window");
        error_window.setResizable(false);
        error_window.setAlwaysOnTop(true);
        error_window.show();
        error_window.setOnCloseRequest(event -> errorWindowIsActive = false);
        errorWindowIsActive = true;
    }

    private void setMessageError(Integer i, Label label) throws IOException {
        InputStream file_to_open = getClass().getResourceAsStream("Text_file/ErrorMessage");  //seleziona il file txt da aprire
        InputStreamReader file_decode = new InputStreamReader(file_to_open);    //trasforma il contenuto del file che apre da bit TxtLesson2 caratteri ASCII
        BufferedReader file_to_read = new BufferedReader(file_decode);    //legge e bufferizza i caratteri letti da uno stream di caratteri in input
        String line_to_add;
        while ((line_to_add = file_to_read.readLine()) != null) {
            if (line_to_add.startsWith(i.toString() + ":")) {
                line_to_add = line_to_add.replace(i.toString() + ":", "");
                label.setText(line_to_add);
            }
        }
    }
}
