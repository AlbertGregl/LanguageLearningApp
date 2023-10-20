package algebra.hr.dal.entity;

import algebra.hr.dal.enums.TaskType;
import jakarta.persistence.*;

import java.util.List;
//https://stackoverflow.com/questions/8969059/difference-between-onetomany-and-elementcollection
@Entity
@Table(name = "TaskCustomMC")
public class TaskCustomMC extends Task{
    @Column(name = "Answer", nullable = false, length = 256)
    private String answer;

    @ElementCollection
    @CollectionTable(name = "TaskChoices", joinColumns = @JoinColumn(name = "TaskID"))
    @Column(name = "ChoiceText")
    private List<String> choices;

    public TaskCustomMC() {
    }

    public TaskCustomMC(TaskType taskType, List<Quiz> quizzes, String taskText, String answer, List<String> choices) {
        super(taskType, quizzes, taskText);
        this.answer = answer;
        this.choices = choices;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    @Override
    public String toString() {
        return "TaskCustomMC{" +
                "answer='" + answer + '\'' +
                ", choices=" + choices +
                '}';
    }
}
