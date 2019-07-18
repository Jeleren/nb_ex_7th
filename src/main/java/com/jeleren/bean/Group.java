package com.jeleren.bean;

import java.util.List;

public class Group {
    private String name;
    private int level;
    private int id;
    private int parent_id;
    private List<Group> kids;

    public Group() {
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", id=" + id +
                ", parent_id=" + parent_id +
                ", kids=" + kids +
                '}';
    }

    public List<Group> getKids() {
        return kids;
    }

    public void setKids(List<Group> kids) {
        this.kids = kids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }
}
