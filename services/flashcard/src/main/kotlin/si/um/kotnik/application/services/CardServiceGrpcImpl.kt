package si.um.kotnik.application.services

import io.grpc.stub.StreamObserver
import io.quarkus.grpc.GrpcService
import si.um.kotnik.CardServiceGrpc
import si.um.kotnik.Flashcard
import si.um.kotnik.Flashcard.CardSetDtoMessage
import si.um.kotnik.Flashcard.FlashCardDtoMessage
import si.um.kotnik.Flashcard.CardSetIdRequest
import si.um.kotnik.Flashcard.CardSetListMessage
import si.um.kotnik.Flashcard.FlashCardIdRequest
import si.um.kotnik.Flashcard.ResponseDtoGrpc
import si.um.kotnik.application.dtos.AttemptDto
import si.um.kotnik.application.dtos.CardSetDto
import si.um.kotnik.application.dtos.FlashCardDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@GrpcService
class CardServiceGrpcImpl(private val cardService: CardService) : CardServiceGrpc.CardServiceImplBase() {

    // Add Card Set
    override fun addCardSet(request: CardSetDtoMessage?, responseObserver: StreamObserver<Flashcard.ResponseDtoGrpc>?) {
        cardService.addCardSet(request!!.toCardSetDto())
            .map { response ->
                val cardSetDtoMessage = response.data as? CardSetDto // Cast safely
                ResponseDtoGrpc.newBuilder()
                    .setMessage("Card set added")
                    .setCardSet(
                        cardSetDtoMessage?.toProto() ?: CardSetDtoMessage.getDefaultInstance()
                    )
                    .build()
            }
            .subscribe()
            .with(
                { responseObserver?.onNext(it); responseObserver?.onCompleted() },
                { responseObserver?.onError(it) }
            )
    }

    // Get Card Set by ID
    override fun getCardSetById(
        request: CardSetIdRequest?,
        responseObserver: StreamObserver<Flashcard.ResponseDtoGrpc>?
    ) {
        var cardSet = cardService.getCardSetById(request!!.id)
            .onItem().transform { cardSet ->
                cardSet ?: throw IllegalStateException("Card set does not exist")

                val cardSetDto = cardSet.data as? CardSetDto

                ResponseDtoGrpc.newBuilder()
                    .setCardSet(cardSetDto?.toProto() ?: CardSetDtoMessage.getDefaultInstance())
                    .build()
            }
            .subscribe().with(
                { response ->
                    responseObserver?.onNext(response)
                    responseObserver?.onCompleted()
                },
                { error ->
                    responseObserver?.onError(error)
                }
            )


    }

    // Get all Card Sets
    override fun getCardSets(
        request: Flashcard.EmptyRequest,
        responseObserver: StreamObserver<Flashcard.ResponseDtoGrpc>?
    ) {
        cardService.getCardSets()
            .onItem().transform { cardSet ->
                val responseBuilder = ResponseDtoGrpc.newBuilder()
                    .setMessage("Success")
                    .setStatusCode(200)


                cardSet ?: throw IllegalStateException("Card set does not exist")

                val cardSetDtoList = cardSet.data as? ArrayList<CardSetDto>

                val cardSetProto = cardSetDtoList?.toProto()

                if (cardSetProto?.cardSetsCount!! > 0) {
                    print(cardSetProto)
                    responseBuilder.setCardSets(cardSetProto)
                }

                val response = responseBuilder.build()

                response
            }
            .subscribe().with(
                { response ->
                    responseObserver?.onNext(response)
                    responseObserver?.onCompleted()
                },
                { error ->
                    responseObserver?.onError(error)
                }
            )
    }

    // Get Card Sets by Language
    override fun getCardSetsByLanguage(
        request: Flashcard.LanguageRequest,
        responseObserver: StreamObserver<ResponseDtoGrpc>?
    ) {
        cardService.getCardSetsByLanguage(null, request.languageCode)
            .onItem().transform { cardSet ->
                val responseBuilder = ResponseDtoGrpc.newBuilder()
                    .setMessage("Success")
                    .setStatusCode(200)


                cardSet ?: throw IllegalStateException("Card set does not exist")

                val cardSetDtoList = cardSet.data as? ArrayList<CardSetDto>

                val cardSetProto = cardSetDtoList?.toProto()

                if (cardSetProto?.cardSetsCount!! > 0) {
                    print(cardSetProto)
                    responseBuilder.setCardSets(cardSetProto)
                }

                val response = responseBuilder.build()

                response
            }
            .subscribe().with(
                { response ->
                    responseObserver?.onNext(response)
                    responseObserver?.onCompleted()
                },
                { error ->
                    responseObserver?.onError(error)
                }
            )
    }

