package com.stolksdorf.flawlesstip;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FlawlessTipActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        
        Spinner tip_spinner = (Spinner) findViewById(R.id.tip_percentage);
	    ArrayAdapter<CharSequence> tipAdapter = ArrayAdapter.createFromResource(this,
	             R.array.tip_array, android.R.layout.simple_spinner_item);
	    tipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    tip_spinner.setAdapter(tipAdapter);	     
	    tip_spinner.setSelection(1);
        
	    Spinner amount_spinner = (Spinner) findViewById(R.id.round_amount);
	    ArrayAdapter<CharSequence> roundAdapter = ArrayAdapter.createFromResource(this,
	             R.array.round_array, android.R.layout.simple_spinner_item);
	    roundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    amount_spinner.setAdapter(roundAdapter);	     
	    amount_spinner.setSelection(1);
	    
    }
}