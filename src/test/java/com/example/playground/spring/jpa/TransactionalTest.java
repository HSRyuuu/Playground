package com.example.playground.spring.jpa;

import com.example.playground.spring.jpa.entity.AppUser;
import com.example.playground.spring.jpa.entity.TestEntity;
import com.example.playground.spring.jpa.repository.TestEntityRepository;
import com.example.playground.spring.jpa.repository.UserRepository;
import com.example.playground.spring.transactional.InnerService;
import com.example.playground.spring.transactional.OuterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionalTest {

    @Autowired
    private OuterService outerService;

    @Autowired
    private TestEntityRepository testEntityRepository;

    @Test
    void testTransactional() {
        try{
            outerService.outerMethod();
        }catch(Exception e){
            System.out.println("예외 발생: " + e.getMessage());
        }
        System.out.println("✅ 결과: entityExists="+ testEntityRepository.existsById(1L) );
    }
}
