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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginClient {

    public static void main(String[] args) throws IOException, UnknownHostException, NoSuchAlgorithmException {
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
        loginRequest.setPassword(encryptPassword(password));
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

    private static String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hashedPassBytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        return new String(hashedPassBytes);
    }
}
