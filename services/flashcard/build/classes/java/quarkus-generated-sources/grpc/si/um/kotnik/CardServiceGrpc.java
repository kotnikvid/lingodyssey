package si.um.kotnik;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.quarkus.Generated(value = "by gRPC proto compiler (version 1.59.0)", comments = "Source: flashcard.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CardServiceGrpc {

    private CardServiceGrpc() {
    }

    public static final java.lang.String SERVICE_NAME = "si.um.kotnik.CardService";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.CardSetDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc> getAddCardSetMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "AddCardSet", requestType = si.um.kotnik.Flashcard.CardSetDtoMessage.class, responseType = si.um.kotnik.Flashcard.ResponseDtoGrpc.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.CardSetDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc> getAddCardSetMethod() {
        io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.CardSetDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc> getAddCardSetMethod;
        if ((getAddCardSetMethod = CardServiceGrpc.getAddCardSetMethod) == null) {
            synchronized (CardServiceGrpc.class) {
                if ((getAddCardSetMethod = CardServiceGrpc.getAddCardSetMethod) == null) {
                    CardServiceGrpc.getAddCardSetMethod = getAddCardSetMethod = io.grpc.MethodDescriptor.<si.um.kotnik.Flashcard.CardSetDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddCardSet")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.CardSetDtoMessage.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.ResponseDtoGrpc.getDefaultInstance())).setSchemaDescriptor(new CardServiceMethodDescriptorSupplier("AddCardSet")).build();
                }
            }
        }
        return getAddCardSetMethod;
    }

    private static volatile io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.CardSetIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getGetCardSetByIdMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "GetCardSetById", requestType = si.um.kotnik.Flashcard.CardSetIdRequest.class, responseType = si.um.kotnik.Flashcard.ResponseDtoGrpc.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.CardSetIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getGetCardSetByIdMethod() {
        io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.CardSetIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getGetCardSetByIdMethod;
        if ((getGetCardSetByIdMethod = CardServiceGrpc.getGetCardSetByIdMethod) == null) {
            synchronized (CardServiceGrpc.class) {
                if ((getGetCardSetByIdMethod = CardServiceGrpc.getGetCardSetByIdMethod) == null) {
                    CardServiceGrpc.getGetCardSetByIdMethod = getGetCardSetByIdMethod = io.grpc.MethodDescriptor.<si.um.kotnik.Flashcard.CardSetIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCardSetById")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.CardSetIdRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.ResponseDtoGrpc.getDefaultInstance())).setSchemaDescriptor(new CardServiceMethodDescriptorSupplier("GetCardSetById")).build();
                }
            }
        }
        return getGetCardSetByIdMethod;
    }

    private static volatile io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.EmptyRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getGetCardSetsMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "GetCardSets", requestType = si.um.kotnik.Flashcard.EmptyRequest.class, responseType = si.um.kotnik.Flashcard.ResponseDtoGrpc.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.EmptyRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getGetCardSetsMethod() {
        io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.EmptyRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getGetCardSetsMethod;
        if ((getGetCardSetsMethod = CardServiceGrpc.getGetCardSetsMethod) == null) {
            synchronized (CardServiceGrpc.class) {
                if ((getGetCardSetsMethod = CardServiceGrpc.getGetCardSetsMethod) == null) {
                    CardServiceGrpc.getGetCardSetsMethod = getGetCardSetsMethod = io.grpc.MethodDescriptor.<si.um.kotnik.Flashcard.EmptyRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCardSets")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.EmptyRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.ResponseDtoGrpc.getDefaultInstance())).setSchemaDescriptor(new CardServiceMethodDescriptorSupplier("GetCardSets")).build();
                }
            }
        }
        return getGetCardSetsMethod;
    }

    private static volatile io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.LanguageRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getGetCardSetsByLanguageMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "GetCardSetsByLanguage", requestType = si.um.kotnik.Flashcard.LanguageRequest.class, responseType = si.um.kotnik.Flashcard.ResponseDtoGrpc.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.LanguageRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getGetCardSetsByLanguageMethod() {
        io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.LanguageRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getGetCardSetsByLanguageMethod;
        if ((getGetCardSetsByLanguageMethod = CardServiceGrpc.getGetCardSetsByLanguageMethod) == null) {
            synchronized (CardServiceGrpc.class) {
                if ((getGetCardSetsByLanguageMethod = CardServiceGrpc.getGetCardSetsByLanguageMethod) == null) {
                    CardServiceGrpc.getGetCardSetsByLanguageMethod = getGetCardSetsByLanguageMethod = io.grpc.MethodDescriptor.<si.um.kotnik.Flashcard.LanguageRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCardSetsByLanguage")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.LanguageRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.ResponseDtoGrpc.getDefaultInstance())).setSchemaDescriptor(new CardServiceMethodDescriptorSupplier("GetCardSetsByLanguage")).build();
                }
            }
        }
        return getGetCardSetsByLanguageMethod;
    }

    private static volatile io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.UserEmailRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getGetCardSetsByUserMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "GetCardSetsByUser", requestType = si.um.kotnik.Flashcard.UserEmailRequest.class, responseType = si.um.kotnik.Flashcard.ResponseDtoGrpc.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.UserEmailRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getGetCardSetsByUserMethod() {
        io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.UserEmailRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getGetCardSetsByUserMethod;
        if ((getGetCardSetsByUserMethod = CardServiceGrpc.getGetCardSetsByUserMethod) == null) {
            synchronized (CardServiceGrpc.class) {
                if ((getGetCardSetsByUserMethod = CardServiceGrpc.getGetCardSetsByUserMethod) == null) {
                    CardServiceGrpc.getGetCardSetsByUserMethod = getGetCardSetsByUserMethod = io.grpc.MethodDescriptor.<si.um.kotnik.Flashcard.UserEmailRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCardSetsByUser")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.UserEmailRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.ResponseDtoGrpc.getDefaultInstance())).setSchemaDescriptor(new CardServiceMethodDescriptorSupplier("GetCardSetsByUser")).build();
                }
            }
        }
        return getGetCardSetsByUserMethod;
    }

    private static volatile io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.CardSetDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc> getUpdateCardSetMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "UpdateCardSet", requestType = si.um.kotnik.Flashcard.CardSetDtoMessage.class, responseType = si.um.kotnik.Flashcard.ResponseDtoGrpc.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.CardSetDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc> getUpdateCardSetMethod() {
        io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.CardSetDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc> getUpdateCardSetMethod;
        if ((getUpdateCardSetMethod = CardServiceGrpc.getUpdateCardSetMethod) == null) {
            synchronized (CardServiceGrpc.class) {
                if ((getUpdateCardSetMethod = CardServiceGrpc.getUpdateCardSetMethod) == null) {
                    CardServiceGrpc.getUpdateCardSetMethod = getUpdateCardSetMethod = io.grpc.MethodDescriptor.<si.um.kotnik.Flashcard.CardSetDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateCardSet")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.CardSetDtoMessage.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.ResponseDtoGrpc.getDefaultInstance())).setSchemaDescriptor(new CardServiceMethodDescriptorSupplier("UpdateCardSet")).build();
                }
            }
        }
        return getUpdateCardSetMethod;
    }

    private static volatile io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.CardSetIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getDeleteCardSetMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "DeleteCardSet", requestType = si.um.kotnik.Flashcard.CardSetIdRequest.class, responseType = si.um.kotnik.Flashcard.ResponseDtoGrpc.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.CardSetIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getDeleteCardSetMethod() {
        io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.CardSetIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getDeleteCardSetMethod;
        if ((getDeleteCardSetMethod = CardServiceGrpc.getDeleteCardSetMethod) == null) {
            synchronized (CardServiceGrpc.class) {
                if ((getDeleteCardSetMethod = CardServiceGrpc.getDeleteCardSetMethod) == null) {
                    CardServiceGrpc.getDeleteCardSetMethod = getDeleteCardSetMethod = io.grpc.MethodDescriptor.<si.um.kotnik.Flashcard.CardSetIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteCardSet")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.CardSetIdRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.ResponseDtoGrpc.getDefaultInstance())).setSchemaDescriptor(new CardServiceMethodDescriptorSupplier("DeleteCardSet")).build();
                }
            }
        }
        return getDeleteCardSetMethod;
    }

    private static volatile io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.FlashCardDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc> getAddFlashCardMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "AddFlashCard", requestType = si.um.kotnik.Flashcard.FlashCardDtoMessage.class, responseType = si.um.kotnik.Flashcard.ResponseDtoGrpc.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.FlashCardDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc> getAddFlashCardMethod() {
        io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.FlashCardDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc> getAddFlashCardMethod;
        if ((getAddFlashCardMethod = CardServiceGrpc.getAddFlashCardMethod) == null) {
            synchronized (CardServiceGrpc.class) {
                if ((getAddFlashCardMethod = CardServiceGrpc.getAddFlashCardMethod) == null) {
                    CardServiceGrpc.getAddFlashCardMethod = getAddFlashCardMethod = io.grpc.MethodDescriptor.<si.um.kotnik.Flashcard.FlashCardDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddFlashCard")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.FlashCardDtoMessage.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.ResponseDtoGrpc.getDefaultInstance())).setSchemaDescriptor(new CardServiceMethodDescriptorSupplier("AddFlashCard")).build();
                }
            }
        }
        return getAddFlashCardMethod;
    }

    private static volatile io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.FlashCardIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getGetFlashCardByIdMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "GetFlashCardById", requestType = si.um.kotnik.Flashcard.FlashCardIdRequest.class, responseType = si.um.kotnik.Flashcard.ResponseDtoGrpc.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.FlashCardIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getGetFlashCardByIdMethod() {
        io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.FlashCardIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getGetFlashCardByIdMethod;
        if ((getGetFlashCardByIdMethod = CardServiceGrpc.getGetFlashCardByIdMethod) == null) {
            synchronized (CardServiceGrpc.class) {
                if ((getGetFlashCardByIdMethod = CardServiceGrpc.getGetFlashCardByIdMethod) == null) {
                    CardServiceGrpc.getGetFlashCardByIdMethod = getGetFlashCardByIdMethod = io.grpc.MethodDescriptor.<si.um.kotnik.Flashcard.FlashCardIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetFlashCardById")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.FlashCardIdRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.ResponseDtoGrpc.getDefaultInstance())).setSchemaDescriptor(new CardServiceMethodDescriptorSupplier("GetFlashCardById")).build();
                }
            }
        }
        return getGetFlashCardByIdMethod;
    }

    private static volatile io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.FlashCardDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc> getUpdateFlashCardMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "UpdateFlashCard", requestType = si.um.kotnik.Flashcard.FlashCardDtoMessage.class, responseType = si.um.kotnik.Flashcard.ResponseDtoGrpc.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.FlashCardDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc> getUpdateFlashCardMethod() {
        io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.FlashCardDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc> getUpdateFlashCardMethod;
        if ((getUpdateFlashCardMethod = CardServiceGrpc.getUpdateFlashCardMethod) == null) {
            synchronized (CardServiceGrpc.class) {
                if ((getUpdateFlashCardMethod = CardServiceGrpc.getUpdateFlashCardMethod) == null) {
                    CardServiceGrpc.getUpdateFlashCardMethod = getUpdateFlashCardMethod = io.grpc.MethodDescriptor.<si.um.kotnik.Flashcard.FlashCardDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateFlashCard")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.FlashCardDtoMessage.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.ResponseDtoGrpc.getDefaultInstance())).setSchemaDescriptor(new CardServiceMethodDescriptorSupplier("UpdateFlashCard")).build();
                }
            }
        }
        return getUpdateFlashCardMethod;
    }

    private static volatile io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.FlashCardIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getDeleteFlashCardMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "DeleteFlashCard", requestType = si.um.kotnik.Flashcard.FlashCardIdRequest.class, responseType = si.um.kotnik.Flashcard.ResponseDtoGrpc.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.FlashCardIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getDeleteFlashCardMethod() {
        io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.FlashCardIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc> getDeleteFlashCardMethod;
        if ((getDeleteFlashCardMethod = CardServiceGrpc.getDeleteFlashCardMethod) == null) {
            synchronized (CardServiceGrpc.class) {
                if ((getDeleteFlashCardMethod = CardServiceGrpc.getDeleteFlashCardMethod) == null) {
                    CardServiceGrpc.getDeleteFlashCardMethod = getDeleteFlashCardMethod = io.grpc.MethodDescriptor.<si.um.kotnik.Flashcard.FlashCardIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteFlashCard")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.FlashCardIdRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.ResponseDtoGrpc.getDefaultInstance())).setSchemaDescriptor(new CardServiceMethodDescriptorSupplier("DeleteFlashCard")).build();
                }
            }
        }
        return getDeleteFlashCardMethod;
    }

    private static volatile io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.AttemptDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc> getAddAttemptMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "AddAttempt", requestType = si.um.kotnik.Flashcard.AttemptDtoMessage.class, responseType = si.um.kotnik.Flashcard.ResponseDtoGrpc.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.AttemptDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc> getAddAttemptMethod() {
        io.grpc.MethodDescriptor<si.um.kotnik.Flashcard.AttemptDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc> getAddAttemptMethod;
        if ((getAddAttemptMethod = CardServiceGrpc.getAddAttemptMethod) == null) {
            synchronized (CardServiceGrpc.class) {
                if ((getAddAttemptMethod = CardServiceGrpc.getAddAttemptMethod) == null) {
                    CardServiceGrpc.getAddAttemptMethod = getAddAttemptMethod = io.grpc.MethodDescriptor.<si.um.kotnik.Flashcard.AttemptDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddAttempt")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.AttemptDtoMessage.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(si.um.kotnik.Flashcard.ResponseDtoGrpc.getDefaultInstance())).setSchemaDescriptor(new CardServiceMethodDescriptorSupplier("AddAttempt")).build();
                }
            }
        }
        return getAddAttemptMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static CardServiceStub newStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<CardServiceStub> factory = new io.grpc.stub.AbstractStub.StubFactory<CardServiceStub>() {

            @java.lang.Override
            public CardServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new CardServiceStub(channel, callOptions);
            }
        };
        return CardServiceStub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static CardServiceBlockingStub newBlockingStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<CardServiceBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<CardServiceBlockingStub>() {

            @java.lang.Override
            public CardServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new CardServiceBlockingStub(channel, callOptions);
            }
        };
        return CardServiceBlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static CardServiceFutureStub newFutureStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<CardServiceFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<CardServiceFutureStub>() {

            @java.lang.Override
            public CardServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new CardServiceFutureStub(channel, callOptions);
            }
        };
        return CardServiceFutureStub.newStub(factory, channel);
    }

    /**
     */
    public interface AsyncService {

        /**
         * <pre>
         * CardSet endpoints
         * </pre>
         */
        default void addCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddCardSetMethod(), responseObserver);
        }

        /**
         */
        default void getCardSetById(si.um.kotnik.Flashcard.CardSetIdRequest request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCardSetByIdMethod(), responseObserver);
        }

        /**
         */
        default void getCardSets(si.um.kotnik.Flashcard.EmptyRequest request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCardSetsMethod(), responseObserver);
        }

        /**
         */
        default void getCardSetsByLanguage(si.um.kotnik.Flashcard.LanguageRequest request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCardSetsByLanguageMethod(), responseObserver);
        }

        /**
         */
        default void getCardSetsByUser(si.um.kotnik.Flashcard.UserEmailRequest request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCardSetsByUserMethod(), responseObserver);
        }

        /**
         */
        default void updateCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateCardSetMethod(), responseObserver);
        }

        /**
         */
        default void deleteCardSet(si.um.kotnik.Flashcard.CardSetIdRequest request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteCardSetMethod(), responseObserver);
        }

        /**
         * <pre>
         * FlashCard endpoints
         * </pre>
         */
        default void addFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddFlashCardMethod(), responseObserver);
        }

        /**
         */
        default void getFlashCardById(si.um.kotnik.Flashcard.FlashCardIdRequest request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetFlashCardByIdMethod(), responseObserver);
        }

        /**
         */
        default void updateFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateFlashCardMethod(), responseObserver);
        }

        /**
         */
        default void deleteFlashCard(si.um.kotnik.Flashcard.FlashCardIdRequest request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteFlashCardMethod(), responseObserver);
        }

        /**
         * <pre>
         * Attempt endpoints
         * </pre>
         */
        default void addAttempt(si.um.kotnik.Flashcard.AttemptDtoMessage request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddAttemptMethod(), responseObserver);
        }
    }

    /**
     * Base class for the server implementation of the service CardService.
     */
    public static abstract class CardServiceImplBase implements io.grpc.BindableService, AsyncService {

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return CardServiceGrpc.bindService(this);
        }
    }

    /**
     * A stub to allow clients to do asynchronous rpc calls to service CardService.
     */
    public static class CardServiceStub extends io.grpc.stub.AbstractAsyncStub<CardServiceStub> {

        private CardServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected CardServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new CardServiceStub(channel, callOptions);
        }

        /**
         * <pre>
         * CardSet endpoints
         * </pre>
         */
        public void addCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getAddCardSetMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void getCardSetById(si.um.kotnik.Flashcard.CardSetIdRequest request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getGetCardSetByIdMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void getCardSets(si.um.kotnik.Flashcard.EmptyRequest request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getGetCardSetsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void getCardSetsByLanguage(si.um.kotnik.Flashcard.LanguageRequest request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getGetCardSetsByLanguageMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void getCardSetsByUser(si.um.kotnik.Flashcard.UserEmailRequest request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getGetCardSetsByUserMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void updateCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getUpdateCardSetMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void deleteCardSet(si.um.kotnik.Flashcard.CardSetIdRequest request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getDeleteCardSetMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * FlashCard endpoints
         * </pre>
         */
        public void addFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getAddFlashCardMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void getFlashCardById(si.um.kotnik.Flashcard.FlashCardIdRequest request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getGetFlashCardByIdMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void updateFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getUpdateFlashCardMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void deleteFlashCard(si.um.kotnik.Flashcard.FlashCardIdRequest request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getDeleteFlashCardMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * Attempt endpoints
         * </pre>
         */
        public void addAttempt(si.um.kotnik.Flashcard.AttemptDtoMessage request, io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getAddAttemptMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     * A stub to allow clients to do synchronous rpc calls to service CardService.
     */
    public static class CardServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<CardServiceBlockingStub> {

        private CardServiceBlockingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected CardServiceBlockingStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new CardServiceBlockingStub(channel, callOptions);
        }

        /**
         * <pre>
         * CardSet endpoints
         * </pre>
         */
        public si.um.kotnik.Flashcard.ResponseDtoGrpc addCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getAddCardSetMethod(), getCallOptions(), request);
        }

        /**
         */
        public si.um.kotnik.Flashcard.ResponseDtoGrpc getCardSetById(si.um.kotnik.Flashcard.CardSetIdRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getGetCardSetByIdMethod(), getCallOptions(), request);
        }

        /**
         */
        public si.um.kotnik.Flashcard.ResponseDtoGrpc getCardSets(si.um.kotnik.Flashcard.EmptyRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getGetCardSetsMethod(), getCallOptions(), request);
        }

        /**
         */
        public si.um.kotnik.Flashcard.ResponseDtoGrpc getCardSetsByLanguage(si.um.kotnik.Flashcard.LanguageRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getGetCardSetsByLanguageMethod(), getCallOptions(), request);
        }

        /**
         */
        public si.um.kotnik.Flashcard.ResponseDtoGrpc getCardSetsByUser(si.um.kotnik.Flashcard.UserEmailRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getGetCardSetsByUserMethod(), getCallOptions(), request);
        }

        /**
         */
        public si.um.kotnik.Flashcard.ResponseDtoGrpc updateCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getUpdateCardSetMethod(), getCallOptions(), request);
        }

        /**
         */
        public si.um.kotnik.Flashcard.ResponseDtoGrpc deleteCardSet(si.um.kotnik.Flashcard.CardSetIdRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getDeleteCardSetMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * FlashCard endpoints
         * </pre>
         */
        public si.um.kotnik.Flashcard.ResponseDtoGrpc addFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getAddFlashCardMethod(), getCallOptions(), request);
        }

        /**
         */
        public si.um.kotnik.Flashcard.ResponseDtoGrpc getFlashCardById(si.um.kotnik.Flashcard.FlashCardIdRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getGetFlashCardByIdMethod(), getCallOptions(), request);
        }

        /**
         */
        public si.um.kotnik.Flashcard.ResponseDtoGrpc updateFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getUpdateFlashCardMethod(), getCallOptions(), request);
        }

        /**
         */
        public si.um.kotnik.Flashcard.ResponseDtoGrpc deleteFlashCard(si.um.kotnik.Flashcard.FlashCardIdRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getDeleteFlashCardMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * Attempt endpoints
         * </pre>
         */
        public si.um.kotnik.Flashcard.ResponseDtoGrpc addAttempt(si.um.kotnik.Flashcard.AttemptDtoMessage request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getAddAttemptMethod(), getCallOptions(), request);
        }
    }

    /**
     * A stub to allow clients to do ListenableFuture-style rpc calls to service CardService.
     */
    public static class CardServiceFutureStub extends io.grpc.stub.AbstractFutureStub<CardServiceFutureStub> {

        private CardServiceFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected CardServiceFutureStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new CardServiceFutureStub(channel, callOptions);
        }

        /**
         * <pre>
         * CardSet endpoints
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<si.um.kotnik.Flashcard.ResponseDtoGrpc> addCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getAddCardSetMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetById(si.um.kotnik.Flashcard.CardSetIdRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getGetCardSetByIdMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSets(si.um.kotnik.Flashcard.EmptyRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getGetCardSetsMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetsByLanguage(si.um.kotnik.Flashcard.LanguageRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getGetCardSetsByLanguageMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<si.um.kotnik.Flashcard.ResponseDtoGrpc> getCardSetsByUser(si.um.kotnik.Flashcard.UserEmailRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getGetCardSetsByUserMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<si.um.kotnik.Flashcard.ResponseDtoGrpc> updateCardSet(si.um.kotnik.Flashcard.CardSetDtoMessage request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getUpdateCardSetMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<si.um.kotnik.Flashcard.ResponseDtoGrpc> deleteCardSet(si.um.kotnik.Flashcard.CardSetIdRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getDeleteCardSetMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * FlashCard endpoints
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<si.um.kotnik.Flashcard.ResponseDtoGrpc> addFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getAddFlashCardMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<si.um.kotnik.Flashcard.ResponseDtoGrpc> getFlashCardById(si.um.kotnik.Flashcard.FlashCardIdRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getGetFlashCardByIdMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<si.um.kotnik.Flashcard.ResponseDtoGrpc> updateFlashCard(si.um.kotnik.Flashcard.FlashCardDtoMessage request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getUpdateFlashCardMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<si.um.kotnik.Flashcard.ResponseDtoGrpc> deleteFlashCard(si.um.kotnik.Flashcard.FlashCardIdRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getDeleteFlashCardMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * Attempt endpoints
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<si.um.kotnik.Flashcard.ResponseDtoGrpc> addAttempt(si.um.kotnik.Flashcard.AttemptDtoMessage request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getAddAttemptMethod(), getCallOptions()), request);
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

        private final AsyncService serviceImpl;

        private final int methodId;

        MethodHandlers(AsyncService serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                case METHODID_ADD_CARD_SET:
                    serviceImpl.addCardSet((si.um.kotnik.Flashcard.CardSetDtoMessage) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver);
                    break;
                case METHODID_GET_CARD_SET_BY_ID:
                    serviceImpl.getCardSetById((si.um.kotnik.Flashcard.CardSetIdRequest) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver);
                    break;
                case METHODID_GET_CARD_SETS:
                    serviceImpl.getCardSets((si.um.kotnik.Flashcard.EmptyRequest) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver);
                    break;
                case METHODID_GET_CARD_SETS_BY_LANGUAGE:
                    serviceImpl.getCardSetsByLanguage((si.um.kotnik.Flashcard.LanguageRequest) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver);
                    break;
                case METHODID_GET_CARD_SETS_BY_USER:
                    serviceImpl.getCardSetsByUser((si.um.kotnik.Flashcard.UserEmailRequest) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver);
                    break;
                case METHODID_UPDATE_CARD_SET:
                    serviceImpl.updateCardSet((si.um.kotnik.Flashcard.CardSetDtoMessage) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver);
                    break;
                case METHODID_DELETE_CARD_SET:
                    serviceImpl.deleteCardSet((si.um.kotnik.Flashcard.CardSetIdRequest) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver);
                    break;
                case METHODID_ADD_FLASH_CARD:
                    serviceImpl.addFlashCard((si.um.kotnik.Flashcard.FlashCardDtoMessage) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver);
                    break;
                case METHODID_GET_FLASH_CARD_BY_ID:
                    serviceImpl.getFlashCardById((si.um.kotnik.Flashcard.FlashCardIdRequest) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver);
                    break;
                case METHODID_UPDATE_FLASH_CARD:
                    serviceImpl.updateFlashCard((si.um.kotnik.Flashcard.FlashCardDtoMessage) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver);
                    break;
                case METHODID_DELETE_FLASH_CARD:
                    serviceImpl.deleteFlashCard((si.um.kotnik.Flashcard.FlashCardIdRequest) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver);
                    break;
                case METHODID_ADD_ATTEMPT:
                    serviceImpl.addAttempt((si.um.kotnik.Flashcard.AttemptDtoMessage) request, (io.grpc.stub.StreamObserver<si.um.kotnik.Flashcard.ResponseDtoGrpc>) responseObserver);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                default:
                    throw new AssertionError();
            }
        }
    }

    public static io.grpc.ServerServiceDefinition bindService(AsyncService service) {
        return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(getAddCardSetMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.CardSetDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc>(service, METHODID_ADD_CARD_SET))).addMethod(getGetCardSetByIdMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.CardSetIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>(service, METHODID_GET_CARD_SET_BY_ID))).addMethod(getGetCardSetsMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.EmptyRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>(service, METHODID_GET_CARD_SETS))).addMethod(getGetCardSetsByLanguageMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.LanguageRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>(service, METHODID_GET_CARD_SETS_BY_LANGUAGE))).addMethod(getGetCardSetsByUserMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.UserEmailRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>(service, METHODID_GET_CARD_SETS_BY_USER))).addMethod(getUpdateCardSetMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.CardSetDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc>(service, METHODID_UPDATE_CARD_SET))).addMethod(getDeleteCardSetMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.CardSetIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>(service, METHODID_DELETE_CARD_SET))).addMethod(getAddFlashCardMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.FlashCardDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc>(service, METHODID_ADD_FLASH_CARD))).addMethod(getGetFlashCardByIdMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.FlashCardIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>(service, METHODID_GET_FLASH_CARD_BY_ID))).addMethod(getUpdateFlashCardMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.FlashCardDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc>(service, METHODID_UPDATE_FLASH_CARD))).addMethod(getDeleteFlashCardMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.FlashCardIdRequest, si.um.kotnik.Flashcard.ResponseDtoGrpc>(service, METHODID_DELETE_FLASH_CARD))).addMethod(getAddAttemptMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<si.um.kotnik.Flashcard.AttemptDtoMessage, si.um.kotnik.Flashcard.ResponseDtoGrpc>(service, METHODID_ADD_ATTEMPT))).build();
    }

    private static abstract class CardServiceBaseDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {

        CardServiceBaseDescriptorSupplier() {
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return si.um.kotnik.Flashcard.getDescriptor();
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("CardService");
        }
    }

    private static final class CardServiceFileDescriptorSupplier extends CardServiceBaseDescriptorSupplier {

        CardServiceFileDescriptorSupplier() {
        }
    }

    private static final class CardServiceMethodDescriptorSupplier extends CardServiceBaseDescriptorSupplier implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {

        private final java.lang.String methodName;

        CardServiceMethodDescriptorSupplier(java.lang.String methodName) {
            this.methodName = methodName;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(methodName);
        }
    }

    private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

    public static io.grpc.ServiceDescriptor getServiceDescriptor() {
        io.grpc.ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (CardServiceGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new CardServiceFileDescriptorSupplier()).addMethod(getAddCardSetMethod()).addMethod(getGetCardSetByIdMethod()).addMethod(getGetCardSetsMethod()).addMethod(getGetCardSetsByLanguageMethod()).addMethod(getGetCardSetsByUserMethod()).addMethod(getUpdateCardSetMethod()).addMethod(getDeleteCardSetMethod()).addMethod(getAddFlashCardMethod()).addMethod(getGetFlashCardByIdMethod()).addMethod(getUpdateFlashCardMethod()).addMethod(getDeleteFlashCardMethod()).addMethod(getAddAttemptMethod()).build();
                }
            }
        }
        return result;
    }
}
