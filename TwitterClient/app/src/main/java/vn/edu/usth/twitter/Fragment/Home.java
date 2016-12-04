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
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;
import com.twitter.sdk.android.tweetui.FixedTweetTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.twitter.R;

public class Home extends ListFragment {
    public Home() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterSession session = Twitter.getSessionManager().getActiveSession();

//        UserTimeline userTimeline = new UserTimeline.Builder().userId(session.getUserId()).build();
//
//        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity())
//                .setTimeline(userTimeline)
//                .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
//                .build();
//        setListAdapter(adapter);

        final List<Tweet> tweets = new ArrayList<>();
        Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                for (Tweet t : result.data) {
                    tweets.add(t);
                    android.util.Log.d("twittercommunity", "tweet is " + t.text);
                }
                final FixedTweetTimeline fixedTweetTimeline = new FixedTweetTimeline.Builder()
                        .setTweets(tweets)
                        .build();
                final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity())
                        .setTimeline(fixedTweetTimeline)
                        .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                        .build();
                setListAdapter(adapter);
            }

            @Override
            public void failure(TwitterException exception) {
                android.util.Log.d("twittercommunity", "exception " + exception);
            }
        };

        TwitterCore.getInstance().getApiClient(session).getStatusesService()
                .homeTimeline(null, null, null, null, null, null, null)
                .enqueue(callback);
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
