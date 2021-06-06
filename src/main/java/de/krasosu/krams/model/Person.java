package de.krasosu.krams.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Person {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name", nullable = false)
  private String name;

  @CreationTimestamp private Instant creationDate;

  @CreationTimestamp private Instant modificationDate;

  @Column(name = "enabled")
  private boolean enabled = true;

  @Column(name = "age")
  private Integer age;

  @Column(name = "city")
  private String city;

  @Column(name = "zipCode")
  private Integer zipCode;

  @Column(name = "skills")
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "PERSON_ID")
  private List<Skill> skills = new ArrayList<>();
}
