package algebra.hr.dal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "AudioWords")
public class AudioWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AudioWordID", updatable = false, nullable = false)
    private int audioWordID;

    @Column(name = "Content", nullable = false, length = 1024)
    private String content;

    @Column(name = "Duration")
    private double duration;

    @ManyToOne
    @JoinColumn(name = "BaseWordID", nullable = false)
    private Word baseWord;

    @ManyToOne
    @JoinColumn(name = "TranslatedWordID", nullable = false)
    private Word translatedWord;

    public AudioWord() {
    }

    public AudioWord(String content, double duration, Word baseWord, Word translatedWord) {
        this.content = content;
        this.duration = duration;
        this.baseWord = baseWord;
        this.translatedWord = translatedWord;
    }

    public int getAudioID() {
        return audioWordID;
    }

    public void setAudioID(int audioID) {
        this.audioWordID = audioID;
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
        return "AudioWord{" +
                "audioWordID=" + audioWordID +
                ", content='" + content + '\'' +
                ", duration=" + duration +
                ", baseWord=" + baseWord +
                ", translatedWord=" + translatedWord +
                '}';
    }
}
