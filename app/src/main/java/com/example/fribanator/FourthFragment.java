package com.example.fribanator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class FourthFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FourthFragment() {
        // Required empty public constructor
    }

    public static FourthFragment newInstance(String param1, String param2) {
        FourthFragment fragment = new FourthFragment();
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


        View view = inflater.inflate(R.layout.fragment_fourth, container, false);

        TextView rataName = view.findViewById(R.id.rataNameTxt);
        TextView fairwayInfo = view.findViewById(R.id.fairwayInfoTxt);
        TextView scoreTxt = view.findViewById(R.id.scoreTxt);

        Button minusBtn = view.findViewById(R.id.minusBtn);
        Button plusBtn = view.findViewById(R.id.plusBtn);
        Button saveBtn = view.findViewById(R.id.saveBtn);

        RataViewModel model = new ViewModelProvider(requireActivity()).get(RataViewModel.class);
        ScoreViewModel scoreViewModel = new ViewModelProvider(this).get(ScoreViewModel.class);

        model.getSelected().observe(getViewLifecycleOwner(), rata -> {
            rataName.setText(rata.getName());
            fairwayInfo.setText("Väylä: " + (scoreViewModel.currentFairway + 1) + "\nPar: " + rata.getParList().get(scoreViewModel.currentFairway));
        });

        minusBtn.setOnClickListener(v -> {
            scoreViewModel.minusScoreNumber();
            scoreTxt.setText(String.valueOf(scoreViewModel.scoreNumber));
        });

        plusBtn.setOnClickListener(v -> {
            scoreViewModel.plusScoreNumber();
            scoreTxt.setText(String.valueOf(scoreViewModel.scoreNumber));
        });

        saveBtn.setOnClickListener(v -> {

            model.getSelected().observe(getViewLifecycleOwner(), rata -> {
                Log.d("demotest", "parlist size: " + rata.getParList().size());
                Log.d("demotest", "currentfairway: " + scoreViewModel.currentFairway);


                if ((scoreViewModel.currentFairway + 1) < rata.getParList().size()) {
                    Log.d("demotest", "" + rata.getParList());

                    String test = String.valueOf(rata.getParList().get(scoreViewModel.currentFairway));
                    Integer fairwayScore = scoreViewModel.scoreNumber - Integer.parseInt(test);
                    Log.d("demotest", "fairwayscore saved " + fairwayScore);
                    scoreViewModel.scoreList.add(fairwayScore);
                    scoreViewModel.plusCurrentFairway();
                    fairwayInfo.setText("Väylä " + (scoreViewModel.currentFairway + 1) + "\nPar: " + rata.getParList().get(scoreViewModel.currentFairway));

                    scoreViewModel.resetScoreNumber();
                    scoreTxt.setText(String.valueOf(scoreViewModel.scoreNumber));

                } else {
                    String test = String.valueOf(rata.getParList().get(scoreViewModel.currentFairway));
                    Integer fairwayScore = scoreViewModel.scoreNumber - Integer.parseInt(test);
                    scoreViewModel.scoreList.add(fairwayScore);

                    int sum = 0;
                    for (int i : scoreViewModel.scoreList) {
                        sum += i;
                    }

                    fairwayInfo.setText("Kierros ohi\nTulos: " + sum);

                    Toast.makeText(view.getContext(), "Kierros ohi, tuloksesi: " + sum, Toast.LENGTH_LONG).show();

                    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                    scoreViewModel.insert(new Score(rata.getName(), scoreViewModel.scoreList, date));
                    scoreViewModel.resetCurrentFairway();
                    scoreViewModel.scoreList = null;

                    plusBtn.setVisibility(View.INVISIBLE);
                    minusBtn.setVisibility(View.INVISIBLE);
                    saveBtn.setVisibility(View.INVISIBLE);

                    scoreViewModel.resetScoreNumber();
                    scoreTxt.setText(String.valueOf(sum));
                }
            });
        });

        return view;
    }
}