package si.um.kotnik.application.services

import si.um.kotnik.application.dtos.AttemptDto
import si.um.kotnik.application.interfaces.ICardService
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.core.Response
import si.um.kotnik.domain.entities.Attempt
import si.um.kotnik.domain.entities.CardSet
import si.um.kotnik.domain.entities.FlashCard
import si.um.kotnik.repository.FlashCardRepository
import si.um.kotnik.application.dtos.CardSetDto
import si.um.kotnik.application.dtos.FlashCardDto
import si.um.kotnik.application.dtos.ResponseDto
import si.um.kotnik.repository.AttemptRepository
import si.um.kotnik.repository.CardSetRepository
import org.bson.types.ObjectId
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import si.um.kotnik.domain.entities.Answer
import java.util.*

@ApplicationScoped
class CardService : ICardService {

    private val logger: Logger = LoggerFactory.getLogger(CardService::class.java)

    @Inject
    lateinit var cardSetRepository: CardSetRepository

    @Inject
    lateinit var flashcardRepository: FlashCardRepository

    @Inject
    lateinit var attemptRepository: AttemptRepository

    // Helper function to convert ObjectId to String
    private fun ObjectId.toStringId() = this.toHexString()

    // Helper function to convert String to ObjectId
    private fun String.toObjectId() = ObjectId(this)

    override fun addCardSet(dto: CardSetDto): Uni<ResponseDto<Any?>> {
        val cardSet = CardSet(name = dto.name, userEmail = dto.userEmail, languageCode = dto.languageCode)

        cardSetRepository.persist(cardSet)

        logger.debug("Persisted CardSet: $cardSet")

        // Persisting the CardSet and handling the response/reactive flow
        return Uni.createFrom().item(ResponseDto(data = cardSet, statusCode = Response.Status.OK.statusCode))
    }

    override fun addFlashcardToSet(dto: FlashCardDto): Uni<ResponseDto<Any?>> {
        val answers = dto.otherAnswers.map {a -> Answer(
            body = a
        ) }.toMutableList()

        answers.add(Answer(body = dto.correctAnswer, isCorrect = true))

        val flashcard = FlashCard(
            title = dto.title,
            body = dto.body,
            pointsAwarded = dto.pointsAwarded,
            answers = answers
        )

        val cardSet = cardSetRepository.findById(dto.cardSetId!!.toObjectId())

        if (cardSet == null) {
            logger.warn("CardSet not found for ID: ${dto.cardSetId}")

            return Uni.createFrom()
                .item(ResponseDto(data = null, statusCode = Response.Status.NOT_FOUND.statusCode))
        }

        cardSet.flashCards.add(flashcard)

        cardSetRepository.update(cardSet)

        logger.debug("Updated CardSet with new Flashcard: $flashcard")

        return Uni.createFrom().item(ResponseDto(data = flashcard, statusCode = Response.Status.OK.statusCode))
    }

    override fun updateCardSet(dto: CardSetDto): Uni<ResponseDto<Any?>> {
        val cardSet = cardSetRepository.findById(dto.id.toObjectId())

        cardSet.name = dto.name
        cardSet.userEmail = dto.userEmail

        cardSetRepository.update(cardSet)

        logger.debug("Updated CardSet: $cardSet")

        return Uni.createFrom().item(ResponseDto(data = cardSet, statusCode = Response.Status.OK.statusCode))
    }

    override fun deleteCardSet(id: String): Uni<ResponseDto<Any?>> {
        val cardSet = cardSetRepository.findById(id.toObjectId())

        cardSetRepository.delete(cardSet)

        logger.debug("Deleted CardSet: $cardSet")

        return Uni.createFrom().item(ResponseDto(data = cardSet, statusCode = Response.Status.NO_CONTENT.statusCode))
    }

    override fun getCardSetById(id: String): Uni<ResponseDto<Any?>> {
        val cardSet = cardSetRepository.findById(id.toObjectId())

        val dto = CardSetDto(
            name = cardSet.name,
            userEmail = cardSet.userEmail,
            languageCode = cardSet.languageCode,
            flashCards = cardSet.flashCards.map { flashCard ->
                FlashCardDto(
                    id = flashCard.id?.toStringId(),
                    cardSetId = flashCard.cardSet?.toStringId(),
                    title = flashCard.title,
                    body = flashCard.body,
                    correctAnswer = flashCard.answers.first { a -> a.isCorrect }.body,
                    pointsAwarded = flashCard.pointsAwarded,
                    otherAnswers = flashCard.answers.filter { a -> !a.isCorrect}.map { it.body }.toMutableList()
                )
            }
        )

        logger.debug("Found CardSet: $dto")

        return Uni.createFrom().item(ResponseDto(data = dto, statusCode = Response.Status.OK.statusCode))
    }

