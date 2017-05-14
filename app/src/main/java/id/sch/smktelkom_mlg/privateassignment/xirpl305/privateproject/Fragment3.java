package id.sch.smktelkom_mlg.privateassignment.xirpl305.privateproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl305.privateproject.adapter.UpcomingAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl305.privateproject.model.Result;
import id.sch.smktelkom_mlg.privateassignment.xirpl305.privateproject.model.ResultResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl305.privateproject.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl305.privateproject.service.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {

    ArrayList<Result> mlist = new ArrayList<>();
    RecyclerView recyclerView;
    UpcomingAdapter upcomingAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        upcomingAdapter = new UpcomingAdapter(this, mlist, getContext());
        recyclerView.setAdapter(upcomingAdapter);
        LinearLayoutManager grid = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(grid);
        downloadDataSources();
        return view;
    }

    private void downloadDataSources() {
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=465f9ccede40dfd1c392d68498768ebd&language=en-US&page=1";

        GsonGetRequest<ResultResponse> myRequest = new GsonGetRequest<ResultResponse>
                (url, ResultResponse.class, null, new Response.Listener<ResultResponse>() {

                    @Override
                    public void onResponse(ResultResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        //if (response.status.equals("ok"))
                        //{
                        //    fillColor(response.sources);
                        mlist.addAll(response.results);
                        upcomingAdapter.notifyDataSetChanged();
                        //   }
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(myRequest);

    }
}
