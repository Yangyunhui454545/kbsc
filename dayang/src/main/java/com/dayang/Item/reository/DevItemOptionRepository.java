package com.dayang.Item.reository;

import com.dayang.domain.Item;
import com.dayang.domain.Item_option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevItemOptionRepository extends JpaRepository<Item_option, Long> {
    List<Item_option> findByItem(Item item);
}
