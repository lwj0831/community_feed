package org.fastcampus.community_feed.admin.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetTableListResponseDto<T> {
    private int totalCount;
    List<T> tableList;
}
