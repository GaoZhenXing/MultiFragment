package com.jason.multifragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.jason.multifragment.struct.FunctionsManager;

/**
 * Created by Jason on 2017/10/14.
 */

public class BaseFragment extends Fragment {


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity){
           ((MainActivity) context).setFunctionForFragment();
        }else{

        }
    }
}
