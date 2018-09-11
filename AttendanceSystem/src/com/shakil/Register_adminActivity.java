package com.shakil;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shakil.R;

public class Register_adminActivity extends Activity {
	public static EditText ad;
	public static EditText ps1;
	public String adm;
	public String pss1;
	DBAdapter db;
	Cursor c;
	public Button but;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.admin);
	        ad=(EditText)findViewById(R.id.AUsn);
	        ps1=(EditText)findViewById(R.id.AdminPass);
	        but=(Button)findViewById(R.id.sin);
	        db=new DBAdapter(this);
	        
	 }
	 public void admin_reg(View v)
		{
			Intent i=new Intent(this,Admin_register.class);
			finish();
			startActivity(i);
		}
	 
	public void admin_home(View v1)
	{
		DBAdapter db = new DBAdapter(this);
	    adm=ad.getText().toString();
	    pss1=ps1.getText().toString();
			if(adm.equals("")||pss1.equals(""))
			{
				if(adm.equals(""))
				{
				Toast.makeText(getApplicationContext(), "Pls Fill in the Admin name!", Toast.LENGTH_LONG).show();
				}
				else  if(pss1.equals(""))
				{	
					Toast.makeText(getApplicationContext(), "Pls Fill in the Password!", Toast.LENGTH_LONG).show();
				}
			}
			else
			{
				try{
			
					db.open();
					c=db.getRecordByAdmin1(adm);
					if(c.getCount()==0)
					{
						Toast.makeText(getApplicationContext(), "No such Admin exist!!", Toast.LENGTH_LONG).show();
						return ;	
					}
					if(!c.getString(3).equals(pss1))
					{
						Toast.makeText(getApplicationContext(), "Incorrect Password!", Toast.LENGTH_LONG).show();
						return ;
					}
					db.close();
				}
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(), "Could Not Retrieve Data", Toast.LENGTH_LONG).show();
					return ;
				}
				
			Intent i=new Intent(this,Admin_homeActivity.class);
			finish();
	        startActivity(i);
			}
			

	}
	
	
}