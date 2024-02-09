package com.Ohsul.Ohsul.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.*;

@Getter
@Setter
public class User {
    @Id
    private Integer userNumber;

    private String userId;
    private String userPw;
    private String salt;
    private String userName;
    private String userNickname;
}
