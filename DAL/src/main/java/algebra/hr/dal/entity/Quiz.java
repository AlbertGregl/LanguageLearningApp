package algebra.hr.dal.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDQuiz", updatable = false, nullable = false)
    private int idQuiz;

    @Column(name = "Name", nullable = false, length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "LessonID")
    private Lesson lesson;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "Quiz_Task",
            joinColumns = @JoinColumn(name = "IDQuiz"),
            inverseJoinColumns = @JoinColumn(name = "TaskID")
    )
    private List<Task> tasks = new ArrayList<>();



    //CONSTRUCTOR
    public Quiz(String name, Lesson lesson) {
        this.name = name;
        this.lesson = lesson;
    }

    public Quiz() {
    }

    public Quiz(String name) {
        this.name = name;
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "idQuiz=" + idQuiz +
                ", name='" + name + '\'' +
                ", lesson=" + lesson +
                '}';
    }
}
