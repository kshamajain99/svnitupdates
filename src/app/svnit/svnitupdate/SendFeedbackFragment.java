package app.svnit.svnitupdate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;

public class SendFeedbackFragment extends WebViewFragment {
	
	public SendFeedbackFragment(){}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	 	getWebView().getSettings().setJavaScriptEnabled(true);
		//getWebView().loadUrl("https://www.google.com");
	 	getWebView().loadUrl("https://docs.google.com/forms/d/1K-BNxeqYI64kTornyWm7jPwitAigedl-h3mKJxYIZjA/viewform?c=0&w=1");
		setHasOptionsMenu(true);
	}
}
