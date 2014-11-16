package br.ufjf.gpsfleet.listeners;

import android.app.ActionBar;
import android.support.v4.view.ViewPager;

public class PageViewChangeListener implements ViewPager.OnPageChangeListener {
	
	private ActionBar actionBar;
	
	public PageViewChangeListener(ActionBar ab) {
		this.actionBar = ab;
	}

	@Override
    public void onPageSelected(int position) {
        // on changing the page
        // make respected tab selected
        actionBar.setSelectedNavigationItem(position);
    }
 
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }
 
    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

}
