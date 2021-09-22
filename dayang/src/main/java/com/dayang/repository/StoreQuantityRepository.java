package com.dayang.repository;

import com.dayang.domain.Item_option;
import com.dayang.domain.Store;
import com.dayang.domain.StoreQuantity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface StoreQuantityRepository extends JpaRepository<StoreQuantity, Long> {

    @Query("select sq from StoreQuantity sq where sq.itemOption =:item_option")
    List<StoreQuantity> findByItemOptions(@Param("item_option") Item_option item_option);


    @Query("select sq from StoreQuantity sq where sq.stock_quantity = 0 and sq.store =:store")
    List<StoreQuantity> soldoutItemoptions(@Param("store") Store store);

}
