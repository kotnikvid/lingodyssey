package si.um.kotnik.controllers

import si.um.kotnik.application.services.CardService
import io.smallrye.mutiny.Uni
import si.um.kotnik.application.dtos.AttemptDto
import jakarta.ws.rs.*
import si.um.kotnik.application.dtos.CardSetDto
import si.um.kotnik.application.dtos.FlashCardDto
import si.um.kotnik.application.dtos.ResponseDto

@Path("/")
class CardController(
    private val cardService: CardService
) {

    @POST
    @Path("/cardSets")
    fun getCardSet(dto: CardSetDto): Uni<ResponseDto<Any?>> {
        return cardService.addCardSet(dto)
    }

    @GET
    @Path("/cardSets/{id}")
    fun getCardSet(@PathParam("id") id: String): Uni<ResponseDto<Any?>> {
        return cardService.getCardSetById(id)
    }

    @GET
    @Path("/cardSets")
    fun getAllCardSets(): Uni<ResponseDto<Any?>> {
        return cardService.getCardSets()
    }

    @GET
    @Path("/cardSets/language")
    fun getCardSetsByLanguage(
        @QueryParam("languageName") languageName: String?,
        @QueryParam("languageCode") languageCode: String?
    ): Uni<ResponseDto<Any?>> {
        return cardService.getCardSetsByLanguage(languageName, languageCode)
    }

    @GET
    @Path("/cardSets/user")
    fun getCardSetsByUser(@QueryParam("email") email: String): Uni<ResponseDto<Any?>> {
        return cardService.getCardSetsByUser(email)
    }

    @PUT
    @Path("/cardSets")
    fun updateCardSet(dto: CardSetDto): Uni<ResponseDto<Any?>> {
        return cardService.updateCardSet(dto)
    }

    @DELETE
    @Path("/cardSets/{id}")
    fun deleteCardSet(@PathParam("id") id: String): Uni<ResponseDto<Any?>> {
        return cardService.deleteCardSet(id)
    }

    // flashcards

    @POST
    @Path("/flashCards")
    fun addFlashcard(dto: FlashCardDto): Uni<ResponseDto<Any?>> {
        return cardService.addFlashcardToSet(dto)
    }

    @GET
    @Path("/flashCards/{id}")
    fun getFlashcard(@PathParam("id") id: String): Uni<ResponseDto<Any?>> {
        return cardService.getFlashcardById(id)
    }

    @PUT
    @Path("/flashCards")
    fun updateFlashcard(dto: FlashCardDto): Uni<ResponseDto<Any?>> {
        return cardService.updateFlashcard(dto)
    }

    @DELETE
    @Path("/flashCards/{id}")
    fun deleteFlashcard(@PathParam("id") id: String): Uni<ResponseDto<Any?>> {
        return cardService.deleteFlashcard(id)
    }

    // answers

    @POST
    @Path("/attempts")
    fun addAttempt(dto: AttemptDto): Uni<ResponseDto<Any?>> {
        return cardService.addAttempt(dto)
    }

//    @GET
//    @Path("/access/cardset")
//    fun checkCardSetAccess(
//        @QueryParam("email") email: String,
//        @QueryParam("id") id: String
//    ): Uni<Boolean> {
//        return cardService.userHasCardSetAccess(email, id)
//    }
//
//    @GET
//    @Path("/access/flashcard")
//    fun checkFlashcardAccess(
//        @QueryParam("email") email: String,
//        @QueryParam("id") id: String
//    ): Uni<Boolean> {
//        return cardService.userHasFlashcardAccess(email, id)
//    }
}