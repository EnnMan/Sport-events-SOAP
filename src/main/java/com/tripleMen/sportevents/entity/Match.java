package com.tripleMen.sportevents.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
public class Match {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Relation avec l'équipe à domicile
  @ManyToOne
  @JoinColumn(name = "home_team_id", nullable = false)
  private Team homeTeam;

  // Relation avec l'équipe extérieure
  @ManyToOne
  @JoinColumn(name = "away_team_id", nullable = false)
  private Team awayTeam;

  // Relation avec le stade où se joue le match
  @ManyToOne
  @JoinColumn(name = "stadium_id", nullable = false)
  private Stadium stadium;

  @Column(name = "match_date", nullable = false)
  private LocalDateTime matchDate;

  @Column(name = "home_score")
  private Integer homeScore;

  @Column(name = "away_score")
  private Integer awayScore;

  // Constructeur par défaut (obligatoire pour JPA)
  public Match() {
  }

  // Constructeur avec paramètres (sans scores - match pas encore joué)
  public Match(Team homeTeam, Team awayTeam, Stadium stadium, LocalDateTime matchDate) {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.stadium = stadium;
    this.matchDate = matchDate;
  }

  // Constructeur complet (avec scores - match terminé)
  public Match(Team homeTeam, Team awayTeam, Stadium stadium, LocalDateTime matchDate, Integer homeScore,
      Integer awayScore) {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.stadium = stadium;
    this.matchDate = matchDate;
    this.homeScore = homeScore;
    this.awayScore = awayScore;
  }

  // Getters et Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Team getHomeTeam() {
    return homeTeam;
  }

  public void setHomeTeam(Team homeTeam) {
    this.homeTeam = homeTeam;
  }

  public Team getAwayTeam() {
    return awayTeam;
  }

  public void setAwayTeam(Team awayTeam) {
    this.awayTeam = awayTeam;
  }

  public Stadium getStadium() {
    return stadium;
  }

  public void setStadium(Stadium stadium) {
    this.stadium = stadium;
  }

  public LocalDateTime getMatchDate() {
    return matchDate;
  }

  public void setMatchDate(LocalDateTime matchDate) {
    this.matchDate = matchDate;
  }

  public Integer getHomeScore() {
    return homeScore;
  }

  public void setHomeScore(Integer homeScore) {
    this.homeScore = homeScore;
  }

  public Integer getAwayScore() {
    return awayScore;
  }

  public void setAwayScore(Integer awayScore) {
    this.awayScore = awayScore;
  }

  // toString pour faciliter le debug
  @Override
  public String toString() {
    return "Match{" +
        "id=" + id +
        ", homeTeam=" + (homeTeam != null ? homeTeam.getName() : "null") +
        ", awayTeam=" + (awayTeam != null ? awayTeam.getName() : "null") +
        ", stadium=" + (stadium != null ? stadium.getName() : "null") +
        ", matchDate=" + matchDate +
        ", score=" + (homeScore != null ? homeScore : "-") + " - " + (awayScore != null ? awayScore : "-") +
        '}';
  }
}
