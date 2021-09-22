package com.dayang.controller;

import com.dayang.domain.*;
import com.dayang.service.ItemService;
import com.dayang.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final StoreService storeService;

    /* 임의로 추가함. index.html에서 '오늘의 상품' 탭을 클릭했을 때 이동 */

    @GetMapping("/categoryList")
    public String searchItem(){ return "searchItem/categoryList"; }

    @Transactional
    @RequestMapping(method = RequestMethod.GET, value ="/category/{list}")
    public String categoryItem(@PathVariable("list") Long category_id,
                               @RequestParam(value = "sort", defaultValue = "item_id") String sort, Model model,
                               @RequestParam(value="page", defaultValue = "1") Integer pageNum){

        return "searchItem/category";
    }

    @GetMapping("/item/{item_id}")
    public String Item(@PathVariable("item_id")Long id, Model model){


        return "searchItem/item";
    }
    @GetMapping("/item/{item_id}/detail")
    public String ItemDetail(@PathVariable("item_id") Long id, Model model){

        return "searchItem/item-detail";
    }


    @GetMapping("item/{store_id}/{item_id}/item_option")
    public String ItemOption(@PathVariable("item_id") Long id, @PathVariable("store_id") Long store_id, Model model){

        return "searchItem/item-option";
    }

    @GetMapping("/searchVoice")
    public String searchVoicePage(Model model) {

        return "searchItem/voice-search";
    }

    @GetMapping("/searchVoice/{keyword}")
    public String searchVoice(@PathVariable("keyword")String keyword, Model model, @RequestParam(value= "sort", defaultValue = "reviewCnt") String sort, @RequestParam(value="page", defaultValue = "1") Integer pageNum){



        return "searchItem/search-result";
    }


    @GetMapping("/review/{item_id}")
    public String review(@PathVariable("item_id")Long id, Model model){

        return "searchItem/review";
    }

    @GetMapping("/todayGoods")
    public String bestItem(Model model){
        return "searchItem/today-goods";

    }
}