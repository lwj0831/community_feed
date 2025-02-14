package org.fastcampus.community_feed.post.domain.content;

import org.fastcampus.community_feed.post.domain.content.Content;

public class PostContent extends Content {

    private static final int POST_MIN_LENGTH = 5;
    private static final int POST_MAX_LENGTH = 500;
    public PostContent(String content) {
        super(content);
    }

    @Override
    protected void checkText(String contentText) {
        if(contentText==null || contentText.isEmpty()){
            throw new IllegalStateException();
        }
        if(contentText.length()<POST_MIN_LENGTH){
            throw new IllegalStateException();
        }
        if(contentText.length()>POST_MAX_LENGTH){
            throw new IllegalStateException();
        }

    }


}
