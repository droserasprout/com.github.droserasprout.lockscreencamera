# Secure Lockscreen Camera

Xposed Framework module to skip authentication on \"Quickly open camera\" gesture.

## Requirements

* Android 11+ (other versions not tested)
* [Riru](https://github.com/RikkaApps/Riru)
* [LSPosed](https://github.com/LSPosed/LSPosed) ([EdXposed](https://github.com/ElderDrivers/EdXposed) not tested)
* [ANXCamera](https://github.com/XEonAX/ANXCamera10) or [GCam port](https://www.celsoazevedo.com/files/android/google-camera/) (other cameras not tested)

## Installation

1. Install latest version of [Magisk](https://github.com/topjohnwu/Magisk)
2. Install [Riru](https://github.com/Magisk-Modules-Repo/riru-core) and [Riru LSPosed](https://github.com/Magisk-Modules-Repo/riru_lsposed) from Magisk module repository
3. Install Secure Lockscreen Camera package
4. Enable module in LSPosed app with recommended scope, reboot

## How does it work

This module patches two classes to achieve it's goal:

* `com.android.camera.Camera` to draw activity over lockscreen without invoking Keyguard.
* `com.android.server.GestureLauncherService` to create custom intent for this activity.

## Thanks

* Author of original [Enable Camera on Lockscreen]() module with the same functionality but written 9 years ago. 
* [LSPosed Telegram group](https://t.me/joinchat/Rtn1JXdFWlFclzIM) for valuable advice.

## Further reading

Just a bunch of useful links I used while developing this module.

### General

* [Android API reference](https://developer.android.com/reference/)
* [Xposed Framework API reference](https://api.xposed.info/reference/packages.html)
* [Xposed Framework development tutorial](https://github.com/rovo89/XposedBridge/wiki/Development-tutorial)

### StackOverflow quetions
* [How to get Context through hooking in android](https://stackoverflow.com/questions/28059264/how-to-get-context-through-hooking-in-android)
* [Hook A Private Method In Another App Using Xposed](https://stackoverflow.com/questions/37259755/hook-a-private-method-in-another-app-using-xposed)
* [How to capture multiple photos through camera in Android](https://stackoverflow.com/questions/26137731/how-to-capture-multiple-photos-through-camera-in-android)
* [Get Activity name dynamically - android](https://stackoverflow.com/questions/10628152/get-activity-name-dynamically-android)
* [Calling one Activity from another in Android](https://stackoverflow.com/questions/5152564/calling-one-activity-from-another-in-android)
* [Android : Displaying view over the lockscreen (like Google Maps)](https://stackoverflow.com/questions/60732308/android-displaying-view-over-the-lockscreen-like-google-maps)
* [How To Show Activity On Lock Screen When Password Is Set In Android](https://stackoverflow.com/questions/65809352/how-to-show-activity-on-lock-screen-when-password-is-set-in-android)

### Projects
* [Xposed-Modules-Repo/nil.nadph.qnotified](https://github.com/Xposed-Modules-Repo/nil.nadph.qnotified) - one of the most complicated modules out there.
* [Mohamed-Gibreel/jitsi-flutter-phone](https://github.com/Mohamed-Gibreel/jitsi-flutter-phone) - draws `JitsiMeetPluginActivity` activity the same way
* [darkeyes84/dark_tricks](https://github.com/darkeyes84/dark_tricks) (outdated) - cool tricks
* [sundayliu/XposedExamples](https://github.com/sundayliu/XposedExamples) - Xposed-related part of Lucky Patcher)