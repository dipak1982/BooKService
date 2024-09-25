package com.user.registration.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "member")
public class MemberEntity implements Serializable {
  private static final long serialVersionUID = -2731425678149216053L;

  @Id
  @GeneratedValue
  @Column(name = "Id", length = 20)
  private Long memberId;

  @Column(name = "type", length = 20)
  private String type;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "member")
  private UserEntity user;

  public Long getMemberId() {
    return memberId;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
