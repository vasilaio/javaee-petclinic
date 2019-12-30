package ro.kbs.petclinic.vets.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlElement;
import lombok.Data;

@Entity
@Table(name = "vets")
@Data
public class Vet {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "first_name")
  @NotEmpty
  private String firstName;

  @Column(name = "last_name")
  @NotEmpty
  private String lastName;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
      inverseJoinColumns = @JoinColumn(name = "specialty_id"))
  private Set<Specialty> specialties;

  protected Set<Specialty> getSpecialtiesInternal() {
    if (this.specialties == null) {
      this.specialties = new HashSet<Specialty>();
    }
    return this.specialties;
  }

  @XmlElement
  public List<Specialty> getSpecialties() {
    List<Specialty> list = new ArrayList<Specialty>();
    for (Specialty s : getSpecialtiesInternal()) {
      list.add(s);
    }
    Collections.sort(list);
    return list;
  }

  public String getSpecialtiesAsString() {
    StringBuilder stringBuilder = new StringBuilder();
    if (getNrOfSpecialties() == 0) {
      stringBuilder.append("none");
    } else {
      for (Specialty specialty : getSpecialties()) {
        stringBuilder.append(specialty.getName());
        stringBuilder.append(" ");
      }
    }
    return stringBuilder.toString();
  }

  public int getNrOfSpecialties() {
    return getSpecialtiesInternal().size();
  }

  public void addSpecialty(Specialty specialty) {
    getSpecialtiesInternal().add(specialty);
  }

  public void removeSpecialties() {
    this.specialties = new HashSet<Specialty>();
  }
}
