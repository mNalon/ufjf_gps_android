package br.ufjf.gpsfleet;


import br.ufjf.gpsfleet.adapters.TabsPagerAdapter;
import br.ufjf.gpsfleet.listeners.ActionBarListener;
import br.ufjf.gpsfleet.listeners.PageViewChangeListener;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;
import android.app.ActionBar.Tab;

@SuppressLint("ShowToast")
public class MainActivity extends FragmentActivity {
	
	private final String[] tabs = {"Busca","Chat"};
	ActionBar actionBar;
	ViewPager viewPager;
	private TabsPagerAdapter fragmentAdapter;
	private boolean exitEnabled = false;
	public static Toast toast;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actionBar = getActionBar();
		
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		fragmentAdapter = new TabsPagerAdapter(getSupportFragmentManager(), this.tabs);
		
		viewPager.setAdapter(fragmentAdapter);
		viewPager.setOnPageChangeListener(new PageViewChangeListener(actionBar));
		
		toast = Toast.makeText(this, "Se deseja realmente sair do aplicativo pressione o botão de voltar novamente", Toast.LENGTH_SHORT);
		this.createTabs();
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void createTabs(){
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.TabListener tabListener = new ActionBarListener(this.viewPager);
		for (String tab : this.tabs){
			Tab objectTab = actionBar.newTab().setText(tab).setTabListener(tabListener); 
			actionBar.addTab(objectTab);
		}
	}
	
	@Override
	public void onBackPressed() {
		if(exitEnabled){
			toast.cancel();
			super.onBackPressed();
		}else{
			exitEnabled = true;
			toast.show();
			Thread disableExitThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(2000);
						exitEnabled = false;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			disableExitThread.start();
		}
		
	}
	
	
	
}
