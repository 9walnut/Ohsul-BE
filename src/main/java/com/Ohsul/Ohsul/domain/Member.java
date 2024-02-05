package com.Ohsul.Ohsul.domain;

import jakarta.persistence.*;
import lombok.*;

// 테이블 임의로 작성 중어서 추후 변경가능합니다.
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="user_id")
  private Long id;

  @Column(updatable = false, unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private Integer state;

  @Column(name = "nickname")
  private String nickname;

}
