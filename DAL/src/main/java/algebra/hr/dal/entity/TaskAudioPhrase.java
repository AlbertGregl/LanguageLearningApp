package algebra.hr.dal.entity;

import algebra.hr.dal.enums.TaskType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.List;

@Entity
@DiscriminatorValue("6")
//@Table(name = "TaskAudioPhrase")
public class TaskAudioPhrase extends Task{
    public TaskAudioPhrase() {
    }

    public TaskAudioPhrase(TaskType taskType, List<Quiz> quizzes, String taskText, AudioPhrase audiophrase) {
        super(taskType, quizzes, taskText);
        this.audiophrase = audiophrase;
    }

    @ManyToOne
    @JoinColumn(name = "AudioPhraseID", nullable = true)
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
