package algebra.hr.dal.entity;

import algebra.hr.dal.enums.TaskType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "TaskPhrase")
public class TaskPhrase extends Task {
    public TaskPhrase() {
    }

    public TaskPhrase(TaskType taskType, List<Quiz> quizzes, String taskText, TranslationPhrase translationPhrase) {
        super(taskType, quizzes, taskText);
        this.translationPhrase = translationPhrase;
    }

    @ManyToOne
    @JoinColumn(name = "TranslationPhraseID", nullable = false)
    private TranslationPhrase translationPhrase;

    public TranslationPhrase getTranslationPhrase() {
        return translationPhrase;
    }

    public void setTranslationPhrase(TranslationPhrase translationPhrase) {
        this.translationPhrase = translationPhrase;
    }

    @Override
    public String toString() {
        return "TaskPhrase{" +
                "translationPhrase=" + translationPhrase +
                '}';
    }
}
