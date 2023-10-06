package algebra.hr.dal.entity;

import jakarta.persistence.*;

//https://www.baeldung.com/jpa-join-column

@Entity
@Table(name = "TranslationPhrase")
public class TranslationPhrase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TranslationPhraseID", updatable = false, nullable = false)
    private int translationPhraseID;

    @ManyToOne
    @JoinColumn(name = "PhraseID", nullable = false)
    private Phrase phrase;

    @ManyToOne
    @JoinColumn(name = "LanguageID", nullable = false)
    private Language language;

    @Column(name = "TranslatedPhrase", nullable = false, length = 1024)
    private String translatedPhrase;

    // CONSTRUCTORS
    public TranslationPhrase() {
    }

    public TranslationPhrase(Phrase phrase, Language language, String translatedPhrase) {
        this.phrase = phrase;
        this.language = language;
        this.translatedPhrase = translatedPhrase;
    }

    // GET AND SET
    public int getTranslationPhraseID() {
        return translationPhraseID;
    }

    public void setTranslationPhraseID(int translationPhraseID) {
        this.translationPhraseID = translationPhraseID;
    }

    public Phrase getPhrase() {
        return phrase;
    }

    public void setPhrase(Phrase phrase) {
        this.phrase = phrase;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getTranslatedPhrase() {
        return translatedPhrase;
    }

    public void setTranslatedPhrase(String translatedPhrase) {
        this.translatedPhrase = translatedPhrase;
    }

    // Optional: toString, equals, and hashCode methods
    @Override
    public String toString() {
        return "TranslationPhrase{" +
                "translationPhraseID=" + translationPhraseID +
                ", phrase=" + phrase +
                ", language=" + language +
                ", translatedPhrase='" + translatedPhrase + '\'' +
                '}';
    }
}