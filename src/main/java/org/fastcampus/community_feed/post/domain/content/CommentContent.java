package org.fastcampus.community_feed.post.domain.content;

public class CommentContent extends Content {

    private static int MAX_COMMENT_LENGTH = 100;

    public CommentContent(String contentText) {
        super(contentText);
    }

    @Override
    protected void checkText(String contentText) {
        if(contentText==null || contentText.isEmpty()){
            throw new IllegalStateException();
        }
        if(contentText.length()>MAX_COMMENT_LENGTH){
            throw new IllegalStateException();
        }

    }
}
