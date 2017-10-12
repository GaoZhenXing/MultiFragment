package com.jason.multifragment.struct;

import android.util.Log;

/**
 * Created by Jason on 2017/10/13.
 */

class FunctionException extends Throwable {
    public FunctionException(String s) {
        Log.e("Error", s);
    }
}
