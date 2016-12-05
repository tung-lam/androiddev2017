package vn.edu.usth.twitter.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;

import vn.edu.usth.twitter.EmbeddedTweetActivity;
import vn.edu.usth.twitter.R;

public class Message extends Fragment {
    public Message() {
        super();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);
        ImageView view = (ImageView) rootView.findViewById(R.id.tweet);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TwitterSession session = Twitter.getSessionManager().getActiveSession();
                Intent intent = new Intent(getActivity(), EmbeddedTweetActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
