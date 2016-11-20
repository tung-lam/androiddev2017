package vn.edu.usth.twitter;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.edu.usth.twitter.Fragment.Home;

public class TwitterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);

        PagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);

        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
    }

    public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
        private final int PAGE_COUNT = 3;
        private String titles[] = new String[] { "Home", "Notification", "Message" };
        public HomeFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public int getCount() {
            return PAGE_COUNT; // number of pages for a ViewPager
        }
        @Override
        public Fragment getItem(int page) {
            return new Home();
// returns an instance of vn.edu.usth.weather.Fragment corresponding to the specified page
//        switch (page) {
//            case 0: return WeatherAndForecast.newInstance();
//            case 1: return WeatherAndForecast.newInstance();
//            case 2: return WeatherAndForecast.newInstance();
//        }
//        return new EmptyFragment(); // failsafe
        }
        @Override
        public CharSequence getPageTitle(int page) {
// returns a tab title corresponding to the specified page
            return titles[page];
        }
    }
}
