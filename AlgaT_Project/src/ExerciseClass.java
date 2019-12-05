import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;


public class ExerciseClass extends Main implements Initializable{

    private boolean complete = false;
    private boolean is_showSolution = true;

    private Integer current_exercize_page1 = 0;
    private Integer current_exercize_page2 = 0;
    private Integer current_exercize_page3 = 0;

    private LinkedList<Page> list_of_pages1 = null;
    private LinkedList<Page> list_of_pages2 = null;
    private LinkedList<Page> list_of_pages3 = null;

    @FXML private Label Title;
    @FXML private Label LabelDomanda;
    @FXML private Label solution;
    @FXML private ImageView ImageView1;
    @FXML private RadioButton value1;
    @FXML private RadioButton value2;
    @FXML private RadioButton value3;
    @FXML private RadioButton value4;
    @FXML private Label errorMessage;
    @FXML private TextField textBox;
    @FXML private ToggleGroup group ;


    /* METHODS */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            inizializzaEsercitazioni();
            setSolutionNoVisible();
            setRadioButtonGroup();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void setRadioButtonGroup() {
        group = new ToggleGroup();
        value1.setToggleGroup(group);
        value2.setToggleGroup(group);
        value3.setToggleGroup(group);
        value4.setToggleGroup(group);
    }

    //crea la lista di pagine da vedere
    private void inizializzaEsercitazioni() throws IOException {
        list_of_pages1 = new LinkedList<Page>();
        list_of_pages2 = new LinkedList<Page>();
        list_of_pages3 = new LinkedList<Page>();
        creat_listPages_of_all_Exercise(list_of_pages1, "Text_file/Tests/TxtExercise1" );
        creat_listPages_of_all_Exercise(list_of_pages2, "Text_file/Tests/TxtExercise2");
        creat_listPages_of_all_Exercise(list_of_pages3, "Text_file/Tests/TxtExercise3");
        setPage();
    }

    private void creat_listPages_of_all_Exercise(LinkedList<Page> listOfPage, String String) throws IOException {
        InputStream file_to_open = getClass().getResourceAsStream(String);  //seleziona il file txt da aprire
        InputStreamReader file_decode = new InputStreamReader(file_to_open);    //trasforma il contenuto del file che apre da bit TxtLesson2 caratteri ASCII
        BufferedReader file_to_read = new BufferedReader(file_decode);    //legge e bufferizza i caratteri letti da uno stream di caratteri in input
        creat_list_exercise_Pages(file_to_read, listOfPage);
    }


    //crea la lista di pagine dell'esercitazione da svolgere
    //tmp = file tct con le domande e risposte
    //list = lista delle pagine dell'esercitazione
    private void creat_list_exercise_Pages(BufferedReader tmp, LinkedList<Page> list) throws IOException {
        String complete_page = "";
        String line_of_page_to_add;
        Image image_to_load = null;
        String[] answers = new String[4];

        //finche il file non e finito
        while ((line_of_page_to_add = tmp.readLine())!= null) {
            //se esiste il file
            if (line_of_page_to_add.startsWith("TXT:")) {
                //se il file inizia con TXT: , rimpiazzalo con ""
                line_of_page_to_add = line_of_page_to_add.replace("TXT:", "");
                complete_page = complete_page.concat(line_of_page_to_add);
            }
            if (line_of_page_to_add.startsWith("IMG:")){
                line_of_page_to_add = line_of_page_to_add.replace("IMG:", "");
                //rimuovo IMG: e se non ce nessuna immagine non caricare nulla
                if (line_of_page_to_add.contains("null")) image_to_load = null;
                //altrimenti crea una nuova instanza di immagine
                else image_to_load = new Image(getClass().getResourceAsStream(line_of_page_to_add));
            }else if(line_of_page_to_add.startsWith("CK1:")){
                line_of_page_to_add = line_of_page_to_add.replace("CK1:", "");
                answers[0]=line_of_page_to_add;
            }else if(line_of_page_to_add.startsWith("CK2:")){
                line_of_page_to_add = line_of_page_to_add.replace("CK2:", "");
                answers[1]=line_of_page_to_add;
            }else if(line_of_page_to_add.startsWith("CK3:")){
                line_of_page_to_add = line_of_page_to_add.replace("CK3:", "");
                answers[2]=line_of_page_to_add;
            }else if(line_of_page_to_add.startsWith("CK4:")){
                line_of_page_to_add = line_of_page_to_add.replace("CK4:", "");
                answers[3]=line_of_page_to_add;
            }
            //se inizia con END crea una nuova pagina aggiungendo il testo e l'immagine, aggiungila alla lista dell'esercitazione e incrementa il contatore delle pagine
            if (line_of_page_to_add.startsWith("END")){
                Page newP = new Page(complete_page, image_to_load, answers);
                list.add(newP);
                complete_page = "";
            }
        }
        //chiudi il file
        tmp.close();
    }

