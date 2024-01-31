package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.domain.Bar;

import java.util.List;

public interface BarService {
    Bar createBar(Bar bar);
    List<Bar> getAllBarInfo();
    Bar getBarById(String id);
    void deleteBarById(String id);
}
