package aplication;

import android.app.Application;

/**
 * Created by Daniel Paguay on 05/01/2016.
 */
public class ConfigureApp extends Application
{
    public static final String TAG = ConfigureApp.class.getSimpleName();
    //private RequestQueue mRequestQueue;
    private static ConfigureApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized ConfigureApp getmInstance(){return mInstance;}

   /* public RequestQueue getmRequestQueue(){
        if(mRequestQueue ==null){
            mRequestQueue = volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }*/
}
