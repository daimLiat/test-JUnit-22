package com.github.daimLiat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SubClassTest {

    @Test
    public void subClassTestFirst () {
        Assertions.assertEquals(7,1);
    }

    @Test
    public void subClassTestSecond() {
        Assertions.assertEquals(2, 1 + 1);
    }

    @Test
    public void subClassTestThird() {
        Assertions.assertTrue(1 + 1 == 2, "the statement isn't true");
    }
}
