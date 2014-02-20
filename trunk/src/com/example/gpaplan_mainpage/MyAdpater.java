package com.example.gpaplan_mainpage;

import java.util.ArrayList;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;


public class MyAdpater extends BaseAdapter {
	
	private Context c;
	private LayoutInflater inflater;
	
	public ArrayList<Class_info> Class_info_array; 
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
			convertView = inflater.inflate(R.layout.item_view,null);
		//}
	
			EditText edit_Grade = (EditText)convertView.findViewById(R.id.editGrade);
			EditText edit_Credit = (EditText)convertView.findViewById(R.id.editCredit);
			EditText edit_Subject=(EditText)convertView.findViewById(R.id.editSubject);
			ToggleButton edit_major=(ToggleButton)convertView.findViewById(R.id.button_MajorOr);
			
			Class_info ci = this.Class_info_array.get(position);
			edit_Grade.setText(ci.getGrade());
			edit_Credit.setText(ci.getCredit());
			edit_Subject.setText(ci.getSubject());
			
			edit_major.setChecked(ci.isMajor());
			

			edit_Grade.addTextChangedListener(new TextWacherImpl2(pos, TextWacherImpl.Grade));
			edit_Credit.addTextChangedListener(new TextWacherImpl2(pos,TextWacherImpl.Credit));
			edit_Subject.addTextChangedListener(new TextWacherImpl2(pos,TextWacherImpl.Subject));
			edit_major.setOnCheckedChangeListener(new ButtonCheakImpl2(pos));
			
			
			
		
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
		
		static final int Grade = 0;
		static final int Credit = 1;
		static final int Subject = 2;
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
			case Grade:
				ci.setGrade(s.toString());
				break;
			case Credit:
				ci.setCredit(s.toString());
				break;
			case Subject:
				ci.setSubject(s.toString());
				break;
			default:
				break;
			}
		}
		
	}

	class ButtonCheakImpl implements CompoundButton.OnCheckedChangeListener{

		Class_info ci;
		
		public ButtonCheakImpl(Class_info ci){
			this.ci=ci;
		}
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			ci.setMajor(isChecked);
		}
		
	}

	class TextWacherImpl2 implements TextWatcher{

		int position;
		int type;
		
		static final int Grade = 0;
		static final int Credit = 1;
		static final int Subject = 2;
		static final int MAJOR = 3;
		
		public TextWacherImpl2(int position, int type){
			this.position = position;
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
			case Grade:
				Class_info_array.get(position).setGrade(s.toString());
				break;
			case Credit:
				Class_info_array.get(position).setCredit(s.toString());
				break;
			case Subject:
				Class_info_array.get(position).setSubject(s.toString());
				break;
			default:
				break;
			}
		}
		
	}

	class ButtonCheakImpl2 implements CompoundButton.OnCheckedChangeListener{

		int pos;
		
		public ButtonCheakImpl2(int pos){
			this.pos=pos;
		}
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			Class_info_array.get(pos).setMajor(isChecked);
		}
		
	}
}
