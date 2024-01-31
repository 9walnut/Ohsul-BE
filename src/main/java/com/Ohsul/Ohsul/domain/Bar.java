package com.Ohsul.Ohsul.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bar")
public class Bar {

    @Getter
    @Setter
    @Id
    private String id;
    private String name;
    private int value;
}
