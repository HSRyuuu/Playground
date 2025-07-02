package com.example.playground.spring.transactional;


import com.example.playground.spring.jpa.entity.AppUser;
import com.example.playground.spring.jpa.entity.TestEntity;
import com.example.playground.spring.jpa.repository.TestEntityRepository;
import com.example.playground.spring.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OuterService {

    private final InnerService innerService;
    private final TestEntityRepository testEntityRepository;

    // OuterService.class
    @Transactional(propagation = Propagation.REQUIRED)
    public void outerMethod() {
        try{
            innerService.innerMethod();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }




    @Transactional
    public void anotherOuterMethod() {
        throw new RuntimeException("");
    }


}
