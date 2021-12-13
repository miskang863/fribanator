package com.example.fribanator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ThirdFragment extends Fragment {
    private ScoreViewModel model;
    RecyclerView recyclerView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ThirdFragment() {
    }

    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        model = new ViewModelProvider(requireActivity()).get(ScoreViewModel.class);

        recyclerView = view.findViewById(R.id.scorerecycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

        ScoreRecyclerAdapter adapter = new ScoreRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        ScoreViewModel scoreViewModel = new ViewModelProvider(this).get(ScoreViewModel.class);
        scoreViewModel.getAllScores().observe(getViewLifecycleOwner(), scores -> {
            adapter.setScores(scores);
        });

        adapter.setOnItemClickListener(score -> {
            model.select(score);
            Navigation.findNavController(view).navigate(R.id.myAction2);
        });

        return view;
    }
}