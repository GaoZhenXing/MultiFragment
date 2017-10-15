# MultiFragment
**fragment间传递值 封装 抽象方法构架  万能接口**

将fragment 接口层 封装, 在Activity中添加 方法, 在fragment中调用方法 实现 fragment 和Activity之间通讯,实现fragment和fragment间通讯.

Fragment 通讯原则:
   Two Fragment should never communicate directly;
   
   两个fragment之间不能直接通讯;
   
 **实现方式:**
  fragemnt1 -->Activity --> fragmetn 2
  
   **解决 Activity中有多个接口实现 不利于管理**
   
   通过接口实现fragment   自定义架构  
   
   先对方法进行抽象
 
 ```
public abstract class Function {
    public String mfunctionName;

    public Function(String funcName) {
        this.mfunctionName = funcName;
    }

//    public abstract void funcation();
}
```
  实现 4种不同的抽象类 (是否有返回值,是否有参数)
 ```
 FunctionNoParamNoResult
 FunctionWithParamAndResult
 FunctionWithParamOnly
 FunctionWithResultOnly
```
 使用方法 :
 
- 先定义方法名称 
```
       public static  final String INTERFACE_NPNR=Fragment1.class.getName()+"NPNR";
   public static  final String INTERFACE_NP=Fragment1.class.getName()+"NP";
  ```
  
    通过方法名 去调用 Activity中的实现
    
-  在fragment调用 Activity的的方法
  ```
    public void setFunctionForFragment() {
        FunctionsManager.getInstance()
                //fragment1   实现方式 无参数 无返回值
                .addFunction(new FunctionNoParamNoResult(Fragment1.INTERFACE_NPNR) {
                    @Override
                    public void function() {
                        Toast.makeText(MainActivity.this, "成功调用无返回值 无参数的接口", Toast.LENGTH_LONG).show();
                    }
                })
                //fragment1   实现  有参数  回调
                .addFunction(new FunctionWithParamOnly<Integer>(Fragment1.INTERFACE_NP) {

                    @Override
                    public void function(Integer o) {
                        Toast.makeText(MainActivity.this, "mainActivity接收到fragment1参数:" + o, Toast.LENGTH_LONG).show();
                    }
                })
                //fragment2 只有返回值 实现方式
                .addFunction(new FunctionWithResultOnly<String>(Fragment2.INTERFACE_RESULT_ONLY) {

                    @Override
                    public String function() {
                        return "Activity返回 I Love You";
                    }
                })
                //fragment2   实现  有参数 有返回值 回调
                .addFunction(new FunctionWithParamAndResult<String, Integer>(Fragment3.INTERFACE_WITH_RESULT_PARAM) {


                    @Override
                    public String function(Integer integer) {
                        return "Activity接收到参数:" + integer + ",返回 :fdaf ";
                    }
                });

    }
  ```
 - 在fragment OnAttach() 方法中调用 
  ```
  @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity){
           ((MainActivity) context).setFunctionForFragment();
        }else{

        }
    }
 ```
 
 在fragment 中 调用就可以了
 ```
          getView().findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //无参数 无返回值
                FunctionsManager.getInstance().invokeFunc(INTERFACE_NPNR);
                //携带参数
                FunctionsManager.getInstance().invokeFunc(INTERFACE_NP,333);
                //有返回值 返回类型为 String
                FunctionsManager.getInstance().invokeFunc(INTERFACE_NP,String.class);
                //有携带参数 有返回值 返回类型为String
                FunctionsManager.getInstance().invokeFunc(INTERFACE_NP,333,String.class);
            }
        });
 ```