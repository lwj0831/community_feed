package org.fastcampus.community_feed.post.domain;

import org.fastcampus.community_feed.common.domain.PositiveIntegerCounter;
import org.fastcampus.community_feed.post.domain.content.PostContent;
import org.fastcampus.community_feed.user.domain.User;

public class Post {
    private final Long id;
    private final User author;
    private final PostContent content;
    private final PositiveIntegerCounter likeCounter;
    private PostPublicationState state;

    public Post(Long id, User author, PostContent content) {
        if(author ==null){
            throw new IllegalStateException();
        }
        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCounter = new PositiveIntegerCounter();
        this.state = PostPublicationState.PUBLIC;
    }

    public void like(User user){
        if(user.equals(author)){
            throw new IllegalStateException();
        }
        likeCounter.increase();
    }

    public void unlike(){
        likeCounter.decrease();
    }

    public void updateContent(User user,String updateContent,PostPublicationState state){
        if(!user.equals(author)){
            throw new IllegalStateException();
        }
        content.updateContent(updateContent);
        this.state=state;
    }


}
