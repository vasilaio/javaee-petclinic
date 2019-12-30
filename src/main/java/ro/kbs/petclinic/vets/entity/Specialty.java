package ro.kbs.petclinic.vets.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "specialties")
@Data
public class Specialty implements Comparable<Specialty> {
  @Id
  @GeneratedValue
  private Long id;

  @NotBlank
  @Column(name = "name")
  private String name;

  @Override
  public int compareTo(Specialty other) {
    return this.name.compareTo(other.getName());
  }
}
