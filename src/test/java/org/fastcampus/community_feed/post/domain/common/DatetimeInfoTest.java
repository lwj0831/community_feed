package org.fastcampus.community_feed.post.domain.common;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DatetimeInfoTest {

    @Test
    void givenCreatedWhenUpdateThenEditedTrueAndTimeIsUpdated() {
        // given
        DatetimeInfo info = new DatetimeInfo();
        LocalDateTime datetime = info.getDateTime();

        // when
        info.updateEditDateTime();

        // then
        assertNotEquals(datetime, info.getDateTime());
        assertTrue(info.isEdited());
    }

}