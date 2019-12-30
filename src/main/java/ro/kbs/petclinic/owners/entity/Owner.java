package ro.kbs.petclinic.owners.entity;

import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import ro.kbs.petclinic.pets.entity.Pet;

@Entity
@Table(name = "owners")
@Data
public class Owner {
  @Id
  @GeneratedValue
  private Long id;
  @NotBlank
  @Column(name = "first_name")
  private String firstName;
  @NotBlank
  @Column(name = "last_name")
  private String lastName;
  @NotBlank
  @Column(name = "address")
  private String address;
  @Column(name = "city")
  @NotBlank
  private String city;
  @NotBlank
  @Column(name = "telephone")
  @Digits(fraction = 0, integer = 10)
  private String telephone;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.EAGER)
  @OrderBy("name asc")
  private Set<Pet> pets = new TreeSet<Pet>();

  public void addPet(Pet pet) {
    pets.add(pet);
    pet.setOwner(this);
  }
}
