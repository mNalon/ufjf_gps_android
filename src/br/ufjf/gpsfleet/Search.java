package br.ufjf.gpsfleet;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Search extends Fragment{
	WebView webview;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.search, container, false);
        webview = (WebView) rootView.findViewById(R.id.main_webview);
        webview.setWebViewClient(new WebViewClient());
        
        webview.loadUrl("http://www.google.com");
        
//        String summary = "<html><body>Serviço indisponível</body></html>";
//        webview.loadData(summary, "text/html", null);
         
        return rootView;
    }

}
