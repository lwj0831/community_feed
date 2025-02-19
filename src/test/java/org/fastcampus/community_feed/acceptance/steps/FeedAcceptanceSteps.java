package org.fastcampus.community_feed.acceptance.steps;

import io.restassured.RestAssured;
import org.fastcampus.community_feed.post.application.dto.CreatePostRequestDto;
import org.fastcampus.community_feed.post.application.dto.GetPostContentResponseDto;
import org.springframework.http.MediaType;

import java.util.List;

public class FeedAcceptanceSteps {

    public static Long requestCreatePost(CreatePostRequestDto dto) {
        return RestAssured
                .given().log().all()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/post")
                .then().log().all()
                .extract()
                .jsonPath()
                .getObject("value", Long.class);  //응답 json의 필드명 "value":~의 해당 값 갖고옴
    }

    public static List<GetPostContentResponseDto> requestFeedList(String token) {
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .header("Authorization", "Bearer " + token) //여기서 Header로 AOP활용해 UserInfo user받음
                .get("/feed")
                .then().log().all()
                .extract()
                .jsonPath()
                .getList("value", GetPostContentResponseDto.class);
    }

    public static Integer requestFeedCode(String token) {
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .header("Authorization", "Bearer " + token)
                .get("/feed")
                .then().log().all()
                .extract()
                .jsonPath()
                .get("code");
    }
}
