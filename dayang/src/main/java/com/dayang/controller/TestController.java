package com.dayang.controller;

import com.dayang.domain.*;
import com.dayang.service.CartService;
import com.dayang.service.ItemService;
import com.dayang.service.StoreService;
import com.dayang.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    private final ItemService itemService;
    private final CartService cartService;

    @GetMapping("/test")
    public String test(){
        return "test/test-start";
    }


    @GetMapping("/test/{item_id}")
    public String ItemOption(@PathVariable("item_id") Long id, Model model){
        Item item = itemService.findOne(id);
        Item_img item_img = itemService.itemImg(item.getId());
        List<Item_option> item_options = itemService.itemOptionList(item);
        List<TestColor> testColors = testService.findByItem(item);

        model.addAttribute("item_img", item_img);
        model.addAttribute("item", item);
        model.addAttribute("item_options", item_options);
        model.addAttribute("testColor", testColors);
        return "test/test-main";
    }



    @GetMapping("/testAll")
    public String testItems(@RequestParam(value = "pageNum", defaultValue = "1")int pageNum, Model model){
        List<Cart> carts = cartService.findTestableCart(pageNum);

        return "test/test-cart";
    }
}