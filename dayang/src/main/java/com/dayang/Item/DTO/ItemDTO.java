package com.dayang.Item.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemDTO {

    private Long itemId;
    private String itemName;
    private String itemImage;
    private int itemPrice;
    private int itemDiscountPrice;
    private char itemTestable;
    private String isVegan;

}
