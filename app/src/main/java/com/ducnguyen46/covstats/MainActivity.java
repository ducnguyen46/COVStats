package com.ducnguyen46.covstats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.ducnguyen46.covstats.service.CovidDataTask;
import com.ducnguyen46.covstats.view.BottomNavigtionMainAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.ducnguyen46.covstats.constant.Constant.URL_LINK;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNav;
    private BottomNavigtionMainAdapter bottomNavAdapter;

    private CovidDataTask covidDataTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertDataToDatabase();

        bottomNav = findViewById(R.id.bottomNavMain);
        viewPager = findViewById(R.id.viewPagerMain);
        bottomNavAdapter = new BottomNavigtionMainAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(bottomNavAdapter);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.country:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.education:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.news:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.chart:
                        viewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                bottomNav.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });

    }

    private void insertDataToDatabase(){
        covidDataTask = new CovidDataTask(MainActivity.this);
        covidDataTask.execute(URL_LINK);
    }
}