package br.ufjf.gpsfleet.listeners;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

public class ActionBarListener implements ActionBar.TabListener{
	
	private ViewPager viewPager;
	
	public ActionBarListener(ViewPager vp){
		this.viewPager = vp;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

}
