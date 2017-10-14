package com.jason.multifragment.struct;

/**
 * Created by Jason on 2017/10/13.
 */

public abstract class FunctionWithParamOnly<Param> extends Function {
    public FunctionWithParamOnly(String funcName) {
        super(funcName);
    }

    public abstract void function(Param param);
}
