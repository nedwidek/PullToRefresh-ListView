package eu.erikw.pulltorefresh.sample;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;
import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshHelper.OnRefreshListener;

import java.util.ArrayList;

public class PullToRefreshListViewSampleActivity extends TabActivity{
    
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TabHost tabHost = getTabHost();
        
        TabSpec listViewTabSpec = tabHost.newTabSpec("ListView");
        listViewTabSpec.setIndicator("Normal List");
        Intent listViewIntent = new Intent(this, PTRListViewActivity.class);
        listViewTabSpec.setContent(listViewIntent);
        tabHost.addTab(listViewTabSpec);
        
        TabSpec expandableListViewTabSpec = tabHost.newTabSpec("ExpandableListView");
        expandableListViewTabSpec.setIndicator("Expandable List");
        Intent expandableListViewIntent = new Intent(this, PTRExpandableListViewActivity.class);
        expandableListViewTabSpec.setContent(expandableListViewIntent);
        tabHost.addTab(expandableListViewTabSpec);
    }
}