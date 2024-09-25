package com.springboot.app.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "category")
public class Category implements Serializable {
  private static final long serialVersionUID = -2731425678149216053L;

  @Id @GeneratedValue private long id;

  @Column(name = "categoryName", length = 100)
  private String catName;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date created_dt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date modify_dt;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "category")
  private Book book;

  public Category() {
    super();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCatName() {
    return catName;
  }

  public void setCatName(String catName) {
    this.catName = catName;
  }

  public Date getCreated_dt() {
    return created_dt;
  }

  public void setCreated_dt(Date created_dt) {
    this.created_dt = created_dt;
  }

  public Date getModify_dt() {
    return modify_dt;
  }

  public void setModify_dt(Date modify_dt) {
    this.modify_dt = modify_dt;
  }
}
