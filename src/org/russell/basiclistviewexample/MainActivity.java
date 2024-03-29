package org.russell.basiclistviewexample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class MainActivity extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initList();
		
		// We get the ListView component from the layout
		ListView lv = (ListView) findViewById(R.id.listView);
		
		// This is a simple adapter that accepts as parameter
		// Context
		// Data list
		// The row layout that is used during row creation
		// The keys used to retrieve 6the data
		// The View id used to show the data.  The key number and the view id must match
		SimpleAdapter simpleAdpt = new SimpleAdapter(this,
				planetsList, 
				android.R.layout.simple_list_item_1, 
				new String[] {"planet"}, 
				new int[] {android.R.id.text1});
		
		lv.setAdapter(simpleAdpt);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id){
				
				// We know the View is a TextView so we can cast it
				TextView clickedView = (TextView) view;
				
				Toast.makeText(MainActivity.this, "Item with id ["+id+"] - Position ["+position+"] - Planet ["+clickedView.getText()+"]", Toast.LENGTH_SHORT).show();
				
			}
		});
	
		// We want to create a context Menu when the user long click on an item
		@Override
		public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
			super.onCreateContextMenu(menu, v, menuInfo);
			AdapterContextMenuInfo aInfo = (AdapterContextMenuInfo) menuInfo;
			
			// We know that each row in the adapter is a Map
			HashMap map = (HashMap) simpleAdpt.getItem(aInfo.position);
			menu.setHeaderTitle("Options for " + map.get("planet"));
			menu.add(1, 1, 1, "Details");
			menu.add(1, 2, 2, "Delete");
			
		}
		
		registerForContextMenu(lv);
		
	}
	
	// The data to show
	List<Map<String,String>> planetsList = new ArrayList<Map<String,String>>();
	
	private void initList() {
		// We populate the planets
		
		planetsList.add(createPlanet("planet", "Mercury"));
		planetsList.add(createPlanet("planet", "Venus"));
		planetsList.add(createPlanet("planet", "Mars"));
		planetsList.add(createPlanet("planet", "Jupiter"));
		planetsList.add(createPlanet("planet", "Saturn"));
		planetsList.add(createPlanet("planet", "Uranus"));
		planetsList.add(createPlanet("planet", "Neptune"));
	}
	private HashMap<String, String> createPlanet(String key, String name) {
		HashMap<String, String> planet = new HashMap<String, String>();
		planet.put(key, name);
		
		return planet;
	}
	
	
	
}
