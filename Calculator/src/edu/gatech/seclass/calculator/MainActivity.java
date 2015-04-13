package edu.gatech.seclass.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	
	public String firstOperands;
	public String secondOperands;
	public String output;
	public String operator;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void calculator(View view){
		
		
		EditText txt = (EditText) findViewById(R.id.text1);
		
		switch(view.getId()){
		
		case R.id.button3:
			
			if(txt.getText().toString().equals("Error")){
				
				txt.setText("9");
				break;
			}
			
			else{
				txt.setText(txt.getText().toString() + "9");
			    break;}
		case R.id.button2:
			
			if(txt.getText().toString().equals("Error")){
				
				txt.setText("8");
				break;
			}
			
			else{
				txt.setText(txt.getText().toString() + "8");
				break;}
			
		case R.id.button1:
			
			if(txt.getText().toString().equals("Error")){
				
				txt.setText("7");
				break;
			}
			
			else{
				txt.setText(txt.getText().toString() + "7");
				break;}
			
		case R.id.button4:
			
			if(txt.getText().toString().equals("Error")){
				
				txt.setText("Error");
				break;}
			
		   else if(operator != null&&operator.length()!=0){	
				txt.setText("Error");			
				firstOperands = null;
				operator = null;
				secondOperands = null;
				break;
			}
				
			
			else if ("".equals(txt.getText().toString().trim()))
			{
				txt.setText("Error");							
				firstOperands = null;
				operator =null;
				secondOperands = null;
				break;
			}
			else{
				firstOperands = txt.getText().toString();
				operator = "+";
				txt.setText(null);
				break;}
		

		case R.id.button5:

			if(txt.getText().toString().equals("Error")){
				
				txt.setText("4");
				break;
			}			
			else {
				txt.setText(txt.getText().toString() + "4");
				break;}

		case R.id.button6:

			if(txt.getText().toString().equals("Error")){
				
				txt.setText("5");
				break;
			}	
			else{
				txt.setText(txt.getText().toString() + "5");
				break;}
			
		case R.id.button7:
			if(txt.getText().toString().equals("Error")){
				
				txt.setText("6");
				break;
			}				
			else{ 
				txt.setText(txt.getText().toString() + "6");
				break;}
			
		case R.id.button8:
			if(txt.getText().toString().equals("Error")){
				
				txt.setText("Error");
				break;}
			
		   else if(operator != null&&operator.length()!=0){	
				txt.setText("Error");			
				firstOperands = null;
				operator = null;
				secondOperands = null;
				break;
			}
				
			
			else if ("".equals(txt.getText().toString().trim()))
			{
				txt.setText("Error");							
				firstOperands = null;
				operator =null;
				secondOperands = null;
				break;}
			else{
				firstOperands = txt.getText().toString();
				operator ="-";
				txt.setText(null);
				break;}
			
		case R.id.button9:
			if(txt.getText().toString().equals("Error")){
				
				txt.setText("1");
				break;
			}
			else{
				txt.setText(txt.getText().toString() + "1");
				break;}
			
		case R.id.button10:
			if(txt.getText().toString().equals("Error")){
				
				txt.setText("2");
				break;
			}				
			else{
				txt.setText(txt.getText().toString() + "2");
				break;}
			
		case R.id.button11:
			
			if(txt.getText().toString().equals("Error")){
				
				txt.setText("3");
				break;
			}				
			else{
				txt.setText(txt.getText().toString() + "3");
			
				break;}	
			
		case R.id.button12:
			
			if(txt.getText().toString().equals("Error")){
				
				txt.setText("Error");
				break;}
			
		   else if(operator != null&&operator.length()!=0){	
				txt.setText("Error");			
				firstOperands = null;
				operator = null;
				secondOperands = null;
				break;
			}
				
			
			else if ("".equals(txt.getText().toString().trim()))
			{
				txt.setText("Error");							
				firstOperands = null;
				operator =null;
				secondOperands = null;
				break;}
			else{
				firstOperands = txt.getText().toString();
				operator ="*";
				txt.setText(null);
				break;}

		case R.id.button13:
			
			if(txt.getText().toString().equals("Error")){
				
				txt.setText("0");
				break;
			}				
			else {
				txt.setText(txt.getText().toString() + "0");
			
				break;}
			
		case R.id.button14:
			txt.setText("");			
			firstOperands = null;
			operator =null;
			secondOperands = null;
			break;
			
	
		case R.id.button15:

			if(txt.getText().toString().equals("Error")){
				
				txt.setText("Error");
				break;}
						
			
			else if (firstOperands == null || operator == null)
			{
				txt.setText("Error");			
				firstOperands = null;
				operator =null;
				secondOperands = null;
			}
			else{

				if ("".equals(txt.getText().toString().trim())){
					txt.setText("Error");			
					firstOperands = null;
					operator =null;
					secondOperands = null;
					
				}
				
				else{
					
					secondOperands = txt.getText().toString();
					double result;
				
					if (operator.equals( "+")) {
					result = Double.parseDouble(firstOperands)+Double.parseDouble(secondOperands);
						txt.setText(Double.toString(result));
						firstOperands = null;
						secondOperands = null;
						operator = null;}
									
				
					else if (operator.equals("-")){
						result = Double.parseDouble(firstOperands) - Double.parseDouble(secondOperands);
						txt.setText(Double.toString(result));
						firstOperands = null;
						secondOperands = null;
						operator = null;}
						
						
					else if (operator.equals("*")){
						result = Double.parseDouble(firstOperands) * Double.parseDouble(secondOperands);
						txt.setText(Double.toString(result));
						firstOperands = null;
						secondOperands = null;
						operator = null;}		
						
					
		
		}	}			
		
		}


		
	}
}

