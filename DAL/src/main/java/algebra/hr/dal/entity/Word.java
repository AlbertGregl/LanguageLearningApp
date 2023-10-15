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

    @ManyToOne
    @JoinColumn(name = "LanguageID", nullable = false)
    private Language language;  //each word is associated with a language

    // CONTRUCTORS
    public Word() {}

    public Word(String wordText, Language language) {
        this.wordText = wordText;
        this.language = language;
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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Word{" +
                "wordID=" + wordID +
                ", wordText='" + wordText + '\'' +
                ", language=" + language +
                '}';
    }
}
