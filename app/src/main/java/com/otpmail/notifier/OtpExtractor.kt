package com.otpmail.notifier

object OtpExtractor {
    private val otpRegex = Regex("(?i)(?:otp|code|verification|password)[^0-9]{0,30}([0-9]{4,8})|\\b([0-9]{4,8})\\b")

    fun extract(text: String): String? {
        val match = otpRegex.find(text) ?: return null
        return match.groupValues.drop(1).firstOrNull { it.isNotBlank() } ?: match.value
    }
}
