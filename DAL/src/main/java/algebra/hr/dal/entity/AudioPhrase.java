package algebra.hr.dal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "AudioPhrases")
public class AudioPhrase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AudioPhraseID", updatable = false, nullable = false)
    private int audioPhraseID;

    @Column(name = "Content", nullable = false, length = 1024)
    private String content;

    @Column(name = "Duration")
    private double duration;

    @ManyToOne
    @JoinColumn(name = "BasePhraseID", nullable = false)
    private Phrase basePhrase;

    @ManyToOne
    @JoinColumn(name = "TranslatedPhraseID", nullable = false)
    private Phrase translatedPhrase;

    public AudioPhrase() {
    }

    public AudioPhrase(String content, double duration, Phrase basePhrase, Phrase translatedPhrase) {
        this.content = content;
        this.duration = duration;
        this.basePhrase = basePhrase;
        this.translatedPhrase = translatedPhrase;
    }

    public int getAudioID() {
        return audioPhraseID;
    }

    public void setAudioID(int audioID) {
        this.audioPhraseID = audioID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
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
        return "AudioPhrase{" +
                "audioPhraseID=" + audioPhraseID +
                ", content='" + content + '\'' +
                ", duration=" + duration +
                ", basePhrase=" + basePhrase +
                ", translatedPhrase=" + translatedPhrase +
                '}';
    }
}
