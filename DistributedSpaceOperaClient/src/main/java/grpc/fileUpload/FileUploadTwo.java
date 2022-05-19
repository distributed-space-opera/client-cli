package grpc.fileUpload;


import com.google.protobuf.ByteString;
import grpc.PropertiesHelper;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.client.protos.StreamingGrpc;
import org.client.protos.UploadFileReply;
import org.client.protos.UploadFileRequest;
import org.gateway.protos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FileUploadTwo {

    private static InputStream inputStream;
    private static final int CHUNK_SIZE = 4096;

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger("FileUpload");

        int port = Integer.parseInt(args[1]);
        String ipAddress = args[2];
        String path = args[3];
        long inputFileSize = 0;
        String[] split = path.split("/");
        String fileName = split[split.length - 1];


        // input file for testing
        Path filePath = Paths.get(path);


        try {
            inputStream = Files.newInputStream(filePath);
        } catch(IOException e){
            System.out.println("IO Exception");
            e.printStackTrace();
        }
        StreamObserver<UploadFileReply> responseObserver = new StreamObserver<UploadFileReply>() {
            @Override
            public void onNext(UploadFileReply uploadFileReply) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        };



        ManagedChannel gatewayChannel = ManagedChannelBuilder.forAddress(ipAddress, port).usePlaintext().build();
        AuthenticateGrpc.AuthenticateBlockingStub gatewayStub = AuthenticateGrpc.newBlockingStub(gatewayChannel);

        ManagedChannel nodeChannel = ManagedChannelBuilder.forAddress("3.144.5.32",  50051).usePlaintext().build();
        StreamObserver<UploadFileRequest> requestObserver = StreamingGrpc.newStub(nodeChannel).uploadFile(responseObserver);

        PropertiesHelper helper = new PropertiesHelper();
        String token = helper.getAuthProperty("jwtToken");

        if (!token.isBlank()) {
            logger.error("Please Login First");
        }

        try {
            inputFileSize = Files.size(filePath);
        }catch (IOException e){
            logger.error("IO Exception");
            e.printStackTrace();
        }

        //uploadRequest
        try {
            UploadRequest.Builder dbld = UploadRequest.newBuilder();
            dbld.setClientIp(String.valueOf(InetAddress.getLocalHost()));
            dbld.setFilename(fileName);
            dbld.setFilesize(inputFileSize);
            dbld.setToken(token);

            UploadResponse uploadRes = gatewayStub.getNodeForUpload(dbld.build());
            logger.info("Upload File Node IP: " + uploadRes.getNodeip());
            logger.info("Upload File Message: " + uploadRes.getMessage());
        } catch(UnknownHostException e){
            logger.error("UploadRequest Error");
            e.printStackTrace();
        }

        gatewayChannel.shutdown();

        //uploadFileRequest
        try{
            byte[] bytes = new byte[CHUNK_SIZE];
            int size;
            while ((size = inputStream.read(bytes)) > 0){
                UploadFileRequest.Builder builder = UploadFileRequest.newBuilder();
                builder.setPayload(ByteString.copyFrom(bytes, 0 , size)).setFilename("test");
                requestObserver.onNext(builder.build());
            }

            // close the stream
            inputStream.close();
            responseObserver.onCompleted();
        } catch (RuntimeException | IOException e) {
            responseObserver.onError(e);
        }
        responseObserver.onCompleted();
        nodeChannel.shutdown();
    }



}

