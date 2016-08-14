package app.svnit.svnitupdate;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebviewActivity extends Activity {

	private WebView webView;
	ProgressBar myProgress ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final Activity activity = this;
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
	     
		setContentView(R.layout.activity_webview);
		   // get the action bar
        ActionBar actionBar = getActionBar();
 
        // Enabling Back navigation on Action Bar icon
        actionBar.setDisplayHomeAsUpEnabled(true);

		   
		myProgress = (ProgressBar)findViewById(R.id.progressBar1);
		webView = (WebView) findViewById(R.id.webView);
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        
        
        
        webView.setWebChromeClient(new WebChromeClient(){
        	@Override
        	public void onProgressChanged(WebView view, int newProgress) {
        		// TODO Auto-generated method stub
        		super.onProgressChanged(view, newProgress);
        		myProgress.setProgress(newProgress * 100);
        		if (newProgress == 100) {
        			   myProgress.setVisibility(View.GONE);
        			   
        			   } else{
        			   myProgress.setVisibility(View.VISIBLE);
        			   }
        	}
        });
        
	}	
}
