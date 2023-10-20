package algebra.hr.dal.entity;

import algebra.hr.dal.enums.TaskType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.List;

@Entity
//@Table(name = "TaskAudioWord")
@DiscriminatorValue("5")
public class TaskAudioWord extends Task{
    public TaskAudioWord() {
    }

    public TaskAudioWord(TaskType taskType, List<Quiz> quizzes, String taskText, AudioWord audioWord) {
        super(taskType, quizzes, taskText);
        this.audioWord = audioWord;
    }

    @ManyToOne
    @JoinColumn(name = "AudioWordID", nullable = true)
    private AudioWord audioWord;

    public AudioWord getAudioWord() {
        return audioWord;
    }

    public void setAudioWord(AudioWord audioWord) {
        this.audioWord = audioWord;
    }

    @Override
    public String toString() {
        return "TaskAudioWord{" +
                "audioWord=" + audioWord +
                '}';
    }
}
