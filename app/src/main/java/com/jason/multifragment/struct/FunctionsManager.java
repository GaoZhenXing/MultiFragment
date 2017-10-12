package com.jason.multifragment.struct;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by Jason on 2017/10/13.
 */

public class FunctionsManager {
    //不带参数无返回值的参数 key 为 方法的名字
    private HashMap<String, FunctionNoParamNoResult> mFunctionNoParanNoResult;
    // 参数有返回值的参数 key 为 方法的名字
    private HashMap<String, FunctionWithParamAndResult> mFunctionWithParamAndResult;
    //仅有参数的参数 key 为 方法的名字
    private HashMap<String, FunctionWithParamOnly> mFunctionWithParamOnly;
    //仅有返回值的参数 key 为 方法的名字
    private HashMap<String, FunctionWithResultOnly> mFunctionWithResultOnly;

    private static final FunctionsManager ourInstance = new FunctionsManager();

    public static FunctionsManager getInstance() {
        return ourInstance;
    }

    private FunctionsManager() {
        mFunctionNoParanNoResult = new HashMap<>();
        mFunctionWithParamAndResult = new HashMap<>();
        mFunctionWithParamOnly = new HashMap<>();
        mFunctionWithResultOnly = new HashMap<>();
    }

    public FunctionsManager addFunction(FunctionWithResultOnly function) {
        mFunctionWithResultOnly.put(function.mfunctionName, function);
        return this;
    }
    public void invokeFunc(String funcName){
        if (TextUtils.isEmpty(funcName)){
            return;
        }
        if (null!=mFunctionNoParanNoResult){
            FunctionNoParamNoResult functionNoParamNoResult = mFunctionNoParanNoResult.get(funcName);
            if (null!=functionNoParamNoResult){
                functionNoParamNoResult.function();
            }
            if (null==functionNoParamNoResult){
                try {
                    throw new FunctionException("没有发现方法名为"+funcName);
                }catch (FunctionException e){
                    e.printStackTrace();
                }
            }
        }

    }

}
