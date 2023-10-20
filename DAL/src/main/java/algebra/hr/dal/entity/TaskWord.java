package algebra.hr.dal.entity;

import algebra.hr.dal.enums.TaskType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "TaskWord")
public class TaskWord extends Task{
    public TaskWord() {
    }

    public TaskWord(TaskType taskType, List<Quiz> quizzes, String taskText, TranslationWord translationWord) {
        super(taskType, quizzes, taskText);
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
