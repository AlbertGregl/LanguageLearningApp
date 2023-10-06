package algebra.hr.dal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Words")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WordID", updatable = false, nullable = false)
    private int wordID;

    @Column(name = "WordText", nullable = false, length = 255)
    private String wordText;

    // CONTRUCTORS
    public Word() {}

    public Word(String wordText) {
        this.wordText = wordText;
    }

    // GET AND SET


    public int getWordID() {
        return wordID;
    }

    public void setWordID(int wordID) {
        this.wordID = wordID;
    }

    public String getWordText() {
        return wordText;
    }

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    @Override
    public String toString() {
        return "Word{" +
                "wordID=" + wordID +
                ", wordText='" + wordText + '\'' +
                '}';
    }
}
