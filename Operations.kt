package com.example.timecalculator

class Operations (private val timeOne:String,private val timeTwo:String) {
    fun parseTime(time: String): Int {
        var seconds = 0
        val regex = "(\\d+h)?(\\d+m)?(\\d+s)?".toRegex()
        val match = regex.matchEntire(time.trim())

        match?.groups?.let { groups ->
            groups[1]?.value?.let { hours -> // Обработка часов
                seconds += hours.dropLast(1).toInt() * 3600
            }
            groups[2]?.value?.let { minutes -> // Обработка минут
                seconds += minutes.dropLast(1).toInt() * 60
            }
            groups[3]?.value?.let { sec -> // Обработка секунд
                seconds += sec.dropLast(1).toInt()
            }
        }
        return seconds
    }
    fun formatTime(seconds: Int): String {
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val remainingSeconds = seconds % 60
        return buildString {
            if (hours > 0) append("${hours}h")
            if (minutes > 0) append("${minutes}m")
            if (remainingSeconds > 0 || isEmpty()) append("${remainingSeconds}s")
        }
    }
    fun addTime(): String {
        val totalSeconds = parseTime(timeOne) + parseTime(timeTwo)
        return formatTime(totalSeconds)
    }
    fun subtractTime(): String {
        val totalSeconds = (parseTime(timeOne) - parseTime(timeTwo)).coerceAtLeast(0)
        return formatTime(totalSeconds)
    }

}