    override fun getCardSets(): Uni<ResponseDto<Any?>> {
        val cardSets = cardSetRepository.findAll().list<CardSet>()

        val dtos = cardSets.map { cardSet -> CardSetDto(
            name = cardSet.name,
            userEmail = cardSet.userEmail,
            languageCode = cardSet.languageCode
        )}

        logger.debug("Found CardSets: $dtos")

        return Uni.createFrom().item(ResponseDto(data = dtos, statusCode = Response.Status.OK.statusCode))
    }

    override fun getFlashcardById(id: String): Uni<ResponseDto<Any?>> {
        val flashCard = flashcardRepository.findById(id.toObjectId())

        val dto = FlashCardDto(
            id = flashCard.id?.toStringId(),
            cardSetId = flashCard.cardSet?.toStringId(),
            title = flashCard.title,
            body = flashCard.body,
            correctAnswer = flashCard.answers.first { a -> a.isCorrect }.body,
            pointsAwarded = flashCard.pointsAwarded,
            otherAnswers = flashCard.answers.filter { a -> !a.isCorrect}.map { it.body }.toMutableList()
        )

        logger.debug("Found Flashcard: $dto")

        return Uni.createFrom().item(ResponseDto(data = dto, statusCode = Response.Status.OK.statusCode))
    }

    override fun getCardSetsByLanguage(languageName: String?, languageCode: String?): Uni<ResponseDto<Any?>> {
        val cardSets = cardSetRepository.find("languageCode", languageCode).list<CardSet>()

        val dtos = cardSets.map { cardSet -> CardSetDto(
            name = cardSet.name,
            userEmail = cardSet.userEmail,
            languageCode = cardSet.languageCode
        )}

        logger.debug("Found CardSets by Language: $dtos")

        return Uni.createFrom().item(ResponseDto(data = dtos, statusCode = Response.Status.OK.statusCode))
    }

    override fun getCardSetsByUser(email: String): Uni<ResponseDto<Any?>> {
        val cardSets = cardSetRepository.find("userEmail", email).list<CardSet>()

        val dtos = cardSets.map { cardSet -> CardSetDto(
            name = cardSet.name,
            userEmail = cardSet.userEmail,
            languageCode = cardSet.languageCode
        )}

        logger.debug("Found CardSets by User: $dtos")

        return Uni.createFrom().item(ResponseDto(data = dtos, statusCode = Response.Status.OK.statusCode))
    }

    override fun updateFlashcard(dto: FlashCardDto): Uni<ResponseDto<Any?>> {
        val flashCard = flashcardRepository.findById(dto.id!!.toObjectId())

        flashCard.title = dto.title
        flashCard.body = dto.body
        flashCard.pointsAwarded = dto.pointsAwarded

        flashcardRepository.update(flashCard)

        logger.debug("Updated Flashcard: $flashCard")

        return Uni.createFrom().item(ResponseDto(data = flashCard, statusCode = Response.Status.OK.statusCode))
    }

    override fun deleteFlashcard(id: String): Uni<ResponseDto<Any?>> {
        val flashCard = flashcardRepository.findById(id.toObjectId())

        flashcardRepository.delete(flashCard)

        logger.debug("Deleted Flashcard: $flashCard")

        return Uni.createFrom().item(ResponseDto(data = null, statusCode = Response.Status.NO_CONTENT.statusCode))
    }

    override fun addAttempt(dto: AttemptDto): Uni<ResponseDto<Any?>> {
        val attempt = Attempt(
            userEmail = dto.userEmail,
            startedAt = dto.startedAt,
            endedAt = dto.endedAt,
            hasPassed = dto.hasPassed,
            cardSet = dto.cardSetId.toObjectId(),
        )

        attemptRepository.persist(attempt)

        logger.debug("Persisted Attempt: $attempt")

        return Uni.createFrom().item(ResponseDto(data = attempt, statusCode = Response.Status.CREATED.statusCode))
    }

    override fun userHasCardSetAccess(email: String, id: String): Uni<Boolean> {
        val cardSet = cardSetRepository.findById(ObjectId(id))

        return Uni.createFrom().item(cardSet.userEmail.lowercase(Locale.getDefault()) == email.lowercase(Locale.getDefault()))
    }

    override fun userHasFlashcardAccess(email: String, id: String): Uni<Boolean> {
        val flashCard = flashcardRepository.findById(ObjectId(id))
        val cardSet = cardSetRepository.findById(flashCard.cardSet)

        return Uni.createFrom().item(cardSet.userEmail.lowercase(Locale.getDefault()) == email.lowercase(Locale.getDefault()))
    }
}
