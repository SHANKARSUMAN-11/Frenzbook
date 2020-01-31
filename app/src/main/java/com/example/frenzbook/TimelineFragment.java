package com.example.frenzbook;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TimelineFragment extends Fragment {

    RecyclerView timeline;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_my_profile,container,false);
        timeline = view.findViewById(R.id.recycler);

        Api api = App.getRetrofit().create(Api.class);


        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
