package eu.erikw.pulltorefresh.sample;

import java.util.ArrayList;

import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshHelper.OnRefreshListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class PTRListViewActivity extends Activity implements AdapterView.OnItemClickListener {

	private static ArrayList<String> items;
	static{
		items = new ArrayList<String>();
		
		items.add("Ajax Amsterdam");
		items.add("Barcelona");
		items.add("Manchester United");
		items.add("Chelsea");
		items.add("Real Madrid");
		items.add("Bayern Munchen");
		items.add("Internazionale");
		items.add("Valencia");
		items.add("Arsenal");
		items.add("AS Roma");
		items.add("Tottenham Hotspur");
		items.add("PSV");
		items.add("Olympique Lyon");
		items.add("AC Milan");
		items.add("Dortmund");
		items.add("Schalke 04");
		items.add("Twente");
		items.add("Porto");
		items.add("Juventus");
	}
	
	private PullToRefreshListView listView;
    private ArrayAdapter<String> adapter;
    
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pull_to_refresh_list_view);
		
		listView = (PullToRefreshListView) findViewById(R.id.pull_to_refresh_listview);

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
        
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
	}

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
        Toast.makeText(this, adapter.getItem(i), Toast.LENGTH_SHORT).show();
    }
}
