package algebra.hr.dal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Phrase")
public class Phrase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PhraseID", updatable = false, nullable = false)
    private int phraseID;

    @Column(name = "PhraseText", nullable = false, columnDefinition = "NVARCHAR(1024)")
    private String phraseText;

    // CONSTRUCTORS
    public Phrase() {
    }

    public Phrase(String phraseText) {
        this.phraseText = phraseText;
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

    @Override
    public String toString() {
        return "Phrase{" +
                "phraseID=" + phraseID +
                ", phraseText='" + phraseText + '\'' +
                '}';
    }
}