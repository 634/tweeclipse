package com.github.tweeclipse.views;

import static com.github.tweeclipse.utils.Const.RETURN_STRING;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;

import com.github.tweeclipse.Tweeclipse;
import com.github.tweeclipse.utils.Const;
import com.github.tweeclipse.utils.Tweet;
import com.github.tweeclipse.utils.TwitterUtil;

public class TweetView extends ViewPart {

	public static final String ID = "com.github.tweeclipse.views.TweetView";

	private Text text;

	// 再読み込み
	private Action refreshAction;

	public TweetView() {
		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		text = new Text(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.READ_ONLY | SWT.WRAP);

		initialize();
	}

	/**
	 * 共通初期化処理
	 */
	private void initialize() {
		text.setBackground(new Color(Display.getCurrent(), 255, 255, 255));

		refreshAction = new Action() {
			@Override
			public void run() {
				readTweet();
			}
		};

		IActionBars bars = getViewSite().getActionBars();
		IToolBarManager toolBarManager = bars.getToolBarManager();

		refreshAction.setText("再読み込み");
		refreshAction.setToolTipText("再読み込み");
		refreshAction.setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "/icons/action_refresh.gif"));
		toolBarManager.add(refreshAction);

		readTweet();
	}

	private void readTweet() {
		IPreferenceStore store = Tweeclipse.getDefault().getPreferenceStore();
		String twitterUser = store.getString(Const.PREFERENCE_TWITTER_USER_ID);
		String twitterPassword = store.getString(Const.PREFERENCE_TWITTER_USER_PASSWORD);
		if (twitterUser.equals("") || twitterPassword.equals("")) {
			text.setText("接続できませんでした。設定ページのユーザー情報を確認してください。");
			return;
		}

		TwitterUtil twitterUtil = new TwitterUtil();
		if (!twitterUtil.isValidAuthorization()) {
			text.setText("接続できませんでした。設定ページのユーザー情報を確認してください。");
			return;
		}

		List<Tweet> tweetList = twitterUtil.getFriendTimeLineList();

		String source = "";
		for (Tweet tweet : tweetList) {
			source += tweet.user.name + " ： " + tweet.tweet + RETURN_STRING;
		}

		text.setText(source);
	}

	@Override
	public void setFocus() {
	}
}