package algebra.hr.dal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Phrase")
public class Phrase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PhraseID", updatable = false, nullable = false)
    private int phraseID;

    @Column(name = "PhraseText", nullable = false, columnDefinition = "NVARCHAR(1024)")
    @NotEmpty(message = "Phrase is required")
    private String phraseText;

    @ManyToOne
    @JoinColumn(name = "LanguageID", nullable = false)
    @NotNull(message = "Language is required")
    private Language language;  //each phrase is associated with a language

    // CONSTRUCTORS
    public Phrase() {
        System.out.println("Lazy init");
    }

    public Phrase(String phraseText, Language language) {
        this.phraseText = phraseText;
        this.language = language;
    }

    // GET AND SET
    public int getPhraseID() {
        return phraseID;
    }

    public void setPhraseID(int phraseID) {
        this.phraseID = phraseID;
    }

    public String getPhraseText() {
        return phraseText;
    }

    public void setPhraseText(String phraseText) {
        this.phraseText = phraseText;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Phrase{" +
                "phraseID=" + phraseID +
                ", phraseText='" + phraseText + '\'' +
                ", language=" + language +
                '}';
    }
}