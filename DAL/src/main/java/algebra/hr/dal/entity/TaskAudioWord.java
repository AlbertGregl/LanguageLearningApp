package algebra.hr.dal.entity;

import algebra.hr.dal.enums.TaskType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class TaskAudioWord extends Task{
    public TaskAudioWord(TaskType taskType, Quiz quiz, String taskText) {
        super(taskType, quiz, taskText);
    }

    public TaskAudioWord(TaskType taskType, Quiz quiz, String taskText, AudioWord audioWord) {
        super(taskType, quiz, taskText);
        this.audioWord = audioWord;
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
