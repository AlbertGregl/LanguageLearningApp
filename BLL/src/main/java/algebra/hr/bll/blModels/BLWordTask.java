package algebra.hr.bll.blModels;

import algebra.hr.dal.entity.Quiz;
import algebra.hr.dal.enums.TaskType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class BLWordTask {
    private int taskID;
    @Min(value = 0, message = "Translation is required!")
    private int translationWord;
    private TaskType taskType;
    @NotEmpty(message = "Task text is required")
    private String taskText;
    private List<Quiz> quizzes = new ArrayList<>();

    public BLWordTask() {
    }

    public BLWordTask(int translationWord, TaskType taskType, String taskText) {
        this.translationWord = translationWord;
        this.taskType = taskType;
        this.taskText = taskText;
    }

    public BLWordTask(int translationWord, TaskType taskType, String taskText, List<Quiz> quizzes) {
        this.translationWord = translationWord;
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

    public int getTranslationWord() {
        return translationWord;
    }

    public void setTranslationWord(int translationWord) {
        this.translationWord = translationWord;
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
        return "BLWordTask{" +
                "taskID=" + taskID +
                ", translationWord=" + translationWord +
                ", taskType=" + taskType +
                ", taskText='" + taskText + '\'' +
                ", quizzes=" + quizzes +
                '}';
    }
}
