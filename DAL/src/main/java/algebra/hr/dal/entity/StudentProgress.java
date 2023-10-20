package algebra.hr.dal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "StudentProgress")
public class StudentProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProgressID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "Score", nullable = false)
    private int score;

    @Column(name = "QuizzesSolved", nullable = false)
    private int quizzesSolved;

    @Column(name = "LessonsPassed", nullable = false)
    private int lessonsPassed;

    @OneToOne
    @JoinColumn(name = "Username", referencedColumnName = "username")
    private User user;

    public StudentProgress(int score, int quizzesSolved, int lessonsPassed, User user) {
        this.score = score;
        this.quizzesSolved = quizzesSolved;
        this.lessonsPassed = lessonsPassed;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getQuizzesSolved() {
        return quizzesSolved;
    }

    public void setQuizzesSolved(int quizzesSolved) {
        this.quizzesSolved = quizzesSolved;
    }

    public int getLessonsPassed() {
        return lessonsPassed;
    }

    public void setLessonsPassed(int lessonsPassed) {
        this.lessonsPassed = lessonsPassed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "StudentProgress{" +
                "id=" + id +
                ", score=" + score +
                ", quizzesSolved=" + quizzesSolved +
                ", lessonsPassed=" + lessonsPassed +
                ", user=" + user +
                '}';
    }
}
