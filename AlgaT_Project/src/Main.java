import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class Main extends Application  {

    protected static Stage window;
    protected static Stage error_window;
    protected static Stage simulator_window = new Stage();
    protected static boolean simulatorIsActive = false;

    protected static Integer current_lesson = 0;
    protected static Integer current_exercize = 0;

    protected static boolean ok_lesson2 = false;
    protected static boolean ok_lesson3 = false;

    protected static boolean ok_exercise1 = false;
    protected static boolean ok_exercise2 = false;
    protected static boolean ok_exercise3 = false;


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
        window.setHeight(600);
        window.setResizable(false);
        window.setOnCloseRequest(event -> simulator_window.close());
        Parent initialLayout = FXMLLoader.load(getClass().getResource("Fxml_file/InitialScene.fxml"));
        Scene initialScene = new Scene(initialLayout);
        window.setScene(initialScene);
        window.show();
    }


    public void simulatorButton() throws Exception {
        if (!simulatorIsActive) {
            simulatorIsActive = true;
            window.setX(0);
            simulator_window.setX(650);
            simulator_window.setY(60);
            simulator_window.setWidth(680);
            simulator_window.setHeight(600);
            simulator_window.setTitle("SIMULATOR");
            simulator_window.setResizable(false);
            simulator_window.setOnCloseRequest(event1 -> simulatorIsActive = false);
            Parent simulatorLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/SimulatorScene.fxml"));
            Scene simulatorScene = new Scene(simulatorLayout);
            simulator_window.setScene(simulatorScene);
            simulator_window.show();
        } else {
            window.centerOnScreen();
            simulator_window.close();
            simulatorIsActive = false;
        }
    }

    public void homeButton() throws Exception{
        window.setTitle("AlgaT");
        Parent simulatorLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/InitialScene.fxml"));
        Scene simulatorScene = new Scene(simulatorLayout);
        window.setScene(simulatorScene);
    }

    public void setError_window(Integer i) throws IOException {
        error_window = new Stage();
        error_window.setX(250);
        error_window.setY(300);
        error_window.setWidth(220);
        error_window.setHeight(220);
        error_window.setTitle("ERROR WINDOW");
        error_window.setResizable(false);
        error_window.initModality(Modality.APPLICATION_MODAL);
        AnchorPane anchorPane = new AnchorPane();
        Button b1 = new Button("OK");
        b1.setLayoutX(90);
        b1.setLayoutY(150);
        b1.setOnAction(e -> error_window.close());
        Label l1 = new Label();
        l1.relocate(20 ,20);
        l1.setPrefSize(180,80);
        setMessageError(i,l1);
        l1.setTextAlignment(TextAlignment.CENTER);
        l1.setWrapText(true);
        anchorPane.getChildren().addAll(b1, l1);
        Scene scene = new Scene(anchorPane);
        error_window.setScene(scene);
        error_window.show();
    }

    private void setMessageError(Integer i, Label label) throws IOException {
        InputStream file_to_open = getClass().getResourceAsStream("Text_file/ErrorMessage");  //seleziona il file txt da aprire
        InputStreamReader file_decode = new InputStreamReader(file_to_open);    //trasforma il contenuto del file che apre da bit TxtLesson2 caratteri ASCII
        BufferedReader file_to_read = new BufferedReader(file_decode);    //legge e bufferizza i caratteri letti da uno stream di caratteri in input
        String line_to_add = "";
        String complete_page = "";
        while ((line_to_add = file_to_read.readLine()) != null) {
            if (line_to_add.startsWith(i.toString() + ":")) {
                line_to_add = line_to_add.replace(i.toString() + ":", "");
                complete_page = complete_page.concat(line_to_add);
            }
        }
        label.setText(complete_page);
    }
}

