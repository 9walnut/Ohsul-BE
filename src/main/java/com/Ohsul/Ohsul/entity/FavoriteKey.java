package com.Ohsul.Ohsul.entity;

import com.Ohsul.Ohsul.domain.Bar;
import com.Ohsul.Ohsul.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteKey implements Serializable {
    
    // 각 entity의 id 필드 참조
    private Integer user;
    private Integer bar;

    // entity의 동일성과 동등성 확인
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoriteKey that)) return false;
        return Objects.equals(user, that.user) && Objects.equals(bar, that.bar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, bar);
    }
}
