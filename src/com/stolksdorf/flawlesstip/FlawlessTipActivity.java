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
import android.widget.TextView;

public class FlawlessTipActivity extends Activity {
	
	EditText cost_textbox;
	Spinner tip_spinner;
	Spinner amount_spinner;
	Spinner direction_spinner;
	
	TextView final_tip_percentage;
	TextView final_cost ;
	TextView final_tip_amount;	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//Get Text Views
		final_tip_percentage = (TextView) findViewById(R.id.final_tip_percentage);
		final_tip_amount= (TextView) findViewById(R.id.final_tip_amount);
		final_cost = (TextView) findViewById(R.id.final_cost);
		
		
		//Cost Text Box
		cost_textbox= (EditText) findViewById(R.id.cost);
		cost_textbox.addTextChangedListener(new TextWatcher(){
			@Override
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
			@Override
			public void afterTextChanged(Editable arg0) {
				updateLayout();
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
	    });
		
		
		//Tip Spinner Events
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
				
		//Amount Spinner Events
		amount_spinner = (Spinner) findViewById(R.id.round_amount);
		ArrayAdapter<CharSequence> roundAdapter = ArrayAdapter.createFromResource(this,
		         R.array.round_array, android.R.layout.simple_spinner_item);
		roundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		amount_spinner.setAdapter(roundAdapter);
		amount_spinner.setSelection(1);
		amount_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onNothingSelected(AdapterView<?> parent) {}
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				updateLayout();				
			}
		});
		 
		//Direction Spinner Events
		direction_spinner = (Spinner) findViewById(R.id.round_direction);
		ArrayAdapter<CharSequence> directionAdapter = ArrayAdapter.createFromResource(this,
		         R.array.roundDirection_array, android.R.layout.simple_spinner_item);
		directionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		direction_spinner.setAdapter(directionAdapter);
		direction_spinner.setSelection(0);
		direction_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onNothingSelected(AdapterView<?> parent) {}
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				updateLayout();				
			}
		});
	}
	
	
	public float getCost(){
		return Float.parseFloat(cost_textbox.getText().toString());
	}	
	
	public float getTipPercentage(){
		Resources res = getResources();
		String[] sizes = res.getStringArray(R.array.tipValue_array);
		return Float.parseFloat(sizes[tip_spinner.getSelectedItemPosition()]);
	}
		
	public float getRoundAmount(){
		return Float.parseFloat(amount_spinner.getSelectedItem().toString());
	}
		
	public float roundTo(float value, float target){
		int choicePos = direction_spinner.getSelectedItemPosition();
		if(choicePos == 1){
			return (float)(Math.ceil(value/ target) * target);
		}else if(choicePos == 2){
			Log.d("FLAWLESSTIP","IN HURR");
			return (float)(Math.floor(value/ target) * target);
		}
		return (float)(Math.round(value/ target) * target);		
	}
	
	
	
	public void updateLayout(){
		String fc = "$0.00";
		String ftp = "0.00%";
		String fta = "$0.00";
		try{ 
			 float roundAmount = getRoundAmount();
			 float tipPercentage = getTipPercentage();
			 float cost = getCost();
			 
			 float totalRounded = roundTo(cost * (1 + tipPercentage), roundAmount);
			 float finalTipPercentage = (totalRounded / cost - 1)*100;
			 float finalTipAmount = totalRounded - cost;
			 
			 fc = "$" + String.format("%.2f",totalRounded);
			 ftp = String.format("%.2f",finalTipPercentage) + "%";
			 fta = "$" + String.format("%.2f",finalTipAmount);		
			
		}catch(Exception E){
			fc = "$0.00";
			ftp = "0.00%";
			fta = "$0.00";
		}		
		
		final_tip_percentage.setText(ftp);
		final_tip_amount.setText(fta);
		final_cost.setText(fc);
		
	}
}