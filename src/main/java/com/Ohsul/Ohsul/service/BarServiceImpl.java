package com.Ohsul.Ohsul.service;

import com.Ohsul.Ohsul.domain.Bar;
import com.Ohsul.Ohsul.repository.BarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarServiceImpl implements BarService {
    private final BarRepository barRepository;

    @Autowired
    public BarServiceImpl(BarRepository barRepository) {
        this.barRepository = barRepository;
    }

    @Override
    public Bar createBar(Bar bar) {
        return barRepository.save(bar);
    }

    @Override
    public List<Bar> getAllBarInfo() {
        return barRepository.findAll();
    }

    @Override
    public Bar getBarById(String id) {
        return barRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteBarById(String id) {
        barRepository.deleteById(id);
    }
}
