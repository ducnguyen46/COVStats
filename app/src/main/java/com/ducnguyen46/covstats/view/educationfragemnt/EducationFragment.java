package com.ducnguyen46.covstats.view.educationfragemnt;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ducnguyen46.covstats.MainActivity;
import com.ducnguyen46.covstats.R;
import com.ducnguyen46.covstats.view.newsfragment.WebViewActivity;

public class EducationFragment extends Fragment {

    private Button reportBtn;

    @Override
    public void onResume() {
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Education");
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_education, container, false);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Education");

        reportBtn = view.findViewById(R.id.reportBtn);
        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", "https://tokhaiyte.vn/");
                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}