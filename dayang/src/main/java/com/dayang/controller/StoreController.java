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
    private final ItemService itemService;
    private final OrderService orderService;

    @GetMapping("/stores")
    public String stores(Model model){
        List<Store> stores = storeService.findAllStore();
        List<Position> positions = storeService.findAllPosition();
        model.addAttribute("stores", stores);
        model.addAttribute("positions", positions);
        return "store/stores";
    }

    @GetMapping("/soldout/{store_id}/{item_option_id}")
    public String soldout(@PathVariable("store_id")Long id, @PathVariable("item_option_id")Long option_id, Model model){


        return "store/near-store";

    }

    @GetMapping("/storeDistance/{store_id}")
    public String distance(@PathVariable("store_id")Long id, Model model){


        return "store/store-content";
    }
    @GetMapping("/admin/{store_id}")
    public String admin(@PathVariable("store_id") Long id, @RequestParam("code") String pw,  Model model){
        Store store = storeService.findById(id);
        if(!store.getCode().equals(pw)){
            return "login/fail";
        }

        int todaySale = orderService.todaySales(store);
        int weekSale = orderService.thisWeekSales(store);
        List<StoreQuantity> storeQuantities = itemService.soldOutOptions(store);
        List<Item> item = new ArrayList<>();
        List<Item_option>item_options = new ArrayList<>();
        List<Item_img> item_imgs = new ArrayList<>();
        for(StoreQuantity storeQuantity : storeQuantities){
            item.add(itemService.findOne(storeQuantity.getItem().getId()));
            item_options.add(itemService.findItemOptionById(storeQuantity.getItemOption().getId()));
            item_imgs.add(itemService.itemImg(storeQuantity.getItem().getId()));
        }


        model.addAttribute("todaySale", todaySale);
        model.addAttribute("weekSale", weekSale);
        model.addAttribute("item", item);
        model.addAttribute("item_options", item_options);
        model.addAttribute("item_img", item_imgs);

        return "login/admin";
    }



}