package com.dayang.service;

import com.dayang.domain.Item;
import com.dayang.domain.TestColor;
import com.dayang.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public List<TestColor> findByItem(Item item){
        List<TestColor> testColors = testRepository.findByItem(item);
        return testColors;
    }
}
