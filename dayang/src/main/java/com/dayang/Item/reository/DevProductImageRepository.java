package com.dayang.Item.reository;

import com.dayang.domain.Item;
import com.dayang.domain.Product_img;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevProductImageRepository extends JpaRepository<Product_img, Long> {

    List<Product_img> findByItem(Item item);
}
