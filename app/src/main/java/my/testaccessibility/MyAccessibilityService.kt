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