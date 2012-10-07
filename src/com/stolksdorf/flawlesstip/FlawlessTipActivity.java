package com.stolksdorf.flawlesstip;

import android.R.string;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class FlawlessTipActivity extends Activity {
	
	
	EditText cost;
	Spinner tip_spinner;
	Spinner amount_spinner;
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		
		//Cost Text Box
		cost = (EditText) findViewById(R.id.cost);
		cost.addTextChangedListener(new TextWatcher(){
			@Override
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
			@Override
			public void afterTextChanged(Editable arg0) {
				updateLayout();
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
	    });
		
		
		//Tip Spinner
		tip_spinner = (Spinner) findViewById(R.id.tip_percentage);
		ArrayAdapter<CharSequence> tipAdapter = ArrayAdapter.createFromResource(this,
		         R.array.tip_array, android.R.layout.simple_spinner_item);
		tipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tip_spinner.setAdapter(tipAdapter);	     
		tip_spinner.setSelection(1);
		tip_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onNothingSelected(AdapterView<?> parent) {}
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				updateLayout();				
			}
		});
		
		
		amount_spinner = (Spinner) findViewById(R.id.round_amount);
		ArrayAdapter<CharSequence> roundAdapter = ArrayAdapter.createFromResource(this,
		         R.array.round_array, android.R.layout.simple_spinner_item);
		roundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		amount_spinner.setAdapter(roundAdapter);
		amount_spinner.setSelection(1);
	}
	
	
	public float getCost(){
		return Float.parseFloat(cost.getText().toString());
	}
	
	
	public float getTipPercentage(){
		Resources res = getResources();
		String[] sizes = res.getStringArray(R.array.tipValue_array);
		return Float.parseFloat(sizes[tip_spinner.getSelectedItemPosition()]);
	}
		
	public float getRoundAmount(){
		return Float.parseFloat(amount_spinner.getSelectedItem().toString());
	}
	
	
	
	public void updateLayout(){
		String result = "";
		try{ 
			result = Float.toString(getRoundAmount());
		}catch(Exception E){
			result = "nope";
		}		
		
		
		Log.d("FlawlessTip", result);
		
	}
}