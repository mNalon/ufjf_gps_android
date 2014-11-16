package br.ufjf.gpsfleet.adapters;

import br.ufjf.gpsfleet.Chat;
import br.ufjf.gpsfleet.Search;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class TabsPagerAdapter extends FragmentPagerAdapter {
	
	private final String tabs[];
	private final int numberOfItems;
 
    public TabsPagerAdapter(FragmentManager fm,String tabs[]) {
        super(fm);
        this.tabs = tabs;
        this.numberOfItems = this.tabs.length;
        
    }
 
    @Override
    public Fragment getItem(int index) {
        return getFragment(this.tabs[index]);
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return numberOfItems;
    }
    
    
    
    
    private Fragment getFragment(String name){
    	if(name.equals("Busca")){
    		return new Search();
    	}else{
    		if(name.equals("Chat")){
    			return new Chat();
    		}
    	}
    	return null;
    }
 
}
