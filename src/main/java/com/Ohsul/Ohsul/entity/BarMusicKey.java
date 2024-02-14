package com.Ohsul.Ohsul.entity;

import com.Ohsul.Ohsul.domain.Bar;
import com.Ohsul.Ohsul.domain.Music;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarMusicKey implements Serializable {
    private Integer music;
    private Integer bar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BarMusicKey that)) return false;
        return Objects.equals(music, that.music) && Objects.equals(bar, that.bar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(music, bar);
    }
}
