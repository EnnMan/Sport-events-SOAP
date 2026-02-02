package com.tripleMen.sportevents.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stadiums")
public class Stadium {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(length = 100)
  private String city;

  @Column
  private Integer capacity;

  // Constructeur par défaut (obligatoire pour JPA)
  public Stadium() {
  }

  // Constructeur avec paramètres
  public Stadium(String name, String city, Integer capacity) {
    this.name = name;
    this.city = city;
    this.capacity = capacity;
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

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  // toString pour faciliter le debug
  @Override
  public String toString() {
    return "Stadium{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", city='" + city + '\'' +
        ", capacity=" + capacity +
        '}';
  }
}
