package algebra.hr.dal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TranslationWord")
public class TranslationWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TranslationWordID", updatable = false, nullable = false)
    private int translationWordID;

    @ManyToOne()
    @JoinColumn(name = "WordID", nullable = false)
    private Word word;

    @ManyToOne()
    @JoinColumn(name = "LanguageID", nullable = false)
    private Language language;

    //the longest word in the world is Sanskrit 195-chars :)
    @Column(name = "TranslatedWord", nullable = false, length = 255)
    private String translatedWord;

    // CONTRUCTOR
    public TranslationWord() {}

    public TranslationWord(Word word, Language language, String translatedWord) {
        this.word = word;
        this.language = language;
        this.translatedWord = translatedWord;
    }

    // GET AND SET
    public int getTranslationWordID() {
        return translationWordID;
    }

    public void setTranslationWordID(int translationWordID) {
        this.translationWordID = translationWordID;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    public void setTranslatedWord(String translatedWord) {
        this.translatedWord = translatedWord;
    }

    @Override
    public String toString() {
        return "TranslationWord{" +
                "translationWordID=" + translationWordID +
                ", word=" + word +
                ", language=" + language +
                ", translatedWord='" + translatedWord + '\'' +
                '}';
    }

}
