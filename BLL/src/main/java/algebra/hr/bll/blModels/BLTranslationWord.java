package algebra.hr.bll.blModels;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class BLTranslationWord {
    private int translationID;
    @NotEmpty(message = "Base word is required")
    private String baseWord;
    @Min(value = 0, message = "Language is required!")
    private int baseLanguage;
    @NotEmpty(message = "Translated word is required")
    private String translatedWord;
    @Min(value = 0, message = "Language is required")
    private int translatedLanguage;

    public BLTranslationWord() {
    }

    public BLTranslationWord(String baseWord, int baseLanguage, String translatedWord, int translatedLanguage) {
        this.baseWord = baseWord;
        this.baseLanguage = baseLanguage;
        this.translatedWord = translatedWord;
        this.translatedLanguage = translatedLanguage;
    }

    public int getTranslationID() {
        return translationID;
    }

    public void setTranslationID(int translationID) {
        this.translationID = translationID;
    }

    public String getBaseWord() {
        return baseWord;
    }

    public void setBaseWord(String baseWord) {
        this.baseWord = baseWord;
    }

    public int getBaseLanguage() {
        return baseLanguage;
    }

    public void setBaseLanguage(int baseLanguage) {
        this.baseLanguage = baseLanguage;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    public void setTranslatedWord(String translatedWord) {
        this.translatedWord = translatedWord;
    }

    public int getTranslatedLanguage() {
        return translatedLanguage;
    }

    public void setTranslatedLanguage(int translatedLanguage) {
        this.translatedLanguage = translatedLanguage;
    }

    @Override
    public String toString() {
        return "BLTranslationWord{" +
                "translationID=" + translationID +
                ", baseWord='" + baseWord + '\'' +
                ", baseLanguage=" + baseLanguage +
                ", translatedWord='" + translatedWord + '\'' +
                ", translatedLanguage=" + translatedLanguage +
                '}';
    }
}
