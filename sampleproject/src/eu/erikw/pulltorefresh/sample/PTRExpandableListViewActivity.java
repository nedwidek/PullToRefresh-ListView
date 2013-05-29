package eu.erikw.pulltorefresh.sample;

import java.util.ArrayList;

import eu.erikw.PullToRefreshExpandableListView;
import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshHelper.OnRefreshListener;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class PTRExpandableListViewActivity extends Activity implements OnGroupClickListener, OnChildClickListener {
	
	
	
	private PullToRefreshExpandableListView listView;
    private MyAdapter adapter;
    
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pull_to_refresh_expandable_list_view);
		
		listView = (PullToRefreshExpandableListView) findViewById(R.id.pullToRefreshExpandableListView);

        // OPTIONAL: Disable scrolling when list is refreshing
        // listView.setLockScrollWhileRefreshing(false);

        // OPTIONAL: Uncomment this if you want the Pull to Refresh header to show the 'last updated' time
        // listView.setShowLastUpdatedText(true);

        // OPTIONAL: Uncomment this if you want to override the date/time format of the 'last updated' field
        // listView.setLastUpdatedDateFormat(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));

        // OPTIONAL: Uncomment this if you want to override the default strings
        // listView.setTextPullToRefresh("Pull to Refresh");
        // listView.setTextReleaseToRefresh("Release to Refresh");
        // listView.setTextRefreshing("Refreshing");

        // MANDATORY: Set the onRefreshListener on the list. You could also use
        // listView.setOnRefreshListener(this); and let this Activity
        // implement OnRefreshListener.
        listView.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// Your code to refresh the list contents goes here

				// Make sure you call listView.onRefreshComplete()
				// when the loading is done. This can be done from here or any
				// other place, like on a broadcast receive from your loading
				// service or the onPostExecute of your AsyncTask.

				// For the sake of this sample, the code will pause here to
				// force a delay when invoking the refresh
				listView.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						listView.onRefreshComplete();
					}
				}, 2000);
			}
		});
       
        adapter = new MyAdapter(this);
        listView.setAdapter(adapter);

        listView.setOnGroupClickListener(this);
        listView.setOnChildClickListener(this);
	}
	
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		Toast.makeText(this, adapter.getChild(groupPosition, childPosition), Toast.LENGTH_SHORT).show();
		return true;
	}

	@Override
	public boolean onGroupClick(ExpandableListView parent, View v,
			int groupPosition, long id) {
		Toast.makeText(this, adapter.getGroup(groupPosition), Toast.LENGTH_SHORT).show();
		return false;
	}
	
	private class MyAdapter extends BaseExpandableListAdapter {
		
		private ArrayList<String> groups;
		private ArrayList<ArrayList<String>> children;
		private Context ctx;
		
		public MyAdapter(Context ctx) {
			
			this.ctx = ctx;
			
			groups = new ArrayList<String>();
			children = new ArrayList<ArrayList<String>>();
			
			ArrayList<String> child = new ArrayList<String>();
			child.add("C");
			child.add("C++");
			child.add("Java");
			child.add("Perl");
			child.add("PHP");
			children.add(child);
			groups.add("Programming");
			
			child = new ArrayList<String>();
			child.add("Android");
			child.add("Blackberry");
			child.add("iPhone");
			child.add("Windows Phone");
			children.add(child);
			groups.add("Mobile OS");
			
			child = new ArrayList<String>();
			child.add("Apple");
			child.add("Asus");
			child.add("HTC");
			child.add("Nokia");
			child.add("Samsung");
			children.add(child);
			groups.add("Manufacturers");
			
			child = new ArrayList<String>();
			child.add("Arabic");
			child.add("English");
			child.add("German");
			child.add("Japanese");
			child.add("Spanish");
			children.add(child);
			groups.add("Languages");
		}
		
		@Override
		public String getChild(int groupPosition, int childPosition) {
			return children.get(groupPosition).get(childPosition);
		}
		
		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}
		
		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
			}
			
			TextView childTextView = (TextView) convertView.findViewById(android.R.id.text1);
			childTextView.setTextColor(Color.BLACK);
			childTextView.setText(children.get(groupPosition).get(childPosition));
			
			return convertView;
		}
		
		@Override
		public int getChildrenCount(int groupPosition) {
			return children.get(groupPosition).size();
		}
		
		@Override
		public String getGroup(int groupPosition) {
			return groups.get(groupPosition);
		}
		
		@Override
		public int getGroupCount() {
			return groups.size();
		}
		
		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}
		
		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
			}
			
			TextView groupTextView = (TextView) convertView.findViewById(android.R.id.text1);
			groupTextView.setTextColor(Color.BLACK);
			groupTextView.setText(groups.get(groupPosition));
			
			return convertView;
		}
		
		@Override
		public boolean hasStableIds() {
			return true;
		}
		
		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}
}
