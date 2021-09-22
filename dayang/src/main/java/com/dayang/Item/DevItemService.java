package com.dayang.Item;

import com.dayang.Item.DTO.ImageDTO;
import com.dayang.Item.DTO.ItemDTO;
import com.dayang.Item.DTO.OptionDTO;
import com.dayang.Item.DTO.ItemPopularDTO;
import com.dayang.Item.reository.DevItemImageRepository;
import com.dayang.Item.reository.DevItemOptionRepository;
import com.dayang.Item.reository.DevItemRepository;
import com.dayang.Item.reository.DevProductImageRepository;
import com.dayang.category.CategoryDTO;
import com.dayang.category.DevCategoryService;
import com.dayang.domain.*;
import com.dayang.store.DevStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DevItemService {


    private final DevItemRepository devItemRepository;
    private final DevItemImageRepository devItemImageRepository;
    private final DevItemOptionRepository devItemOptionRepository;
    private final DevCategoryService devCategoryService;
    private final DevProductImageRepository devProductImageRepository;
    private final DevStoreService devStoreService;

    private  static List<Long> random  = new ArrayList<>();


    public Item_img findItemImageByItem(Item item){
        return devItemImageRepository.findTop1ByItem(item);
    }

    public Page<Item> findByVegan(int pageNum, String sort){
        return devItemRepository.findByIsVegan("Y", PageRequest.of(pageNum-1, 9,Sort.by(sort)));
    }

    public Map<String, Object> vegan(int pageNum, String sort){
        Page<Item> itemList = findByVegan(pageNum, sort);
        List<ItemDTO> itemDTOList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for(Item item : itemList){
            Item_img itemImg = findItemImage(item);
            itemDTOList.add(itemDTO(item, itemImg));
        }
        map.put("item", itemDTOList);
        map.put("size", itemList.getTotalElements());

        return map;
    }

    public ItemDTO itemDTO(Item item, Item_img itemImg){

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemId(item.getId());
        itemDTO.setItemName(item.getName());
        itemDTO.setItemPrice(item.getItem_price());
        itemDTO.setItemDiscountPrice(item.getDiscount_price());
        itemDTO.setItemTestable(item.getIs_testable());
        itemDTO.setItemImage(itemImg.getItem_img());
        itemDTO.setIsVegan(item.getIsVegan());
        return itemDTO;
    }
    public ImageDTO imageDTO(Product_img productImg, Item item){
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setImage(productImg.getProduct_img());
        imageDTO.setImageId(productImg.getId());
        imageDTO.setItemId(item.getId());

        return imageDTO;
    }

    public Map<String, Object> findByCategory(List<CategoryDTO> categories, int pageNum, String sort){
        List<Category> category = new ArrayList<>();
        for(CategoryDTO c: categories) category.add(devCategoryService.findById(c.getCategoryId()));
        Page<Item> items = categoryItem(pageNum, sort, category);
        Map<String, Object> map = new HashMap<>();
        List<ItemDTO> itemDTOList = new ArrayList<>();

        for(Item i : items){
            Item_img itemImage = findItemImageByItem(i);
            ItemDTO itemDTO = itemDTO(i, itemImage);
            itemDTOList.add(itemDTO);
        }
        map.put("item", itemDTOList);
        map.put("size", items.getTotalElements());
        return map;
    }

    private Page<Item> categoryItem(int pageNum, String sort, List<Category> category) {
        return devItemRepository.findByCategoriesIn(category, PageRequest.of(pageNum - 1, 9, Sort.by(sort).descending()));
    }


    public Item findById(Long id){
        return devItemRepository.findById(id).get();
    }

    public Item_img findItemImage(Item item){
        return devItemImageRepository.findTop1ByItem(item);
    }

    public ItemDTO findOneItem(Long id){
        Item item = new Item();
        Item_img itemImg = new Item_img();
        try {
            item = findById(id);
            itemImg = findItemImage(item);

        }
        catch (NoSuchElementException e){
            item = null;
            itemImg = null;
        }
        if(item==null) return null;
        ItemDTO itemDTO = itemDTO(item, itemImg);
        return itemDTO;
    }
    public List<Item_option> findItemOptionByItem(Item item){ return devItemOptionRepository.findByItem(item);}

    public List<ImageDTO> productImage(Long id){
        Item item = findById(id);
        List<Product_img> productImgs = devProductImageRepository.findByItem(item);
        List<ImageDTO> imageDTOList = new ArrayList<>();

        for(Product_img p : productImgs){
            ImageDTO imageDTO = imageDTO(p,item);
            imageDTOList.add(imageDTO);
        }
        return imageDTOList;
    }

    public List<OptionDTO> itemOptions(Long itemId){
        Item item = findById(itemId);
        List<Item_option> item_options = findItemOptionByItem(item);
        List<OptionDTO> optionDTOList = new ArrayList<>();
        for(Item_option i : item_options){
            OptionDTO optionDTO = new OptionDTO();
            optionDTO.setOptionId(i.getId());
            optionDTO.setOptionName(i.getItem_option_name());
            optionDTO.setTotalCnt(i.getStockQuantity());
            optionDTOList.add(optionDTO);
        }
        return optionDTOList;
    }

    public List<OptionDTO> itemOption(Long itemId, Long storeId){
        Store store = devStoreService.findById(storeId);
        Item item = findById(itemId);
        List<Item_option> item_options = findItemOptionByItem(item);
        List<OptionDTO> optionDTOList = new ArrayList<>();

        for(Item_option i : item_options){
            OptionDTO optionDTO = new OptionDTO();
            StoreQuantity storeQuantity = devStoreService.findStoreQuantity(i, store);
            optionDTO.setOptionId(i.getId());
            optionDTO.setOptionName(i.getItem_option_name());
            optionDTO.setTotalCnt(i.getStockQuantity());
 //           optionDTO.setStoreCnt(storeQuantity.getStock_quantity());
            optionDTOList.add(optionDTO);
        }
        return optionDTOList;
    }

    public List<ItemDTO> searchItem(String keyword, int pageNum, String sort){
        List<Item> itemList = findByNameContaining(keyword, pageNum, sort);
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for(Item item : itemList){
            Item_img item_img = devItemImageRepository.findTop1ByItem(item);
            ItemDTO itemDTO = itemDTO(item, item_img);
            itemDTOList.add(itemDTO);
        }
        return itemDTOList;
    }

    private List<Item> findByNameContaining(String keyword, int pageNum, String sort) {
        return devItemRepository.findByNameContaining(keyword, PageRequest.of(pageNum - 1, 9, Sort.by(sort).descending()));
    }

    public int searchItemSize(String keyword){
        int size = devItemRepository.findByNameContaining(keyword).size();
        return size;
    }

    public List<ItemPopularDTO> popularity(){
        List<Item> itemList = devItemRepository.findTop10ByIdGreaterThanOrderByPopularityDesc(1L);
        List<ItemPopularDTO> itemPopularDTOList = new ArrayList<>();

        for(int i=0; i<10; i++){
            ItemPopularDTO itemPopularDTO = new ItemPopularDTO();
            itemPopularDTO.setItemId(itemList.get(i).getId());
            itemPopularDTO.setItemName(itemList.get(i).getName());
            itemPopularDTO.setRank(i+1);
            itemPopularDTOList.add(itemPopularDTO);
        }
        return itemPopularDTOList;
    }
    public static List<Long> randomNumber(){
        Random random = new Random();
        List<Long> list = new ArrayList<>();
        list.add((long) random.nextInt(1458));
        for(int i = 1 ; i < 5 ; i++) {
            int num = random.nextInt(1458);
            for (int j = 0; j < i; j++){
                if(list.get(j) == (long) num){
                    i--;
                    break;
                }
            }
            list.add((long) num);
        }
        return list;
    }

    public static List<Long> rand(){
        random = randomNumber();
        return random;
    }

    public List<ItemDTO> mdsItem(List<Long> list){
        List<Item> itemList = new ArrayList<>();
        for(Long l : list){
            itemList.add(findById(l));
        }
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for(Item item : itemList){
            Item_img itemImg = findItemImage(item);
            ItemDTO itemDTO = itemDTO(item, itemImg);
            itemDTOList.add(itemDTO);
        }
        return itemDTOList;
    }

    public List<ItemDTO> bestSeller(){
        List<Item> itemList = devItemRepository.findTop3ByIdGreaterThanOrderByOrderCnt(1L);
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for(Item item : itemList){
            Item_img itemImg = findItemImage(item);
            ItemDTO itemDTO = itemDTO(item, itemImg);
            itemDTOList.add(itemDTO);
        }
        return itemDTOList;
    }
}
