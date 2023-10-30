package algebra.hr.dal.entity;

import algebra.hr.dal.enums.Difficulty;
import algebra.hr.dal.enums.LessonCode;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name ="Lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LessonID", updatable = false, nullable = false)
    private int lessonID;

    @Enumerated(EnumType.STRING) //annotation means that the enum will be persisted as a String !
    @Column(name = "LessonCode", length = 50, nullable = false)
    private LessonCode lessonCode;

    @Enumerated(EnumType.STRING) //annotation means that the enum will be persisted as a String !
    @Column(name = "Difficulty", length = 50, nullable = false)
    private Difficulty difficulty;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Quiz> quizzes;

    public Lesson() {
    }

    public Lesson(LessonCode lessonCode, Difficulty difficulty, List<Quiz> quizzes) {
        this.lessonCode = lessonCode;
        this.difficulty = difficulty;
        this.quizzes = quizzes;
    }

    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }

    public LessonCode getLessonCode() {
        return lessonCode;
    }

    public void setLessonCode(LessonCode lessonCode) {
        this.lessonCode = lessonCode;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonID=" + lessonID +
                ", lessonCode=" + lessonCode +
                ", difficulty=" + difficulty +
                ", quizzes=" + quizzes +
                '}';
    }
}
