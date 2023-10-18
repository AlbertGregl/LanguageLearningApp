package algebra.hr.dal.entity;

import algebra.hr.dal.enums.TaskType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TaskAudioPhrase")
public class TaskAudioPhrase extends Task{
    public TaskAudioPhrase(TaskType taskType, Quiz quiz, String taskText) {
        super(taskType, quiz, taskText);
    }

    @ManyToOne
    @JoinColumn(name = "AudioPhraseID", nullable = false)
    private AudioPhrase audiophrase;

    public AudioPhrase getAudiophrase() {
        return audiophrase;
    }

    public void setAudiophrase(AudioPhrase audiophrase) {
        this.audiophrase = audiophrase;
    }

    @Override
    public String toString() {
        return "TaskAudioPhrase{" +
                "audiophrase=" + audiophrase +
                '}';
    }
}
