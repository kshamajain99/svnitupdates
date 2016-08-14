package app.svnit.svnitupdate;

import android.os.Bundle;
import android.webkit.WebViewFragment;

public class SendUpdateFragment extends WebViewFragment {
	
	public SendUpdateFragment(){}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	 	getWebView().getSettings().setJavaScriptEnabled(true);
		getWebView().loadUrl("https://docs.google.com/forms/d/1POnJ0CWzXsHf5DZBXxS-_1t25I-GZyxiJUavlLGNbpQ/viewform?usp=send_form");

	
	}
}
