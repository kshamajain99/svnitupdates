package app.svnit.svnitupdate;
import android.app.Application;

import com.parse.Parse;
 
public class ParseApplication extends Application {
 
    @Override
    public void onCreate() {
        super.onCreate();
        //
       Parse.enableLocalDatastore(this);
        // initialization code
        Parse.initialize(this, "pIttP8KjaecNBrHCIo5O63IgxwgBkAyBDx8JDlxc", "9DLKDTe7shKb5fWThi4KaQU2do6jQLDrpQhrCNQ0");
        // If you would like all objects to be private by default, remove this
        // line.
        
    }
 
}