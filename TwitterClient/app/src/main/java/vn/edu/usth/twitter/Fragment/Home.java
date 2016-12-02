package vn.edu.usth.twitter.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import vn.edu.usth.twitter.R;

public class Home extends ListFragment {
    public Home() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterSession session = Twitter.getSessionManager().getActiveSession();

        UserTimeline userTimeline = new UserTimeline.Builder().userId(session.getUserId()).build();

//        final SearchTimeline searchTimeline = new SearchTimeline.Builder()
//                .query("#twitterflock")
//                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity())
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView view = (ImageView) rootView.findViewById(R.id.tweet);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TwitterSession session = Twitter.getSessionManager().getActiveSession();
                final Intent intent = new ComposerActivity.Builder(getActivity())
                        .session(session)
                        .createIntent();
                startActivity(intent);
            }
        });
        return rootView;
    }
}
