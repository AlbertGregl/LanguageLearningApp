package algebra.hr.dal.entity;

import algebra.hr.dal.enums.TaskType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "TaskCustom")
public class TaskCustom extends Task{
    public TaskCustom() {
    }

    public TaskCustom(TaskType taskType, List<Quiz> quizzes, String taskText, String taskAnswer) {
        super(taskType, quizzes, taskText);
        this.taskAnswer = taskAnswer;
    }

    @Column(name = "TaskAnswer", nullable = false, length = 1024)
    private String taskAnswer;

    public String getTaskAnswer() {
        return taskAnswer;
    }

    public void setTaskAnswer(String taskAnswer) {
        this.taskAnswer = taskAnswer;
    }

    @Override
    public String toString() {
        return "TaskCustom{" +
                "taskAnswer='" + taskAnswer + '\'' +
                '}';
    }
}
