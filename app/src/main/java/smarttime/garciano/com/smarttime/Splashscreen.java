package smarttime.garciano.com.smarttime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Splashscreen extends AppCompatActivity {

    private static int progress;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    public static final String TAG = Splashscreen.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        /**requestWindowFeature(Window.FEATURE_NO_TITLE);
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);**/
        progress = 0;
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setMax(50);

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (progressStatus < 50) {
                    progressStatus = doSomeWork();
                    handler.post(new Runnable() {
                        public void run() { progressBar.setProgress(progressStatus);}
                    });
                }
                handler.post(new Runnable(){
                    public void run(){
                        progressBar.setVisibility(1);
                    }
                });
            }
            private int doSomeWork(){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                return ++progress;
            }
        }).start();

        final ImageView iv = (ImageView) findViewById(R.id.imgLogo);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.splashscreen_anim);
        final Animation anim = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);

        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                iv.startAnimation(anim);
                finish();
               // Intent i = new Intent(getBaseContext(), SetActivity.class);
                //startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

    }
}


