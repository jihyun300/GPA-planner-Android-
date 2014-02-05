package com.example.gpaplan_mainpage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.gpaplan_mainpage.R;

import android.R.drawable;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ExpandableListView.OnChildClickListener;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_main);		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	
		
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		this.setTitle("학점계산기");
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			if(i==0){
				Tab MainList = actionBar.newTab();
				MainList.setTabListener(this);
				MainList.setIcon(R.drawable.mainlist_selector);
				
			actionBar.addTab(MainList);
					//.setText(mSectionsPagerAdapter.getPageTitle(i))
					
			}
			else
			{
				actionBar.addTab(actionBar.newTab().setIcon(R.drawable.statistics_selector)
						
						//.setText(mSectionsPagerAdapter.getPageTitle(i))
						.setTabListener(this));
				
				
			}
		}
		
		//actionBar.setSplitBackgroundDrawable(getResources());
		
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()== R.id.action_subject_add){
		Intent intent = new Intent(this, SubjetctAddActivity.class);
		startActivity(intent);
		return true;	
		}
		else{
		return super.onOptionsItemSelected(item);
		}
		
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}



	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		
		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a FirstSectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			switch(position){
			case 0:
			/*	Fragment fragment = new FirstSectionFragment();
				Bundle args = new Bundle();
				args.putInt(FirstSectionFragment.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				return fragment;
				*/
				return new FirstSectionFragment();
			case 1:
				return new SecondSectionFragment();
				default:
					return new SecondSectionFragment();

			}
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}
 
		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			
			}
			return null;
		}
	}

	/**
	 * Define Each SectionFragment 
	 */
	public static class FirstSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		
		
		//Expandable listView
		mExpandableListAdpater adpt ;
		ExpandableListView lstView;
		List<GroupItem> lst_group;
		
		public FirstSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			/*TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
					
		
			 */
			//Set up ExpandableListView
			lstView= (ExpandableListView)rootView.findViewById(R.id.explist);
			lst_group = new ArrayList<GroupItem>();
			// Loading Data;
			LoadGroupData();
			
			adpt =  new mExpandableListAdpater(rootView.getContext(),lstView,lst_group);
			lstView.setAdapter(adpt);
			final ExpandableListView.OnChildClickListener mChildClickListener =
					new OnChildClickListener() {
						
					@Override
						public boolean onChildClick(ExpandableListView parent, View v,
								int groupPosition, int childPosition, long id) {
							// TODO Auto-generated method stub
						Intent intent = new Intent(getActivity(), SubjetctAddActivity.class);
						startActivity(intent);
							return false;
						}
					};
		
			
			lstView.setOnChildClickListener(mChildClickListener);
			lstView.setChoiceMode(ExpandableListView.CHOICE_MODE_MULTIPLE_MODAL);
			lstView.setMultiChoiceModeListener(new MultiChoiceModeListener(){

				@Override
				public boolean onActionItemClicked(ActionMode mode,
						MenuItem item) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean onCreateActionMode(ActionMode mode, Menu menu) {
					// TODO Auto-generated method stub
					ExpandableListView.OnChildClickListener m2ChildClickListener =
							new OnChildClickListener() {
								
							@Override
								public boolean onChildClick(ExpandableListView parent, View v,
										int groupPosition, int childPosition, long id) {
									// TODO Auto-generated method stub
								int index = lstView.getFlatListPosition(ExpandableListView
						                   .getPackedPositionForChild(groupPosition, childPosition));
								if(lstView.getCheckedItemPositions().get(index)==true)
									lstView.setItemChecked(index, false);
								else
									lstView.setItemChecked(index, true);
							//Toast.makeText(getApplicationContext(), lstView.getCheckedItemPositions().toString(), Toast.LENGTH_SHORT).show();
					/*	SparseBooleanArray item_check = lstView.getCheckedItemPositions();
						for(int i =0;i<item_check.size();i++){
							lstView.setItemChecked(i,item_check.get(i));
						}
						item_check.clear();*/
							return false;
							}
							
							};
					lstView.setOnChildClickListener(m2ChildClickListener);
					return true;
				}

				@Override
				public void onDestroyActionMode(ActionMode mode) {
					// TODO Auto-generated method stub
					lstView.setOnChildClickListener(mChildClickListener);
				}

				@Override
				public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void onItemCheckedStateChanged(ActionMode mode,
						int position, long id, boolean checked) {
					// TODO Auto-generated method stub
					
				}});
			return rootView;
		}
		private void LoadGroupData(){
			GroupItem gitem = new GroupItem();
			List<ChildItem> lstchd = new ArrayList<ChildItem>();
			ChildItem ci = new ChildItem();
			//First DB占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占싶몌옙 占쌨아와쇽옙 占십깍옙화占쏙옙占쏙옙占쏙옙.
			gitem.setData("1학년 1학기");
			ci.setSubData("A+","한글","전공",3.2);
			lstchd.add(ci);
			ci = new ChildItem();
			ci.setSubData("C","디지털영상처리 ","전공",3.3);
			lstchd.add(ci);
			ci = new ChildItem();
			ci.setSubData("B","영화의이해 ","교양",3.4);
			lstchd.add(ci);
			ci = new ChildItem();
			ci.setSubData("B-","심리학의이해(2)","전공",3.1);
			lstchd.add(ci);
			gitem.setItems(lstchd);
			lst_group.add(gitem);
			//Second
			lstchd = new ArrayList<ChildItem>();
			gitem= new GroupItem();
			gitem.setData("1학년2학기");
			ci = new ChildItem();
			ci.setSubData("B+","데이터구조","전공",3.4);
			lstchd.add(ci);
			ci= new ChildItem();
			ci.setSubData("A","글쓰기","교양",3.1);
			lstchd.add(ci);
			gitem.setItems(lstchd);
			lst_group.add(gitem);
		
			
		}
	}
	public static class SecondSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public SecondSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_second,
					container, false);
			return rootView;
		}
	}
	
}
