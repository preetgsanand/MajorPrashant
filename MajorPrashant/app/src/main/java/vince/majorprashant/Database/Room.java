package vince.majorprashant.Database;

import com.orm.SugarRecord;

/**
 * Created by vince on 4/15/17.
 */
public class Room extends SugarRecord {


    private long webid;
    private String name;
    private long block;
    private int floor;
    private int category;
    private long assisstant;
    public Room() {

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

    public long getBlock() {
        return block;
    }

    public void setBlock(long block) {
        this.block = block;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public long getAssisstant() {
        return assisstant;
    }

    public void setAssisstant(long assisstant) {
        this.assisstant = assisstant;
    }
}
