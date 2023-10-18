package algebra.hr.dal.entity;

import algebra.hr.dal.enums.TaskType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TaskAudioWord")
public class TaskAudioWord extends Task{
    public TaskAudioWord(TaskType taskType, Quiz quiz, String taskText) {
        super(taskType, quiz, taskText);
    }

    @ManyToOne
    @JoinColumn(name = "AudioWordID", nullable = false)
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
