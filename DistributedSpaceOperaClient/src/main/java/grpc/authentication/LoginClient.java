package grpc.authentication;

import grpc.PropertiesHelper;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.gateway.protos.AuthenticateGrpc;
import org.gateway.protos.Reply;
import org.gateway.protos.Request;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LoginClient {
    public static void main(String[] args) throws UnknownHostException {
        PropertiesHelper propertiesHelper = new PropertiesHelper();

        String connectionAddr = args[0];
        int port = Integer.parseInt(args[1]);
        String password = args[2];
        String clientIp = InetAddress.getLocalHost().toString();

        ManagedChannel channel = ManagedChannelBuilder.forAddress(connectionAddr, port).usePlaintext().build();

        AuthenticateGrpc.AuthenticateBlockingStub authenticateBlockingStub = AuthenticateGrpc.newBlockingStub(channel);

        Request.Builder loginRequest = Request.newBuilder();
        loginRequest.setIp(clientIp);
        loginRequest.setPassword(password);
        loginRequest.setType("CLIENT");

        Reply loginReply = authenticateBlockingStub.login(loginRequest.build());

        System.out.println("Message: " + loginReply.getMessage());
        System.out.println("Token: " + loginReply.getToken());
        System.out.println("Master IP: " + loginReply.getMasterip());

        propertiesHelper.setAuthProperty("jwtToken", loginReply.getToken());
        propertiesHelper.setAuthProperty("masterIp", loginReply.getMasterip());

        channel.shutdown();

    }
}
