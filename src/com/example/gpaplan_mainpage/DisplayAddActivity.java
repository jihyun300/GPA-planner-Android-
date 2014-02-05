package com.example.gpaplan_mainpage;

import java.util.ArrayList;



import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class DisplayAddActivity extends Activity implements OnClickListener{

	private final int DYNAMIC_VIEW_ID=0xf8000;
	private LinearLayout dynamicLayout;
	private int num=0;
	
	
	MyAdpater adap;
	ArrayList<Class_info> classInfo;
	public ListView listview;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_add);
		// Show the Up button in the action bar.
		setupActionBar();
	
		dynamicLayout=(LinearLayout)findViewById(R.id.dynamicArea);
		
/*		DBManager dManager=new DBManager(this,"myMember.db",null,1);
		
		SQLiteDatabase db=dManager.getWritableDatabase();*/
		
/*		findViewById(R.id.addButton).setOnClickListener(this);
		findViewById(R.id.delButton).setOnClickListener(this);
*/
		//�߰���ư �κ�
		  View footView = getLayoutInflater().inflate(R.layout.button_view, null);
		     footView.findViewById(R.id.addButton).setOnClickListener(this);
		     footView.findViewById(R.id.delButton).setOnClickListener(this);
		
		classInfo = new ArrayList<Class_info>();
		adap = new MyAdpater(this,classInfo);

		
		listview = (ListView) findViewById(R.id.listview1);
	   
	    
	    listview.addFooterView(footView);
		listview.setAdapter(adap);
		//�߰� ���� ����
//		listview.setOnItemClickListener(mItemClickListener);
		 
//		Class_info cl1 = new Class_info("","");
//		classInfo.add(cl1);
//		addLayout(); //�ʱ��
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_add, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_ok1:
			openOK();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void openOK() {
		// TODO Auto-generated method stub
	
		onBackPressed();
	}
	
	/**
	 * ���г� ���б� �Է��ϴ� �Լ�
	 */
	public void addLayout(){		
		
/*		EditText editText1=(EditText)findViewById(R.id.editText1);
		EditText editText2=(EditText)findViewById(R.id.editText2); */	
		
		EditText editText1=(EditText)findViewById(R.id.editYear);
		EditText editText2=(EditText)findViewById(R.id.editSemester);
		EditText editText3=(EditText)findViewById(R.id.editClass);
		ToggleButton editMajor=(ToggleButton)findViewById(R.id.button_MajorOr);
		
		String inputValue1=editText1.getText().toString();
		String inputValue2=editText2.getText().toString();
		String inputValue3=editText3.getText().toString();
		boolean inputValue4=editMajor.isChecked();
		
		refresh(inputValue1,inputValue2,inputValue3,inputValue4);
		editText1.setText("");
		editText2.setText("");
		editText3.setText("");
		editMajor.setChecked(true);
		
	}

	private void refresh(String inputValue1, String inputValue2,String inputValue3,boolean inputValue4) {
		
		//if(classInfo.size()>1){
//			int firstPos=listview.getFirstVisiblePosition();
		
/*		for(int position=firstPos;position<=firstPos+classInfo.size()-1;position++){
		View clickedView=(View)listview.getAdapter().getView(position,null,listview);

		EditText edit1=(EditText)clickedView.findViewById(R.id.textYear);
	

		String edit1_changed=edit1.getText().toString();
		Class_info cl2 = new Class_info(edit1_changed,"1");
		//��̸���Ʈ ���� ����
		classInfo.set(position, cl2);
		}*/
			//classInfo = new ArrayList<Class_info>();
		/*	System.out.println(listview.getChildCount());
			for(int position=0;position<classInfo.size()-1;position++){
				System.out.println("i;m here");
				View clickedView=(View)listview.getChildAt(position);
				
				System.out.println(position);
				String edit1_changed="";
				String edit2_changed="";
				String edit3_changed="";
				boolean edit4_changed=true;
				if(null==clickedView)
					Log.i("DEMO_TAG", position+"//"+classInfo.size());
				EditText edit1=(EditText)clickedView.findViewById(R.id.textYear);
				EditText edit2=(EditText)clickedView.findViewById(R.id.textSemester);
				EditText edit3=(EditText)clickedView.findViewById(R.id.textClass);
				ToggleButton edit4=(ToggleButton)clickedView.findViewById(R.id.button_major);

				if(null!=edit1)
					edit1_changed=edit1.getText().toString();
				if(null!=edit2)
					edit2_changed=edit2.getText().toString();
				if(null!=edit3)
					edit3_changed=edit3.getText().toString();
				if(null!=edit4)
				edit4_changed=edit4.isChecked();
				Class_info cl2 = new Class_info(edit1_changed,edit2_changed,edit3_changed,edit4_changed);
				//��̸���Ʈ ���� ����
				classInfo.set(position,cl2);
				
			
				}
		//}
*/		
		// TODO Auto-generated method stub
		Class_info cl1 = new Class_info(inputValue1,inputValue2,inputValue3,inputValue4);
		this.classInfo.add(cl1);
				
				
		adap.notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.addButton:
			addLayout();
			break;
		case R.id.delButton:
			Toast.makeText(getApplicationContext(), "delete", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}

/*	AdapterView.OnItemClickListener mItemClickListener=new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
			View clickedView=(View)listview.getAdapter().getItem(position);
			EditText edit1=(EditText)clickedView.findViewById(R.id.textYear);
	//		EditText edit1=(EditText)findViewById(R.id.editYear);
			String edit1_changed=edit1.getText().toString();
			Class_info cl1 = new Class_info(edit1_changed,"1");
			//��̸���Ʈ ���� ����
			classInfo.set(position, cl1);
			// TODO Auto-generated method stub
			
		}
	};*/
}
