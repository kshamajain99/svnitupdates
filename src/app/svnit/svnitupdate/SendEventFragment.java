package app.svnit.svnitupdate;

import android.os.Bundle;
import android.webkit.WebViewFragment;

public class SendEventFragment extends WebViewFragment {
	
	public SendEventFragment(){}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	 	getWebView().getSettings().setJavaScriptEnabled(true);
		getWebView().loadUrl("https://docs.google.com/forms/d/1UsyMhjsuDRpSa2MExoRv45R0gK-5FMfDZ4A6enPWTvY/viewform?usp=send_form");

	
	}
}
