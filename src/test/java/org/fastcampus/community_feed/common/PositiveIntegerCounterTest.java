package org.fastcampus.community_feed.common;

import org.fastcampus.community_feed.common.domain.PositiveIntegerCounter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositiveIntegerCounterTest {

    @Test
    void givenCreated_whenIncrease_thenCountIsOne(){
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        //when
        counter.increase();
        //then
        assertEquals(1,counter.getCount());
    }
    @Test
    void givenCreatedAndIncrease_whenDecrease_thenCountIsOne(){
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        //when
        counter.increase();
        counter.decrease();
        //then
        assertEquals(0,counter.getCount());
    }

}