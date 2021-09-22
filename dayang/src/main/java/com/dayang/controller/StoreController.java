package com.dayang.controller;

import com.dayang.domain.*;
import com.dayang.service.ItemService;
import com.dayang.service.OrderService;
import com.dayang.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/stores")
    public String stores(Model model){

        return "store/stores";
    }


    @GetMapping("/storeDistance/{store_id}")
    public String distance(@PathVariable("store_id")Long id, Model model){


        return "store/store-content";
    }

}