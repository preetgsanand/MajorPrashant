package vince.majorprashant.Utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import vince.majorprashant.Database.Batch;
import vince.majorprashant.Database.Block;
import vince.majorprashant.Database.Department;
import vince.majorprashant.Database.Job;
import vince.majorprashant.Database.Period;
import vince.majorprashant.Database.Role;
import vince.majorprashant.Database.Room;
import vince.majorprashant.Database.User;

/**
 * Created by vince on 3/17/17.
 */
public class Utils {

    public static int activity;
    public static int MainAdminActivity;

    public static long DateToLong(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        try {
            date = date.split("T")[0];
            Date d = (Date) formatter.parse(date);
            long mills = d.getTime();
            return mills;

        } catch (Exception e) {
            Log.e("Date - Utils", e.toString());
        }
        return 0;
    }

    public static String LongToDate(long mills) {
        Date date = new Date(mills);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String LongToDjangoDate(long mills) {
        Date date = new Date(mills);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static long TimeToLong(String time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));


            Date date = sdf.parse("1970-01-01 " + time);
            return date.getTime();
        } catch (Exception e) {
            Log.e("Time to Long", e.toString());
        }
        return 0;
    }

    public static JSONObject JobToUserSubmitJson(Job job) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", job.getWebid());
            jsonObject.put("name", job.getName());
            jsonObject.put("user", User.findById(User.class, 1).getWebid());
            jsonObject.put("description", job.getDescription());
            jsonObject.put("deadline", Utils.LongToDjangoDate(job.getDeadline()) + "T00:00:00Z");
            jsonObject.put("added", Utils.LongToDjangoDate(job.getAdded()) + "T00:00:00Z");
            jsonObject.put("status", job.getStatus());
            jsonObject.put("submitrequest", 1);
        } catch (Exception e) {
            Log.e("JobToJson ", e.toString());
        }
        return jsonObject;
    }

    public static JSONObject JobToAdminAcceptJson(Job job) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", job.getWebid());
            jsonObject.put("name", job.getName());
            jsonObject.put("user", User.findById(User.class, 1).getWebid());
            jsonObject.put("description", job.getDescription());
            jsonObject.put("deadline", Utils.LongToDjangoDate(job.getDeadline()) + "T00:00:00Z");
            jsonObject.put("added", Utils.LongToDjangoDate(job.getAdded()) + "T00:00:00Z");
            jsonObject.put("status", 2);
            jsonObject.put("submitrequest", 2);
        } catch (Exception e) {
            Log.e("JobToJson ", e.toString());
        }
        return jsonObject;
    }

    public static List<Job> JSONArrayToJob(JSONArray response) {
        List<Job> jobs = new ArrayList<>();
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject jsonObject = response.getJSONObject(i);
                Job job = new Job();
                job.setWebid(jsonObject.getLong("id"));
                job.setName(jsonObject.getString("name"));
                job.setAdded(Utils.DateToLong(jsonObject.getString("added")));
                job.setDeadline(Utils.DateToLong(jsonObject.getString("deadline")));
                job.setDescription(jsonObject.getString("description"));
                JSONArray user = jsonObject.getJSONArray("user");
                String userId = "";
                for (int j = 0; j < user.length(); j++) {
                    int userInt = user.getInt(j);
                    if (j == user.length() - 1) {
                        userId = userId + userInt;
                    } else {
                        userId = userId + userInt + " ";
                    }
                }
                job.setUserId(userId);
                Log.e("userid", job.getUserId());
                job.setStatus(jsonObject.getInt("status"));
                job.setUserRequired(jsonObject.getInt("userRequired"));
                job.setSubmitRequest(jsonObject.getInt("submitrequest"));
                job.setDepartment(jsonObject.getInt("department"));
                jobs.add(job);

            }
            return jobs;
        } catch (Exception e) {
            Log.e("Json Parsing Job Utils", e.toString());
            return null;
        }
    }

    public static JSONObject JobToJson(Job job) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", job.getName());
            jsonObject.put("description", job.getDescription());
            jsonObject.put("deadline", Utils.LongToDjangoDate(job.getDeadline()) + "T00:00:00Z");
            if (job.getUserRequired() != 0) {
                jsonObject.put("userRequired", job.getUserRequired());
            }
            jsonObject.put("added", Utils.LongToDjangoDate(job.getAdded()) + "T00:00:00Z");
            jsonObject.put("status", job.getStatus());
            jsonObject.put("submitrequest", job.getSubmitRequest());
            jsonObject.put("department", job.getDepartment());
        } catch (Exception e) {
            Log.e("JobToJson ", e.toString());
        }
        return jsonObject;
    }

    public static Job JsonToJob(JSONObject jsonObject) {
        try {
            Job job = new Job();
            job.setName(jsonObject.getString("name"));
            job.setDeadline(Utils.DateToLong(jsonObject.getString("deadline")));
            job.setDescription(jsonObject.getString("description"));
            JSONArray user = jsonObject.getJSONArray("user");
            String userId = "";
            for (int j = 0; j < user.length(); j++) {
                int userInt = user.getInt(j);
                if (j == user.length() - 1) {
                    userId = userId + userInt;
                } else {
                    userId = userId + userInt + " ";
                }
            }
            job.setUserId(userId);
            job.setUserRequired(jsonObject.getInt("userRequired"));
            Log.e("userid", job.getUserId() + "");
            job.setStatus(jsonObject.getInt("status"));
            job.setSubmitRequest(jsonObject.getInt("submitrequest"));
            job.setDepartment(jsonObject.getInt("department"));
            return job;
        } catch (Exception e) {
            Log.e("Json Parsing Job Utils", e.toString());
            return null;
        }
    }

    public static User JsonToUser(JSONObject userObject) {
        try {
            User user = new User();
            user.setWebid(userObject.getLong("id"));
            user.setName(userObject.getString("name"));
            user.setEmail(userObject.getString("email"));
            user.setPhone(userObject.getString("phone"));
            user.setDob(Utils.DateToLong(userObject.getString("dob")));
            user.setDepartment(userObject.getLong("department"));
            JSONArray roles = userObject.getJSONArray("role");
            String role = "";
            for (int i = 0; i < roles.length(); i++) {
                if (i == roles.length() - 1) {
                    role = role + roles.get(i).toString();
                } else {
                    role = role + roles.get(i).toString() + " ";
                }
            }
            Log.e("Roles", role);
            user.setRole(role);
            return user;
        } catch (Exception e) {
            Log.e("Json Parsing Utils User", e.toString());
            return null;
        }
    }

    public static List<User> JSONArrayTOUser(JSONArray response) {
        List<User> users = new ArrayList<>();
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject userObject = response.getJSONObject(i);
                User user = new User();
                user.setWebid(userObject.getLong("id"));
                user.setName(userObject.getString("name"));
                user.setEmail(userObject.getString("email"));
                user.setPhone(userObject.getString("phone"));
                user.setDob(Utils.DateToLong(userObject.getString("dob")));
                user.setDepartment(userObject.getLong("department"));
                JSONArray roles = userObject.getJSONArray("role");
                String role = "";
                for (int j = 0; j < roles.length(); j++) {
                    if (j == roles.length() - 1) {
                        role = role + roles.get(j).toString();
                    } else {
                        role = role + roles.get(j).toString() + " ";
                    }
                }
                Log.e("Roles", role);
                user.setRole(role);
                users.add(user);
            }

            return users;
        } catch (Exception e) {
            Log.e("Json Array to User", e.toString());
            return null;
        }
    }

    public static JSONObject UserToJson(User user) {
        JSONObject userObject = new JSONObject();
        try {
            userObject.put("name", user.getName());
            userObject.put("phone", user.getPhone());
            userObject.put("email", user.getEmail());
            userObject.put("dob", Utils.LongToDjangoDate(user.getDob()) + "T00:00:00Z");
            userObject.put("department", user.getDepartment());
            return userObject;
        } catch (Exception e) {
            Log.e("USer to JSON ", e.toString());
            return null;
        }
    }

    public static int initialSync(JSONArray response) {
        try {
            List<Batch> batches = JsonArrayToBatch(response.getJSONArray(6));
            List<Block> blocks = JsonArrayToBlock(response.getJSONArray(5));
            List<Period> periods = JsonArrayToPeriod(response.getJSONArray(2));
            List<Role> roles = JsonArrayToRole(response.getJSONArray(1));
            List<Room> rooms = JsonArrayToRoom(response.getJSONArray(4));
            List<User> users = JSONArrayTOUser(response.getJSONArray(0));
            List<Department> departments = JsonArrayToDepartment(response.getJSONArray(3));

            if(batches != null && blocks != null && periods != null &&
                    roles != null && rooms != null && users != null && departments != null) {
                Batch.saveInTx(batches);
                Block.saveInTx(blocks);
                Period.saveInTx(periods);
                Role.saveInTx(roles);
                Room.saveInTx(rooms);
                User.saveInTx(users);
                Department.saveInTx(departments);
            }
            else {
                return 0;
            }
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public static List<Batch> JsonArrayToBatch(JSONArray response) {
        try {
            List<Batch> batches = new ArrayList<>();
            for (int i = 0; i < response.length(); i++) {
                Batch batch = new Batch();
                JSONObject batchObj = response.getJSONObject(i);
                batch.setWebid(batchObj.getLong("id"));
                batch.setDepartment(batchObj.getLong("department"));
                batch.setYear(batchObj.getInt("year"));
                batch.setShift(batchObj.getInt("shift"));
                batches.add(batch);
            }
            return batches;
        } catch (Exception e) {
            Log.e("Json to Batch", e.toString());
            return null;
        }
    }


    public static List<Block> JsonArrayToBlock(JSONArray response) {
        try {
            List<Block> blocks = new ArrayList<>();
            for (int i = 0; i < response.length(); i++) {
                Block block = new Block();
                JSONObject blockObj = response.getJSONObject(i);
                block.setWebid(blockObj.getLong("id"));
                block.setName(blockObj.getString("name"));
                blocks.add(block);
            }
            return blocks;
        } catch (Exception e) {
            Log.e("Json to Block", e.toString());
            return null;
        }
    }


    public static List<Period> JsonArrayToPeriod(JSONArray response) {
        try {
            List<Period> periods = new ArrayList<>();
            for (int i = 0; i < response.length(); i++) {
                Period period = new Period();
                JSONObject periodObj = response.getJSONObject(i);

                period.setWebid(periodObj.getLong("id"));
                period.setEntity(periodObj.getLong("entity"));
                period.setRoom(periodObj.getLong("room"));
                period.setBatch(periodObj.getLong("batch"));
                period.setCategory(periodObj.getInt("category"));
                period.setWeekday(periodObj.getInt("weekday"));
                period.setStart(TimeToLong(periodObj.getString("start")));
                period.setEnd(TimeToLong(periodObj.getString("end")));
                periods.add(period);
            }
            return periods;
        } catch (Exception e) {
            Log.e("Json to Period", e.toString());
            return null;
        }
    }

    public static List<Role> JsonArrayToRole(JSONArray response) {
        try {
            List<Role> roles = new ArrayList<>();
            for (int i = 0; i < response.length(); i++) {
                Role role = new Role();
                JSONObject roleObj = response.getJSONObject(i);

                role.setWebid(roleObj.getLong("id"));
                role.setName(roleObj.getString("name"));
                roles.add(role);
            }
            return roles;
        } catch (Exception e) {
            Log.e("Json to Role", e.toString());
            return null;
        }
    }


    public static List<Room> JsonArrayToRoom(JSONArray response) {
        try {
            List<Room> rooms = new ArrayList<>();
            for (int i = 0; i < response.length(); i++) {
                Room room = new Room();
                JSONObject roomObj = response.getJSONObject(i);

                room.setWebid(roomObj.getLong("id"));
                room.setName(roomObj.getString("name"));
                room.setCategory(roomObj.getInt("category"));
                room.setBlock(roomObj.getLong("block"));
                room.setFloor(roomObj.getInt("floor"));
                rooms.add(room);
            }
            return rooms;
        } catch (Exception e) {
            Log.e("Json to Role", e.toString());
            return null;
        }
    }

    public static List<Department> JsonArrayToDepartment(JSONArray response) {
        try {
            List<Department> departments = new ArrayList<>();
            for (int i = 0; i < response.length(); i++) {
                Department department = new Department();
                JSONObject departmentObj = response.getJSONObject(i);
                department.setWebid(departmentObj.getLong("id"));
                department.setName(departmentObj.getString("name"));
                department.setInfo(departmentObj.getString("info"));
                departments.add(department);
            }
            return departments;
        } catch (Exception e) {
            Log.e("Json to Department", e.toString());
            return null;
        }
    }
}
