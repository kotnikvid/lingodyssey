package si.um.kotnik;

import java.util.function.BiFunction;
import io.quarkus.grpc.MutinyClient;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: flashcard.proto")
public class CardServiceClient implements CardService, MutinyClient<MutinyCardServiceGrpc.MutinyCardServiceStub> {

    private final MutinyCardServiceGrpc.MutinyCardServiceStub stub;

    public CardServiceClient(String name, io.grpc.Channel channel, BiFunction<String, MutinyCardServiceGrpc.MutinyCardServiceStub, MutinyCardServiceGrpc.MutinyCardServiceStub> stubConfigurator) {
        this.stub = stubConfigurator.apply(name, MutinyCardServiceGrpc.newMutinyStub(channel));
    }

    private CardServiceClient(MutinyCardServiceGrpc.MutinyCardServiceStub stub) {
        this.stub = stub;
    }

    public CardServiceClient newInstanceWithStub(MutinyCardServiceGrpc.MutinyCardServiceStub stub) {
        return new CardServiceClient(stub);
    }

    @Override
    public MutinyCardServiceGrpc.MutinyCardServiceStub getStub() {
        return stub;
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> addCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request) {
        return stub.addCardSet(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetById(si.um.kotnik.Flashcard.CardSetIdRequest request) {
        return stub.getCardSetById(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSets(si.um.kotnik.Flashcard.EmptyRequest request) {
        return stub.getCardSets(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetsByLanguage(si.um.kotnik.Flashcard.LanguageRequest request) {
        return stub.getCardSetsByLanguage(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetsByUser(si.um.kotnik.Flashcard.UserEmailRequest request) {
        return stub.getCardSetsByUser(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> updateCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request) {
        return stub.updateCardSet(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> deleteCardSet(si.um.kotnik.Flashcard.CardSetIdRequest request) {
        return stub.deleteCardSet(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> addFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request) {
        return stub.addFlashCard(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getFlashCardById(si.um.kotnik.Flashcard.FlashCardIdRequest request) {
        return stub.getFlashCardById(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> updateFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request) {
        return stub.updateFlashCard(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> deleteFlashCard(si.um.kotnik.Flashcard.FlashCardIdRequest request) {
        return stub.deleteFlashCard(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> addAttempt(si.um.kotnik.Flashcard.AttemptDtoMessage request) {
        return stub.addAttempt(request);
    }
}
