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
 
   `
public abstract class Function {
    public String mfunctionName;

    public Function(String funcName) {
        this.mfunctionName = funcName;
    }

//    public abstract void funcation();
}`
