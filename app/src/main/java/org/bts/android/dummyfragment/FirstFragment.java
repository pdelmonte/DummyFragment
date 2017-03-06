package org.bts.android.dummyfragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements View.OnClickListener{

    public interface ICom2Activity {
        public void getRandomNumberFromFragment1();
    }

    private ICom2Activity mInterfaceInstance;

    private static final String TAG = FirstFragment.class.getName();

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        final Button btnUpdateSecondFrag = (Button) rootView.findViewById(R.id.btn_update_fragment2);
        btnUpdateSecondFrag.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            this.mInterfaceInstance = (ICom2Activity) context;
        } catch (ClassCastException e) {
            Log.e(FirstFragment.TAG, "Error on casting context");
        }
    }

    @Override
    public void onClick(View whichView) {

        Log.i(FirstFragment.TAG, "Button Clicked!");

        if (mInterfaceInstance != null) {
            mInterfaceInstance.getRandomNumberFromFragment1();
        }

    }
}
