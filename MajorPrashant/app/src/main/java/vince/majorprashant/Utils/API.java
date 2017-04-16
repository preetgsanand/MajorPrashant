package vince.majorprashant.Utils;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vince.majorprashant.Database.Job;
import vince.majorprashant.Database.User;
import vince.majorprashant.InitializationActivity;
import vince.majorprashant.MainActivity;
import vince.majorprashant.R;


/**
 * Created by vince on 3/25/17.
 */
public class API {

    private Context context;
    private int code;
    private String phoneNumber;
    private Handler handler;
    private User user;
    private Job job;
    public API(int code,Context context) {
        this.code = code;
        this.context = context;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void callAPI() {
        switch (code) {
            case 0://userget();
                break;
            case 1:userput();
                break;
            case 2://userdelete();
                break;
            case 3://jobget();
                break;
            case 4:jobput();
                break;
            case 5://jobdelete();
                break;
            case 6://userlist();
                break;
            case 7://joblist();
                break;
            case 8://usersearch();
                break;
            case 9:jobsearch();
                break;
            case 10:useradd();
                break;
            case 11:jobadd();
                break;
            case 12:usercheck();
                break;
            case 13:refreshJobUser();
                break;
        }
    }

    //----------------------------------------User Put - 1------------------------------------------
    private void userput() {
        handler = new Handler();
        final JSONObject jobObject = Utils.UserToJson(user);
        if(jobObject == null) {
            postResult(-1);
            return;
        }
        Log.e("Job Json",jobObject.toString());
        new Thread() {
            public void run() {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT,
                        context.getResources().getString(R.string.user_put)+user.getWebid()+"/",
                        jobObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(final JSONObject response) {

                                try {
                                    if (response == null) {
                                        handler.post(new Runnable() {
                                            public void run() {
                                                postResult(-1);
                                            }
                                        });
                                    } else {
                                        handler.post(new Runnable() {
                                            public void run() {
                                                try {
                                                    postResult(1);


                                                } catch (Exception e) {

                                                }
                                            }
                                        });
                                    }
                                } catch (Exception e) {
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error Json : ",error.toString());
                                postResult(-1);
                            }
                        }
                ) {

                    /**
                     * Passing some request headers
                     */
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };
                Volley.newRequestQueue(context).add(jsonObjectRequest);
            }
        }.start();
    }

