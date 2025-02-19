package org.fastcampus.community_feed.admin.ui.query;

import org.fastcampus.community_feed.admin.ui.dto.users.GetDailyRegisterUserResponseDto;

import java.util.List;

public interface UserStatsQueryRepository {
    List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays);
}
