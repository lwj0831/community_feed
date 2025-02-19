package org.fastcampus.community_feed.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.admin.ui.query.UserStatsQueryRepository;
import org.fastcampus.community_feed.admin.ui.dto.users.GetDailyRegisterUserResponseDto;
import org.fastcampus.community_feed.common.utils.TimeCalculator;
import org.fastcampus.community_feed.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserStatsQueryRepositoryImpl implements UserStatsQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QUserEntity userEntity = QUserEntity.userEntity;

    @Override
    public List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays) {
        return queryFactory
                .select(
                        Projections.fields(
                                GetDailyRegisterUserResponseDto.class,
                                userEntity.regDate.as("date"),
                                userEntity.count().as("count")
                        )
                )
                .from(userEntity)
                .where(userEntity.regDate.after(TimeCalculator.getDateDaysAgo(beforeDays))) //데이터 크기 줄이기(범위한정)
                .groupBy(userEntity.regDate)
                .orderBy(userEntity.regDate.asc())
                .fetch();
    }
}
