import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;



public class ExerciseClass extends Main implements Initializable{


    private boolean complete = false;
//    private boolean complete2= false;
//    private boolean complete3 = false;


    private Integer current_exercize_page1 = 0;
    private Integer current_exercize_page2 = 0;
    private Integer current_exercize_page3 = 0;
    private LinkedList<Page> list_of_pages1 = null;
    private LinkedList<Page> list_of_pages2 = null;
    private LinkedList<Page> list_of_pages3 = null;
    private Integer number_of_pages_of_exercise1 = 0;
    private Integer number_of_pages_of_exercise2 = 0;
    private Integer number_of_pages_of_exercise3 = 0;

    @FXML private Label Title;
    @FXML private Label Label1;
    @FXML private ImageView ImageView1;
    @FXML private CheckBox value1;
    @FXML private CheckBox value2;
    @FXML private CheckBox value3;
    @FXML private CheckBox value4;



    public void initialize(URL location, ResourceBundle resources) {
        try {
            xxx(current_exercise);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void xxx(Integer i) throws IOException {
        if (i == 1){
            list_of_pages1 = new LinkedList<Page>();
            creat_listPages_of_all_Exercise(number_of_pages_of_exercise1, list_of_pages1);
        }
        else if (i == 2){
            list_of_pages2 = new LinkedList<Page>();
            creat_listPages_of_all_Exercise(number_of_pages_of_exercise2, list_of_pages2);
        }
        else if (i == 3){
            list_of_pages3 = new LinkedList<Page>();
            creat_listPages_of_all_Exercise(number_of_pages_of_exercise3, list_of_pages3);
        }

    }



    private void creat_listPages_of_all_Exercise(Integer i, LinkedList<Page> tmp) throws IOException {
        String x = "";
        if (current_exercise == 1) x = "Text_file/TxtExercise1";
        if (current_exercise == 2) x = "Text_file/TxtExercise2";
        if (current_exercise == 3) x = "Text_file/TxtExercise3";
        InputStream file_to_open = getClass().getResourceAsStream(x);  //seleziona il file txt da aprire
        InputStreamReader file_decode = new InputStreamReader(file_to_open);    //trasforma il contenuto del file che apre da bit TxtLesson2 caratteri ASCII
        BufferedReader file_to_read = new BufferedReader(file_decode);    //legge e bufferizza i caratteri letti da uno stream di caratteri in input

        creat_list_exercise_Pages(file_to_read, tmp);

        i = tmp.size();
        setPage(current_exercise, tmp);
    }

//crea la lista di pagine delle esercitazioni da svolgere
    private void creat_list_exercise_Pages(BufferedReader tmp, LinkedList<Page> list) throws IOException {
        Integer i = 0;
        String complete_page = "";
        String line_of_page_to_add;
        Image image_to_load = null;

        while ((line_of_page_to_add = tmp.readLine())!= null) {

            //se esiste il file
            if (line_of_page_to_add.startsWith("TXT:")){
                //aggiungi la prima riga nella variabile stringa line_of_page_to_add
                line_of_page_to_add = line_of_page_to_add.replace("TXT:", "");
                //se e un lable
                char control = line_of_page_to_add.charAt(line_of_page_to_add.length() - 1);

                if (control == '#') {

                    line_of_page_to_add = line_of_page_to_add.replace("#", "");


                    complete_page = complete_page.concat(line_of_page_to_add);
                    complete_page += '\n';
                }
                else complete_page = complete_page.concat(line_of_page_to_add);
            }
            if (line_of_page_to_add.startsWith("IMG:")){
                line_of_page_to_add = line_of_page_to_add.replace("IMG:", "");
                if (line_of_page_to_add.contains("null")) image_to_load = null;
                else image_to_load = new Image(getClass().getResourceAsStream(line_of_page_to_add));
            }
            if (line_of_page_to_add.startsWith("END")){
                Page newP = new Page(i, complete_page, image_to_load);
                list.add(newP);
                complete_page = "";
                i++;
            }
        }
        tmp.close();
    }

// setta il numero di esercitazione da svolgere
    public void setPage(Integer currentExercise, LinkedList<Page> list){
        if(currentExercise == 1) {
            Page nPage = list.get(current_exercize_page1);
            Label1.setText(nPage.getText());
            ImageView1.setImage(nPage.getImage());
        }
        else if(currentExercise == 2) {
            Page nPage = list.get(current_exercize_page2);
            Label1.setText(nPage.getText());
            ImageView1.setImage(nPage.getImage());
        }
        else if(currentExercise == 3) {
            Page nPage = list.get(current_exercize_page3);
            Label1.setText(nPage.getText());
            ImageView1.setImage(nPage.getImage());
        }
    }


    public void setNew_Page(){
        LinkedList<Page> tmp = new LinkedList<Page>();
        Integer x = 0;

        if (current_exercise == 1) {
            x = current_exercize_page1;
            tmp = list_of_pages1;
        }
        else if (current_exercise == 2) {
            x = current_exercize_page2;
            tmp = list_of_pages2;
        }
        else if (current_exercise == 3) {
            x = current_exercize_page3;
            tmp = list_of_pages3;
        }
        try {
            if (x == tmp.size() && complete){
                //sblocco delle lezioni
                switch (current_exercise){
                    case 1:
                        ok_lesson2 = true;
                    case 2:
                        ok_lesson3 = true;
                }
                Parent simulatorLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/InitialScene.fxml"));
                Scene simulatorScene = new Scene(simulatorLayout);
                window.setScene(simulatorScene);
            }else if(x == tmp.size()){
                setPage(current_exercise, tmp);

            }
            else if (x < 0){
                Parent simulatorLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/InitialScene.fxml"));
                Scene simulatorScene = new Scene(simulatorLayout);
                window.setScene(simulatorScene);
            }
            else{
                setPage(current_exercise, tmp);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void nextPage(ActionEvent event) {
        if (current_exercise == 1) {
            current_exercize_page1++;
        }
        else if (current_exercise == 2) {
            current_exercize_page2++;
        }
        else if (current_exercise == 3) {
            current_exercize_page3++;
        }
        setNew_Page();
    }


    public void prevPage(ActionEvent event) {
        if (current_exercise == 1) {
            current_exercize_page1--;
        }
        else if (current_exercise == 2) {
            current_exercize_page2--;
        }
        else if (current_exercise == 3) {
            current_exercize_page3--;
        }
        setNew_Page();
    }


}


