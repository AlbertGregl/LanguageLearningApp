package algebra.hr.dal.entity;

import algebra.hr.dal.enums.TaskType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TaskWord")
public class TaskWord extends Task{
    public TaskWord(TaskType taskType, Quiz quiz, String taskText) {
        super(taskType, quiz, taskText);
    }

    public TaskWord(TaskType taskType, Quiz quiz, String taskText, TranslationWord translationWord) {
        super(taskType, quiz, taskText);
        this.translationWord = translationWord;
    }

    @ManyToOne
    @JoinColumn(name = "TranslationWordID", nullable = false)
    private TranslationWord translationWord;

    public TranslationWord getTranslationWord() {
        return translationWord;
    }

    public void setTranslationWord(TranslationWord translationWord) {
        this.translationWord = translationWord;
    }

    @Override
    public String toString() {
        return "TaskWord{" +
                "translationWord=" + translationWord +
                '}';
    }
}
