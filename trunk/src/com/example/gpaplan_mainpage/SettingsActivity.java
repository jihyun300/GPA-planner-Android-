package com.example.gpaplan_mainpage;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
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
	Context context;
	ListPreference listPreference;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
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
	
	void showDialog(){
		
		DialogFragment newFragment= MyAlertDialogFragment.newInstance(R.string.title_alertdialog);
		newFragment.show(getFragmentManager(),"dialog");
	}
	boolean f;
	public void doPositiveClick(){
		f=false;
		Toast.makeText(getApplicationContext(), "do positive", Toast.LENGTH_SHORT).show();
		
	}
	public void doNegativedClick(){
		/*취소버튼을 눌렀을때 임의대로 돌리는 기능을함
		이상한일이..일단 4.5를 선택하면 alert다이얼로그를제외한 프로세스는 진행이된후 그다음에
		다이얼로그의 네 아니오 응답은 먼저 우선적으로 처리되지가 않는다(wait걸면 될것도 같은데 모르겟음)
		따라서 아니오를 눌렀을때에는 listPreference의 값을 임의로 4.3으로 되돌린다.
		summary도 같이 되돌리도록 했다.
		*/
		f=true;
		Toast.makeText(getApplicationContext(), "do negative", Toast.LENGTH_SHORT).show();
		String stringValue ="0";
		
		listPreference.setValue(stringValue);
		int index = listPreference.findIndexOfValue(stringValue);
		listPreference.setSummary
		(index >= 0 ? listPreference.getEntries()[index]
			: "");	
	}
	public boolean isDialogCancled(){
		return f;
	}
	
	/* 경고창 관련한 다이얼로그 프래그먼트이다.
	 * 책을 참고해서 만들엇긴햇따. 번들의 사용법 이런걸 잘 알수 있어서 좋앗던듯
	 * 내맘대로 커스터 마이징 하는게 가능함을 볼수 있다. 
	 */
	public static class MyAlertDialogFragment extends DialogFragment{
		public static MyAlertDialogFragment newInstance(int title){
			MyAlertDialogFragment frag = new MyAlertDialogFragment();
			Bundle args = new Bundle();
			args.putInt("title",title);
			frag.setArguments(args);
			return frag;
		}
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			int title =  getArguments().getInt("title");
			return new AlertDialog.Builder(getActivity())
		
			.setIconAttribute(android.R.attr.alertDialogIcon)
			.setTitle(title)
			.setCancelable(true)
			.setMessage("4.3에서  4.5로 변경시에는\n\n-성적이 0성적으로 변경됩니다.\n\n계속하시겠습니까?")
			.setNegativeButton("NO", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					((SettingsActivity)getActivity()).doNegativedClick();
				}
			})
			.setPositiveButton("YES", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub					
				((SettingsActivity)getActivity()).doPositiveClick();
				}
			}
			
			).create();
			//return super.onCreateDialog(savedInstanceState);
			
		}
	}


	/** {@inheritDoc} */
	
	/**
	 * A preference value change listener that updates the preference's summary
	 * to reflect its new value.
	 */
	private  Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
		
		
		@Override
		public boolean onPreferenceChange( Preference preference, Object value) {
			if(value==null)
				value="";
			String stringValue = value.toString();

			if (preference instanceof ListPreference) {
				 listPreference = (ListPreference) preference;
			
				 int index = listPreference.findIndexOfValue(stringValue);
				
				 f=false;
				if(!(listPreference.getValue().equals(stringValue))&&index==1){
					
					showDialog();
					/*
					AlertDialog.Builder builder = new AlertDialog.Builder(preference.getContext());
					builder.setMessage("4.3에서  4.5로 변경시에는\n\n-성적이 0성적으로 변경됩니다.\n\n계속하시겠습니까?")
						.setCancelable(true).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
							//-를 0으로 바꾸는거 동의안함
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
							get
								dialog.cancel();
							}
						})
						.setPositiveButton("네", new DialogInterface.OnClickListener() {
					
							//-를 0으로 바꾸는거 동의
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								modifyDB();
								
							}
						});
					*/
					
					 
				}
				// Set the summary to reflect the new value.
					
					preference.setSummary
					(index >= 0 ? listPreference.getEntries()[index]
						: "");	
		

			} else {
				// For all other preferences, set the summary to the value's
				// simple string representation.
				preference.setSummary(stringValue);
				
			}
			
			return true;
		}
	};
	
	/**
	 * Binds a preference's summary to its value. More specifically, when the
	 * preference's value is changed, its summary (line of text below the
	 * preference title) is updated to reflect the value. The summary is also
	 * immediately updated upon calling this method. The exact display format is
	 * dependent on the type of preference.
	 * 
	 * @see #sBindPreferenceSummaryToValueListener
	 */
	private void bindPreferenceSummaryToValue(Preference preference) {
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
	public class GeneralPreferenceFragment extends PreferenceFragment {
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
	private static void modifyDB(){
		//Toast.makeText(c,"바꾸고싶당",Toast.LENGTH_SHORT).show();
	}
}

