package com.Ohsul.Ohsul.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarAlcoholKey implements Serializable {
    private Integer bar;
    private Integer alcohol;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BarAlcoholKey that)) return false;
        return Objects.equals(bar, that.bar) && Objects.equals(alcohol, that.alcohol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bar, alcohol);
    }
}
