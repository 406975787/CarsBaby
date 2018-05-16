package app.weather.com.textndk.bean;

import com.daren.base.BaseBean;

/**
 * Created by mengxj on 2018/5/16.
 */

public class CarsDataBean extends BaseBean{

    private String url;
    private String id;
    private String title;
    private String user;
    private String picUrl;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public CarsDataBean(String url, String id, String title, String user, String picUrl) {
        this.url = url;
        this.id = id;
        this.title = title;
        this.user = user;
        this.picUrl = picUrl;
    }
}
