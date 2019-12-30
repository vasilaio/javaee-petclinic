package ro.kbs.petclinic.pets.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import ro.kbs.petclinic.owners.entity.Owner;
import ro.kbs.petclinic.visits.entity.Visit;

@Entity
@Table(name = "pets")
@Data
public class Pet implements Comparable<Pet> {
  @Id
  @GeneratedValue
  private Long id;
  @NotBlank
  @Column(name = "name")
  private String name;
  @NotNull
  @Column(name = "birth_date")
  @Temporal(TemporalType.DATE)
  private Date birthDate;
  @NotNull
  @ManyToOne
  @JoinColumn(name = "type_id")
  private PetType type;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "owner_id")
  private Owner owner;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet", fetch = FetchType.EAGER)
  @OrderBy("date")
  private Set<Visit> visits = new HashSet<Visit>();

  public void addVisit(Visit visit) {
    visits.add(visit);
  }

  @Override
  public int compareTo(Pet o) {
    return name.compareTo(o.getName());
  }
}
