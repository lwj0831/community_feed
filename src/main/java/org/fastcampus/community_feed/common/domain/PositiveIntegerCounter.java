package org.fastcampus.community_feed.common.domain;

public class PositiveIntegerCounter {

    private int count;

    public PositiveIntegerCounter(int count) {
        this.count = count;
    }

    public PositiveIntegerCounter() {
        this(0);
    }

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
