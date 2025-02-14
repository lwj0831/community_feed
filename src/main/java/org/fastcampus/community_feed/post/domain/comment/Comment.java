package org.fastcampus.community_feed.post.domain.comment;

import org.fastcampus.community_feed.common.domain.PositiveIntegerCounter;
import org.fastcampus.community_feed.post.domain.Post;
import org.fastcampus.community_feed.post.domain.content.CommentContent;
import org.fastcampus.community_feed.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final CommentContent content;
    private final PositiveIntegerCounter likeCounter;

    public Comment(Long id, Post post, User author, CommentContent content) {
        if(id == null){
            throw new IllegalStateException();
        }
        if(post == null){
            throw new IllegalStateException();
        }
        if(content == null){
            throw new IllegalStateException();
        }
        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCounter = new PositiveIntegerCounter();
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

    public void updateComment(User user, String updateContent){
        if(!user.equals(author)){
            throw new IllegalStateException();
        }
        content.updateContent(updateContent);
    }
}
