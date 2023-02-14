package utils

import java.time.Duration
import java.time.LocalDateTime

class DateUtils {
    companion object {
        fun getTotalHoursBetween(startDateTime: LocalDateTime, endDateTime: LocalDateTime): Int {
            return Duration.between(startDateTime, endDateTime).toHours().toInt()
        }
    }
}