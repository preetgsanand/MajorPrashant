package vince.majorprashant.Database;

import com.orm.SugarRecord;

/**
 * Created by vince on 4/15/17.
 */
public class Block extends SugarRecord {


    private long webid;
    private String name;
    public Block() {

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
}
