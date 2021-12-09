package com.example.fribanator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FifthFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FifthFragment() {
    }

    public static FifthFragment newInstance(String param1, String param2) {
        FifthFragment fragment = new FifthFragment();
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
        View view = inflater.inflate(R.layout.fragment_fifth, container, false);

        TextView scoreTxt = view.findViewById(R.id.historyScores);
        TextView dateTxt = view.findViewById(R.id.historyDate);
        TextView nameTxt = view.findViewById(R.id.historyName);
        TextView sumTxt = view.findViewById(R.id.scoreSumTxt);

        ScoreViewModel theScoreModel = new ViewModelProvider(getActivity()).get(ScoreViewModel.class);

        theScoreModel.getSelected().observe(getViewLifecycleOwner(), score -> {
            String scoreString = "";
            int sum = 0;

            for (int i = 0; i < score.getScoreList().size(); i++) {
                int vayla = i + 1;

                String tarpeetonString = String.valueOf(score.getScoreList().get(i));
                Integer tarpeetonInteger = Integer.parseInt(tarpeetonString);
                sum += tarpeetonInteger;

                if (tarpeetonInteger > 0) {
                    scoreString += "\nVäylä " + vayla + " tulos: +" + tarpeetonString;
                } else if (tarpeetonInteger == 0) {
                    scoreString += "\nVäylä " + vayla + " tulos: PAR";
                } else {
                    scoreString += "\nVäylä " + vayla + " tulos: " + tarpeetonString;
                }
            }

            if (sum > 0) {
                sumTxt.setText("Kokonaistulos: +" + sum);
            } else if (sum == 0) {
                sumTxt.setText("Kokonaistulos: PAR");
            } else {
                sumTxt.setText("Kokonaistulos: " + sum);
            }

            sumTxt.setText("Kokonaistulos: " + sum);
            nameTxt.setText(score.getRataName());
            dateTxt.setText(score.getDate());
            scoreTxt.setText(scoreString);

        });

        return view;
    }
}