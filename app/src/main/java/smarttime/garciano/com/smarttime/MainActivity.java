package smarttime.garciano.com.smarttime;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer, dlDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private TextView text;

    private TextView userName;
    private TextView emailAddress;
    private TextView pass;
    Firebase mRef;
    public static String username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //userName = (TextView) findViewById(R.id.username);
        emailAddress = (TextView) findViewById(R.id.emailaddress);
        pass = (TextView) findViewById(R.id.password);


        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Find our drawer view
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        // Find our drawer view
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        dlDrawer.setDrawerListener(drawerToggle);


        Firebase.setAndroidContext(this);
        userName = (TextView) findViewById(R.id.username);
        mRef = new Firebase("https://torrid-inferno-6852.firebaseio.com/username");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String SuperData = (String) dataSnapshot.getValue();
                userName.setText(SuperData);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }


    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, dlDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on
        // position
        Fragment fragment = null;

        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_profile:
                fragmentClass = ProfileFragment.class;
                break;
            case R.id.nav_contacts:
                fragmentClass = ContactsFragment.class;
                break;
            case R.id.nav_receivedEvents:
                fragmentClass = ReceivedEventsFragment.class;
                break;
            case R.id.nav_sentEvents:
                fragmentClass = SentEventsFragment.class;
                break;
            case R.id.nav_events:
                fragmentClass = EventsFragment.class;
                break;
            case R.id.nav_logout:
                fragmentClass = LogoutFragment.class;
            default:
                fragmentClass = ProfileFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item, update the title, and close the drawer
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        Firebase mRef = new Firebase("https://torrid-inferno-6852.firebaseio.com/username");
//        //Firebase messagesRef = mRef.child("username");
//       // mRef = new Firebase("https://torrid-inferno-6852.firebaseio.com/");
//        mRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//               // String username = dataSnapshot.getValue(String.class);
//                for(DataSnapshot postSnapshot: dataSnapshot.getChildren())
//                {
//                  Retrievedata username = postSnapshot.child("username").getValue(Retrievedata.class);
//                    userName.setText("username");
//                }
//                Log.v("E_VALUE", username);
//                //userName.setText(dataSnapshot.getValue(String.class));
//                // emailAddress.setText(data);
//                //pass.setText(data);
//
//            }
///*
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
//                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
//                    BlogPost post = postSnapshot.getValue(BlogPost.class);
//                    System.out.println(post.getAuthor() + " - " + post.getTitle());
//                }
//            }
//*/
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Make sure this is the method with just `Bundle` as the signature
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }
}