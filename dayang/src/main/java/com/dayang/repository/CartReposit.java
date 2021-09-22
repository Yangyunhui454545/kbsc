package com.dayang.repository;

import com.dayang.domain.Cart;
import com.dayang.domain.Item;
import com.dayang.domain.Item_option;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartReposit {
    private final EntityManager em;

    public void save(Cart cart){ em.persist(cart); }

    public Cart findOne(Item_option item_option){
        return em.createQuery("select c from Cart c where c.item_option =:item_option", Cart.class)
                .setParameter("item_option", item_option)
                .getSingleResult();
    }
}
