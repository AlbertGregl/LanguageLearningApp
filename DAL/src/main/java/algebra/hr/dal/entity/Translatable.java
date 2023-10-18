package algebra.hr.dal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Translatable")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Translatable {
  //marker class
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  public Translatable(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
