package algebra.hr.dal.entity;

import jakarta.persistence.*;

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

    //CONSTRUCTOR
    public Quiz(String name, Lesson lesson) {
        this.name = name;
        this.lesson = lesson;
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

    @Override
    public String toString() {
        return "Quiz{" +
                "idQuiz=" + idQuiz +
                ", name='" + name + '\'' +
                ", lesson=" + lesson +
                '}';
    }
}
