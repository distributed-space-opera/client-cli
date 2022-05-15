package grpc.authentication;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.gateway.protos.AuthenticateGrpc;
import org.gateway.protos.Reply;
import org.gateway.protos.Request;

public class AuthenticationClient {
    public static void main(String[] args) {
        String connectionAddr = args[0];
        int port = Integer.parseInt(args[1]);
        String username = args[2];
        String password = args[3];

        ManagedChannel channel = ManagedChannelBuilder.forAddress(connectionAddr, port).usePlaintext().build();

        AuthenticateGrpc.AuthenticateBlockingStub authenticateBlockingStub = AuthenticateGrpc.newBlockingStub(channel);

        Request request = Request.newBuilder().setName(username).setPassword(password).build();
        Reply reply = authenticateBlockingStub.register(request);

        System.out.println(reply.getMasterip());

        channel.shutdown();

    }
}
