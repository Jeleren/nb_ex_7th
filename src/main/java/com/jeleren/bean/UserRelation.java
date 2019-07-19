package com.jeleren.bean;

public class UserRelation {
    private int follow_id;
    private int fan_id;

    public UserRelation() {
    }

    public UserRelation(int follow_id, int fan_id) {
        this.follow_id = follow_id;
        this.fan_id = fan_id;
    }

    public int getFollow_id() {
        return follow_id;
    }

    public void setFollow_id(int follow_id) {
        this.follow_id = follow_id;
    }

    public int getFan_id() {
        return fan_id;
    }

    public void setFan_id(int fan_id) {
        this.fan_id = fan_id;
    }

    @Override
    public String toString() {
        return "UserRelation{" +
                "follow_id=" + follow_id +
                ", fan_id=" + fan_id +
                '}';
    }
}
