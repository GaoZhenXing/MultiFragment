package com.jason.multifragment.struct;

/**
 * Created by Jason on 2017/10/13.
 */

public abstract class FunctionNoParamNoResult extends Function {
    public FunctionNoParamNoResult(String funcName) {
        super(funcName);
    }
//    public FunctionNoParamNoResult(String funcName) {
//        super(funcName);
//    }

    public abstract void function();
}
