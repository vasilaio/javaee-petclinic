package ro.kbs.petclinic.pets.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "types")
@Data
public class PetType {
  @Id
  @GeneratedValue
  private Long id;
  @NotBlank
  @Column(name = "name")
  private String name;
}
