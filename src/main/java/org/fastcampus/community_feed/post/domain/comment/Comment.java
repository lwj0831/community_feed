package org.fastcampus.community_feed.post.domain.comment;

import lombok.Builder;
import org.fastcampus.community_feed.common.domain.PositiveIntegerCounter;
import org.fastcampus.community_feed.post.domain.Post;
import org.fastcampus.community_feed.post.domain.content.CommentContent;
import org.fastcampus.community_feed.post.domain.content.Content;
import org.fastcampus.community_feed.user.domain.User;

@Builder
public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final CommentContent content;
    private final PositiveIntegerCounter likeCounter;

    public Comment(Long id, Post post, User author, CommentContent content, PositiveIntegerCounter positiveIntegerCounter) {
        if (post == null) {
            throw new IllegalArgumentException("post should not be null");
        }
        if (author == null) {
            throw new IllegalArgumentException("author should not be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("content should not be null or empty");
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCounter = positiveIntegerCounter;
    }

    public Comment(Long id, Post post, User author, CommentContent content) {
        this(id, post, author, content, new PositiveIntegerCounter());
    }

    public Comment(Long id, Post post, User author, String content) {
        this(id, post, author, new CommentContent(content), new PositiveIntegerCounter());
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

    public void updateContent(User user, String updateContent){
        if(!user.equals(author)){
            throw new IllegalArgumentException();
        }
        content.updateContent(updateContent);
    }

    public int getLikeCount(){
        return likeCounter.getCount();
    }

    public Content getContent(){
        return content;
    }
    public String getContentText(){
        return content.getContentText();
    }

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public User getAuthor() {
        return author;
    }
}
