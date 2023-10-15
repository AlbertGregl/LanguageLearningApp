package algebra.hr.dal.entity;

import jakarta.persistence.*;

//https://www.baeldung.com/jpa-join-column

@Entity
@Table(name = "TranslationPhrase")
public class TranslationPhrase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PhraseTranslationID", updatable = false, nullable = false)
    private int phraseTranslationID;

    @ManyToOne
    @JoinColumn(name = "BasePhraseID", nullable = false)
    private Phrase basePhrase;

    @ManyToOne
    @JoinColumn(name = "TranslatedPhraseID", nullable = false)
    private Phrase translatedPhrase;

    // CONSTRUCTORS
    public TranslationPhrase() {
    }

    public TranslationPhrase(Phrase basePhrase, Phrase translatedPhrase) {
        this.basePhrase = basePhrase;
        this.translatedPhrase = translatedPhrase;
    }

    public int getPhraseTranslationID() {
        return phraseTranslationID;
    }

    public void setPhraseTranslationID(int phraseTranslationID) {
        this.phraseTranslationID = phraseTranslationID;
    }

    public Phrase getBasePhrase() {
        return basePhrase;
    }

    public void setBasePhrase(Phrase basePhrase) {
        this.basePhrase = basePhrase;
    }

    public Phrase getTranslatedPhrase() {
        return translatedPhrase;
    }

    public void setTranslatedPhrase(Phrase translatedPhrase) {
        this.translatedPhrase = translatedPhrase;
    }

    @Override
    public String toString() {
        return "TranslationPhrase{" +
                "phraseTranslationID=" + phraseTranslationID +
                ", basePhrase=" + basePhrase +
                ", translatedPhrase=" + translatedPhrase +
                '}';
    }
}