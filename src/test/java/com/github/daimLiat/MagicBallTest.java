package com.github.daimLiat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MagicBallTest {

    @Test
    public void testLucky() {
        Assertions.assertEquals(7, MagicBall.getLucky());
    }
}