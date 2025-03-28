package si.um.kotnik;

import static si.um.kotnik.CardServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: flashcard.proto")
public final class MutinyCardServiceGrpc implements io.quarkus.grpc.MutinyGrpc {

    private MutinyCardServiceGrpc() {
    }

    public static MutinyCardServiceStub newMutinyStub(io.grpc.Channel channel) {
        return new MutinyCardServiceStub(channel);
    }

    public static class MutinyCardServiceStub extends io.grpc.stub.AbstractStub<MutinyCardServiceStub> implements io.quarkus.grpc.MutinyStub {

        private CardServiceGrpc.CardServiceStub delegateStub;

        private MutinyCardServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = CardServiceGrpc.newStub(channel);
        }

        private MutinyCardServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = CardServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected MutinyCardServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new MutinyCardServiceStub(channel, callOptions);
        }

        /**
         * <pre>
         *  CardSet endpoints
         * </pre>
         */
        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> addCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::addCardSet);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetById(si.um.kotnik.Flashcard.CardSetIdRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::getCardSetById);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSets(si.um.kotnik.Flashcard.EmptyRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::getCardSets);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetsByLanguage(si.um.kotnik.Flashcard.LanguageRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::getCardSetsByLanguage);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetsByUser(si.um.kotnik.Flashcard.UserEmailRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::getCardSetsByUser);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> updateCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::updateCardSet);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> deleteCardSet(si.um.kotnik.Flashcard.CardSetIdRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::deleteCardSet);
        }

        /**
         * <pre>
         *  FlashCard endpoints
         * </pre>
         */
        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> addFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::addFlashCard);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getFlashCardById(si.um.kotnik.Flashcard.FlashCardIdRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::getFlashCardById);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> updateFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::updateFlashCard);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> deleteFlashCard(si.um.kotnik.Flashcard.FlashCardIdRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::deleteFlashCard);
        }

        /**
         * <pre>
         *  Attempt endpoints
         * </pre>
         */
        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> addAttempt(si.um.kotnik.Flashcard.AttemptDtoMessage request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::addAttempt);
        }
    }

    public static abstract class CardServiceImplBase implements io.grpc.BindableService {

        private String compression;

        /**
         * Set whether the server will try to use a compressed response.
         *
         * @param compression the compression, e.g {@code gzip}
         */
        public CardServiceImplBase withCompression(String compression) {
            this.compression = compression;
            return this;
        }

        /**
         * <pre>
         *  CardSet endpoints
         * </pre>
         */
        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> addCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetById(si.um.kotnik.Flashcard.CardSetIdRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSets(si.um.kotnik.Flashcard.EmptyRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetsByLanguage(si.um.kotnik.Flashcard.LanguageRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetsByUser(si.um.kotnik.Flashcard.UserEmailRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> updateCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> deleteCardSet(si.um.kotnik.Flashcard.CardSetIdRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         *  FlashCard endpoints
         * </pre>
         */
        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> addFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> getFlashCardById(si.um.kotnik.Flashcard.FlashCardIdRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> updateFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> deleteFlashCard(si.um.kotnik.Flashcard.FlashCardIdRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         *  Attempt endpoints
         * </pre>
         */
        public io.smallrye.mutiny.Uni<si.um.kotnik.Flashcard.ResponseDtoGrpc> addAttempt(si.um.kotnik.Flashcard.AttemptDtoMessage request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(si.um.kotnik.CardServiceGrpc.getAddCardSetMethod(), asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.CardSetDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc>(this, METHODID_ADD_CARD_SET, compression))).addMethod(si.um.kotnik.CardServiceGrpc.getGetCardSetByIdMethod(), asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.CardSetIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>(this, METHODID_GET_CARD_SET_BY_ID, compression))).addMethod(si.um.kotnik.CardServiceGrpc.getGetCardSetsMethod(), asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.EmptyRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>(this, METHODID_GET_CARD_SETS, compression))).addMethod(si.um.kotnik.CardServiceGrpc.getGetCardSetsByLanguageMethod(), asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.LanguageRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>(this, METHODID_GET_CARD_SETS_BY_LANGUAGE, compression))).addMethod(si.um.kotnik.CardServiceGrpc.getGetCardSetsByUserMethod(), asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.UserEmailRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>(this, METHODID_GET_CARD_SETS_BY_USER, compression))).addMethod(si.um.kotnik.CardServiceGrpc.getUpdateCardSetMethod(), asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.CardSetDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc>(this, METHODID_UPDATE_CARD_SET, compression))).addMethod(si.um.kotnik.CardServiceGrpc.getDeleteCardSetMethod(), asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.CardSetIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>(this, METHODID_DELETE_CARD_SET, compression))).addMethod(si.um.kotnik.CardServiceGrpc.getAddFlashCardMethod(), asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.FlashCardDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc>(this, METHODID_ADD_FLASH_CARD, compression))).addMethod(si.um.kotnik.CardServiceGrpc.getGetFlashCardByIdMethod(), asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.FlashCardIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>(this, METHODID_GET_FLASH_CARD_BY_ID, compression))).addMethod(si.um.kotnik.CardServiceGrpc.getUpdateFlashCardMethod(), asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.FlashCardDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc>(this, METHODID_UPDATE_FLASH_CARD, compression))).addMethod(si.um.kotnik.CardServiceGrpc.getDeleteFlashCardMethod(), asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.FlashCardIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>(this, METHODID_DELETE_FLASH_CARD, compression))).addMethod(si.um.kotnik.CardServiceGrpc.getAddAttemptMethod(), asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.AttemptDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc>(this, METHODID_ADD_ATTEMPT, compression))).build();
        }
    }

    private static final int METHODID_ADD_CARD_SET = 0;

    private static final int METHODID_GET_CARD_SET_BY_ID = 1;

    private static final int METHODID_GET_CARD_SETS = 2;

    private static final int METHODID_GET_CARD_SETS_BY_LANGUAGE = 3;

    private static final int METHODID_GET_CARD_SETS_BY_USER = 4;

    private static final int METHODID_UPDATE_CARD_SET = 5;

    private static final int METHODID_DELETE_CARD_SET = 6;

    private static final int METHODID_ADD_FLASH_CARD = 7;

    private static final int METHODID_GET_FLASH_CARD_BY_ID = 8;

    private static final int METHODID_UPDATE_FLASH_CARD = 9;

    private static final int METHODID_DELETE_FLASH_CARD = 10;

    private static final int METHODID_ADD_ATTEMPT = 11;

    private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>, io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final CardServiceImplBase serviceImpl;

        private final int methodId;

        private final String compression;

        MethodHandlers(CardServiceImplBase serviceImpl, int methodId, String compression) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
            this.compression = compression;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                case METHODID_ADD_CARD_SET:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((si.um.kotnik.Flashcard.CardSetDtoMessage) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver, compression, serviceImpl::addCardSet);
                    break;
                case METHODID_GET_CARD_SET_BY_ID:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((si.um.kotnik.Flashcard.CardSetIdRequest) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver, compression, serviceImpl::getCardSetById);
                    break;
                case METHODID_GET_CARD_SETS:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((si.um.kotnik.Flashcard.EmptyRequest) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver, compression, serviceImpl::getCardSets);
                    break;
                case METHODID_GET_CARD_SETS_BY_LANGUAGE:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((si.um.kotnik.Flashcard.LanguageRequest) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver, compression, serviceImpl::getCardSetsByLanguage);
                    break;
                case METHODID_GET_CARD_SETS_BY_USER:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((si.um.kotnik.Flashcard.UserEmailRequest) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver, compression, serviceImpl::getCardSetsByUser);
                    break;
                case METHODID_UPDATE_CARD_SET:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((si.um.kotnik.Flashcard.CardSetDtoMessage) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver, compression, serviceImpl::updateCardSet);
                    break;
                case METHODID_DELETE_CARD_SET:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((si.um.kotnik.Flashcard.CardSetIdRequest) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver, compression, serviceImpl::deleteCardSet);
                    break;
                case METHODID_ADD_FLASH_CARD:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((si.um.kotnik.Flashcard.FlashCardDtoMessage) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver, compression, serviceImpl::addFlashCard);
                    break;
                case METHODID_GET_FLASH_CARD_BY_ID:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((si.um.kotnik.Flashcard.FlashCardIdRequest) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver, compression, serviceImpl::getFlashCardById);
                    break;
                case METHODID_UPDATE_FLASH_CARD:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((si.um.kotnik.Flashcard.FlashCardDtoMessage) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver, compression, serviceImpl::updateFlashCard);
                    break;
                case METHODID_DELETE_FLASH_CARD:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((si.um.kotnik.Flashcard.FlashCardIdRequest) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver, compression, serviceImpl::deleteFlashCard);
                    break;
                case METHODID_ADD_ATTEMPT:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((si.um.kotnik.Flashcard.AttemptDtoMessage) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver, compression, serviceImpl::addAttempt);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }
}
