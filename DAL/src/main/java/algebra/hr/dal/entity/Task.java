package algebra.hr.dal.entity;

import algebra.hr.dal.enums.TaskType;
import jakarta.persistence.*;

@Entity
@Table(name = "Task")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TaskID", updatable = false, nullable = false)
    private int taskID;

    @Enumerated(EnumType.STRING)
    @Column(name = "TaskType", nullable = false)
    private TaskType taskType;

    @ManyToOne
    @JoinColumn(name = "QuizID", nullable = false)
    private Quiz quiz;

    @Column(name = "TaskText", nullable = false, length = 1024)
    private String taskText;

    public Task(TaskType taskType, Quiz quiz, String taskText) {
        this.taskType = taskType;
        this.quiz = quiz;
        this.taskText = taskText;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }
}