package org.fastcampus.community_feed.common.domain;

public class PositiveIntegerCounter {

    private int count;

    public void increase(){
        count++;
    }

    public void decrease(){
        if(count<=0){
            return;
        }
        count--;
    }

    public int getCount() {
        return count;
    }
}
