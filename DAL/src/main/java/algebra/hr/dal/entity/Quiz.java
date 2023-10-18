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

    //CONSTRUCTOR

    public Quiz(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "idQuiz=" + idQuiz +
                ", name='" + name + '\'' +
                '}';
    }
}
