package grpc.authentication;

import grpc.PropertiesHelper;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.gateway.protos.AuthenticateGrpc;
import org.gateway.protos.Reply;
import org.gateway.protos.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class LoginClient {
    public static void main(String[] args) throws IOException {
        Logger logger = LoggerFactory.getLogger("Login");
        PropertiesHelper propertiesHelper = new PropertiesHelper();

        String connectionAddr = args[0];
        int port = Integer.parseInt(args[1]);
        String password = args[2];
        //String clientIp = InetAddress.getLocalHost().toString();
        String clientIp = args[3];

        ManagedChannel channel = ManagedChannelBuilder.forAddress(connectionAddr, port).usePlaintext().build();

        AuthenticateGrpc.AuthenticateBlockingStub authenticateBlockingStub = AuthenticateGrpc.newBlockingStub(channel);

        Request.Builder loginRequest = Request.newBuilder();
        loginRequest.setIp(clientIp);
        loginRequest.setPassword(password);
        loginRequest.setType("CLIENT");

        Reply loginReply = authenticateBlockingStub.login(loginRequest.build());

        logger.info("Message: " + loginReply.getMessage());
        logger.info("Token: " + loginReply.getToken());
        logger.info("Master IP: " + loginReply.getMasterip());

        propertiesHelper.setAuthProperty("jwtToken", loginReply.getToken());
        propertiesHelper.setAuthProperty("masterIp", loginReply.getMasterip());

        propertiesHelper.saveProperties();

        channel.shutdown();

    }
}
