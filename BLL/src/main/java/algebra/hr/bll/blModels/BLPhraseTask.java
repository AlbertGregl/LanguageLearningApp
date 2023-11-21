package algebra.hr.bll.blModels;

import algebra.hr.dal.entity.Quiz;
import algebra.hr.dal.enums.TaskType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class BLPhraseTask {
    private int taskID;
    @Min(value = 0, message = "Translation is required!")
    private int translationPhrase;
    private TaskType taskType;
    @NotEmpty(message = "Task text is required")
    private String taskText;
    private List<Quiz> quizzes = new ArrayList<>();

    public BLPhraseTask() {
    }

    public BLPhraseTask(int translationPhrase, TaskType taskType, String taskText, List<Quiz> quizzes) {
        this.translationPhrase = translationPhrase;
        this.taskType = taskType;
        this.taskText = taskText;
        this.quizzes = quizzes;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getTranslationPhrase() {
        return translationPhrase;
    }

    public void setTranslationPhrase(int translationPhrase) {
        this.translationPhrase = translationPhrase;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public String toString() {
        return "BLPhraseTask{" +
                "taskID=" + taskID +
                ", translationPhrase=" + translationPhrase +
                ", taskType=" + taskType +
                ", taskText='" + taskText + '\'' +
                ", quizzes=" + quizzes +
                '}';
    }
}
