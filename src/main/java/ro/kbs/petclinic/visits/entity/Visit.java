package ro.kbs.petclinic.visits.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import ro.kbs.petclinic.pets.entity.Pet;

@Entity
@Table(name = "visits")
@Data
public class Visit implements Comparable<Visit> {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Column(name = "visit_date")
  @Temporal(TemporalType.DATE)
  private Date date;

  @NotEmpty
  @Column(name = "description")
  private String description;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "pet_id")
  private Pet pet;

  @Override
  public int compareTo(Visit o) {
    return date.compareTo(o.getDate());
  }
}
