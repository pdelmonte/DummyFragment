package org.bts.android.dummyfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    private static final String TAG = SecondFragment.class.getSimpleName();
    private TextView mTxtViewInfo;
    private double mExternalRandomNumber;

    public SecondFragment() {
        // Required empty public constructor
    }

    public SecondFragment(double mRandomNumber) {
        this.mExternalRandomNumber = mRandomNumber;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        this.mTxtViewInfo = (TextView) rootView.findViewById(R.id.tv_info_second_fragment);

        if(this.mExternalRandomNumber != 0.0d) {
            this.updateTextView(this.mExternalRandomNumber);
        }

        return rootView;

    }

    public void updateTextView(double mRandomNumber) {

        this.mTxtViewInfo.setText(String.valueOf(mRandomNumber));
        Log.i(SecondFragment.TAG, "TextView updated!");
    }
}
