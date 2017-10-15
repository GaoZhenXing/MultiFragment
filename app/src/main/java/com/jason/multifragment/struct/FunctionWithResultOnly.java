package com.jason.multifragment.struct;

/**
 * Created by Jason on 2017/10/13.
 */

public abstract class FunctionWithResultOnly<Result> extends Function {


    public FunctionWithResultOnly(String funcName ) {
        super(funcName);
    }

    public abstract Result function();
}
