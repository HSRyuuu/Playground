package com.example.playground.spring.jpa;

import com.example.playground.spring.jpa.entity.Payment;
import com.example.playground.spring.jpa.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JpaTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    void testUuidGenerator(){
        Payment payment = new Payment();
        Payment save = paymentRepository.save(payment);
        System.out.println(save);
    }
}
