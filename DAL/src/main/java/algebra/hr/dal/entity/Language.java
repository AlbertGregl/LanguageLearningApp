package algebra.hr.dal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LanguageID", updatable = false, nullable = false)
    @NotNull(message = "Language ID cannot be null")
    private int languageID;

    @Column(name = "LanguageName", nullable = false, length = 255)
    @NotBlank(message = "Language name is required")
    private String languageName;

    // CONSTRUCTORS
    public Language() {
    }

    public Language(String languageName) {
        this.languageName = languageName;
    }

    // GET SET
    public int getLanguageID() {
        return languageID;
    }

    public void setLanguageID(int languageID) {
        this.languageID = languageID;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }


    @Override
    public String toString() {
        return "Languages{" +
                "languageID=" + languageID +
                ", languageName='" + languageName + '\'' +
                '}';
    }
}