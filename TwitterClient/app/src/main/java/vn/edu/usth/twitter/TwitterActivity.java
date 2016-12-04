package vn.edu.usth.twitter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import vn.edu.usth.twitter.Fragment.Home;
import vn.edu.usth.twitter.Fragment.Message;
import vn.edu.usth.twitter.Fragment.Notification;

public class TwitterActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);

        PagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        final ViewPager pager = (ViewPager) findViewById(R.id.pager);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);

        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);

        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        myToolbar.setTitle("Home");
                        break;
                    case 1:
                        myToolbar.setTitle("Notification");
                        break;
                    case 2:
                        myToolbar.setTitle("Message");
                        break;
                    default:
                        myToolbar.setTitle("Twitter Client");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        final TwitterSession session = Twitter.getSessionManager().getActiveSession();

//        ImageView view = (ImageView) findViewById(R.id.tweet);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final Intent intent = new ComposerActivity.Builder(TwitterActivity.this)
//                        .session(session)
//                        .createIntent();
//                startActivity(intent);
//            }
//        });
//        ImageView view = (ImageView) findViewById(R.id.tweet);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent nextActivity = new Intent(TwitterActivity.this, LoginActivity.class);
//                startActivity(nextActivity);
//            }
//        });

        //Navigation Drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private int[] imageResId = new int[]{
            R.drawable.home_1,
            R.drawable.notification,
            R.drawable.message_1
    };



    public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
        private final int PAGE_COUNT = 3;
        private String titles[] = new String[]{"Home", "Notification", "Message"};

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
                case 0:
                    return new Home();
                case 1:
                    return new Notification();
                case 2:
                    return new Message();
                default:
                    return new Home();
            }
//        return new EmptyFragment(); // failsafe
        }

        @Override
        public CharSequence getPageTitle(int position) {
// returns a tab title corresponding to the specified page

            Drawable image = ContextCompat.getDrawable(getApplicationContext(), imageResId[position]);
            image.setBounds(0, 0, image.getIntrinsicWidth() - 90, image.getIntrinsicHeight() - 90);

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
        //Toggle to start Navigation Bar
        if(mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_refresh:
                final TwitterSession session = TwitterCore.getInstance().getSessionManager()
                        .getActiveSession();
                Intent intent = new Intent(this, TwitterActivity.class);
                startActivity(intent);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
