package com.github.droserasprout.lockscreencamera;

import android.app.Activity;
import android.app.AndroidAppHelper;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Window;
import android.view.WindowManager;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class LockscreenCamera implements IXposedHookLoadPackage {
    public void handleLoadPackage(LoadPackageParam loadPackageParam) {
        XposedBridge.log("lockscreencamera: applying hooks");
        if (loadPackageParam.packageName.equals("com.android.camera")) {
            XposedBridge.log("lockscreencamera: applying com.android.camera hooks");

            XC_MethodHook onCreateHook = new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                    XposedBridge.log("lockscreencamera: hooking Camera activity");
                    Activity activity = (Activity) param.thisObject;

                    activity.setShowWhenLocked(true);
                    activity.setTurnScreenOn(true);

                    final Window win = activity.getWindow();
                    win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
            };

            XposedHelpers.findAndHookMethod(
                    "com.android.camera.Camera",
                    loadPackageParam.classLoader,
                    "onCreate",
                    Bundle.class,
                    onCreateHook);
        } else if (loadPackageParam.packageName.equals("android")) {
            XposedBridge.log("lockscreencamera: applying com.android.server.GestureLauncherService hooks");

            XC_MethodHook handleCameraGestureHook = new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                    XposedBridge.log("lockscreencamera: hooking GestureLauncherService");

                    Context context = AndroidAppHelper.currentApplication();

                    Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA_SECURE);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    context.startActivity(intent);
                }
            };

            XposedHelpers.findAndHookMethod(
                    "com.android.server.GestureLauncherService",
                    loadPackageParam.classLoader,
                    "handleCameraGesture",
                    boolean.class,
                    int.class,
                    handleCameraGestureHook
            );
        } else {
            XposedBridge.log("lockscreencamera: skipping " + loadPackageParam.packageName);
        }
    }
}
