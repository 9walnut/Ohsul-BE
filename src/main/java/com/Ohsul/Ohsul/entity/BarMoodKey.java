package com.Ohsul.Ohsul.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarMoodKey implements Serializable {
    private Integer mood;
    private Integer bar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BarMoodKey that)) return false;
        return Objects.equals(mood, that.mood) && Objects.equals(bar, that.bar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mood, bar);
    }
}
