package com.joana.bundler.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

//When the Controller needs to retrieve or modify data,
//it sends requests to the Model layer.
//The Model layer then uses the ORM or DAO layer to interact with
//the database, perform the requested operations, and return the results to the Controller.

@Entity
@Table(name="names")
public class Joy {

  //creates a unique ID-autoIcrementing type in sql database
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //list the fields that I want listed in the @Table
  @NotNull
  @Size(min=2, message=" must be at least 2 characters")
  private String name;

  //notnull or notempty handles validations
  @NotBlank(message = "gender required")
  private String gender;

  @NotNull
  @Size(min=2, message=" must be at least 2 characters")
  private String origin;

  @NotNull
  @Size(min=2, message=" must be at least 2 characters")
  private String meaning;

  //created a non user-able updatable field --- created and updated AT
  @Column(updatable=false)
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private Date createdAt;
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private Date updatedAt;

  //creates the PrimaryKey element that joins this table to the One table...
  //meaning Books can belong to ONE user... and user can have many books
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="user_id")
  private User user;


  public Joy(){
    //leave this contrusctor empty
  }

  //no need to put Long id, nor created and updated at
  //why? because these above mentioned will not change based on input from user

  public Joy(String name, String gender, String origin, String meaning, User user) {
    this.name = name;
    this.gender = gender;
    this.origin = origin;
    this.meaning = meaning;
    this.user = user;
  }



//handles the updating and creating  
  @PrePersist
  protected void onCreate() {
    this.createdAt = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = new Date();
  }

//*********** getters and setters ************/

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return this.gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getOrigin() {
    return this.origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getMeaning() {
    return this.meaning;
  }

  public void setMeaning(String meaning) {
    this.meaning = meaning;
  }

  public Date getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }


  
}