package algebra.hr.dal.entity;

import algebra.hr.dal.enums.TaskType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "TaskAudioPhrase")
public class TaskAudioPhrase extends Task{
    public TaskAudioPhrase() {
    }

    public TaskAudioPhrase(TaskType taskType, List<Quiz> quizzes, String taskText, AudioPhrase audiophrase) {
        super(taskType, quizzes, taskText);
        this.audiophrase = audiophrase;
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
