package com.dayang.store;

import com.dayang.domain.Item_option;
import com.dayang.domain.Store;
import com.dayang.domain.StoreQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevStoreQuantityRepository extends JpaRepository <StoreQuantity, Long> {
   StoreQuantity findByItemOptionAndStore(Item_option item_option, Store store);

}
