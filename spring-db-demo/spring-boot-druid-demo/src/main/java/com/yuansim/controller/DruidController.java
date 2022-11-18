package com.yuansim.controller;

import com.yuansim.dao.CursorDao;
import com.yuansim.domain.Driver;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DruidController {

    private final CursorDao cursorDao;

    @GetMapping("/driver/list")
    public List<Driver> getForLimit(@RequestParam("limit") Integer limit) {

        List<Driver> drivers = cursorDao.findByLimit(limit, 10);

        return drivers;
    }
}
