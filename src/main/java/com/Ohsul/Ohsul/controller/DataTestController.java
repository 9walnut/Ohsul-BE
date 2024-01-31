package com.Ohsul.Ohsul.controller;

import com.Ohsul.Ohsul.domain.Bar;
import com.Ohsul.Ohsul.service.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bar")
public class DataTestController {
    private BarService barService;

    @Autowired
    public void BarController(BarService barService) {
        this.barService = barService;
    }

    @PostMapping
    public Bar createBar(@RequestBody Bar bar) {
        return barService.createBar(bar);
    }

    @GetMapping
    public List<Bar> getAllBar() {
        return barService.getAllBarInfo();
    }

    @GetMapping("/{barId}")
    public Bar getBarById(@PathVariable String barId) {
        return barService.getBarById(barId);
    }

    @DeleteMapping("/{barId}")
    public void deleteBarById(@PathVariable String barId) {
        barService.deleteBarById(barId);
    }
}
