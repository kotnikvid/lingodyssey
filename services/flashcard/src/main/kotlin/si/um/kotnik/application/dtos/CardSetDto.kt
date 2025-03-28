package si.um.kotnik.application.dtos

data class CardSetDto(
    var id: String = "",
    var name: String = "",
    var userEmail: String = "",
    var languageName: String = "",
    var languageCode: String = "",
    var flashCards: List<FlashCardDto> = emptyList(),
)
