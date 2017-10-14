package com.jason.multifragment.struct;

/**
 * Created by Jason on 2017/10/13.
 */

public abstract class FunctionWithParamAndResult<Result, Param> extends Function {
    /**
     * 参数
     * @param funcName
     */
    public FunctionWithParamAndResult(String funcName) {
        super(funcName);
    }

    public abstract Result function(Param param);
}
