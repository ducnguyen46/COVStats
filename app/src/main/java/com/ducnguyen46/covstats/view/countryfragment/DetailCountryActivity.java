package com.ducnguyen46.covstats.view.countryfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ducnguyen46.covstats.R;
import com.ducnguyen46.covstats.models.CovidCountry;
import com.ducnguyen46.covstats.view.newsfragment.WebViewActivity;
import com.squareup.picasso.Picasso;

public class DetailCountryActivity extends AppCompatActivity {

    private TextView nameDetailTv, newCaseTv, totalCaseTv, newDeathTv, totalDeathTv, totalRecoverdTv, newRecoveredTv;
    private Button newsBtn, vaccineBtn;
    private ImageView imgFlag;
    private CovidCountry covidCountry;
    private String url_gg_news = "https://www.google.com/search?q=covid+in+";
    private String url_region = "https://www.google.com/search?q=covid+vaccine+chart+in+";

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_country);
        Intent intent = getIntent();
        covidCountry = (CovidCountry) intent.getSerializableExtra("country");
        url_gg_news += covidCountry.getCountry().toLowerCase();
        url_region += covidCountry.getCountry().toLowerCase();

        getSupportActionBar().setTitle(covidCountry.getCountry() + " - statistic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameDetailTv = findViewById(R.id.nameDetailTv);
        imgFlag = findViewById(R.id.imgFlagDetail);
        newCaseTv = findViewById(R.id.newCaseDetailTv);
        totalCaseTv = findViewById(R.id.totalCaseDetailTv);
        newDeathTv = findViewById(R.id.newDeathDetailTv);
        totalDeathTv = findViewById(R.id.totalDeathDetailTv);
        totalRecoverdTv = findViewById(R.id.totalRecoveredDetailTv);
        newRecoveredTv = findViewById(R.id.newRecoveredDetailTv);
        newsBtn = findViewById(R.id.moreNewsBtn);
        vaccineBtn = findViewById(R.id.moreInfoBtn);

        nameDetailTv.setText(covidCountry.getCountry());
        Picasso.get()
                .load("https://flagcdn.com/w160/" + covidCountry.getCountryCode().toLowerCase() +".png")
                .into(imgFlag);
        newCaseTv.setText("+" + String.format("%1$,d", covidCountry.getNewConfirmed()));
        totalCaseTv.setText(String.format("%1$,d", covidCountry.getTotalConfirmed()));
        newDeathTv.setText("+" + String.format("%1$,d", covidCountry.getNewDeath()));
        totalDeathTv.setText(String.format("%1$,d", covidCountry.getTotalDeath()));
        newRecoveredTv.setText("+" + String.format("%1$,d", covidCountry.getNewRecovered()));
        totalRecoverdTv.setText(String.format("%1$,d", covidCountry.getTotalRecovered()));

        newsBtn.setText("More news in " + covidCountry.getCountry());
        vaccineBtn.setText("Vaccine covid in " + covidCountry.getCountry());
        newsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoWebview = new Intent(DetailCountryActivity.this, WebViewActivity.class);
                gotoWebview.putExtra("url", url_gg_news);
                startActivity(gotoWebview);
            }
        });

        vaccineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoWebview = new Intent(DetailCountryActivity.this, WebViewActivity.class);
                gotoWebview.putExtra("url", url_region);
                startActivity(gotoWebview);
            }
        });
    }
}