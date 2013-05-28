package eu.erikw;

import java.text.SimpleDateFormat;

import eu.erikw.PullToRefreshHelper.OnRefreshListener;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ExpandableListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class PullToRefreshExpandableListView extends ExpandableListView {
	
	PullToRefreshHelper ptrHelper;
	
	public PullToRefreshExpandableListView(Context context){
        super(context);
        ptrHelper = new PullToRefreshHelper(this);
        ptrHelper.init();
    }

    public PullToRefreshExpandableListView(Context context, AttributeSet attrs){
        super(context, attrs);
        ptrHelper = new PullToRefreshHelper(this);
        ptrHelper.init();
    }

    public PullToRefreshExpandableListView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        ptrHelper = new PullToRefreshHelper(this);
        ptrHelper.init();
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        ptrHelper.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener){
        ptrHelper.setOnItemLongClickListener(getOnItemLongClickListener());
    }

    /**
     * Activate an OnRefreshListener to get notified on 'pull to refresh'
     * events.
     *
     * @param onRefreshListener The OnRefreshListener to get notified
     */
    public void setOnRefreshListener(OnRefreshListener onRefreshListener){
        ptrHelper.setOnRefreshListener(onRefreshListener);
    }

    /**
     * @return If the list is in 'Refreshing' state
     */
    public boolean isRefreshing(){
        return ptrHelper.isRefreshing();
    }

    /**
     * Default is false. When lockScrollWhileRefreshing is set to true, the list
     * cannot scroll when in 'refreshing' mode. It's 'locked' on refreshing.
     *
     * @param lockScrollWhileRefreshing
     */
    public void setLockScrollWhileRefreshing(boolean lockScrollWhileRefreshing){
    	ptrHelper.setLockScrollWhileRefreshing(lockScrollWhileRefreshing);
    }

    /**
     * Default is false. Show the last-updated date/time in the 'Pull ro Refresh'
     * header. See 'setLastUpdatedDateFormat' to set the date/time formatting.
     *
     * @param showLastUpdatedText
     */
    public void setShowLastUpdatedText(boolean showLastUpdatedText){
    	ptrHelper.setShowLastUpdatedText(showLastUpdatedText);
    }

    /**
     * Default: "dd/MM HH:mm". Set the format in which the last-updated
     * date/time is shown. Meaningless if 'showLastUpdatedText == false (default)'.
     * See 'setShowLastUpdatedText'.
     *
     * @param lastUpdatedDateFormat
     */
    public void setLastUpdatedDateFormat(SimpleDateFormat lastUpdatedDateFormat){
    	ptrHelper.setLastUpdatedDateFormat(lastUpdatedDateFormat);
    }

    /**
     * Explicitly set the state to refreshing. This
     * is useful when you want to show the spinner and 'Refreshing' text when
     * the refresh was not triggered by 'pull to refresh', for example on start.
     */
    public void setRefreshing(){
        ptrHelper.setRefreshing();
    }

    /**
     * Set the state back to 'pull to refresh'. Call this method when refreshing
     * the data is finished.
     */
    public void onRefreshComplete(){
        ptrHelper.onRefreshComplete();
    }

    /**
     * Change the label text on state 'Pull to Refresh'
     *
     * @param pullToRefreshText Text
     */
    public void setTextPullToRefresh(String pullToRefreshText){
        ptrHelper.setTextPullToRefresh(pullToRefreshText);
    }

    /**
     * Change the label text on state 'Release to Refresh'
     *
     * @param releaseToRefreshText Text
     */
    public void setTextReleaseToRefresh(String releaseToRefreshText){
        ptrHelper.setTextReleaseToRefresh(releaseToRefreshText);
    }

    /**
     * Change the label text on state 'Refreshing'
     *
     * @param refreshingText Text
     */
    public void setTextRefreshing(String refreshingText){
        ptrHelper.setTextRefreshing(refreshingText);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(ptrHelper.onTouchEvent(event)) {
        	return true;
        }
        
        return super.onTouchEvent(event);
    }
    
    public boolean parentOnTouchEvent(MotionEvent event) {
    	return super.onTouchEvent(event);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt){
    	super.onScrollChanged(l, t, oldl, oldt);
        ptrHelper.onScrollChanged(l, t, oldl, oldt);
    }
}
