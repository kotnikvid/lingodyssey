package si.um.kotnik;

import io.quarkus.grpc.MutinyService;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: flashcard.proto")
public interface CardService extends MutinyService {

    /**
     * <pre>
     *  CardSet endpoints
     * </pre>
     */
    io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> addCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request);

    io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetById(si.um.kotnik.Flashcard.CardSetIdRequest request);

    io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSets(si.um.kotnik.Flashcard.EmptyRequest request);

    io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetsByLanguage(si.um.kotnik.Flashcard.LanguageRequest request);

    io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetsByUser(si.um.kotnik.Flashcard.UserEmailRequest request);

    io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> updateCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request);

    io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> deleteCardSet(si.um.kotnik.Flashcard.CardSetIdRequest request);

    /**
     * <pre>
     *  FlashCard endpoints
     * </pre>
     */
    io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> addFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request);

    io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getFlashCardById(si.um.kotnik.Flashcard.FlashCardIdRequest request);

    io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> updateFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request);

    io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> deleteFlashCard(si.um.kotnik.Flashcard.FlashCardIdRequest request);

    /**
     * <pre>
     *  Attempt endpoints
     * </pre>
     */
    io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> addAttempt(si.um.kotnik.Flashcard.AttemptDtoMessage request);
}
