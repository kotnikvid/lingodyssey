package si.um.kotnik.application.dtos

data class FlashCardDto(
    val id: String? = null,
    val cardSetId: String? = null,
    val title: String,
    val body: String,
    val correctAnswer: String,
    val pointsAwarded: Int = 0,
    val otherAnswers: List<String> = mutableListOf()
)