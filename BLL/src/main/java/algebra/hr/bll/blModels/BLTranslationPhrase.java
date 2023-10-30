package algebra.hr.bll.blModels;

public class BLTranslationPhrase {
    private int translationID;
    private String basePhrase;
    private int baseLanguage;
    private String translatedPhrase;
    private int translatedLanguage;

    public BLTranslationPhrase() {
    }

    public BLTranslationPhrase(String basePhrase, int baseLanguage, String translatedPhrase, int translatedLanguage) {
        this.basePhrase = basePhrase;
        this.baseLanguage = baseLanguage;
        this.translatedPhrase = translatedPhrase;
        this.translatedLanguage = translatedLanguage;
    }

    public int getTranslationID() {
        return translationID;
    }

    public void setTranslationID(int translationID) {
        this.translationID = translationID;
    }

    public String getBasePhrase() {
        return basePhrase;
    }

    public void setBasePhrase(String basePhrase) {
        this.basePhrase = basePhrase;
    }

    public int getBaseLanguage() {
        return baseLanguage;
    }

    public void setBaseLanguage(int baseLanguage) {
        this.baseLanguage = baseLanguage;
    }

    public String getTranslatedPhrase() {
        return translatedPhrase;
    }

    public void setTranslatedPhrase(String translatedPhrase) {
        this.translatedPhrase = translatedPhrase;
    }

    public int getTranslatedLanguage() {
        return translatedLanguage;
    }

    public void setTranslatedLanguage(int translatedLanguage) {
        this.translatedLanguage = translatedLanguage;
    }

    @Override
    public String toString() {
        return "BLTranslationPhrase{" +
                "translationID=" + translationID +
                ", basePhrase='" + basePhrase + '\'' +
                ", baseLanguage=" + baseLanguage +
                ", translatedPhrase='" + translatedPhrase + '\'' +
                ", translatedLanguage=" + translatedLanguage +
                '}';
    }
}
