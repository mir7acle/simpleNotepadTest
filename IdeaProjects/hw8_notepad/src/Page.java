import java.util.ArrayList;

public class Page {

    private String title;
    private String text;


    private Page() {
        this.title = "";
        this.text = "";
    }

    Page(String title, String text) {

        this();

        if (title != null) {
            this.title = title;
        }
        if (text != null) {
            this.text = text;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null) {
            this.title = title;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text != null) {
            this.text = text;
        }
    }


    public void addText(String newTitle, String newText) {

        if (newTitle != null && !newTitle.isEmpty()) {
            this.title = this.title + newTitle + "\n";
        }

        if (newText != null && !newText.isEmpty()) {
            this.text = this.text + newText + "\n";
        }
    }

    public void emptyPage() {
        this.text = "";
    }

    public String viewPage() {
        return this.title + "\n" + this.text;
    }
}
