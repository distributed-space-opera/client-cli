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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegistrationClient {

    public static void main(String[] args) throws UnknownHostException, NoSuchAlgorithmException {
        Logger logger = LoggerFactory.getLogger("Registration");

        String connectionAddr = args[0];
        int port = Integer.parseInt(args[1]);
        String password = args[2];
        //String ip = InetAddress.getLocalHost().getHostAddress();
        String ip = args[3];

        if (!validatePassword(password)) {
            System.out.println("Invalid password length. Length must be between 6 to 20");
            return;
        }

        ManagedChannel channel = ManagedChannelBuilder.forAddress(connectionAddr, port).usePlaintext().build();

        AuthenticateGrpc.AuthenticateBlockingStub authenticateBlockingStub = AuthenticateGrpc.newBlockingStub(channel);

        Request.Builder request = Request.newBuilder();
        request.setIp(ip);
//        request.setPassword(encryptPassword(password));
        request.setPassword(password);
        request.setType("CLIENT");

        Reply reply = authenticateBlockingStub.register(request.build());

        logger.info("Message: " + reply.getMessage());
        logger.info("Token: " + reply.getToken());
        logger.info("Master IP: " + reply.getMasterip());

        channel.shutdown();

    }

    private static boolean validatePassword(String password) {
        if (password.length() < 6 || password.length() > 20) {
            return false;
        }

        return true;
    }

    private static String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hashedPassBytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        return new String(hashedPassBytes);
    }
}
