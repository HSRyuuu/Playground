package com.example.playground.spring.jpa;

import com.example.playground.spring.jpa.dto.UserDto;
import com.example.playground.spring.jpa.entity.AppUser;
import com.example.playground.spring.jpa.entity.Order;
import com.example.playground.spring.jpa.entity.Payment;
import com.example.playground.spring.jpa.repository.CustomUserRepository;
import com.example.playground.spring.jpa.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Slf4j
@SpringBootTest
public class AppUserFetchJoinTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserRepository customUserRepository;


    @Nested
    @DisplayName("User 단건 조회")
    @Transactional
    class FindOneUserFetchJoinTest {

        @Test
        @DisplayName("JPQL")
        void userJpql() {
            String userName = "user1";
            // 사용자 하나 조회 (user1)
            AppUser user = userRepository.findWithOrdersAndPaymentsByName(userName).orElseThrow();

            System.out.println("### user.orderList ###");
            for(Order order : user.getOrders()) {
                System.out.println(order.toString());
            }

            System.out.println("### user.paymentList ###");
            for(Payment payment : user.getPayments()) {
                System.out.println(payment.toString());
            }
        }

        @Test
        @DisplayName("QueryDSL")
        void useQueryDSL() {
            // 사용자 하나 조회 (user1)
            AppUser user = customUserRepository
                    .findByName("user1")
                    .orElseThrow();

            System.out.println("### user.orderList ###");
            for(Order order : user.getOrders()) {
                System.out.println(order.toString());
            }

            System.out.println("### user.paymentList ###");

            for(Payment payment : user.getPayments()) {
                System.out.println(payment.toString());
            }
        }
    }


    @Test
    @Transactional
    @DisplayName("User 여러개 조회")
    void fetchJoinUserLimit() {
        List<AppUser> users = customUserRepository.findUsers(2);
        for(AppUser user : users) {
            System.out.println("#############[" + user.getName() + "]#############");

            System.out.println("# orders");
            for(Order order : user.getOrders()) {
                System.out.println(order.toString());
            }

            System.out.println("# payments");
            for(Payment payment : user.getPayments()) {
                System.out.println(payment.toString());
            }
            System.out.println();
        }

    }

    @Nested
    @DisplayName("Cartesian Product 문제 발생 in 조건 검색, DTO 조회")
    class fetchJoinCartesianProductIssues{
        @Test
        @Transactional
        void badExample() {
            List<String> paymentMethods = List.of("PAYPAL");
            List<String> orderItemNames = List.of("order1", "order2");
            PageRequest pageRequest = PageRequest.of(0, 5);

            List<UserDto> users = customUserRepository.searchUserBadExample( paymentMethods, orderItemNames, pageRequest);
            for(UserDto user : users) {
                System.out.println(user.toString());
            }
        }
        @Test
        @Transactional
        void goodExample() {
            List<String> paymentMethods = List.of("PAYPAL");
            List<String> orderItemNames = List.of("order1", "order2");
            PageRequest pageRequest = PageRequest.of(0, 5);

            List<UserDto> users = customUserRepository.searchUser( paymentMethods, orderItemNames, pageRequest);
            for(UserDto user : users) {
                System.out.println(user.toString());
            }
        }
    }



}
