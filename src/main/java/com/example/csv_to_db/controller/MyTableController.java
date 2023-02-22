package com.example.csv_to_db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyTableController extends csvtodatabase{

    @Autowired
    private MyTableService myTableService;

    @GetMapping("/my-table")
    public String getAllMyTables(Model model) {
        model.addAttribute("rows", myTableService.getAllRows(csvtodatabase.csv_file_db));
        return "my-table";
    }
}