package test.example.com.testrecycleview.util;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.example.com.testrecycleview.R;


/**
 * Created by jackie.sun on 2015/12/2.
 */
public class ProgressDialog extends DialogFragment {
    private static ProgressDialog singleton;

    public static ProgressDialog newInstance() {
        if (singleton == null) {
            singleton = new ProgressDialog();
        }
        return singleton;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.notice_dialog_style);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.progress_dialog_layout, container, false);
        return view;
    }

}
