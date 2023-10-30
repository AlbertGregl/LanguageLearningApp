package algebra.hr.dal.entity;

import algebra.hr.dal.enums.TaskType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
//@Table(name = "TaskCustom")
@DiscriminatorValue("4")
public class TaskCustom extends Task{
    public TaskCustom() {
    }

    public TaskCustom(TaskType taskType, List<Quiz> quizzes, String taskText, String taskAnswer) {
        super(taskType, quizzes, taskText);
        this.taskAnswer = taskAnswer;
    }

    @Column(name = "TaskAnswer", nullable = true, length = 1024)
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
