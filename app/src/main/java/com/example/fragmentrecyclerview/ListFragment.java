package com.example.fragmentrecyclerview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListFragment extends Fragment {
    final String URL = "https://jsonplaceholder.typicode.com/";
    final String TAG = "debug";
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: fragment");
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.rvList);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ListApi listApi = retrofit.create(ListApi.class);

        Call<List<ListModel>> call = listApi.getList();
        call.enqueue(new Callback<List<ListModel>>() {
            @Override
            public void onResponse(Call<List<ListModel>> call, Response<List<ListModel>> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: failure");
                    Toast.makeText(getContext(), "Enter valid City", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d(TAG, "onResponse: success");
                List<ListModel> list = response.body();
                ListAdapter listAdapter = new ListAdapter(getContext(), list);
                recyclerView.setAdapter(listAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<ListModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                Toast.makeText(getContext(), "failed inside failure", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}