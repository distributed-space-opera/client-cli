package grpc.authentication;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.gateway.protos.AuthenticateGrpc;
import org.gateway.protos.Reply;
import org.gateway.protos.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class RegistrationClient {
    public static void main(String[] args) throws UnknownHostException {
        Logger logger = LoggerFactory.getLogger("Registration");
        String connectionAddr = args[0];
        int port = Integer.parseInt(args[1]);
        String password = args[2];
        //String ip = InetAddress.getLocalHost().getHostAddress();
        String ip = args[3];

        ManagedChannel channel = ManagedChannelBuilder.forAddress(connectionAddr, port).usePlaintext().build();

        AuthenticateGrpc.AuthenticateBlockingStub authenticateBlockingStub = AuthenticateGrpc.newBlockingStub(channel);

        Request.Builder request = Request.newBuilder();
        request.setIp(ip);
        request.setPassword(password);
        request.setType("CLIENT");

        Reply reply = authenticateBlockingStub.register(request.build());

        logger.info("Message: " + reply.getMessage());
        logger.info("Token: " + reply.getToken());
        logger.info("Master IP: " + reply.getMasterip());

        channel.shutdown();

    }
}
