package com.tripleMen.sportevents.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(length = 50)
  private String country;

  @Column(name = "founded_year")
  private Integer foundedYear;

  // Constructeur par défaut (obligatoire pour JPA)
  public Team() {
  }

  // Constructeur avec paramètres basiques
  public Team(String name, String country, Integer foundedYear) {
    this.name = name;
    this.country = country;
    this.foundedYear = foundedYear;
  }

  // Constructeur avec stade
  public Team(String name, String country, Integer foundedYear, Stadium homeStadium) {
    this.name = name;
    this.country = country;
    this.foundedYear = foundedYear;
  }

  // Getters et Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Integer getFoundedYear() {
    return foundedYear;
  }

  public void setFoundedYear(Integer foundedYear) {
    this.foundedYear = foundedYear;
  }

  // toString pour faciliter le debug
  @Override
  public String toString() {
    return "Team{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", country='" + country + '\'' +
        ", foundedYear=" + foundedYear +
        '}';
  }
}