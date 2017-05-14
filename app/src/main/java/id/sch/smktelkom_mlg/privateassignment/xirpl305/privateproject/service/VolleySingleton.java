package id.sch.smktelkom_mlg.privateassignment.xirpl305.privateproject.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import id.sch.smktelkom_mlg.privateassignment.xirpl305.privateproject.Fragment1;
import id.sch.smktelkom_mlg.privateassignment.xirpl305.privateproject.Fragment2;

/**
 * Created by ASUS TP 450 LDV on 5/13/2017.
 */
public class VolleySingleton
{
    private static volatile VolleySingleton mInstance;
    private static Fragment2 mCtx;
    private RequestQueue mRequestQueue;

    private VolleySingleton(Fragment2 context)
    {
        if (mInstance != null)
        {
            throw new RuntimeException(
                    "Use getInstance() method to get the single instance of this class");
        }
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static VolleySingleton getInstance(Fragment2 context)
    {
        if (mInstance == null)
        {
            synchronized (VolleySingleton.class)
            {
                if (mInstance == null) mInstance = new VolleySingleton(context);
            }
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue()
    {
        if (mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(mCtx.getActivity());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        getRequestQueue().add(req);
    }
}

