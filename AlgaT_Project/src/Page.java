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

    Page(String txt, Image img ,String[] risposte) {
        //super();
        image = img;
        text = txt;
        value = risposte;
    }

    protected Image getImage() {
        return(image);
    }

    protected String getValue(Integer i) {
        return value[i];
    }

    public void setValue(Integer i,String value) {
        this.value[i] = value;
    }

    public String getText() {
        return(text);
    }


}
