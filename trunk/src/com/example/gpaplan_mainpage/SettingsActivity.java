package com.example.gpaplan_mainpage;

import java.util.List;
import java.util.logging.Logger;



import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.widget.Toast;

public class SettingsActivity extends PreferenceActivity {

	private static final boolean ALWAYS_SIMPLE_PREFS = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setupSimplePreferencesScreen();
	}

	
	private void setupSimplePreferencesScreen() {
		if (!isSimplePreferences(this)) {
			return;
		}


		PreferenceCategory fakeHeader = new PreferenceCategory(this);
		addPreferencesFromResource(R.xml.pref_general);
		
		
		bindPreferenceSummaryToValue(findPreference("savedScale"));
		bindPreferenceSummaryToValue(findPreference("savedGoalMajor"));
		bindPreferenceSummaryToValue(findPreference("savedTarget"));
		bindPreferenceSummaryToValue(findPreference("resetdialog"));
	}

	/** {@inheritDoc} */
	@Override
	public boolean onIsMultiPane() {
		return isXLargeTablet(this) && !isSimplePreferences(this);
	}

	/**
	 * Helper method to determine if the device has an extra-large screen. For
	 * example, 10" tablets are extra-large.
	 */
	private static boolean isXLargeTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
	}

	/**
	 * Determines whether the simplified settings UI should be shown. This is
	 * true if this is forced via {@link #ALWAYS_SIMPLE_PREFS}, or the device
	 * doesn't have newer APIs like {@link PreferenceFragment}, or the device
	 * doesn't have an extra-large screen. In these cases, a single-pane
	 * "simplified" settings UI should be shown.
	 */
	private static boolean isSimplePreferences(Context context) {
		return ALWAYS_SIMPLE_PREFS
				|| Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB
				|| !isXLargeTablet(context);
	}

	/** {@inheritDoc} */
	
	/**
	 * A preference value change listener that updates the preference's summary
	 * to reflect its new value.
	 */
	private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
		@Override
		public boolean onPreferenceChange(Preference preference, Object value) {
			if(value==null)
				value="";
			String stringValue = value.toString();

			if (preference instanceof ListPreference) {
				ListPreference listPreference = (ListPreference) preference;
			
				int index = listPreference.findIndexOfValue(stringValue);
				
				if(!(((ListPreference) preference).getValue().equals(stringValue))&&index==1){
					
					final AlertDialog.Builder builder = new AlertDialog.Builder(preference.getContext());
					builder.setMessage("4.3에서  4.5로 변경시에는\n\n-성적이 0성적으로 변경됩니다.\n\n계속하시겠습니까?")
						.setCancelable(true).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
							//-를 0으로 바꾸는거 동의안함
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								dialog.cancel();
							}
						})
						.setPositiveButton("네", new DialogInterface.OnClickListener() {
							//-를 0으로 바꾸는거 동의
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								modifyDB(builder.getContext());
							}
						});
					AlertDialog alert = builder.create();
					alert.show();
					
					
				}
				// Set the summary to reflect the new value.
				preference
						.setSummary(index >= 0 ? listPreference.getEntries()[index]
							: "");
				
				//4.3만 가능하게 할것이므로 
				

			} else {
				// For all other preferences, set the summary to the value's
				// simple string representation.
				preference.setSummary(stringValue);
				
			}
			
			return true;
		}
	};
	private static void modifyDB(Context c){
		Toast.makeText(c,"바꾸고싶당",Toast.LENGTH_SHORT).show();
	}
	/**
	 * Binds a preference's summary to its value. More specifically, when the
	 * preference's value is changed, its summary (line of text below the
	 * preference title) is updated to reflect the value. The summary is also
	 * immediately updated upon calling this method. The exact display format is
	 * dependent on the type of preference.
	 * 
	 * @see #sBindPreferenceSummaryToValueListener
	 */
	private static void bindPreferenceSummaryToValue(Preference preference) {
		// Set the listener to watch for value changes.
		preference
				.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

		// Trigger the listener immediately with the preference's
		// current value.
		
		
		sBindPreferenceSummaryToValueListener.onPreferenceChange(
				preference,
				PreferenceManager.getDefaultSharedPreferences(
						preference.getContext()).getString(preference.getKey(),
						""));
	}
			/**
	 * This fragment shows notification preferences only. It is used when the
	 * activity is showing a two-pane settings UI.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static class GeneralPreferenceFragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.pref_general);

			// Bind the summaries of EditText/List/Dialog/Ringtone preferences
			// to their values. When their values change, their summaries are
			// updated to reflect the new value, per the Android Design
			// guidelines.
			bindPreferenceSummaryToValue(findPreference("savedScale"));
			bindPreferenceSummaryToValue(findPreference("savedGoalMajor"));
			bindPreferenceSummaryToValue(findPreference("savedTarget"));
			bindPreferenceSummaryToValue(findPreference("resetdialog"));
			
		}
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		NavUtils.navigateUpFromSameTask(this);
	}
}

