package si.um.kotnik;

import io.grpc.BindableService;
import io.quarkus.grpc.GrpcService;
import io.quarkus.grpc.MutinyBean;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: flashcard.proto")
public class CardServiceBean extends MutinyCardServiceGrpc.CardServiceImplBase implements BindableService, MutinyBean {

    private final CardService delegate;

    CardServiceBean(@GrpcService CardService delegate) {
        this.delegate = delegate;
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> addCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request) {
        try {
            return delegate.addCardSet(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetById(si.um.kotnik.Flashcard.CardSetIdRequest request) {
        try {
            return delegate.getCardSetById(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSets(si.um.kotnik.Flashcard.EmptyRequest request) {
        try {
            return delegate.getCardSets(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetsByLanguage(si.um.kotnik.Flashcard.LanguageRequest request) {
        try {
            return delegate.getCardSetsByLanguage(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetsByUser(si.um.kotnik.Flashcard.UserEmailRequest request) {
        try {
            return delegate.getCardSetsByUser(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> updateCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request) {
        try {
            return delegate.updateCardSet(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> deleteCardSet(si.um.kotnik.Flashcard.CardSetIdRequest request) {
        try {
            return delegate.deleteCardSet(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> addFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request) {
        try {
            return delegate.addFlashCard(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getFlashCardById(si.um.kotnik.Flashcard.FlashCardIdRequest request) {
        try {
            return delegate.getFlashCardById(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> updateFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request) {
        try {
            return delegate.updateFlashCard(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> deleteFlashCard(si.um.kotnik.Flashcard.FlashCardIdRequest request) {
        try {
            return delegate.deleteFlashCard(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> addAttempt(si.um.kotnik.Flashcard.AttemptDtoMessage request) {
        try {
            return delegate.addAttempt(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }
}
