package grpc.authentication;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.gateway.protos.AuthenticateGrpc;
import org.gateway.protos.Reply;
import org.gateway.protos.Request;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class RegistrationClient {
    public static void main(String[] args) throws UnknownHostException {
        String connectionAddr = args[0];
        int port = Integer.parseInt(args[1]);
        String password = args[2];
        String ip = InetAddress.getLocalHost().toString();

        ManagedChannel channel = ManagedChannelBuilder.forAddress(connectionAddr, port).usePlaintext().build();

        AuthenticateGrpc.AuthenticateBlockingStub authenticateBlockingStub = AuthenticateGrpc.newBlockingStub(channel);

        Request.Builder request = Request.newBuilder();
        request.setIp(ip);
        request.setPassword(password);
        request.setType("CLIENT");

        Reply reply = authenticateBlockingStub.register(request.build());

        System.out.println("Message: " + reply.getMessage());
        System.out.println("Token: " + reply.getToken());
        System.out.println("Master IP: " + reply.getMasterip());

        channel.shutdown();

    }
}
