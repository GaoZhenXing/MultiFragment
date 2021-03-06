package com.jason.multifragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jason.multifragment.struct.FunctionsManager;

/**
 * Created by Jason on 2017/10/12.
 */

public class Fragment1 extends BaseFragment {
    public static final String INTERFACE_NPNR = Fragment1.class.getName() + "NPNR";
    public static final String INTERFACE_NP = Fragment1.class.getName() + "NP";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getView().findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //无参数 无返回值
                FunctionsManager.getInstance().invokeFunc(INTERFACE_NPNR);
                //携带参数
                FunctionsManager.getInstance().invokeFunc(INTERFACE_NP, 333);
                //有返回值 返回类型为 String
                String s = FunctionsManager.getInstance().invokeFunc(INTERFACE_NP, String.class);
                //有携带参数 有返回值 返回类型为String
                String s1 = FunctionsManager.getInstance().invokeFunc(INTERFACE_NP, 333, String.class);
            }
        });
    }
}
