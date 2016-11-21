package vn.edu.usth.twitter;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;

import vn.edu.usth.twitter.Fragment.Home;
import vn.edu.usth.twitter.Fragment.Message;
import vn.edu.usth.twitter.Fragment.Notification;

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

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private int[] imageResId = {
            R.drawable.home,
            R.drawable.retweet,
            R.drawable.message
    };

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
//            return new Home();
// returns an instance of vn.edu.usth.weather.Fragment corresponding to the specified page
        switch (page) {
            case 0: return new Home();
            case 1: return new Notification();
            case 2: return new Message();
            default: return new Home();
        }
//        return new EmptyFragment(); // failsafe
        }
        @Override
        public CharSequence getPageTitle(int position) {
// returns a tab title corresponding to the specified page

            Drawable image = ContextCompat.getDrawable(getApplicationContext(), imageResId[position]);
            image.setBounds(0, 0, image.getIntrinsicWidth()-32, image.getIntrinsicHeight()-32);

            SpannableString sb = new SpannableString(" ");
            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sb;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_twitter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // do something when search is pressed here
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
