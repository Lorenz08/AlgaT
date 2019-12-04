import javafx.scene.image.Image;

public class Page {

    private Integer number_of_page;
    private Image image;
    private String text;
    private String[] value = new String[4];

    Page(String txt, Image img) {
        image = img;
        text = txt;
    }



    Page(Integer i, String txt, Image img ,String[] risposte) {
        super();
        number_of_page = i;
        image = img;
        text = txt;
        value = risposte;
    }
    Page(Integer i, String txt, Image img ) {
        super();
        number_of_page = i;
        image = img;
        text = txt;
    }

    public String getValue(Integer i) {
        return value[i];
    }

    public Image getImage() {
        return(image);
    }

    public String getText() {
        return(text);
    }


}
