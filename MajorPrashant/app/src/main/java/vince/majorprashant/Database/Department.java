package vince.majorprashant.Database;

import com.orm.SugarRecord;

/**
 * Created by vince on 4/16/17.
 */
public class Department extends SugarRecord {


    private long webid;
    private String name;
    private String info;
    public Department() {

    }

    public long getWebid() {
        return webid;
    }

    public void setWebid(long webid) {
        this.webid = webid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
