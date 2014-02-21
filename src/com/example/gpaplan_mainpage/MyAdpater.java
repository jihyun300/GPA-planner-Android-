package com.example.gpaplan_mainpage;

import java.util.ArrayList;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

import android.widget.Toast;

import android.widget.ToggleButton;


public class MyAdpater extends BaseAdapter {
	
	private Context c;
	private LayoutInflater inflater;
	
	private ArrayList<Class_info> Class_info_array; 
	private String[] GradeList=new String[]{"A+","A","A-","B+","B","B-","C+","C","C-","D+","D","D-","F","P","NP"};
	
	
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
			

			NumberPicker edit_Grades = (NumberPicker)convertView.findViewById(R.id.editGrades);
			

			EditText edit_Credit = (EditText)convertView.findViewById(R.id.editCredit);
			EditText edit_Subject=(EditText)convertView.findViewById(R.id.editSubject);
			ToggleButton edit_major=(ToggleButton)convertView.findViewById(R.id.button_MajorOr);
			Button edit_Delete=(Button)convertView.findViewById(R.id.delButton);
			
			edit_Grades.setMinValue(0);
			edit_Grades.setMaxValue(14);
			edit_Grades.setDisplayedValues(GradeList);
			
			Class_info ci = this.Class_info_array.get(position);
			
			int getGradeInt = 0;
			//ci.getGrade가 어떤 숫자일때
			
			for(int i=0;i<GradeList.length;i++){
				if(ci.getGrade().equals(GradeList[i])) getGradeInt=i;
			}
			
			edit_Grades.setValue(getGradeInt);
			edit_Credit.setText(ci.getCredit());
			edit_Subject.setText(ci.getSubject());
			
			edit_major.setChecked(ci.isMajor());
			
			edit_Delete.setTag(position);

			edit_Credit.addTextChangedListener(new TextWacherImpl(ci,TextWacherImpl.Credit));
			edit_Subject.addTextChangedListener(new TextWacherImpl(ci,TextWacherImpl.Subject));
			edit_major.setOnCheckedChangeListener(new ButtonCheakImpl(ci));
			edit_Grades.setOnValueChangedListener(new GradeChangeImpl(ci));
			
	
			

			edit_Delete.setOnClickListener(new View.OnClickListener() {
				
			
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(c,"위치:"+pos, Toast.LENGTH_SHORT).show();
					Class_info_array.remove(pos);
					notifyDataSetChanged();
					
				}
			});
			
			
		
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
	
	class GradeChangeImpl implements NumberPicker.OnValueChangeListener{

		Class_info ci;
		
		public GradeChangeImpl(Class_info ci){
			this.ci=ci;
		}
		@Override
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			// TODO Auto-generated method stub
			ci.setGrade(GradeList[newVal]);
		}
		
	}
}
