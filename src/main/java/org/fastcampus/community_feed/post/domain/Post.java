package org.fastcampus.community_feed.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.fastcampus.community_feed.common.domain.PositiveIntegerCounter;
import org.fastcampus.community_feed.post.domain.content.PostContent;
import org.fastcampus.community_feed.user.domain.User;

@Builder
@AllArgsConstructor
public class Post {
    private final Long id;
    private final User author;
    private final PostContent content;
    private final PositiveIntegerCounter likeCounter;
    private PostPublicationState state;

    public Post(Long id, User author, PostContent content, PostPublicationState state, PositiveIntegerCounter likeCounter) {
        if (author == null) {
            throw new IllegalArgumentException("author should not be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("content should not be null or empty");
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.state = state;
        this.likeCounter = likeCounter;
    }

    public Post(Long id, User author, PostContent content) {
        this(id, author, content, PostPublicationState.PUBLIC, new PositiveIntegerCounter());
    }

    public Post(Long id, User author, String content) {
        this(id, author, new PostContent(content), PostPublicationState.PUBLIC, new PositiveIntegerCounter());
    }

    public void like(User user){
        if(user.equals(author)){
            throw new IllegalArgumentException();
        }
        likeCounter.increase();
    }

    public void unlike(){
        likeCounter.decrease();
    }

    public void updateContent(User user,String updateContent){
        if(!user.equals(author)){
            throw new IllegalArgumentException();
        }
        content.updateContent(updateContent);
        this.state=state;
    }

    public void updateState(PostPublicationState state) {
        this.state = state;
    }

    public int getLikeCount(){
        return likeCounter.getCount();
    }

    public Long getId() {
        return id;
    }

    public PostContent getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public PostPublicationState getState() {
        return state;
    }

    public String getContentText(){
        return content.getContentText();
    }
}
