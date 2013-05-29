package eu.erikw;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.*;
import android.view.animation.Animation.AnimationListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import eu.erikw.PullToRefreshHelper.OnRefreshListener;

/**
 * A generic, customizable Android ListView implementation that has 'Pull to Refresh' functionality.
 * <p/>
 * This ListView can be used in place of the normal Android android.widget.ListView class.
 * <p/>
 * Users of this class should implement OnRefreshListener and call setOnRefreshListener(..)
 * to get notified on refresh events. The using class should call onRefreshComplete() when
 * refreshing is finished.
 * <p/>
 * The using class can call setRefreshing() to set the state explicitly to refreshing. This
 * is useful when you want to show the spinner and 'Refreshing' text when the
 * refresh was not triggered by 'Pull to Refresh', for example on start.
 * <p/>
 * For more information, visit the project page:
 * https://github.com/erikwt/PullToRefresh-ListView
 *
 * @author Erik Wallentinsen <dev+ptr@erikw.eu>
 * @version 1.0.0
 */
public class PullToRefreshListView extends ListView {

    private PullToRefreshHelper ptrHelper;


    public PullToRefreshListView(Context context){
        super(context);
        ptrHelper = new PullToRefreshHelper(this);
        ptrHelper.init();
        init();
    }

    public PullToRefreshListView(Context context, AttributeSet attrs){
        super(context, attrs);
        ptrHelper = new PullToRefreshHelper(this);
        ptrHelper.init();
        init();
    }

    public PullToRefreshListView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        ptrHelper = new PullToRefreshHelper(this);
        ptrHelper.init();
        init();
    }
    
    private void init() {
    	super.setOnItemClickListener(new PTROnItemClickListener());
    	super.setOnItemLongClickListener(new PTROnItemLongClickListener());
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

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt){
    	super.onScrollChanged(l, t, oldl, oldt);
        ptrHelper.onScrollChanged(l, t, oldl, oldt);
    }
    
    private class PTROnItemClickListener implements OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
            ptrHelper.doItemClick(adapterView, view, position, id);
        }
    }

    private class PTROnItemLongClickListener implements OnItemLongClickListener{

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id){
            return ptrHelper.doItemLongClick(adapterView, view, position, id);
        }
    }
}
