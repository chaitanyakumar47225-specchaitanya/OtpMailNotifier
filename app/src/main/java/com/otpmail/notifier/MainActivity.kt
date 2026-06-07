package com.otpmail.notifier

import android.Manifest
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (android.os.Build.VERSION.SDK_INT >= 33) requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 60, 40, 40)
        }
        val title = TextView(this).apply {
            text = "OTP Mail Notifier\nSafe demo build"
            textSize = 24f
        }
        val box = EditText(this).apply {
            hint = "Paste email text here for demo OTP detection"
            minLines = 5
        }
        val btn = Button(this).apply { text = "Detect OTP & Notify" }
        val result = TextView(this).apply { textSize = 18f }

        btn.setOnClickListener {
            val otp = OtpExtractor.extract(box.text.toString())
            result.text = if (otp != null) "Detected OTP: $otp" else "No OTP found"
            if (otp != null) Notifier.showOtp(this, otp, "demo email text")
        }

        root.addView(title)
        root.addView(box)
        root.addView(btn)
        root.addView(result)
        setContentView(root)
    }
}
