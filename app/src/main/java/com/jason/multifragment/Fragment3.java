package com.jason.multifragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jason.multifragment.struct.FunctionsManager;

/**
 * Created by Jason on 2017/10/12.
 */

public class Fragment3 extends BaseFragment {
    public static final String INTERFACE_WITH_RESULT_PARAM = Fragment3.class.getName() + "RESUTLANDPARAM";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getView().findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = FunctionsManager.getInstance().invokeFunc(INTERFACE_WITH_RESULT_PARAM, 1111, String.class);
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
            }
        });
    }
}
