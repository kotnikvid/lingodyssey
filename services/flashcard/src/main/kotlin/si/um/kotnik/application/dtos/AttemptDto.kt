package si.um.kotnik.application.dtos

import java.time.LocalDateTime
import java.time.ZoneOffset

data class AttemptDto (
    val userEmail: String,
    val startedAt: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC),
    val endedAt: LocalDateTime = LocalDateTime.now(ZoneOffset.UTC),
    val hasPassed: Boolean = false,
    val cardSetId: String
)