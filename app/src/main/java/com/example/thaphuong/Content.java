package com.example.thaphuong;

public class Content {
    private String content;
    private String uneversity;
    private String date;
    private String time;

    public Content() {
    }

    public Content(String content, String uneversity, String date, String time ) {
        this.content = content;
        this.uneversity = uneversity;
        this.date = date;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUneversity() {
        return uneversity;
    }

    public void setUneversity(String uneversity) {
        this.uneversity = uneversity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
