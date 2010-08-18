package com.github.tweeclipse.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.github.tweeclipse.Tweeclipse;
import com.github.tweeclipse.utils.Const;

public class TweeclipsePreferenceInitializer extends AbstractPreferenceInitializer {
	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Tweeclipse.getDefault().getPreferenceStore();
		store.setDefault(Const.PREFERENCE_TWITTER_USER_ID, "");
		store.setDefault(Const.PREFERENCE_TWITTER_USER_PASSWORD, "");
	}
}
