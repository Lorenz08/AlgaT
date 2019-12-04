import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;


public class LessonClass extends Main implements Initializable {

    private Integer current_page1 = 0;
    private Integer current_page2 = 0;
    private Integer current_page3 = 0;
    private LinkedList<Page> list_of_pages1 = null;
    private LinkedList<Page> list_of_pages2 = null;
    private LinkedList<Page> list_of_pages3 = null;
    private Integer number_of_pages_of_lesson1 = 0;
    private Integer number_of_pages_of_lesson2 = 0;
    private Integer number_of_pages_of_lesson3 = 0;
    @FXML private Label Label1;
    @FXML private ImageView ImageView1;
    @FXML private Label Label2;
    @FXML private ImageView ImageView2;
    @FXML private Label Label3;
    @FXML private ImageView ImageView3;




    public void initialize(URL location, ResourceBundle resources) {
        try {
            creat_listPages_of_current_lesson(current_lesson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void creat_listPages_of_current_lesson(Integer i) throws IOException {
        if (i == 1){
            list_of_pages1 = new LinkedList<Page>();
            creat_listPages_of_all_lessons(number_of_pages_of_lesson1, list_of_pages1);
        }
        else if (i == 2){
            list_of_pages2 = new LinkedList<Page>();
            creat_listPages_of_all_lessons(number_of_pages_of_lesson2, list_of_pages2);
        }
        else if (i == 3){
            list_of_pages3 = new LinkedList<Page>();
            creat_listPages_of_all_lessons(number_of_pages_of_lesson3, list_of_pages3);
        }
    }



    private void creat_listPages_of_all_lessons(Integer numberOfPage, LinkedList<Page> listOfPage) throws IOException {
        String x = "";
        if (current_lesson == 1) x = "Text_file/TxtLesson1";
        if (current_lesson == 2) x = "Text_file/TxtLesson2";
        if (current_lesson == 3) x = "Text_file/TxtLesson3";
        InputStream file_to_open = getClass().getResourceAsStream(x);  //seleziona il file txt da aprire
        InputStreamReader file_decode = new InputStreamReader(file_to_open);    //trasforma il contenuto del file che apre da bit TxtLesson2 caratteri ASCII
        BufferedReader file_to_read = new BufferedReader(file_decode);    //legge e bufferizza i caratteri letti da uno stream di caratteri in input
        creat_listPages(file_to_read, listOfPage);
        setPage(current_lesson, listOfPage);
    }


    private void creat_listPages(BufferedReader tmp, LinkedList<Page> list) throws IOException {
        Integer i = 0;
        String complete_page = "";
        String line_of_page_to_add;
        Image image_to_load = null;

        while ((line_of_page_to_add = tmp.readLine())!= null) {
            if (line_of_page_to_add.startsWith("TXT:")){
                line_of_page_to_add = line_of_page_to_add.replace("TXT:", "");
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


    public void setPage(Integer currentLesson, LinkedList<Page> list){
        if(currentLesson == 1) {
            Page nPage = list.get(current_page1);
            Label1.setText(nPage.getText());
            ImageView1.setImage(nPage.getImage());
        }
        else if(currentLesson == 2) {
            Page nPage = list.get(current_page2);
            Label2.setText(nPage.getText());
            ImageView2.setImage(nPage.getImage());
        }
        else if(currentLesson == 3) {
            Page nPage = list.get(current_page3);
            Label3.setText(nPage.getText());
            ImageView3.setImage(nPage.getImage());
        }
    }


    public void setNew_Page() throws IOException {
        LinkedList<Page> tmp = new LinkedList<Page>();
        Integer x = 0;
        if (current_lesson == 1) {
            x = current_page1;
            tmp = list_of_pages1;
        } else if (current_lesson == 2) {
            x = current_page2;
            tmp = list_of_pages2;
        } else if (current_lesson == 3) {
            x = current_page3;
            tmp = list_of_pages3;
        }
        if (x == tmp.size()) {
            Parent simulatorLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/InitialScene.fxml"));
            Scene simulatorScene = new Scene(simulatorLayout);
            window.setScene(simulatorScene);
        } else if (x < 0) {
            Parent simulatorLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/InitialScene.fxml"));
            Scene simulatorScene = new Scene(simulatorLayout);
            window.setScene(simulatorScene);
        } else {
            setPage(current_lesson, tmp);
        }
    }



    public void nextPage(ActionEvent event) throws IOException {
        if (current_lesson == 1) {
           current_page1++;
        }
        else if (current_lesson == 2) {
            current_page2++;
        }
        else if (current_lesson == 3) {
            current_page3++;
        }
        setNew_Page();
    }


    public void prevPage(ActionEvent event) throws IOException {
        if (current_lesson == 1) {
            current_page1--;
        }
        else if (current_lesson == 2) {
            current_page2--;
        }
        else if (current_lesson == 3) {
            current_page3--;
        }
        setNew_Page();
    }


}





