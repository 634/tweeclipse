package com.github.tweeclipse.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import com.github.tweeclipse.Tweeclipse;
import com.github.tweeclipse.exceptions.TweeclipseException;

public class TwitterUtil {
	public boolean isValidAuthorization(){
		IPreferenceStore store = Tweeclipse.getDefault().getPreferenceStore();
		String twitterUser = store.getString(Const.PREFERENCE_TWITTER_USER_ID);
		String twitterPassword = store.getString(Const.PREFERENCE_TWITTER_USER_PASSWORD);

		Twitter twitter = new TwitterFactory().getInstance(twitterUser, twitterPassword);
		try {
			twitter.getFriendsTimeline();
			return true;
		} catch (TwitterException e) {
			return false;
		}
	}
	
	public List<Tweet> getFriendTimeLineList() {

		IPreferenceStore store = Tweeclipse.getDefault().getPreferenceStore();
		String twitterUser = store.getString(Const.PREFERENCE_TWITTER_USER_ID);
		String twitterPassword = store.getString(Const.PREFERENCE_TWITTER_USER_PASSWORD);

		Twitter twitter = new TwitterFactory().getInstance(twitterUser, twitterPassword);
		Paging paging = new Paging(1, 50);
		List<Status> statuses;
		try {
			statuses = twitter.getFriendsTimeline(paging);
		} catch (TwitterException e) {
			throw new TweeclipseException(e);
		}

		List<Tweet> tweetList = new ArrayList<Tweet>();
		for (Status status : statuses) {
			User user = new User();
			user.name = status.getUser().getName();

			Tweet tweet = new Tweet();
			tweet.id = status.getId();
			tweet.postDate = status.getCreatedAt();
			tweet.tweet = status.getText();
			tweet.source = status.getSource();
			tweet.user = user;

			tweetList.add(tweet);
		}

		return tweetList;
	}
}
