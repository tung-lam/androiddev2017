package vn.edu.usth.twitter.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.sdk.android.Twitter;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.FixedTweetTimeline;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.Timeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TwitterListTimeline;
import com.twitter.sdk.android.tweetui.UserTimeline;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.twitter.R;

public class Notification extends ListFragment {
    public Notification() {
        super();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterSession session = Twitter.getSessionManager().getActiveSession();

        final List<Tweet> tweets = new ArrayList<>();
//        android.util.Log.d("Number of tweets: ", String.valueOf(tweets.size()));
        TwitterCore.getInstance().getApiClient(session).getStatusesService()
                .mentionsTimeline(null, null, null, null, null, null).enqueue(
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        for (Tweet t : result.data) {
                            tweets.add(t);
                            android.util.Log.d("twittercommunity", "tweet is " + t.text);
                        }
                        final FixedTweetTimeline fixedTweetTimeline = new FixedTweetTimeline.Builder()
                                .setTweets(result.data)
                                .build();

                        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity())
                                .setTimeline(fixedTweetTimeline)
                                .build();
                        setListAdapter(adapter);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        android.util.Log.d("twittercommunity", "exception " + exception);
                    }
                }
        );
        android.util.Log.d("Number of tweets: ", String.valueOf(tweets.size()));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }
}
