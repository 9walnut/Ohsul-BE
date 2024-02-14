package com.Ohsul.Ohsul.domain;

import jakarta.persistence.Column;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
public class Bar {
    private Integer barId;
    private String barName;
    private String roadAddress;
    private String localAddress;
    private String description;
    private String telephone;
    private String barImg;
    private Boolean snack;
    private Boolean toilet;
    private Boolean parkingArea;
}
