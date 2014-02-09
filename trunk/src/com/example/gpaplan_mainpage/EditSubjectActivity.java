/**
 * 
 */
package com.example.gpaplan_mainpage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author User
 *
 */
public class EditSubjectActivity extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View footView = getLayoutInflater().inflate(R.layout.button_view, null);
		Button add_button = (Button) footView.findViewById(R.id.addButton);
		Button del_button = (Button) footView.findViewById(R.id.delButton);
		add_button.setVisibility(View.INVISIBLE);
		del_button.setVisibility(View.INVISIBLE);
		this.setTitle("EditSubjectActivity");
		setContentView(footView);	
		
	}

}