    // compila il template con l'esercitazione corretta
    private void setPage(){
        switch (current_exercise){
            case 1:
                setPage(LabelDomanda, ImageView1, value1, value2, value3, value4, list_of_pages1, current_exercize_page1);
                break;
            case 2:
                setPage(LabelDomanda, ImageView1, value1, value2, value3, value4, list_of_pages2, current_exercize_page2);
                break;
            case 3:
                setPage(LabelDomanda, ImageView1, value1, value2, value3, value4,list_of_pages3, current_exercize_page3);
                break;
        }
    }

    private void setPage(Label LabelDom, ImageView img1, RadioButton value1, RadioButton value2, RadioButton value3, RadioButton value4 ,LinkedList<Page> list, double currentl){
        Page nPage = list.get((int) currentl);
        setTitle();
        LabelDom.setText(nPage.getText());
        LabelDom.setTextAlignment(TextAlignment.CENTER);
        LabelDom.setWrapText(true);
        SetValueRadioButton(value1, nPage, 0);
        SetValueRadioButton(value2, nPage, 1);
        SetValueRadioButton(value3, nPage, 2);
        SetValueRadioButton(value4, nPage, 3);
        img1.setImage(nPage.getImage());
    }

    private void SetValueRadioButton(RadioButton value, Page nPage, int i) {
        value.setText(nPage.getValue(i));
    }

    private void setTitle(){
        switch (current_exercise){
            case 2:
                Title.setText("Test 2");
                break;
            case 3:
                Title.setText("Test 3");
                break;
        }
    }

    private void setNew_Page(double currentExer, LinkedList<Page> list) throws IOException {
        if (currentExer == list.size()){
            setOkLessons();
            showHome();
        } else if (currentExer < 0) {
            showHome();
        } else {
            setPage();
        }
    }

    //sblocco degli esercizi
    private void setOkLessons() {
        switch (current_exercise){
            case 1:
                ok_lesson2 = true;
                break;
            case 2:
                ok_lesson3 = true;
                break;
        }
    }

    private void showHome() throws IOException {
        Parent simulatorLayout = FXMLLoader.load(getClass().getResource("/Fxml_file/InitialScene.fxml"));
        Scene simulatorScene = new Scene(simulatorLayout);
        window.setScene(simulatorScene);
    }

    public void showSolution(){
        if(is_showSolution){
            solution.setVisible(false);
            is_showSolution =false;
        }
        else{
            solution.setVisible(true);
            is_showSolution=true;
        }
    }

    private void setSolutionNoVisible() {
        if (is_showSolution) {
            solution.setVisible(false);
            is_showSolution = false;
        }
    }

    public void nextPage(ActionEvent event) throws IOException{
        switch (current_lesson) {
            case 1:
                if(checkAnswer1()){
                    current_exercize_page1++;
                    setSolutionNoVisible();
                    setNew_Page(current_exercize_page1, list_of_pages1);
                    break;
                }else
                    errorMessage.setText("Sbagliato! Riprova o vedi la soluzione");
                break;
            case 2:
                if(checkAnswer2()){
                    current_exercize_page2++;
                    setSolutionNoVisible();
                    setNew_Page(current_exercize_page2, list_of_pages2);
                    break;
                }else
                    errorMessage.setText("Sbagliato! Riprova o vedi la soluzione");
                break;
            case 3:
                if(checkAnswer3()){
                    current_exercize_page3++;
                    setSolutionNoVisible();
                    setNew_Page(current_exercize_page3, list_of_pages3);
                    break;
                }else
                    errorMessage.setText("Sbagliato! Riprova o vedi la soluzione");
                break;
        }
    }

    public void prevPage(ActionEvent event) throws IOException{
        switch (current_lesson) {
            case 1:
                current_exercize_page1--;
                setSolutionNoVisible();
                setNew_Page(current_exercize_page1, list_of_pages1);
                break;
            case 2:
                current_exercize_page2--;
                setSolutionNoVisible();
                setNew_Page(current_exercize_page2, list_of_pages2);
                break;
            case 3:
                current_exercize_page3--;
                setSolutionNoVisible();
                setNew_Page(current_exercize_page3, list_of_pages3);
                break;
        }
    }



    private boolean checkAnswer1() {
        errorMessage.setText(" ");
        return(value1.isSelected());
    }
    private boolean checkAnswer2() {
        errorMessage.setText(" ");
        return(value2.isSelected());
    }
    private boolean checkAnswer3() {
        errorMessage.setText(" ");
        return(value3.isSelected());
    }
}





