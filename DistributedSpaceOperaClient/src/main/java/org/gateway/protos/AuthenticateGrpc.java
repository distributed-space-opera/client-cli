package org.gateway.protos;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.45.0)",
    comments = "Source: gateway-comm.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AuthenticateGrpc {

  private AuthenticateGrpc() {}

  public static final String SERVICE_NAME = "stream.Authenticate";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.gateway.protos.Request,
      org.gateway.protos.Reply> getRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Register",
      requestType = org.gateway.protos.Request.class,
      responseType = org.gateway.protos.Reply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.gateway.protos.Request,
      org.gateway.protos.Reply> getRegisterMethod() {
    io.grpc.MethodDescriptor<org.gateway.protos.Request, org.gateway.protos.Reply> getRegisterMethod;
    if ((getRegisterMethod = AuthenticateGrpc.getRegisterMethod) == null) {
      synchronized (AuthenticateGrpc.class) {
        if ((getRegisterMethod = AuthenticateGrpc.getRegisterMethod) == null) {
          AuthenticateGrpc.getRegisterMethod = getRegisterMethod =
              io.grpc.MethodDescriptor.<org.gateway.protos.Request, org.gateway.protos.Reply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.gateway.protos.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.gateway.protos.Reply.getDefaultInstance()))
              .setSchemaDescriptor(new AuthenticateMethodDescriptorSupplier("Register"))
              .build();
        }
      }
    }
    return getRegisterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.gateway.protos.Request,
      org.gateway.protos.Reply> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Login",
      requestType = org.gateway.protos.Request.class,
      responseType = org.gateway.protos.Reply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.gateway.protos.Request,
      org.gateway.protos.Reply> getLoginMethod() {
    io.grpc.MethodDescriptor<org.gateway.protos.Request, org.gateway.protos.Reply> getLoginMethod;
    if ((getLoginMethod = AuthenticateGrpc.getLoginMethod) == null) {
      synchronized (AuthenticateGrpc.class) {
        if ((getLoginMethod = AuthenticateGrpc.getLoginMethod) == null) {
          AuthenticateGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<org.gateway.protos.Request, org.gateway.protos.Reply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.gateway.protos.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.gateway.protos.Reply.getDefaultInstance()))
              .setSchemaDescriptor(new AuthenticateMethodDescriptorSupplier("Login"))
              .build();
        }
      }
    }
    return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.gateway.protos.UploadRequest,
      org.gateway.protos.UploadResponse> getGetNodeForUploadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetNodeForUpload",
      requestType = org.gateway.protos.UploadRequest.class,
      responseType = org.gateway.protos.UploadResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.gateway.protos.UploadRequest,
      org.gateway.protos.UploadResponse> getGetNodeForUploadMethod() {
    io.grpc.MethodDescriptor<org.gateway.protos.UploadRequest, org.gateway.protos.UploadResponse> getGetNodeForUploadMethod;
    if ((getGetNodeForUploadMethod = AuthenticateGrpc.getGetNodeForUploadMethod) == null) {
      synchronized (AuthenticateGrpc.class) {
        if ((getGetNodeForUploadMethod = AuthenticateGrpc.getGetNodeForUploadMethod) == null) {
          AuthenticateGrpc.getGetNodeForUploadMethod = getGetNodeForUploadMethod =
              io.grpc.MethodDescriptor.<org.gateway.protos.UploadRequest, org.gateway.protos.UploadResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetNodeForUpload"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.gateway.protos.UploadRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.gateway.protos.UploadResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthenticateMethodDescriptorSupplier("GetNodeForUpload"))
              .build();
        }
      }
    }
    return getGetNodeForUploadMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.gateway.protos.DownloadRequest,
      org.gateway.protos.DownloadResponse> getGetNodeForDownloadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetNodeForDownload",
      requestType = org.gateway.protos.DownloadRequest.class,
      responseType = org.gateway.protos.DownloadResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.gateway.protos.DownloadRequest,
      org.gateway.protos.DownloadResponse> getGetNodeForDownloadMethod() {
    io.grpc.MethodDescriptor<org.gateway.protos.DownloadRequest, org.gateway.protos.DownloadResponse> getGetNodeForDownloadMethod;
    if ((getGetNodeForDownloadMethod = AuthenticateGrpc.getGetNodeForDownloadMethod) == null) {
      synchronized (AuthenticateGrpc.class) {
        if ((getGetNodeForDownloadMethod = AuthenticateGrpc.getGetNodeForDownloadMethod) == null) {
          AuthenticateGrpc.getGetNodeForDownloadMethod = getGetNodeForDownloadMethod =
              io.grpc.MethodDescriptor.<org.gateway.protos.DownloadRequest, org.gateway.protos.DownloadResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetNodeForDownload"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.gateway.protos.DownloadRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.gateway.protos.DownloadResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuthenticateMethodDescriptorSupplier("GetNodeForDownload"))
              .build();
        }
      }
    }
    return getGetNodeForDownloadMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuthenticateStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthenticateStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthenticateStub>() {
        @java.lang.Override
        public AuthenticateStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthenticateStub(channel, callOptions);
        }
      };
    return AuthenticateStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthenticateBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthenticateBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthenticateBlockingStub>() {
        @java.lang.Override
        public AuthenticateBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthenticateBlockingStub(channel, callOptions);
        }
      };
    return AuthenticateBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuthenticateFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthenticateFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthenticateFutureStub>() {
        @java.lang.Override
        public AuthenticateFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthenticateFutureStub(channel, callOptions);
        }
      };
    return AuthenticateFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class AuthenticateImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Methods for client and node
     * </pre>
     */
    public void register(org.gateway.protos.Request request,
        io.grpc.stub.StreamObserver<org.gateway.protos.Reply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
    }

    /**
     */
    public void login(org.gateway.protos.Request request,
        io.grpc.stub.StreamObserver<org.gateway.protos.Reply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     * <pre>
     * Methods required for client only
     * </pre>
     */
    public void getNodeForUpload(org.gateway.protos.UploadRequest request,
        io.grpc.stub.StreamObserver<org.gateway.protos.UploadResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetNodeForUploadMethod(), responseObserver);
    }

    /**
     */
    public void getNodeForDownload(org.gateway.protos.DownloadRequest request,
        io.grpc.stub.StreamObserver<org.gateway.protos.DownloadResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetNodeForDownloadMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                org.gateway.protos.Request,
                org.gateway.protos.Reply>(
                  this, METHODID_REGISTER)))
          .addMethod(
            getLoginMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                org.gateway.protos.Request,
                org.gateway.protos.Reply>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getGetNodeForUploadMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                org.gateway.protos.UploadRequest,
                org.gateway.protos.UploadResponse>(
                  this, METHODID_GET_NODE_FOR_UPLOAD)))
          .addMethod(
            getGetNodeForDownloadMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                org.gateway.protos.DownloadRequest,
                org.gateway.protos.DownloadResponse>(
                  this, METHODID_GET_NODE_FOR_DOWNLOAD)))
          .build();
    }
  }

  /**
   */
  public static final class AuthenticateStub extends io.grpc.stub.AbstractAsyncStub<AuthenticateStub> {
    private AuthenticateStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthenticateStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthenticateStub(channel, callOptions);
    }

    /**
     * <pre>
     * Methods for client and node
     * </pre>
     */
    public void register(org.gateway.protos.Request request,
        io.grpc.stub.StreamObserver<org.gateway.protos.Reply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void login(org.gateway.protos.Request request,
        io.grpc.stub.StreamObserver<org.gateway.protos.Reply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Methods required for client only
     * </pre>
     */
    public void getNodeForUpload(org.gateway.protos.UploadRequest request,
        io.grpc.stub.StreamObserver<org.gateway.protos.UploadResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetNodeForUploadMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getNodeForDownload(org.gateway.protos.DownloadRequest request,
        io.grpc.stub.StreamObserver<org.gateway.protos.DownloadResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetNodeForDownloadMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AuthenticateBlockingStub extends io.grpc.stub.AbstractBlockingStub<AuthenticateBlockingStub> {
    private AuthenticateBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthenticateBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthenticateBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Methods for client and node
     * </pre>
     */
    public org.gateway.protos.Reply register(org.gateway.protos.Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisterMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.gateway.protos.Reply login(org.gateway.protos.Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Methods required for client only
     * </pre>
     */
    public org.gateway.protos.UploadResponse getNodeForUpload(org.gateway.protos.UploadRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetNodeForUploadMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.gateway.protos.DownloadResponse getNodeForDownload(org.gateway.protos.DownloadRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetNodeForDownloadMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AuthenticateFutureStub extends io.grpc.stub.AbstractFutureStub<AuthenticateFutureStub> {
    private AuthenticateFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthenticateFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthenticateFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Methods for client and node
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.gateway.protos.Reply> register(
        org.gateway.protos.Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.gateway.protos.Reply> login(
        org.gateway.protos.Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Methods required for client only
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.gateway.protos.UploadResponse> getNodeForUpload(
        org.gateway.protos.UploadRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetNodeForUploadMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.gateway.protos.DownloadResponse> getNodeForDownload(
        org.gateway.protos.DownloadRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetNodeForDownloadMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER = 0;
  private static final int METHODID_LOGIN = 1;
  private static final int METHODID_GET_NODE_FOR_UPLOAD = 2;
  private static final int METHODID_GET_NODE_FOR_DOWNLOAD = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AuthenticateImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AuthenticateImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER:
          serviceImpl.register((org.gateway.protos.Request) request,
              (io.grpc.stub.StreamObserver<org.gateway.protos.Reply>) responseObserver);
          break;
        case METHODID_LOGIN:
          serviceImpl.login((org.gateway.protos.Request) request,
              (io.grpc.stub.StreamObserver<org.gateway.protos.Reply>) responseObserver);
          break;
        case METHODID_GET_NODE_FOR_UPLOAD:
          serviceImpl.getNodeForUpload((org.gateway.protos.UploadRequest) request,
              (io.grpc.stub.StreamObserver<org.gateway.protos.UploadResponse>) responseObserver);
          break;
        case METHODID_GET_NODE_FOR_DOWNLOAD:
          serviceImpl.getNodeForDownload((org.gateway.protos.DownloadRequest) request,
              (io.grpc.stub.StreamObserver<org.gateway.protos.DownloadResponse>) responseObserver);
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

  private static abstract class AuthenticateBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AuthenticateBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.gateway.protos.GatewayComm.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Authenticate");
    }
  }

  private static final class AuthenticateFileDescriptorSupplier
      extends AuthenticateBaseDescriptorSupplier {
    AuthenticateFileDescriptorSupplier() {}
  }

  private static final class AuthenticateMethodDescriptorSupplier
      extends AuthenticateBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AuthenticateMethodDescriptorSupplier(String methodName) {
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
      synchronized (AuthenticateGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuthenticateFileDescriptorSupplier())
              .addMethod(getRegisterMethod())
              .addMethod(getLoginMethod())
              .addMethod(getGetNodeForUploadMethod())
              .addMethod(getGetNodeForDownloadMethod())
              .build();
        }
      }
    }
    return result;
  }
}
