package com.example.gpaplan_mainpage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
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
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;
import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;
import com.example.controller.Controller;
import com.example.db.GPADto;


import com.example.service.GPAService;
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
		if (item.getItemId() == R.id.action_subject_add) {
			Intent intent = new Intent(this, DisplayAddActivity.class);
			startActivity(intent);
			return true;
		} else {
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
		ArrayList<GroupItem> lst_group;
		GPAService gservice;
		List<GPADto> dtoList;
		private ActionMode mActionMode;
		int expandableListSelectionType = ExpandableListView.PACKED_POSITION_TYPE_NULL;
		public FirstSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			gservice = new GPAService(getActivity());
			/*
			 * TextView dummyTextView = (TextView) rootView
			 * .findViewById(R.id.section_label);
			 * dummyTextView.setText(Integer.toString(getArguments().getInt(
			 * ARG_SECTION_NUMBER)));
			 */
			// Set up ExpandableListView
			lstView = (ExpandableListView) rootView.findViewById(R.id.explist);
			//lstView.setDivider(null);
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
					if (mActionMode != null)  {
						if (expandableListSelectionType == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
							int flatPosition = parent.getFlatListPosition(
									ExpandableListView.getPackedPositionForChild(groupPosition,childPosition));
							parent.setItemChecked(
									flatPosition,
									!parent.isItemChecked(flatPosition));
						}
						return true;
					}
					else{
						Intent intent = new Intent(getActivity(),
								EditSubjectActivity.class);
						int DBid = lst_group.get(groupPosition).getItems().get(childPosition).getDBid();
						intent.putExtra("DBid", DBid);
						startActivity(intent);
						return true;
					}

				}
			};

			lstView.setOnChildClickListener(mChildClickListener);
			lstView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
			////여기여기;;
			lstView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

				@Override
				public void onItemCheckedStateChanged(ActionMode mode, int position,
						long id, boolean checked) {
					int count = lstView.getCheckedItemCount();
					
					if (count == 1) {
						expandableListSelectionType = ExpandableListView.getPackedPositionType(
								lstView.getExpandableListPosition(position));
						
					}
					if(expandableListSelectionType == ExpandableListView.PACKED_POSITION_TYPE_GROUP)
					mode.finish();
					mode.setTitle(String.valueOf(count));
					configureMenu(mode.getMenu(), count);
				
				}

				@Override
				public boolean onCreateActionMode(ActionMode mode, Menu menu) {
					//After orientation change,
					//setting expandableListSelectionType, as tried in setExpandableListSelectionType
					//does not work, because getExpandableListPosition does not return the correct value
					//probably because the adapter has not yet been set up correctly
					//thus we default to PACKED_POSITION_TYPE_GROUP
					//this workaround works because orientation change collapses the groups
					//so we never restore the CAB for PACKED_POSITION_TYPE_CHILD
					expandableListSelectionType = ExpandableListView.PACKED_POSITION_TYPE_GROUP;
					MenuInflater in=mode.getMenuInflater();
					in.inflate(R.menu.action_mode,menu);
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
				public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
					int itemId = item.getItemId();
					SparseBooleanArray checkedItemPositions = lstView.getCheckedItemPositions();
					ArrayList<int[] > list = new ArrayList<int[]>();
					int checkedItemCount = checkedItemPositions.size();
					String msgAction="";
					ArrayList<String> msgObject = new ArrayList<String>();
					class ccip {
						int grouppos;
						int childpos;
						ccip(int g,int c){
							this.grouppos=g;
							this.childpos=c;
						}
					}
					ArrayList<ccip> convertedcheckedItemPos = new ArrayList<ccip>();
					
					if (checkedItemPositions != null) {
						for (int i=0; i<checkedItemCount; i++) {
							if (checkedItemPositions.valueAt(i)) {
								int position = checkedItemPositions.keyAt(i);
								ContextMenu.ContextMenuInfo info;
								long pos = lstView.getExpandableListPosition(position);
								int groupPos = ExpandableListView.getPackedPositionGroup(pos);
								if (ExpandableListView.getPackedPositionType(pos) == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
									msgObject.add("Group " + groupPos);
								} else {
									int childPos = ExpandableListView.getPackedPositionChild(pos);
									msgObject.add("Child " + childPos + " in group " + groupPos);
									convertedcheckedItemPos.add(new ccip(groupPos,childPos));
								}
							}
						}
					}
					
					switch(itemId) {
					
					case R.id.action_delete_item:
					//	for()
						//gservice.gpaDao.DeleteOneGpa(DBid);
						for(ccip posSet : convertedcheckedItemPos){
							ChildItem citem_temp = lst_group.get(posSet.grouppos).getItems().get(posSet.childpos);
							gservice.gpaDao.DeleteOneGpa(citem_temp.getDBid());
						}
						
						lst_group.clear();
						LoadGroupData();
						
						Toast.makeText(getActivity(),"삭제한당", Toast.LENGTH_SHORT).show();
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
					//adpt.setClickedPos(20);
				}
			});
			lstView.setOnGroupClickListener(new OnGroupClickListener() {
				@Override
				public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
					adpt.setClickedPos(groupPosition);
					if (mActionMode != null)  {
						if (expandableListSelectionType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
							int flatPosition = parent.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(groupPosition));
							parent.setItemChecked(
									flatPosition,
									!parent.isItemChecked(flatPosition));
							return true;
						}
					}
					
				
					return false;
				}
			});
			lstView.setOnGroupExpandListener(new OnGroupExpandListener(){

				@Override
				public void onGroupExpand(int groupPosition) {
					// TODO Auto-generated method stub
					//adpt.setClickedPos(20);
				}
				
			});
			return rootView;
		}
		protected void configureMenu(Menu menu, int count) {
			boolean inGroup = expandableListSelectionType == ExpandableListView.PACKED_POSITION_TYPE_GROUP;			
		}
		int index ;
		private void LoadGroupData() {

			try{

				dtoList = gservice.getAllList();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{

				ArrayList<ChildItem> lstchd ;
				Log.i("1",dtoList.size()+"");
				for(GPADto dto_temp:  dtoList){
					ChildItem ci = new ChildItem();
					ci.setSubData(dto_temp.getId(),dto_temp.getGrade(),dto_temp.getSubject(),
							dto_temp.getMajor(), dto_temp.getCredit());
					// First DB占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占싶몌옙 占쌨아와쇽옙 占십깍옙화占쏙옙占쏙옙占쏙옙.		

					GroupItem gitem = new GroupItem();
					gitem.setName(dto_temp.getYear()
							+"학년 "+
							((dto_temp.getSemester()>=4)?"겨울":								
							(dto_temp.getSemester()>=3)?"여름":dto_temp.getSemester())
							
							+"학기");
					gitem.setGrade(gservice.getGPA(GPAService.TOTAL_SCORE, dto_temp.getYear(), dto_temp.getSemester()));
					if(checkgitem(gitem.getName())){

						gitem =lst_group.get(index);  
						lstchd =  gitem.getItems();
						lstchd.add(ci);
					}
					else{
						lstchd = new ArrayList<ChildItem>();
						lstchd.add(ci);
						gitem.setItems(lstchd);
						lst_group.add(gitem);
					}
				}
				adpt.notifyDataSetChanged();
				
			}
		}

		private boolean checkgitem(String name){
			for(GroupItem g:lst_group){
				if(name.equals(g.getName())){
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

		public static final String ARG_SECTION_NUMBER = "section_number";

		public SecondSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_second,
					container, false);
			//////////--------------------그래프시작
			controller = new Controller(rootView.getContext());
			 TextView tv1=(TextView)rootView.findViewById(R.id.textView1); //=(View)findViewById(R.id.textView1);
			 tv1.setText(""+controller.getTotalGPA());
			 TextView tv2=(TextView)rootView.findViewById(R.id.textView2);
			 tv2.setText(""+controller.getTotalCredit());
		/*
		 * DB에서 year semester 가져온다음   이중포문 사용해서
		 * GPAService.TOTAL_SCORE,year,semester 호출한다
		 * setX
		 * setY 
		 * 문제 있을  수 있다
		 * 
		 */
			Line l = new Line();
			LinePoint p = new LinePoint();
			p.setX(0);
			p.setY(controller.getGPA(GPAService.TOTAL_SCORE,1,1));
			l.addPoint(p);

			p = new LinePoint();
			p.setX(2);
			p.setY(controller.getGPA(GPAService.TOTAL_SCORE,1,2));
			l.addPoint(p);

			p = new LinePoint();
			p.setX(4);
			p.setY(controller.getGPA(GPAService.TOTAL_SCORE,2,1));
			l.addPoint(p);

			p = new LinePoint();
			p.setX(6);
			p.setY(controller.getGPA(GPAService.TOTAL_SCORE,2,2));
			l.addPoint(p);

			p = new LinePoint();
			p.setX(8);
			p.setY(controller.getGPA(GPAService.TOTAL_SCORE,3,1));
			l.addPoint(p);

			p = new LinePoint();
			p.setX(10);
			p.setY(controller.getGPA(GPAService.TOTAL_SCORE,3,2));
			l.addPoint(p);

			p = new LinePoint();
			p.setX(12);
			p.setY(controller.getGPA(GPAService.TOTAL_SCORE,4,1));
			l.addPoint(p);

			p = new LinePoint();
			p.setX(14);
			p.setY(controller.getGPA(GPAService.TOTAL_SCORE,4,2));
			l.addPoint(p);
			l.setColor(Color.parseColor("#0099CC"));

			LineGraph li = (LineGraph)rootView.findViewById(R.id.graph3);
			li.addLine(l);
			li.setRangeY(0, 4.6f);
			li.setLineToFill(0);

			///////////

			Line l2 = new Line();
			LinePoint p2 = new LinePoint();
			p2.setX(0);
			p2.setY(controller.getGPA(GPAService.MAJOR_SCORE,1,1));
			l2.addPoint(p2);

			p2 = new LinePoint();
			p2.setX(2);
			p2.setY(controller.getGPA(GPAService.MAJOR_SCORE,1,2));
			l2.addPoint(p2);

			p2 = new LinePoint();
			p2.setX(4);
			p2.setY(controller.getGPA(GPAService.MAJOR_SCORE,2,1));
			l2.addPoint(p2);

			p2 = new LinePoint();
			p2.setX(6);
			p2.setY(controller.getGPA(GPAService.MAJOR_SCORE,2,2));
			l2.addPoint(p2);

			p2 = new LinePoint();
			p2.setX(8);
			p2.setY(controller.getGPA(GPAService.MAJOR_SCORE,3,1));
			l2.addPoint(p2);

			p2 = new LinePoint();
			p2.setX(10);
			p2.setY(controller.getGPA(GPAService.MAJOR_SCORE,3,2));
			l2.addPoint(p2);

			p2 = new LinePoint();
			p2.setX(12);
			p2.setY(controller.getGPA(GPAService.MAJOR_SCORE,4,1));
			l2.addPoint(p2);

			p2 = new LinePoint();
			p2.setX(14);
			p2.setY(controller.getGPA(GPAService.MAJOR_SCORE,4,1));
			l2.addPoint(p2);
			l2.setColor(Color.parseColor("#823EB6"));

			LineGraph li2 = (LineGraph)rootView.findViewById(R.id.graph3);
			li2.addLine(l2);
			li2.setRangeY(0, 4.6f);
			li2.setLineToFill(0);

			///////////////////////////


			Line l3 = new Line();
			LinePoint p3 = new LinePoint();
			p3.setX(0);
			p3.setY(controller.getGPA(GPAService.LIBERALARTS_SCORE,1,1));
			l3.addPoint(p3);

			p3 = new LinePoint();
			p3.setX(2);
			p3.setY(controller.getGPA(GPAService.LIBERALARTS_SCORE,1,2));
			l3.addPoint(p3);

			p3 = new LinePoint();
			p3.setX(4);
			p3.setY(controller.getGPA(GPAService.LIBERALARTS_SCORE,2,1));
			l3.addPoint(p3);

			p3 = new LinePoint();
			p3.setX(6);
			p3.setY(controller.getGPA(GPAService.LIBERALARTS_SCORE,2,2));
			l3.addPoint(p3);

			p3 = new LinePoint();
			p3.setX(8);
			p3.setY(controller.getGPA(GPAService.LIBERALARTS_SCORE,3,1));
			l3.addPoint(p3);

			p3 = new LinePoint();
			p3.setX(10);
			p3.setY(controller.getGPA(GPAService.LIBERALARTS_SCORE,3,2));
			l3.addPoint(p3);

			p3 = new LinePoint();
			p3.setX(12);
			p3.setY(controller.getGPA(GPAService.LIBERALARTS_SCORE,4,1));
			l3.addPoint(p3);

			p3 = new LinePoint();
			p3.setX(14);
			p3.setY(controller.getGPA(GPAService.LIBERALARTS_SCORE,4,2));
			l3.addPoint(p3);
			l3.setColor(Color.parseColor("#CBADE3"));

			LineGraph li3 = (LineGraph)rootView.findViewById(R.id.graph3);
			li3.addLine(l3);
			li3.setRangeY(0, 4.6f);
			li3.setLineToFill(0);



			//			ArrayList<Bar> points = new ArrayList<Bar>();
			//			Bar d = new Bar();
			//			d.setColor(Color.parseColor("#99CC00"));
			//			d.setName("Test1");
			//			d.setValue(10);
			//			Bar d2 = new Bar();
			//			d2.setColor(Color.parseColor("#FFBB33"));
			//			d2.setName("Test2");
			//			d2.setValue(20);
			//			points.add(d);
			//			points.add(d2);
			//
			//			BarGraph g = (BarGraph)findViewById(R.id.graph);
			//			g.setBars(points);



			//////////
			//			PieGraph pg = (PieGraph)rootView.findViewById(R.id.graph2);
			//			PieSlice slice = new PieSlice();
			//			slice.setColor(Color.parseColor("#99CC00"));
			//			slice.setValue(17);
			//			pg.addSlice(slice);
			//			slice = new PieSlice();
			//			slice.setColor(Color.parseColor("#FFBB33"));
			//			slice.setValue(3);
			//			pg.addSlice(slice);
			//			slice = new PieSlice();
			//			slice.setColor(Color.parseColor("#AA66CC"));
			//			slice.setValue(80f);
			//			pg.addSlice(slice);
			////------------------------------------------------------
			return rootView;


		}
	}

}