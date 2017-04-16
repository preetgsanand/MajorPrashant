package vince.majorprashant.Database;

import com.orm.SugarContext;
import com.orm.SugarRecord;

/**
 * Created by vince on 4/15/17.
 */
public class Batch extends SugarRecord{

    private long webid;
    private long department;
    private int year;
    private int shift;
    public Batch() {

    }

    public long getWebid() {
        return webid;
    }

    public void setWebid(long webid) {
        this.webid = webid;
    }

    public long getDepartment() {
        return department;
    }

    public void setDepartment(long department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }
}
