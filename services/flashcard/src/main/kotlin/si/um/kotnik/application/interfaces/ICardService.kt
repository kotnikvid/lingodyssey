package si.um.kotnik.application.interfaces

import io.smallrye.mutiny.Uni
import si.um.kotnik.application.dtos.AttemptDto
import si.um.kotnik.application.dtos.CardSetDto
import si.um.kotnik.application.dtos.FlashCardDto
import si.um.kotnik.application.dtos.ResponseDto

interface ICardService {
    fun addCardSet(dto: CardSetDto): Uni<ResponseDto<Any?>>
    fun addFlashcardToSet(dto: FlashCardDto): Uni<ResponseDto<Any?>>
    fun updateCardSet(dto: CardSetDto): Uni<ResponseDto<Any?>>
    fun deleteCardSet(id: String): Uni<ResponseDto<Any?>>

    fun getCardSetById(id: String): Uni<ResponseDto<Any?>>
    fun getCardSets(): Uni<ResponseDto<Any?>>
    fun getFlashcardById(id: String): Uni<ResponseDto<Any?>>
    fun getCardSetsByLanguage(languageName: String?, languageCode: String?): Uni<ResponseDto<Any?>>
    fun getCardSetsByUser(email: String): Uni<ResponseDto<Any?>>

    fun updateFlashcard(dto: FlashCardDto): Uni<ResponseDto<Any?>>

    fun deleteFlashcard(id: String): Uni<ResponseDto<Any?>>

    fun addAttempt(dto: AttemptDto): Uni<ResponseDto<Any?>>

    fun userHasCardSetAccess(email: String, id: String): Uni<Boolean>
    fun userHasFlashcardAccess(email: String, id: String): Uni<Boolean>
}