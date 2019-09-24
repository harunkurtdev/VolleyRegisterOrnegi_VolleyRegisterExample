package com.serifgungor.volleyloginregister.Model;

public class UserShare {
    private int id;
    private int user_id;
    private String share_time;
    private String share_content;

    @Override
    public String toString() {
        return share_content;
    }

    public UserShare() {
    }

    public UserShare(int id, int user_id, String share_time, String share_content) {
        this.id = id;
        this.user_id = user_id;
        this.share_time = share_time;
        this.share_content = share_content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getShare_time() {
        return share_time;
    }

    public void setShare_time(String share_time) {
        this.share_time = share_time;
    }

    public String getShare_content() {
        return share_content;
    }

    public void setShare_content(String share_content) {
        this.share_content = share_content;
    }
}
