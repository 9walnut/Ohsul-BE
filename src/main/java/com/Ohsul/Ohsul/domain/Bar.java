package com.Ohsul.Ohsul.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Bar {

    @Id
    private String id;
    private String name;
    private int value;
}
