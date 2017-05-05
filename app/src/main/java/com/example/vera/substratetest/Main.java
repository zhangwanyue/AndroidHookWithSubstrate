package com.example.vera.substratetest;

import android.app.PendingIntent;
import android.telephony.SmsManager;
import android.util.Log;

import com.saurik.substrate.MS;

import java.lang.reflect.Method;

/**
 * Created by vera on 17-5-4.
 */

public class Main {
    static void initialize(){
 /*  //修改系统颜色
      MS.hookClassLoad("android.content.res.Resources",new MS.ClassLoadHook(){
            @Override
            public void classLoaded(final Class<?> aClass) {
                Method getColor;
                try {
                    getColor = aClass.getMethod("getColor",Integer.TYPE);
                    Log.i("BUPT","getColor success");
                } catch (NoSuchMethodException e) {
                    getColor = null;
                    Log.i("BUPT","getColor exception");
                }
                if(getColor != null){
                    final MS.MethodPointer old = new MS.MethodPointer();
                    MS.hookMethod(aClass,getColor,new MS.MethodHook(){
                        @Override
                        public Object invoked(Object o, Object... objects) throws Throwable {
                            int color = (Integer) old.invoke(o,objects);
                            Log.i("BUPT", "color:" + Integer.toHexString(color));
                       //     return color & ~0x0000ff00 | 0x00ff0000;
                            return 0xffffff00;
                        }
                    },old);
                }
            }
        }); */

 //监控短信
        MS.hookClassLoad("android.telephony.SmsManager",new MS.ClassLoadHook(){
            @Override
            public void classLoaded(Class<?> aClass) {
                Method sendTextMessage;
                try {
                    sendTextMessage = aClass.getMethod("sendTextMessage",
                            new Class[]{String.class,String.class,String.class,PendingIntent.class,PendingIntent.class});
                    Log.i("SMSHOOK","getMessage success");

                } catch (NoSuchMethodException e) {
                    sendTextMessage = null;
                    Log.i("SMSHOOK","getMessage failed");
                }
                if(sendTextMessage != null){
                    final MS.MethodPointer old = new MS.MethodPointer();
                    MS.hookMethod(aClass,sendTextMessage,new MS.MethodHook(){
                        @Override
                        public Object invoked(Object _this, Object... _args) throws Throwable {
                            Log.i("SMSHOOK","SEND_SMS");
                            Log.i("SMSHOOK","to:"+_args[0]);
                            Log.i("SMSHOOK","from:"+_args[1]);
                            Log.i("SMSHOOK","text:"+_args[2]);
                            return old.invoke(_this,_args);
                        }
                    },old);
                }
            }
        });
/*
//监控所有BufferWriter的write
       MS.hookClassLoad("java.io.BufferedWriter", new MS.ClassLoadHook() {
            @Override
            public void classLoaded(Class<?> BufferedWriter) {
                //code to modify the class when loaded
                Method write;
                try {
                    write = BufferedWriter.getMethod("write", String.class, Integer.TYPE, Integer.TYPE);
                    Log.i("BUFFERWRITER", "Hook java.io.BufferedWriter success");
                } catch (NoSuchMethodException e) {
                    write = null;
                    Log.i("BUFFERWRITER", "Hook java.io.BufferedWriter failed" + e.toString());
                }
                MS.hookMethod(BufferedWriter, write, new MS.MethodAlteration() {
                    public Object invoked(Object _this, Object... _args) throws Throwable {
                        Log.i("BUFFERWRITER", "SEND_SMS");
                        Log.i("BUFFERWRITER", "destination:" + _args[0]);
                        Log.i("BUFFERWRITER", "source:" + _args[1]);
                        Log.i("BUFFERWRITER", "write content:" + _args[0] + ",from:" + _args[1] + ",to:" + _args[2]);
                        return invoke(_this, _args);
                    }
                });
            }
        });*/

    }
}
