package br.ufjf.gpsfleet;

import android.support.v4.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Search extends Fragment{
	WebView webview;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.search, container, false);
        webview = (WebView) rootView.findViewById(R.id.main_webview);
        webview.setWebViewClient(new WebViewClientUfjfGps());
        setupWebView();
        
        webview.loadUrl(getString(R.string.url_load_fleet_monitoring_site));
        
//        String summary = "<html><body>Serviço indisponível</body></html>";
//        webview.loadData(summary, "text/html", null);
         
        return rootView;
    }
	
	private void setupWebView(){
		WebSettings ws = webview.getSettings();
		ws.setJavaScriptEnabled(true);
		ws.setUseWideViewPort(false);
		ws.setLoadWithOverviewMode(true);
		webview.setLongClickable(true);
		
//		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//			webview.setWebContentsDebuggingEnabled(true);
//		}
		
		
	}
	
	public void injectJavascript(){
		String javascript = "document.getElementsByTagName(\"body\")[0].style.width = \"100%\";"+
							"document.getElementsByTagName(\"html\")[0].style.width = \"100%\";"+
							//"document.getElementById(\"container\").style.width = \"100%\";"+
							"document.getElementById(\"content\").style.width= \"inherit\";"+
							"document.getElementById(\"container\").style.margin = \"0\";"+
							"document.querySelectorAll(\"#content h2\")[0].style.width = \"100%\";"+
							"document.querySelectorAll(\"#navigation ul\")[0].style.visibility = \"hidden\";"
//							"document.getElementById(\"navigation\").style.fontSize = \"-webkit-xxx-large\";"+
//							"document.getElementById(\"content\").style.width = \"100%\";"+
//							"document.getElementById(\"content\").style.fontSize = \"-webkit-xxx-large\";"
							;
							
		webview.loadUrl("javascript:"+javascript);
	}
	
	class WebViewClientUfjfGps extends WebViewClient{
	    @Override
	    public void onPageFinished(WebView view, String url) {
	    	// TODO Auto-generated method stub
	    	
	    	
	    	super.onPageFinished(view, url);
	    	injectJavascript();
	    }
	}

}