    //----------------------------------------Job Put - 4------------------------------------------
    private void jobput() {
        handler = new Handler();
        final JSONObject jobObject = Utils.JobToJson(job);
        if(jobObject == null) {
            postResult(-1);
            return;
        }
        Log.e("Job Json",jobObject.toString());
        new Thread() {
            public void run() {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT,
                        context.getResources().getString(R.string.job_put)+job.getWebid()+"/",
                        jobObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(final JSONObject response) {

                                try {
                                    if (response == null) {
                                        handler.post(new Runnable() {
                                            public void run() {
                                                postResult(-1);
                                            }
                                        });
                                    } else {
                                        handler.post(new Runnable() {
                                            public void run() {
                                                try {
                                                    postResult(1);


                                                } catch (Exception e) {

                                                }
                                            }
                                        });
                                    }
                                } catch (Exception e) {
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error Json : ",error.toString());
                                postResult(-1);
                            }
                        }
                ) {

                    /**
                     * Passing some request headers
                     */
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };
                Volley.newRequestQueue(context).add(jsonObjectRequest);
            }
        }.start();
    }
    //----------------------------------------User Add - 10------------------------------------------


    private void useradd() {
        handler = new Handler();
        final JSONObject userObj = Utils.UserToJson(user);
        if(userObj == null) {
            postResult(-1);
            return;
        }
        Log.e("User",userObj.toString());
        new Thread() {
            public void run() {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT,
                        context.getResources().getString(R.string.user_add),
                        userObj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(final JSONObject response) {

                                try {
                                    if (response == null) {
                                        handler.post(new Runnable() {
                                            public void run() {
                                                postResult(-1);
                                            }
                                        });
                                    } else {
                                        handler.post(new Runnable() {
                                            public void run() {
                                                try {
                                                    postResult(1);


                                                } catch (Exception e) {

                                                }
                                            }
                                        });
                                    }
                                } catch (Exception e) {
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error Json : ",error.toString());
                                postResult(-1);
                            }
                        }
                ) {

                    /**
                     * Passing some request headers
                     */
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };
                Volley.newRequestQueue(context).add(jsonObjectRequest);
            }
        }.start();
    }

    //----------------------------------------Job Add - 11------------------------------------------


    private void jobadd() {
        handler = new Handler();
        final JSONObject jobObj = Utils.JobToJson(job);
        if(jobObj == null) {
            postResult(-1);
            return;
        }
        Log.e("Job Json",jobObj.toString());
        new Thread() {
            public void run() {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT,
                        context.getResources().getString(R.string.job_add),
                        jobObj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(final JSONObject response) {

                                try {
                                    if (response == null) {
                                        handler.post(new Runnable() {
                                            public void run() {
                                                postResult(-1);
                                            }
                                        });
                                    } else {
                                        handler.post(new Runnable() {
                                            public void run() {
                                                try {
                                                    job = Utils.JsonToJob(response);
                                                    if(job != null) {
                                                        job.save();
                                                        postResult(1);
                                                    }
                                                    else {
                                                        postResult(-1);
                                                    }


                                                } catch (Exception e) {

                                                }
                                            }
                                        });
                                    }
                                } catch (Exception e) {
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error Json : ",error.toString());
                                postResult(-1);
                            }
                        }
                ) {

                    /**
                     * Passing some request headers
                     */
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };
                Volley.newRequestQueue(context).add(jsonObjectRequest);
            }
        }.start();
    }

    //----------------------------------------User Check - 12------------------------------------------

    public void usercheck() {
        handler = new Handler();
        new Thread() {
            public void run() {
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                        context.getResources().getString(R.string.user_phone_api) + phoneNumber.substring(1),
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(final JSONArray response) {

                                try {
                                    if (response == null) {
                                        handler.post(new Runnable() {
                                            public void run() {
                                                postResult(-1);
                                            }
                                        });
                                    } else {
                                        handler.post(new Runnable() {
                                            public void run() {
                                                try {
                                                    Log.e("JSON : ",response.toString());
                                                    userCheckParseJSON(response);

                                                } catch (Exception e) {

                                                }
                                            }
                                        });
                                    }
                                } catch (Exception e) {
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error Json : ",error.toString());
                                postResult(-1);
                            }
                        }
                );
                Volley.newRequestQueue(context).add(jsonArrayRequest);
            }
        }.start();
    }

    private void userCheckParseJSON(JSONArray response) {
        try {
            JSONArray userArray = response.getJSONArray(0);
            if(userArray.length() == 0) {
                postResult(0);
            }
            else {
                if(Utils.initialSync(response) == 1) {
                    postResult(1);
                }
                else {
                    postResult(-1);
                }
            }

        }
        catch (Exception e) {
            postResult(-1);
            Log.e("User Check Json",e.toString());
        }
    }

    //----------------------------------------Job Search -9 ------------------------------------------


    private void jobsearch() {
        handler = new Handler();
        new Thread() {
            public void run() {
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                        context.getResources().getString(R.string.job_search) + phoneNumber.substring(1),
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(final JSONArray response) {

                                try {
                                    if (response == null) {
                                        handler.post(new Runnable() {
                                            public void run() {
                                                postResult(-1);
                                            }
                                        });
                                    } else {
                                        handler.post(new Runnable() {
                                            public void run() {
                                                try {
                                                    Log.e("JSON : ",response.toString());
                                                    jobSearchParseJson(response);

                                                } catch (Exception e) {

                                                }
                                            }
                                        });
                                    }
                                } catch (Exception e) {
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error Json : ",error.toString());
                                postResult(-1);
                            }
                        }
                );
                Volley.newRequestQueue(context).add(jsonArrayRequest);
            }
        }.start();
    }

    private void jobSearchParseJson(JSONArray response) {
        List<Job> jobs;
        if(response.length() == 0) {
            postResult(0);
        }
        else {
            jobs = Utils.JSONArrayToJob(response);
            if (jobs != null) {
                Job.deleteAll(Job.class);
                Job.saveInTx(jobs);
                postResult(1);
            }
            else {
                postResult(-1);
            }
        }
    }
    //----------------------------------------Refresh Jobs Users - 13------------------------------------------

    public void refreshJobUser() {
        final Handler handler = new Handler();

        new Thread() {
            public void run() {
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                        context.getResources().getString(R.string.job_list),
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(final JSONArray response) {

                                try {
                                    if (response == null) {
                                        handler.post(new Runnable() {
                                            public void run() {

                                                postResult(-1);

                                            }
                                        });
                                    } else {
                                        handler.post(new Runnable() {
                                            public void run() {
                                                try {
                                                    Log.e("JSON : ",response.toString());
                                                    parseJobListJSON(response);

                                                } catch (Exception e) {

                                                }
                                            }
                                        });
                                    }
                                } catch (Exception e) {
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error Json : ",error.toString());
                                postResult(-1);

                            }
                        }
                );
                Volley.newRequestQueue(context).add(jsonArrayRequest);
            }
        }.start();
    }


    private void parseJobListJSON(JSONArray response) {
        List<Job> jobs = Utils.JSONArrayToJob(response);
        if (jobs != null) {
            Job.deleteAll(Job.class);
            Job.saveInTx(jobs);
            refreshUserList();
        }
        else {
            postResult(-1);
        }
    }

    public void refreshUserList() {
        final Handler handlerUser = new Handler();

        new Thread() {
            public void run() {
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                        context.getResources().getString(R.string.user_list),
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(final JSONArray response) {

                                try {
                                    if (response == null) {
                                        handlerUser.post(new Runnable() {
                                            public void run() {
                                                postResult(-1);

                                            }
                                        });
                                    } else {
                                        handlerUser.post(new Runnable() {
                                            public void run() {
                                                try {
                                                    Log.e("JSON : ",response.toString());
                                                    parseUserListJSON(response);

                                                } catch (Exception e) {

                                                }
                                            }
                                        });
                                    }
                                } catch (Exception e) {
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error Json : ",error.toString());
                                postResult(-1);
                            }
                        }
                );
                Volley.newRequestQueue(context).add(jsonArrayRequest);
            }
        }.start();
    }

    private void parseUserListJSON(JSONArray response) {
        List<User> users = Utils.JSONArrayTOUser(response);
        if (users != null) {
            List<User> usersDelete = User.find(User.class,"id!=?",1+"");
            User.deleteInTx(usersDelete);
            User.saveInTx(users);
            postResult(1);
        }
        else {
            postResult(-1);
        }
    }


    private void postResult(int result) {
        switch (Utils.activity) {
            /*case 0:((MainActivity) context).apiResult(code,result);
                break;*/
            case 2:
                ((InitializationActivity) context).apiResult(code,result);
                break;
        }
    }
}
