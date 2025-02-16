package org.fastcampus.community_feed.post.domain.content;

import org.fastcampus.community_feed.post.domain.common.DatetimeInfo;

public abstract class Content {
    String contentText;
    final DatetimeInfo datetimeInfo;

    public Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        datetimeInfo = new DatetimeInfo();
    }

    public void updateContent(String updateContent){
        checkText(contentText);
        this.contentText=updateContent;
        datetimeInfo.updateEditDateTime();
    }

    protected abstract void checkText(String contentText);

    public String getContentText() {
        return contentText;
    }

    public DatetimeInfo getDatetimeInfo() {
        return datetimeInfo;
    }


}
