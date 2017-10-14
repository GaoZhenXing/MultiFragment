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

    //添加没有参数没有返回值的
    public FunctionsManager addFunction(FunctionNoParamNoResult function) {
        mFunctionNoParanNoResult.put(function.mfunctionName, function);
        return this;
    }

    //调用方法
    public void invokeFunc(String funcName) {
        if (TextUtils.isEmpty(funcName)) {
            return;
        }
        if (mFunctionNoParanNoResult != null) {
            FunctionNoParamNoResult functionNoParamNoResult = mFunctionNoParanNoResult.get(funcName);

            if (null != functionNoParamNoResult) {
                functionNoParamNoResult.function();
            } else {
                try {
                    throw new FunctionException("Has no this function:" + funcName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public FunctionsManager addFunction(FunctionWithResultOnly function) {
        mFunctionWithResultOnly.put(function.mfunctionName, function);
        return this;
    }

    public <Result> Result invokeFunc(String funcName, Class<Result> c) {
        if (TextUtils.isEmpty(funcName)) {
            return null;
        }
        if (mFunctionWithResultOnly != null) {
            FunctionWithResultOnly f = mFunctionWithResultOnly.get(funcName);

            if (null != f) {

                if (null != c) {
                    return c.cast(f.function());
                } else {
                    return (Result) f.function();
                }

            } else {
                try {
                    throw new FunctionException("Has no this function:" + funcName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    ;

    public FunctionsManager addFunction(FunctionWithParamOnly function) {
        mFunctionWithParamOnly.put(function.mfunctionName, function);
        return this;
    }

    public <Param> void invokeFunc(String funcName, Param data) {
        if (TextUtils.isEmpty(funcName)) {
            return;
        }
        if (mFunctionWithParamOnly != null) {
            FunctionWithParamOnly f = mFunctionWithParamOnly.get(funcName);

            if (null != f) {

                f.function(data);

            } else {
                try {
                    throw new FunctionException("Has no this function:" + funcName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public FunctionsManager addFunction(FunctionWithParamAndResult function) {
        mFunctionWithParamAndResult.put(function.mfunctionName, function);
        return this;
    }

    public <Result, Param> Result invokeFunc(String funcName, Param data, Class<Result> resultClass) {
        if (TextUtils.isEmpty(funcName)) {
            return null;
        }
        if (mFunctionWithParamOnly != null) {
            FunctionWithParamAndResult f = mFunctionWithParamAndResult.get(funcName);

            if (null != f) {
                if (null != resultClass) {
                    return resultClass.cast(f.function(data));
                } else {
                    return (Result) f.function(data);
                }
            } else {
                try {
                    throw new FunctionException("Has no this function:" + funcName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    ;
}