    // Get Card Sets by User
    override fun getCardSetsByUser(
        request: Flashcard.UserEmailRequest,
        responseObserver: StreamObserver<ResponseDtoGrpc>?
    ) {
        cardService.getCardSetsByUser(request.email)
            .onItem().transform { cardSet ->
                val responseBuilder = ResponseDtoGrpc.newBuilder()
                    .setMessage("Success")
                    .setStatusCode(200)

                cardSet ?: throw IllegalStateException("Card set does not exist")

                val cardSetDtoList = cardSet.data as? ArrayList<CardSetDto>

                val cardSetProto = cardSetDtoList?.toProto()

                if (cardSetProto?.cardSetsCount!! > 0) {
                    print(cardSetProto)
                    responseBuilder.setCardSets(cardSetProto)
                }

                val response = responseBuilder.build()

                response
            }
            .subscribe().with(
                { response ->
                    responseObserver?.onNext(response)
                    responseObserver?.onCompleted()
                },
                { error ->
                    responseObserver?.onError(error)
                }
            )
    }

    // Update Card Set
    override fun updateCardSet(request: CardSetDtoMessage, responseObserver: StreamObserver<ResponseDtoGrpc>?) {
        cardService.getCardSetById(request.id)
            .onItem().transform { cardSet ->
                val update = request.toCardSetDto()

                cardService.updateCardSet(update)

                val responseBuilder = ResponseDtoGrpc.newBuilder()
                    .setMessage("Success")
                    .setStatusCode(200)

                cardSet ?: throw IllegalStateException("Card set does not exist")

                val cardSetDto = cardSet.data as? CardSetDto

                if (cardSetDto != null) {
                    val cardSetProto = cardSetDto.toProto()

                    responseBuilder.setCardSet(cardSetProto)
                }


                val response = responseBuilder.build()

                response
            }
            .subscribe().with(
                { response ->
                    responseObserver?.onNext(response)
                    responseObserver?.onCompleted()
                },
                { error ->
                    responseObserver?.onError(error)
                }
            )
    }

    // Delete Card Set
    override fun deleteCardSet(request: CardSetIdRequest, responseObserver: StreamObserver<ResponseDtoGrpc>?) {
        cardService.getCardSetById(request.id)
            .onItem().transformToUni() { cardSet ->
                cardService.deleteCardSet(request.id).onItem().transform {
                    val responseBuilder = ResponseDtoGrpc.newBuilder()
                        .setMessage("Success")
                        .setStatusCode(204)

                    val response = responseBuilder.build()

                    response
                }
            }
            .subscribe().with(
                { response ->
                    responseObserver?.onNext(response)
                    responseObserver?.onCompleted()
                },
                { error ->
                    responseObserver?.onError(error)
                }
            )
    }


    // Add Flashcard
    override fun addFlashCard(request: FlashCardDtoMessage, responseObserver: StreamObserver<ResponseDtoGrpc>?) {
        val dto = request.toFlashCardDto()

        cardService.addFlashcardToSet(dto)
            .onItem().transform { flashCard ->
                val responseBuilder = ResponseDtoGrpc.newBuilder()
                    .setMessage("Success")
                    .setStatusCode(200)

                flashCard ?: throw IllegalStateException("Card set does not exist")

                val flashCardDto = flashCard.data as? FlashCardDto

                if (flashCardDto != null) {
                    responseBuilder.setFlashCard(flashCardDto.toProto() ?: FlashCardDtoMessage.getDefaultInstance())
                }


                val response = responseBuilder.build()

                response
            }
            .subscribe().with(
                { response ->
                    responseObserver?.onNext(response)
                    responseObserver?.onCompleted()
                },
                { error ->
                    responseObserver?.onError(error)
                }
            )
    }


    // Get Flashcard by ID
    override fun getFlashCardById(request: FlashCardIdRequest, responseObserver: StreamObserver<ResponseDtoGrpc>?) {
        cardService.getFlashcardById(request.id)
            .onItem().transform { flashCard ->
                val responseBuilder = ResponseDtoGrpc.newBuilder()
                    .setMessage("Success")
                    .setStatusCode(200)

                flashCard ?: throw IllegalStateException("FlashCard does not exist")

                val flashCardDto = flashCard.data as? FlashCardDto

                if (flashCardDto != null) {
                    responseBuilder.setFlashCard(flashCardDto.toProto() ?: FlashCardDtoMessage.getDefaultInstance())
                }


                val response = responseBuilder.build()

                response
            }
            .subscribe().with(
                { response ->
                    responseObserver?.onNext(response)
                    responseObserver?.onCompleted()
                },
                { error ->
                    responseObserver?.onError(error)
                }
            )
    }

