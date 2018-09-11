package com.shakil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import com.shakil.R;

public class FactultyActivity extends ListActivity {
	public EditText fname;
	public EditText fpass;
	public EditText fcode;
	public String fcname;
	public String fcpass;
	public String fccode;
	public ListAdapter adapter;
	DBAdapter db;
	Cursor c;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        fname=(EditText)findViewById(R.id.Tname);
        fpass=(EditText)findViewById(R.id.Password);
        fcode=(EditText)findViewById(R.id.Code);
        db = new DBAdapter(this);
        try
        {
        	db.open();
        	c=db.getAllRecord1();
        	adapter = new SimpleCursorAdapter(
        			this, 
        			R.layout.display2, 
        			c, 
        			new String[] {"Fname", "Code"}, 
        			new int[] {R.id.Fname,R.id.CODE});
        	        setListAdapter(adapter);
        			db.close();
        }catch (Exception e) {
        	Toast.makeText(getApplicationContext(), "Could not retrieve Details", Toast.LENGTH_LONG).show();
		}
        
	}

	public void onListItemClick(ListView parent, View view, int position, long id) {
    	
		 AlertDialog.Builder alertDialog = new AlertDialog.Builder(FactultyActivity.this);

		 		
		 		alertDialog.setTitle("Delete Student Details");

		 		
		 		alertDialog.setMessage("Are you sure you want delete this Detail?");

		 		
		 		
		 		final Cursor cursor = (Cursor) adapter.getItem(position);
		 		alertDialog.setPositiveButton("YES",
		 				new DialogInterface.OnClickListener() {
		 					public void onClick(DialogInterface dialog,int which) {
		 						
		 				try{
		 					db.open();
		 					int id=cursor.getInt(cursor.getColumnIndex("_id"));
		 					db.deleteFacultyRecord(id);
		 					if(cursor.moveToFirst())
		 					{
		 						String str=cursor.getString(cursor.getColumnIndexOrThrow("Code"));
		 						db.deleteFacultyRecord1(str);
		 					}
		 					
		 					if(cursor.moveToFirst())
		 					{
		 						String str2=cursor.getString(cursor.getColumnIndexOrThrow("Code"));
		 						db.deleteFacultyRecord2(str2);
		 					}
		 					
		 					db.close();
		 				}
		 				catch(Exception e)
		 				{
		 					Toast.makeText(getApplicationContext(), "Could not delete  the Record", Toast.LENGTH_LONG).show();
		 					return;
		 				}
		 				Toast.makeText(getApplicationContext(), "Record Deleted", Toast.LENGTH_LONG).show(); 
		 				
		 			
		 				
		 				
		 					   Intent i=new Intent(FactultyActivity.this,FactultyActivity.class);
		 					  finish();
		 				   startActivity(i);
		 					}
		  			});
		 	
		 		alertDialog.setNegativeButton("NO",
		 				new DialogInterface.OnClickListener() {
		 					public void onClick(DialogInterface dialog,	int which) {
		 						dialog.cancel();
		 						
		 					}
		 				});

		 	
		 		alertDialog.show();
	}
	public void home_start(View v)
    {
		fcname=fname.getText().toString();
		fcpass=fpass.getText().toString();
		fccode=fcode.getText().toString();
	
		try
		{
			db.open();
			
			c=db.getRecordByName(fccode);
			
				if(c.getCount()!=0)
				{
					Toast.makeText(getApplicationContext(), "This Subject is taken by other Teacher", Toast.LENGTH_LONG).show();
					db.close();
					return ;	
				}
				db.close();
		}catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), "Exception Occured", Toast.LENGTH_LONG).show();
			return;
		}
		if(fcname.equals("")||fcpass.equals("")||fccode.equals(""))
		{
			if(fcname.equals(""))
			{
			Toast.makeText(getApplicationContext(), "Pls Fill in the Faculty Name!", Toast.LENGTH_LONG).show();
			return;
			}
			else  if(fcpass.equals(""))
			{	
				Toast.makeText(getApplicationContext(), "Pls Fill in the Password!", Toast.LENGTH_LONG).show();
				return ;
			}
			else  if(fccode.equals(""))
			{	
				Toast.makeText(getApplicationContext(), "Pls Fill in the subject Code!", Toast.LENGTH_LONG).show();
				return;
			}
		}
		else
		{
			
			try
			{
			
	          	db.open();        
	           db.insertRecord(
	          		fcname,
	          		fcpass,
	          		fccode
	          		); 
	           db.insertClass(fccode, 0);
	          	db.close();
			}catch(Exception e)
			{
					Toast.makeText(getApplicationContext(), "Unable to Insert Pls Try again!!",Toast.LENGTH_LONG).show();
					
			}
     }
		Intent i=new Intent(this,Admin_homeActivity.class);
		finish();
        startActivity(i);
   }
    
}
