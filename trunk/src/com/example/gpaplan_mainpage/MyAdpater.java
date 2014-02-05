package com.example.gpaplan_mainpage;

import java.util.ArrayList;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ToggleButton;


public class MyAdpater extends BaseAdapter {
	
	private Context c;
	private LayoutInflater inflater;
	
	private ArrayList<Class_info> Class_info_array; 
	public MyAdpater(Context c,ArrayList<Class_info> Class_info_Array){
		this.c =c;
	
		this.Class_info_array = Class_info_Array;
		this.inflater = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
	
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Class_info_array.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return Class_info_array.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final int pos = position;	
		//if(convertView == null){
			convertView = inflater.inflate(R.layout.item_view,parent, false);
		//}
		
		EditText edit_year = (EditText)convertView.findViewById(R.id.textYear);
		EditText edit_semester = (EditText)convertView.findViewById(R.id.textSemester);
		EditText edit_class=(EditText)convertView.findViewById(R.id.textClass);
		ToggleButton edit_major=(ToggleButton)convertView.findViewById(R.id.button_major);
		
		Class_info ci = this.Class_info_array.get(position);
		edit_year.setText(ci.getYear());
		edit_semester.setText(ci.getSemester());
		edit_class.setText(ci.getClassName());
		
		edit_major.setChecked(ci.getMajorOrNot());
		

		edit_year.addTextChangedListener(new TextWacherImpl(ci, TextWacherImpl.YEAR));
		edit_semester.addTextChangedListener(new TextWacherImpl(ci,TextWacherImpl.SEMASTER));
		edit_class.addTextChangedListener(new TextWacherImpl(ci,TextWacherImpl.CLASS_NAME));
		
		
		
/*		TextView textYear = (TextView)convertView.findViewById(R.id.textYear);
		TextView textSemester = (TextView)convertView.findViewById(R.id.textSemester);
		textYear.setText(this.Class_info_array.get(position).getYear());
		textSemester.setText(this.Class_info_array.get(position).getSemester());*/
		return convertView;
	}
	
	/**
	 * �ؽ�Ʈ �Է� ��ȭ�� üũ�ϴ� Ŭ����
	 * @author JI HYUN
	 *
	 */
	class TextWacherImpl implements TextWatcher{

		Class_info ci;
		int type;
		
		static final int YEAR = 0;
		static final int SEMASTER = 1;
		static final int CLASS_NAME = 2;
		static final int MAJOR = 3;
		
		public TextWacherImpl(Class_info ci, int type){
			this.ci = ci;
			this.type = type;
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			switch (type) {
			case YEAR:
				System.out.println(ci.getYear());
				ci.setYear(s.toString());
				break;
			case SEMASTER:
				ci.setSemester(s.toString());
			case CLASS_NAME:
				ci.setClassName(s.toString());
			
			default:
				break;
			}
		}
		
	}

}