    // Update Flashcard
    override fun updateFlashCard(request: FlashCardDtoMessage, responseObserver: StreamObserver<ResponseDtoGrpc>?) {
        val dto = request.toFlashCardDto()

        cardService.updateFlashcard(dto)
            .onItem().transform { flashCard ->
                val responseBuilder = ResponseDtoGrpc.newBuilder()
                    .setMessage("Success")
                    .setStatusCode(200)

                flashCard ?: throw IllegalStateException("FlashCard does not exist")

                val flashCardDto = flashCard.data as? FlashCardDto

                if (flashCardDto != null) {
                    responseBuilder.setFlashCard(flashCardDto.toProto())
                }

                val response = responseBuilder.build()

                response
            }
            .subscribe().with(
                { response ->
                    responseObserver?.onNext(response)
                    responseObserver?.onCompleted()
                },
                { error ->
                    responseObserver?.onError(error)
                }
            )
    }

    // Delete Flashcard
    override fun deleteFlashCard(request: FlashCardIdRequest, responseObserver: StreamObserver<ResponseDtoGrpc>?) {
        cardService.deleteFlashcard(request.id)
            .onItem().transform { flashCard ->

                val responseBuilder = ResponseDtoGrpc.newBuilder()
                    .setMessage("Success")
                    .setStatusCode(203)

                val response = responseBuilder.build()

                response
            }
            .subscribe().with(
                { response ->
                    responseObserver?.onNext(response)
                    responseObserver?.onCompleted()
                },
                { error ->
                    responseObserver?.onError(error)
                }
            )
    }

    // Add Attempt
    override fun addAttempt(request: Flashcard.AttemptDtoMessage, responseObserver: StreamObserver<ResponseDtoGrpc>?) {
        val dto = request.toAttemptDto()

        cardService.addAttempt(dto)
            .onItem().transform { attempt ->
                val responseBuilder = ResponseDtoGrpc.newBuilder()
                    .setMessage("Success")
                    .setStatusCode(200)

                attempt ?: throw IllegalStateException("Attemp does not exist")

                val attemptDto = attempt.data as? AttemptDto

                if (attemptDto != null) {
                    responseBuilder.setAttempt(attemptDto.toProto())
                }

                val response = responseBuilder.build()

                response
            }
            .subscribe().with(
                { response ->
                    responseObserver?.onNext(response)
                    responseObserver?.onCompleted()
                },
                { error ->
                    responseObserver?.onError(error)
                }
            )
    }
}

// Extension functions to convert DtoMessages into service models
fun Flashcard.CardSetDtoMessage.toCardSetDto() = CardSetDto(
    id = id,
    name = name,
    userEmail = userEmail,
    languageName = languageName,
    languageCode = languageCode,
    flashCards = flashCardsList.map { it.toFlashCardDto() }
)

fun Flashcard.FlashCardDtoMessage.toFlashCardDto() = FlashCardDto(
    id = id,
    cardSetId = cardSetId,
    title = title,
    body = body,
    correctAnswer = correctAnswer,
    pointsAwarded = pointsAwarded,
    otherAnswers = otherAnswersList
)

fun Flashcard.AttemptDtoMessage.toAttemptDto() = AttemptDto(
    userEmail = userEmail,
    startedAt = LocalDateTime.parse(startedAt),
    endedAt = LocalDateTime.parse(endedAt),
    hasPassed = hasPassed,
    cardSetId = cardSetId
)

fun CardSetDto.toProto(): CardSetDtoMessage {
    try {
        return CardSetDtoMessage.newBuilder()
            .setId(id ?: "")
            .setName(name)
            .setUserEmail(userEmail)
            .setLanguageName(languageName)
            .setLanguageCode(languageCode)
            .addAllFlashCards(flashCards.map { it.toProto() }) // Convert flashcards
            .build()

    } catch (e: Exception) {
        val a = "z";

        return CardSetDtoMessage.newBuilder().build()
    }
}

fun ArrayList<CardSetDto>.toProto(): CardSetListMessage {
    return CardSetListMessage.newBuilder()
        .addAllCardSets(this.map { it.toProto() })
        .build()
}

fun FlashCardDto.toProto(): FlashCardDtoMessage {
    return FlashCardDtoMessage.newBuilder()
        .setId(id ?: "")
        .setCardSetId(cardSetId ?: "")
        .setTitle(title)
        .setBody(body)
        .setCorrectAnswer(correctAnswer)
        .setPointsAwarded(pointsAwarded)
        .addAllOtherAnswers(otherAnswers)
        .build()
}

fun AttemptDto.toProto(): Flashcard.AttemptDtoMessage {
    val formatter = DateTimeFormatter.ISO_DATE_TIME // ISO 8601 format

    return Flashcard.AttemptDtoMessage.newBuilder()
        .setUserEmail(this.userEmail)
        .setStartedAt(this.startedAt.format(formatter)) // Convert LocalDateTime to ISO string
        .setEndedAt(this.endedAt.format(formatter)) // Convert LocalDateTime to ISO string
        .setHasPassed(this.hasPassed)
        .setCardSetId(this.cardSetId)
        .build()
}