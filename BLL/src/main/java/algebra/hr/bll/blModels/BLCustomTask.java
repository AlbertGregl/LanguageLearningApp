package algebra.hr.bll.blModels;

import algebra.hr.dal.entity.Quiz;
import algebra.hr.dal.enums.TaskType;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class BLCustomTask {
    private int taskID;
    @NotEmpty(message = "Answer is required")
    private String taskAnswer;
    private TaskType taskType;
    @NotEmpty(message = "Task text is required")
    private String taskText;
    private List<Quiz> quizzes = new ArrayList<>();

    public BLCustomTask() {
    }

    public BLCustomTask(String taskAnswer, TaskType taskType, String taskText, List<Quiz> quizzes) {
        this.taskAnswer = taskAnswer;
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

    public String getTaskAnswer() {
        return taskAnswer;
    }

    public void setTaskAnswer(String taskAnswer) {
        this.taskAnswer = taskAnswer;
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
        return "BLCustomTask{" +
                "taskID=" + taskID +
                ", taskAnswer='" + taskAnswer + '\'' +
                ", taskType=" + taskType +
                ", taskText='" + taskText + '\'' +
                ", quizzes=" + quizzes +
                '}';
    }
}
