package smarttime.garciano.com.smarttime;

//import com.firebase.client.Firebase;

import com.firebase.client.Firebase;

/**
 * Created by admin on 5/18/2016.
 */
public class CloudActivity extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
