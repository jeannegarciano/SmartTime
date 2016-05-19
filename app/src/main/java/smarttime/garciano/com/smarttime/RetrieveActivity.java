package smarttime.garciano.com.smarttime;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by admin on 5/18/2016.
 */
public class RetrieveActivity extends AppCompatActivity {

    private TextView userName, emailAddress, pass;
    Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override
    protected void onStart() {
        super.onStart();


        userName = (TextView) findViewById(R.id.username);
        emailAddress = (TextView) findViewById(R.id.emailaddress);
        pass = (TextView) findViewById(R.id.password);

        mRef = new Firebase("https://torrid-inferno-6852.firebaseio.com/");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                userName.setText(text);
                // emailAddress.setText(data);
                //pass.setText(data);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}
