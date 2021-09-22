package com.dayang.test;

import com.dayang.domain.Item;
import com.dayang.domain.Item_option;
import com.dayang.domain.TestColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevTestRepository extends JpaRepository<TestColor, Long> {

    TestColor findByItemOption(Item_option itemOption);
}
