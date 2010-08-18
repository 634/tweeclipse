package com.github.tweeclipse.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.github.tweeclipse.Tweeclipse;
import com.github.tweeclipse.utils.Const;

public class TweeclipsePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public TweeclipsePreferencePage() {
		super(GRID);
		setPreferenceStore(Tweeclipse.getDefault().getPreferenceStore());
		setDescription("Tweeclipseの設定をおこないます");
	}

	@Override
	public void createFieldEditors() {
		addField(new StringFieldEditor(Const.PREFERENCE_TWITTER_USER_ID, "twitter userid:", getFieldEditorParent()));

		StringFieldEditor passwordField = new StringFieldEditor(Const.PREFERENCE_TWITTER_USER_PASSWORD, "twitte password:", getFieldEditorParent());
		passwordField.getTextControl( getFieldEditorParent() ).setEchoChar('*');
		addField(passwordField);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}