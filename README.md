Cydia Substrate是一个代码修改平台。它可以修改任何主进程的代码，不管是用Java还是C/C++（native代码）编写的。因此Cydia Substrate是一款强大而实用的HOOK工具。

官网地址：http://www.cydiasubstrate.com/

官方教程：http://www.cydiasubstrate.com/id/20cf4700-6379-4a14-9bc2-853fde8cc9d1

SDK下载地址：http://asdk.cydiasubstrate.com/zips/cydia_substrate-r2.zip


* Substrate几个重要API介绍:

1. MS.hookClassLoad 
函数原型：void hookClassLoad(String name, MS.ClassLoadHook hook);
该方法实现在指定的类被加载的时候发出通知。因为一个类可以在任何时候被加载，所以Substrate提供了一个方法用来检测用户感兴趣的类何时被加载。

2. MS.hookMethod 
该API允许开发者提供一个回调函数替换原来的方法，这个回调函数是一个实现了MS.MethodHook接口的对象，是一个典型的匿名内部类。它包含一个invoked函数。
函数原型：
void hookMethod(Class _class, Member member, MS.MethodHook hook, MS.MethodPointer old);
void hookMethod(Class _class, Member member, MS.MethodAlteration alteration);

* 准备工作：

1. 需要一部root过的手机。
2. 需要在手机上安装Cydia Substrate。
3. 在手机上运行Substrate,界面上点击“Link Substrate Files"，点击"Restart System(Soft)"。
