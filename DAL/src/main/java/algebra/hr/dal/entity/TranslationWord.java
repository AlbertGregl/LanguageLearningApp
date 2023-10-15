package algebra.hr.dal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TranslationWord")
public class TranslationWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TranslationWordID", updatable = false, nullable = false)
    private int translationWordID;

    @ManyToOne
    @JoinColumn(name = "BaseWordID", nullable = false)
    private Word baseWord;

    @ManyToOne
    @JoinColumn(name = "TranslatedWordID", nullable = false)
    private Word translatedWord;

    // CONTRUCTOR
    public TranslationWord() {}

    public TranslationWord(Word baseWord, Word translatedWord) {
        this.baseWord = baseWord;
        this.translatedWord = translatedWord;
    }

    // GET AND SET

    public int getTranslationWordID() {
        return translationWordID;
    }

    public void setTranslationWordID(int translationWordID) {
        this.translationWordID = translationWordID;
    }

    public Word getBaseWord() {
        return baseWord;
    }

    public void setBaseWord(Word baseWord) {
        this.baseWord = baseWord;
    }

    public Word getTranslatedWord() {
        return translatedWord;
    }

    public void setTranslatedWord(Word translatedWord) {
        this.translatedWord = translatedWord;
    }

    @Override
    public String toString() {
        return "TranslationWord{" +
                "translationWordID=" + translationWordID +
                ", baseWord=" + baseWord +
                ", translatedWord=" + translatedWord +
                '}';
    }
}
