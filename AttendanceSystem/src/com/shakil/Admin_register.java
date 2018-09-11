package com.shakil;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.shakil.R;

public class Admin_register extends Activity {
	public EditText aname;
	public EditText ausn;
	public EditText apass1;
	public EditText apass2;
	public String adname;
	public String adusn;
	public String adpass1;
	public String adpass2;
	public ListAdapter adapter;
	DBAdapter db;
	Cursor c;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.admin_reg);
	        aname=(EditText)findViewById(R.id.AdminName);
	        ausn=(EditText)findViewById(R.id.AdminUsn);
	        apass1=(EditText)findViewById(R.id.AdminPass);
	        apass2=(EditText)findViewById(R.id.AdminPass1);
	        db=new DBAdapter(this); 
	 }
	 
	 public void admin_db(View v)
	    {
		    DBAdapter db = new DBAdapter(this);
			adname=aname.getText().toString();
			adusn=ausn.getText().toString();
			adpass1=apass1.getText().toString();
			adpass2=apass2.getText().toString();
		
			try
			{
				db.open();
				
				c=db.getRecordByAdmin(adusn);
				
					if(c.getCount()!=0)
					{
						Toast.makeText(getApplicationContext(), "This User Name is already exist.", Toast.LENGTH_LONG).show();
						db.close();
						return ;	
					}
					db.close();
			}catch(Exception e)
			{
				Toast.makeText(getApplicationContext(), "Exception Occured", Toast.LENGTH_LONG).show();
				return;
			}
			if(adname.equals("")||adusn.equals("")||adpass1.equals("")||adpass2.equals(""))
			{
				if(adname.equals(""))
				{
				Toast.makeText(getApplicationContext(), "Pls Fill in Admin Name!", Toast.LENGTH_LONG).show();
				return;
				}
				if(adusn.equals(""))
				{
				Toast.makeText(getApplicationContext(), "Pls Fill in User Name!", Toast.LENGTH_LONG).show();
				return;
				}
				else  if(adpass1.equals(""))
				{	
					Toast.makeText(getApplicationContext(), "Pls Fill in the Password!", Toast.LENGTH_LONG).show();
					return ;
				}
				else  if(adpass2.equals(""))
				{	
					Toast.makeText(getApplicationContext(), "Pls repeat the Password!", Toast.LENGTH_LONG).show();
					return;
				}
			}
			else
			{
				
				try
				{
				
		          	db.open();        
		           db.insertAdminRecord(
		          		adname,
		          		adusn,
		          		adpass1,
		          		adpass2
		          		);
		          	db.close();
				}catch(Exception e)
				{
						Toast.makeText(getApplicationContext(), "Unable to Insert Pls Try again!!",Toast.LENGTH_LONG).show();
						
				}
	     }
			Intent i=new Intent(this,Register_adminActivity.class);
			finish();
	        startActivity(i);
	   }
	 

}
