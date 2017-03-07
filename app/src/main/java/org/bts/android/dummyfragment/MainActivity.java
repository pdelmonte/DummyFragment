package org.bts.android.dummyfragment;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FirstFragment.ICom2Activity{

    private static final String TAG = MainActivity.class.getSimpleName();
    private FrameLayout mFragmentContainer;
    private View mMainLayout;
    private boolean mTwoPane;
    private double mRandomNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String currentOrientation = getResources().getConfiguration()
                .orientation == Configuration.ORIENTATION_LANDSCAPE ?
                "Landscape":
                "Portrait";
        Log.i(MainActivity.TAG, "Orientation: " + currentOrientation);

        this.mTwoPane = getResources().getConfiguration()
                .orientation != Configuration.ORIENTATION_LANDSCAPE;

        if (this.mTwoPane){
            this.mFragmentContainer = (FrameLayout) findViewById(R.id.frame_layout_container_activity_main);
            this.mMainLayout = findViewById(R.id.linear_layout_activity_main);
            this.mMainLayout.setOnClickListener(this);



        } else {
            final Button btnSwapFrag = (Button) findViewById(R.id.btn_swap_fragment);
            btnSwapFrag.setOnClickListener(this);

            FragmentManager fragManager = this.getSupportFragmentManager();
            fragManager.beginTransaction()
                .replace(R.id.frame_layout_container_activity_main_land, new FirstFragment(), "First Fragment Landscape")
                .commit();
        }


    }

    @Override
    public void onClick(View whichView) {
        if (whichView.getId() == R.id.linear_layout_activity_main) {

            Log.i(MainActivity.TAG, "Main Layout clicked!");

            FragmentManager fragManager = this.getSupportFragmentManager();
            FragmentTransaction fragTransaction = fragManager.beginTransaction();

            fragTransaction.replace(R.id.frame_layout_container_activity_main, new FirstFragment(), "First Fragment");
            fragTransaction.commit();

        } else if(whichView.getId() == R.id.btn_swap_fragment){

            FragmentManager fragManager = this.getSupportFragmentManager();

            boolean isFirstFragment = fragManager.findFragmentByTag("First Fragment Landscape") != null;

            Fragment fragment;
            String fragmentTag;
            int color;

            if(isFirstFragment) {
                fragment = new SecondFragment(this.mRandomNumber);
                fragmentTag = "Second Fragment Landscape";
                color = R.color.colorYellow;

            } else {
                fragment = new FirstFragment();
                fragmentTag = "First Fragment Landscape";
                color = R.color.colorGreen;
            }

            fragManager.beginTransaction()
                    .replace(R.id.frame_layout_container_activity_main_land, fragment, fragmentTag)
                    .commit();
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout_bottom_activity_main_land);
            linearLayout.setBackgroundColor(ContextCompat.getColor(this, color));
        }
    }

    @Override
    public void getRandomNumberFromFragment1() {

        this.mRandomNumber = Math.random();

        Log.i(MainActivity.TAG, "Random Number Generated: " + mRandomNumber);

        if (this.mTwoPane) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            SecondFragment secondFragment = (SecondFragment) fragmentManager.findFragmentById(R.id.fragment_main_activity);

            secondFragment.updateTextView(mRandomNumber);
        }
    }
}
