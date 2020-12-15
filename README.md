# testaccessibility

When the AccessibilityService is used with `StrictMode.VmPolicy.Builder().detectAll()` it shows the following StrictMode violation on start:
## Log
```
2020-12-15 13:49:18.998 491-518/? I/ActivityManager: Start proc 7928:my.TestAccessibility/u0a157 for service {my.TestAccessibility/my.testaccessibility.MyAccessibilityService}
2020-12-15 13:49:19.285 7928-7928/my.TestAccessibility D/NetworkSecurityConfig: No Network Security Config specified, using platform default
2020-12-15 13:49:19.287 7928-7928/my.TestAccessibility D/NetworkSecurityConfig: No Network Security Config specified, using platform default
2020-12-15 13:49:19.302 7928-7928/my.TestAccessibility D/StrictMode: StrictMode policy violation: android.os.strictmode.IncorrectContextUseViolation: Visual services, such as WindowManager, WallpaperService or LayoutInflater should be accessed from Activity or other visual Context. Use an Activity or a Context created with Context#createWindowContext(int, Bundle), which are adjusted to the configuration and visual bounds of an area on screen.
        at android.os.StrictMode.onIncorrectContextUsed(StrictMode.java:2192)
        at android.app.ContextImpl.getSystemService(ContextImpl.java:1915)
        at android.accessibilityservice.AccessibilityService.getSystemService(AccessibilityService.java:2006)
        at android.accessibilityservice.AccessibilityService$2.init(AccessibilityService.java:2091)
        at android.accessibilityservice.AccessibilityService$IAccessibilityServiceClientWrapper.executeMessage(AccessibilityService.java:2311)
        at com.android.internal.os.HandlerCaller$MyHandler.handleMessage(HandlerCaller.java:44)
        at android.os.Handler.dispatchMessage(Handler.java:106)
        at android.os.Looper.loop(Looper.java:223)
        at android.app.ActivityThread.main(ActivityThread.java:7656)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:592)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:947)
     Caused by: java.lang.IllegalAccessException: Tried to access visual service WindowManager from a non-visual Context:my.testaccessibility.MyAccessibilityService@38ec480
        at android.app.ContextImpl.getSystemService(ContextImpl.java:1914)
        at android.accessibilityservice.AccessibilityService.getSystemService(AccessibilityService.java:2006) 
        at android.accessibilityservice.AccessibilityService$2.init(AccessibilityService.java:2091) 
        at android.accessibilityservice.AccessibilityService$IAccessibilityServiceClientWrapper.executeMessage(AccessibilityService.java:2311) 
        at com.android.internal.os.HandlerCaller$MyHandler.handleMessage(HandlerCaller.java:44) 
        at android.os.Handler.dispatchMessage(Handler.java:106) 
        at android.os.Looper.loop(Looper.java:223) 
        at android.app.ActivityThread.main(ActivityThread.java:7656) 
        at java.lang.reflect.Method.invoke(Native Method) 
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:592) 
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:947) 
2020-12-15 13:49:19.312 7928-7928/my.TestAccessibility E/ContextImpl: Tried to access visual service WindowManager from a non-visual Context:my.testaccessibility.MyAccessibilityService@38ec480 Visual services, such as WindowManager, WallpaperService or LayoutInflater should be accessed from Activity or other visual Context. Use an Activity or a Context created with Context#createWindowContext(int, Bundle), which are adjusted to the configuration and visual bounds of an area on screen.
    java.lang.IllegalAccessException: Tried to access visual service WindowManager from a non-visual Context:my.testaccessibility.MyAccessibilityService@38ec480
        at android.app.ContextImpl.getSystemService(ContextImpl.java:1914)
        at android.accessibilityservice.AccessibilityService.getSystemService(AccessibilityService.java:2006)
        at android.accessibilityservice.AccessibilityService$2.init(AccessibilityService.java:2091)
        at android.accessibilityservice.AccessibilityService$IAccessibilityServiceClientWrapper.executeMessage(AccessibilityService.java:2311)
        at com.android.internal.os.HandlerCaller$MyHandler.handleMessage(HandlerCaller.java:44)
        at android.os.Handler.dispatchMessage(Handler.java:106)
        at android.os.Looper.loop(Looper.java:223)
        at android.app.ActivityThread.main(ActivityThread.java:7656)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:592)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:947)
```

## Service:
```
package my.testaccessibility

import android.accessibilityservice.AccessibilityService
import android.os.StrictMode
import android.view.accessibility.AccessibilityEvent

class MyAccessibilityService : AccessibilityService() {

    init {
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        TODO("Not yet implemented")
    }
    override fun onInterrupt() {
        TODO("Not yet implemented")
    }
}
```


## Manifest:
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="my.testaccessibility">

    <application android:label="TestAccessibility">

        <service
            android:name=".MyAccessibilityService"
            android:label="My accessibility service"
            android:permission="android.permission.BIND_ACCESSIBILITY_SERVICE">
            <intent-filter>
                <action android:name="android.accessibilityservice.AccessibilityService" />
            </intent-filter>
        </service>

    </application>

</manifest>
```