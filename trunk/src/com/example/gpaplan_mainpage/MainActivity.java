package com.example.gpaplan_mainpage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.ClipData.Item;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnLayoutChangeListener;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;

import com.example.holographylibrary.*;
import com.example.controller.Controller;
import com.example.db.GPADto;

import com.example.service.GPAService;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	static SharedPreferences savedGrad_major;
	static ArrayList<GroupItem> lst_group;
	long m_startTime;
	long m_endTime;
	static float getScale;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 사용자가 들어왔었는지 확인(write)
		SharedPreferences sharedPref = getSharedPreferences("pref",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt(getString(R.string.savedSetting), 1);
		editor.commit();

		// 4.3 4.5읽기(READ)
		SharedPreferences savedScale = getSharedPreferences("savedScale",
				Context.MODE_PRIVATE);
		savedGrad_major = getSharedPreferences("SharedSetting",
				Context.MODE_PRIVATE);
		float init43 = 4.3f;
		getScale = savedScale.getFloat(getString(R.string.savedScale), init43);
		setContentView(R.layout.activity_main);

		m_startTime = System.currentTimeMillis();
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
			if (i == 0) {
				Tab MainList = actionBar.newTab();
				MainList.setTabListener(this);
				MainList.setIcon(R.drawable.mainlist_selector);

				actionBar.addTab(MainList);
				// .setText(mSectionsPagerAdapter.getPageTitle(i))

			} else {
				actionBar.addTab(actionBar.newTab()
						.setIcon(R.drawable.statistics_selector)

						// .setText(mSectionsPagerAdapter.getPageTitle(i))
						.setTabListener(this));

			}
		}

		// actionBar.setSplitBackgroundDrawable(getResources());

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

		Intent intent;
		switch (item.getItemId()) {
		//	 startActivity(intent);
	//		 return true;

		case R.id.action_subject_add:
			intent = new Intent(this, DisplayAddActivity.class);

		case R.id.action_settings:
			intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			return true;
		default:
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
			switch (position) {
			case 0:
				/*
				 * Fragment fragment = new FirstSectionFragment(); Bundle args =
				 * new Bundle();
				 * args.putInt(FirstSectionFragment.ARG_SECTION_NUMBER, position
				 * + 1); fragment.setArguments(args); return fragment;
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
		public void onResume() {
			super.onResume();
		};

		public static final String ARG_SECTION_NUMBER = "section_number";

		// Expandable listView
		mExpandableListAdpater adpt;
		ExpandableListView lstView;
		TextView blankView;
		GPAService gservice;
		List<GPADto> dtoList;
		private ActionMode mActionMode;
		int expandableListSelectionType = ExpandableListView.PACKED_POSITION_TYPE_NULL;

		public FirstSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			gservice = new GPAService(getActivity());

			// Set up ExpandableListView
			lstView = (ExpandableListView) rootView.findViewById(R.id.explist);
			lstView.setIndicatorBounds(10, 100);
			blankView = (TextView) rootView.findViewById(R.id.blank_textview);
			// lstView.setDivider(null);
			lst_group = new ArrayList<GroupItem>();
			// Loading Data;

			adpt = new mExpandableListAdpater(rootView.getContext(), lstView,
					(ArrayList<GroupItem>) lst_group);
			lstView.setAdapter(adpt);
			lstView.setDivider(null);

			LoadGroupData();

			ExpandableListView.OnChildClickListener mChildClickListener = new OnChildClickListener() {

				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {
					// TODO Auto-generated method stub
					if (mActionMode != null) {
						if (expandableListSelectionType == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
							int flatPosition = parent
									.getFlatListPosition(ExpandableListView
											.getPackedPositionForChild(
													groupPosition,
													childPosition));
							parent.setItemChecked(flatPosition,
									!parent.isItemChecked(flatPosition));
						}
						return true;
					} else {
						Intent intent = new Intent(getActivity(),
								EditSubjectActivity.class);
						int DBid = lst_group.get(groupPosition).getItems()
								.get(childPosition).getDBid();
						intent.putExtra("DBid", DBid);
						startActivity(intent);
						return true;
					}

				}
			};

			lstView.setOnChildClickListener(mChildClickListener);
			lstView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
			// //여기여기;;
			lstView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

				@Override
				public void onItemCheckedStateChanged(ActionMode mode,
						int position, long id, boolean checked) {
					int count = lstView.getCheckedItemCount();

					if (count == 1) {
						expandableListSelectionType = ExpandableListView
								.getPackedPositionType(lstView
										.getExpandableListPosition(position));

					}
					if (expandableListSelectionType == ExpandableListView.PACKED_POSITION_TYPE_GROUP)
						mode.finish();
					mode.setTitle(String.valueOf(count));
					configureMenu(mode.getMenu(), count);

				}

				@Override
				public boolean onCreateActionMode(ActionMode mode, Menu menu) {
					// After orientation change,
					// setting expandableListSelectionType, as tried in
					// setExpandableListSelectionType
					// does not work, because getExpandableListPosition does not
					// return the correct value
					// probably because the adapter has not yet been set up
					// correctly
					// thus we default to PACKED_POSITION_TYPE_GROUP
					// this workaround works because orientation change
					// collapses the groups
					// so we never restore the CAB for
					// PACKED_POSITION_TYPE_CHILD
					expandableListSelectionType = ExpandableListView.PACKED_POSITION_TYPE_GROUP;
					MenuInflater in = mode.getMenuInflater();
					in.inflate(R.menu.action_mode, menu);
					mode.setTitle(String.valueOf(lstView.getCheckedItemCount()));
					mActionMode = mode;
					return true;
				}

				@Override
				public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
					configureMenu(menu, lstView.getCheckedItemCount());
					return false;
				}

				@Override
				public boolean onActionItemClicked(ActionMode mode,
						MenuItem item) {
					int itemId = item.getItemId();
					SparseBooleanArray checkedItemPositions = lstView
							.getCheckedItemPositions();
					ArrayList<int[]> list = new ArrayList<int[]>();
					int checkedItemCount = checkedItemPositions.size();
					String msgAction = "";
					ArrayList<String> msgObject = new ArrayList<String>();
					class ccip {
						int grouppos;
						int childpos;

						ccip(int g, int c) {
							this.grouppos = g;
							this.childpos = c;
						}
					}
					ArrayList<ccip> convertedcheckedItemPos = new ArrayList<ccip>();

					if (checkedItemPositions != null) {
						for (int i = 0; i < checkedItemCount; i++) {
							if (checkedItemPositions.valueAt(i)) {
								int position = checkedItemPositions.keyAt(i);
								ContextMenu.ContextMenuInfo info;
								long pos = lstView
										.getExpandableListPosition(position);
								int groupPos = ExpandableListView
										.getPackedPositionGroup(pos);
								if (ExpandableListView
										.getPackedPositionType(pos) == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
									msgObject.add("Group " + groupPos);
								} else {
									int childPos = ExpandableListView
											.getPackedPositionChild(pos);
									msgObject.add("Child " + childPos
											+ " in group " + groupPos);
									convertedcheckedItemPos.add(new ccip(
											groupPos, childPos));
								}
							}
						}
					}

					switch (itemId) {

					case R.id.action_delete_item:
						// for()
						// gservice.gpaDao.DeleteOneGpa(DBid);
						for (ccip posSet : convertedcheckedItemPos) {
							ChildItem citem_temp = lst_group
									.get(posSet.grouppos).getItems()
									.get(posSet.childpos);
							gservice.gpaDao.DeleteOneGpa(citem_temp.getDBid());
						}

						lst_group.clear();
						LoadGroupData();

						Toast.makeText(getActivity(), "삭제한당",
								Toast.LENGTH_SHORT).show();
						break;
					}

					mode.finish();
					return true;
				}

				@Override
				public void onDestroyActionMode(ActionMode mode) {

					mActionMode = null;

				}
			});
			lstView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

				@Override
				public void onGroupCollapse(int groupPosition) {
					// TODO Auto-generated method stub

					// adpt.setClickedPos(20);
				}
			});
			lstView.setOnGroupClickListener(new OnGroupClickListener() {
				@Override
				public boolean onGroupClick(ExpandableListView parent, View v,
						int groupPosition, long id) {
					adpt.setClickedPos(groupPosition);
					if (mActionMode != null) {
						if (expandableListSelectionType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
							int flatPosition = parent.getFlatListPosition(ExpandableListView
									.getPackedPositionForGroup(groupPosition));
							parent.setItemChecked(flatPosition,
									!parent.isItemChecked(flatPosition));
							return true;
						}
					}
					return false;

				}

			});

			lstView.setScrollbarFadingEnabled(true);
			lstView.addOnLayoutChangeListener(new OnLayoutChangeListener() {

				@Override
				public void onLayoutChange(View v, int left, int top,
						int right, int bottom, int oldLeft, int oldTop,
						int oldRight, int oldBottom) {
					// TODO Auto-generated method stub
					if (bottom >= oldBottom)
						adpt.setClickedPos(100);
				}
			});

			lstView.setOnSystemUiVisibilityChangeListener(new OnSystemUiVisibilityChangeListener() {

				@Override
				public void onSystemUiVisibilityChange(int visibility) {
					// TODO Auto-generated method stub
					// if()
				}
			});
			lstView.setOnGroupExpandListener(new OnGroupExpandListener() {

				@Override
				public void onGroupExpand(int groupPosition) {
					// TODO Auto-generated method stub
					// adpt.setClickedPos(20);
				}

			});

			return rootView;
		}

		protected void configureMenu(Menu menu, int count) {
			boolean inGroup = expandableListSelectionType == ExpandableListView.PACKED_POSITION_TYPE_GROUP;
		}

		int index;

		private void LoadGroupData() {

			if (getScale == 4.3f)
				gservice.setting = 0;
			else
				gservice.setting = 1;

			try {

				dtoList = gservice.getAllList();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (dtoList.size() == 0) {
					lstView.setVisibility(View.GONE);
					blankView.setVisibility(View.VISIBLE);
					return;
				} else {
					blankView.setVisibility(View.GONE);
				}

				ArrayList<ChildItem> lstchd;
				if (dtoList.size() > 0)

					Log.i("1", dtoList.size() + "");
				for (GPADto dto_temp : dtoList) {
					ChildItem ci = new ChildItem();
					ci.setSubData(dto_temp.getId(), dto_temp.getGrade(),
							dto_temp.getSubject(), dto_temp.getMajor(),
							dto_temp.getCredit());
					// First DB占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占싶몌옙 占쌨아와쇽옙 占십깍옙화占쏙옙占쏙옙占쏙옙.

					GroupItem gitem = new GroupItem();
					gitem.setName(dto_temp.getYear()
							+ "학년 "
							+ ((dto_temp.getSemester() >= 4) ? "겨울" : (dto_temp
									.getSemester() >= 3) ? "여름" : dto_temp
									.getSemester())

							+ "학기");
					gitem.setGrade(gservice.getGPA(GPAService.TOTAL_SCORE,
							dto_temp.getYear(), dto_temp.getSemester()));
					gitem.setYear(dto_temp.getYear());
					gitem.setSemester(dto_temp.getSemester());
					if (checkgitem(gitem.getName())) {

						gitem = lst_group.get(index);
						lstchd = gitem.getItems();
						lstchd.add(ci);
					} else {
						lstchd = new ArrayList<ChildItem>();
						lstchd.add(ci);
						gitem.setItems(lstchd);
						lst_group.add(gitem);
					}
				}
				adpt.notifyDataSetChanged();

			}
		}

		private boolean checkgitem(String name) {
			for (GroupItem g : lst_group) {
				if (name.equals(g.getName())) {
					index = lst_group.indexOf(g);
					return true;
				}
			}

			return false;
		}
	}

	public static class SecondSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private Controller controller;
		private float targetGpaPerSemester;
		private int gpaAchievement;

		private int creditForGraduate;
		private float targetGpa;

		public static final String ARG_SECTION_NUMBER = "section_number";

		public SecondSectionFragment() {
		}

		@Override
		public void onResume() {
			// TODO Auto-generated method stub

			super.onResume();
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_second,
					container, false);
			// ////////--------------------그래프시작
			controller = new Controller(rootView.getContext());
			TextView tv1 = (TextView) rootView.findViewById(R.id.textView1); // =(View)findViewById(R.id.textView1);
			tv1.setText("" + controller.getTotalGPA());
			TextView tv2 = (TextView) rootView.findViewById(R.id.textView2);
			tv2.setText("" + controller.getTotalCredit());

			// ㅈㅇ학ㅈㅁ
			// int getGrad_major;
			int defaultNumber = 0;// 아무것도 저장되지 않았을때의 값
			creditForGraduate = savedGrad_major.getInt(getString(R.string.savedGoalMajor), defaultNumber);
			targetGpa = savedGrad_major.getFloat(getString(R.string.savedTarget), 0);

			targetGpaPerSemester = controller.getTargetGpaPerSemester(creditForGraduate, targetGpa);
			gpaAchievement = controller.getGpaAchievement(targetGpa);
			/*
			 * DB에서 year semester 가져온다음 이중포문 사용해서
			 * GPAService.TOTAL_SCORE,year,semester 호출한다 setX setY 문제 있을 수 있다
			 */
			LineGraph li = (LineGraph) rootView.findViewById(R.id.graph3);
			li.showHorizontalGrid(true);
			li.showMinAndMaxValues(true);
			li.setRangeY(0, 4.5f);

			Line tline = new Line();
			Line mline = new Line();
			Line lline = new Line();

			float plot_x = 0;
			for (GroupItem g : lst_group) {
				LinePoint tpoint = new LinePoint();
				LinePoint mpoint = new LinePoint();
				LinePoint lpoint = new LinePoint();
				tpoint.setX(plot_x);
				mpoint.setX(plot_x);
				lpoint.setX(plot_x);

				tpoint.setY(controller.getGPA(GPAService.TOTAL_SCORE,
						g.getYear(), g.getSemester()));
				mpoint.setY(controller.getGPA(GPAService.MAJOR_SCORE,
						g.getYear(), g.getSemester()));
				lpoint.setY(controller.getGPA(GPAService.LIBERALARTS_SCORE,
						g.getYear(), g.getSemester()));
				if (!Float.isNaN(tpoint.getY()))
					tline.addPoint(tpoint);
				if (!Float.isNaN(mpoint.getY()))
					mline.addPoint(mpoint);
				if (!Float.isNaN(lpoint.getY()))
					lline.addPoint(lpoint);

				plot_x += 2.0f;
			}
			if (lst_group.size() > 0) {
				tline.setColor(getResources().getColor(R.color.line_total));
				mline.setColor(getResources().getColor(R.color.line_major));
				lline.setColor(getResources().getColor(R.color.line_liberal));
				li.addLine(tline);
				li.addLine(mline);
				li.addLine(lline);
				li.setGrouplist(lst_group);
			}
			return rootView;
		}
	}

	@Override
	public void onBackPressed() {
		boolean m_isPressedBackButton = true;
		m_endTime = System.currentTimeMillis();

		if (m_endTime - m_startTime > 2000)
			m_isPressedBackButton = false;
		// 백버튼 막기
		if (m_isPressedBackButton == false) {
			m_isPressedBackButton = true;
			m_startTime = System.currentTimeMillis();

			Toast.makeText(this, "뒤로 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT)
					.show();

		} else {
			finish();
			System.exit(0);
			android.os.Process.killProcess(android.os.Process.myPid());
		}
	}
}
