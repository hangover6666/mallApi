package com.zust.mallapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class MallApiApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(UUID.randomUUID());
    }

}
