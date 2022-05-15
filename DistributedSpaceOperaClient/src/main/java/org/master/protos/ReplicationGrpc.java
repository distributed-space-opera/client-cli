package org.master.protos;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.45.0)",
    comments = "Source: master-comm.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ReplicationGrpc {

  private ReplicationGrpc() {}

  public static final String SERVICE_NAME = "stream.Replication";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.master.protos.GetListOfFilesRequest,
      org.master.protos.GetListOfFilesResponse> getGetListOfFilesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetListOfFiles",
      requestType = org.master.protos.GetListOfFilesRequest.class,
      responseType = org.master.protos.GetListOfFilesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.master.protos.GetListOfFilesRequest,
      org.master.protos.GetListOfFilesResponse> getGetListOfFilesMethod() {
    io.grpc.MethodDescriptor<org.master.protos.GetListOfFilesRequest, org.master.protos.GetListOfFilesResponse> getGetListOfFilesMethod;
    if ((getGetListOfFilesMethod = ReplicationGrpc.getGetListOfFilesMethod) == null) {
      synchronized (ReplicationGrpc.class) {
        if ((getGetListOfFilesMethod = ReplicationGrpc.getGetListOfFilesMethod) == null) {
          ReplicationGrpc.getGetListOfFilesMethod = getGetListOfFilesMethod =
              io.grpc.MethodDescriptor.<org.master.protos.GetListOfFilesRequest, org.master.protos.GetListOfFilesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetListOfFiles"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.master.protos.GetListOfFilesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.master.protos.GetListOfFilesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReplicationMethodDescriptorSupplier("GetListOfFiles"))
              .build();
        }
      }
    }
    return getGetListOfFilesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ReplicationStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReplicationStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReplicationStub>() {
        @java.lang.Override
        public ReplicationStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReplicationStub(channel, callOptions);
        }
      };
    return ReplicationStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ReplicationBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReplicationBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReplicationBlockingStub>() {
        @java.lang.Override
        public ReplicationBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReplicationBlockingStub(channel, callOptions);
        }
      };
    return ReplicationBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ReplicationFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReplicationFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReplicationFutureStub>() {
        @java.lang.Override
        public ReplicationFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReplicationFutureStub(channel, callOptions);
        }
      };
    return ReplicationFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ReplicationImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Methods required for CLI
     * </pre>
     */
    public void getListOfFiles(org.master.protos.GetListOfFilesRequest request,
        io.grpc.stub.StreamObserver<org.master.protos.GetListOfFilesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetListOfFilesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetListOfFilesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                org.master.protos.GetListOfFilesRequest,
                org.master.protos.GetListOfFilesResponse>(
                  this, METHODID_GET_LIST_OF_FILES)))
          .build();
    }
  }

  /**
   */
  public static final class ReplicationStub extends io.grpc.stub.AbstractAsyncStub<ReplicationStub> {
    private ReplicationStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReplicationStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReplicationStub(channel, callOptions);
    }

    /**
     * <pre>
     * Methods required for CLI
     * </pre>
     */
    public void getListOfFiles(org.master.protos.GetListOfFilesRequest request,
        io.grpc.stub.StreamObserver<org.master.protos.GetListOfFilesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetListOfFilesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ReplicationBlockingStub extends io.grpc.stub.AbstractBlockingStub<ReplicationBlockingStub> {
    private ReplicationBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReplicationBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReplicationBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Methods required for CLI
     * </pre>
     */
    public org.master.protos.GetListOfFilesResponse getListOfFiles(org.master.protos.GetListOfFilesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetListOfFilesMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ReplicationFutureStub extends io.grpc.stub.AbstractFutureStub<ReplicationFutureStub> {
    private ReplicationFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReplicationFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReplicationFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Methods required for CLI
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.master.protos.GetListOfFilesResponse> getListOfFiles(
        org.master.protos.GetListOfFilesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetListOfFilesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_LIST_OF_FILES = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ReplicationImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ReplicationImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_LIST_OF_FILES:
          serviceImpl.getListOfFiles((org.master.protos.GetListOfFilesRequest) request,
              (io.grpc.stub.StreamObserver<org.master.protos.GetListOfFilesResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ReplicationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ReplicationBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.master.protos.MasterComm.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Replication");
    }
  }

  private static final class ReplicationFileDescriptorSupplier
      extends ReplicationBaseDescriptorSupplier {
    ReplicationFileDescriptorSupplier() {}
  }

  private static final class ReplicationMethodDescriptorSupplier
      extends ReplicationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ReplicationMethodDescriptorSupplier(String methodName) {
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
      synchronized (ReplicationGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ReplicationFileDescriptorSupplier())
              .addMethod(getGetListOfFilesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
