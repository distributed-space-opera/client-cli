package grpc.authentication;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.gateway.protos.AuthenticateGrpc;
import org.gateway.protos.LoginReply;
import org.gateway.protos.LoginRequest;

public class LoginClient {
    public static void main(String[] args) {
        String connectionAddr = args[0];
        int port = Integer.parseInt(args[1]);
        String clientIp = args[2];
        String password = args[3];

        ManagedChannel channel = ManagedChannelBuilder.forAddress(connectionAddr, port).usePlaintext().build();

        AuthenticateGrpc.AuthenticateBlockingStub authenticateBlockingStub = AuthenticateGrpc.newBlockingStub(channel);

        LoginRequest loginRequest = LoginRequest.newBuilder().setClientIp(clientIp).setPassword(password).build();
        LoginReply loginReply = authenticateBlockingStub.login(loginRequest);

        System.out.println("Message: " + loginReply.getMsg());
        System.out.println("Token: " + loginReply.getToken());

        channel.shutdown();

    }
}
