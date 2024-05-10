package com.example.thaphuong.Class;

public class Content {
    private String content;
    private String university;
    private String name;
    private String key;

    public Content() {
    }

    public Content(String content, String university, String name, String key) {
        this.content = content;
        this.university = university;
        this.name = name;
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
