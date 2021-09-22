package com.dayang.repository;

import com.dayang.domain.Item_option;
import com.dayang.domain.Position;
import com.dayang.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class StoreRepository {

    private final EntityManager em;
    public Position getSinglePosition(Store store){
        return em.createQuery("select p from Position p where p.store =:store",Position.class)
                .setParameter("store", store)
                .getSingleResult();
    }

    public List<Position> getAllPosition(){
        return em.createQuery("select p from Position p", Position.class)
                .getResultList();
    }

    public List<Store> getAllStore(){
        return (List<Store>) em.createQuery("select s from Store s", Store.class)
                .getResultList();
    }
    public Store getSingleStore(String name){
        try{
            return em.createQuery("select s from Store s where s.store_name =:name", Store.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }

    public Store findById(Long id){
        return em.createQuery("select s from Store s where s.id =:id", Store.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
