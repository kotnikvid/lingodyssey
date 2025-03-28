package si.um.kotnik.application.services

import io.quarkus.mongodb.panache.PanacheQuery
import org.junit.jupiter.api.Test
import jakarta.ws.rs.core.Response
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import si.um.kotnik.application.dtos.*
import si.um.kotnik.domain.entities.*
import si.um.kotnik.repository.*
import java.time.LocalDateTime

class CardServiceTest {
    @InjectMocks
    lateinit var cardService: CardService

    @Mock
    lateinit var cardSetRepository: CardSetRepository

    @Mock
    lateinit var flashcardRepository: FlashCardRepository

    @Mock
    lateinit var attemptRepository: AttemptRepository

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    private fun String.toObjectId() = ObjectId(this)

    @Test
    fun `test addCardSet should persist card set and return OK`() {
        val dto = CardSetDto(name = "Test Set", userEmail = "user@example.com", languageCode = "en")
        val cardSet = CardSet(name = dto.name, userEmail = dto.userEmail, languageCode = dto.languageCode)

        doNothing().`when`(cardSetRepository).persist(cardSet) // Mocking void method

        val result = cardService.addCardSet(dto).await().indefinitely()

        assertEquals(Response.Status.OK.statusCode, result.statusCode)
        assertEquals(dto.name, (result.data as CardSet).name)
    }

    @Test
    fun `test addFlashcardToSet should add flashcard and return OK`() {
        val cardSetId = ObjectId.get().toHexString()
        val dto = FlashCardDto(
            id = cardSetId,
            cardSetId = cardSetId,
            title = "Flashcard Title",
            body = "Flashcard Body",
            pointsAwarded = 10,
            correctAnswer = "Answer",
            otherAnswers = listOf("Answer1", "Answer2")
        )

        val cardSet = CardSet(id = cardSetId.toObjectId(), name = "Test Set", userEmail = "user@example.com", languageCode = "en")
        val flashcard = FlashCard(title = dto.title, body = dto.body, pointsAwarded = dto.pointsAwarded)

        `when`(cardSetRepository.findById(dto.cardSetId!!.toObjectId())).thenReturn(cardSet)
        doNothing().`when`(cardSetRepository).update(cardSet)

        val result = cardService.addFlashcardToSet(dto).await().indefinitely()

        assertEquals(Response.Status.OK.statusCode, result.statusCode)
        assertEquals(dto.title, (result.data as FlashCard).title)
    }

    @Test
    fun `test updateCardSet should update card set and return OK`() {
        val id = ObjectId.get().toHexString()

        val dto = CardSetDto(id = id, name = "Updated Set", userEmail = "user@example.com", languageCode = "en")
        val cardSet = CardSet(id = dto.id.toObjectId(), name = "Old Set", userEmail = "olduser@example.com", languageCode = "en")

        `when`(cardSetRepository.findById(dto.id.toObjectId())).thenReturn(cardSet)

        doNothing().`when`(cardSetRepository).update(cardSet)

        val result = cardService.updateCardSet(dto).await().indefinitely()

        assertEquals(Response.Status.OK.statusCode, result.statusCode)
        assertEquals(dto.name, (result.data as CardSet).name)
    }

    @Test
    fun `test deleteCardSet should delete card set and return NO_CONTENT`() {
        val id = ObjectId.get().toHexString()
        val cardSet = CardSet(id = id.toObjectId(), name = "Test Set", userEmail = "user@example.com", languageCode = "en")

        `when`(cardSetRepository.findById(id.toObjectId())).thenReturn(cardSet)

        doNothing().`when`(cardSetRepository).delete(cardSet)

        val result = cardService.deleteCardSet(id).await().indefinitely()

        assertEquals(Response.Status.NO_CONTENT.statusCode, result.statusCode)
    }

    @Test
    fun `test getCardSets should return a list of card sets`() {
        val id = ObjectId.get()

        val cardSet = CardSet(id = id, name = "Test Set", userEmail = "user@example.com", languageCode = "en")

        // Mock the PanacheQuery to return a list of card sets when list() is called
        val panacheQuery: PanacheQuery<*>? = mock(PanacheQuery::class.java)
        `when`(cardSetRepository.findAll()).thenReturn(panacheQuery as PanacheQuery<CardSet>?)
        if (panacheQuery != null) {
            `when`(panacheQuery.list<CardSet>()).thenReturn(listOf(cardSet))
        }

        val result = cardService.getCardSets().await().indefinitely()

        assertEquals(Response.Status.OK.statusCode, result.statusCode)
        assertEquals(1, (result.data as List<*>).size)
    }


    @Test
    fun `test addAttempt should persist attempt and return CREATED`() {
        val dto = AttemptDto(userEmail = "user@example.com", startedAt = LocalDateTime.now(), endedAt = LocalDateTime.now(), hasPassed = true, cardSetId = ObjectId.get().toHexString())
        val attempt = Attempt(userEmail = dto.userEmail, startedAt = dto.startedAt, endedAt = dto.endedAt, hasPassed = dto.hasPassed, cardSet = dto.cardSetId.toObjectId())

        doNothing().`when`(attemptRepository).persist(attempt)

        val result = cardService.addAttempt(dto).await().indefinitely()

        assertEquals(Response.Status.CREATED.statusCode, result.statusCode)
        assertEquals(dto.userEmail, (result.data as Attempt).userEmail)
    }

    @Test
    fun `test userHasCardSetAccess should return true when user has access`() {
        val email = "user@example.com"
        val cardSetId = ObjectId.get().toHexString()
        val cardSet = CardSet(id = cardSetId.toObjectId(), userEmail = email, name = "Test Set", languageCode = "en")

        `when`(cardSetRepository.findById(cardSetId.toObjectId())).thenReturn(cardSet)

        val result = cardService.userHasCardSetAccess(email, cardSetId).await().indefinitely()

        assertEquals(true, result)
    }

    @Test
    fun `test userHasFlashcardAccess should return true when user has access`() {
        val email = "user@example.com"
        val flashCardId = ObjectId.get().toHexString()
        val flashCard = FlashCard(id = flashCardId.toObjectId(), title = "Flashcard Title", body = "Flashcard Body", pointsAwarded = 10)
        val cardSet = CardSet(id = ObjectId.get(), userEmail = email, name = "Test Set", languageCode = "en")
        flashCard.cardSet = cardSet.id

        `when`(flashcardRepository.findById(flashCardId.toObjectId())).thenReturn(flashCard)
        `when`(cardSetRepository.findById(flashCard.cardSet)).thenReturn(cardSet)

        val result = cardService.userHasFlashcardAccess(email, flashCardId).await().indefinitely()

        assertEquals(true, result)
    }
}