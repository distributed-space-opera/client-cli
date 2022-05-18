package grpc.fileUpload;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.client.protos.StreamingGrpc;
import org.client.protos.UploadFileReply;
import org.client.protos.UploadFileRequest;
import org.gateway.protos.*;


import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FileUpload {

    private static File tokenFile;
    private static InputStream inputStream;

    public static void main(String[] args) {
        int port = Integer.parseInt(args[1]);
        String ipAddress = args[2];
        String path = args[3];
        long inputFileSize = 0;
        String fileName = "";


        // input file for testing
        Path filePath = Paths.get(path);

        // upload file as chunk
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

        //create token file
        try {
            tokenFile = new File("token.txt");
            if (tokenFile.createNewFile()) {
                System.out.println("File created: " + tokenFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //write to token file
        try {
            FileWriter myWriter = new FileWriter(tokenFile);
            myWriter.write("Tokens in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //read from file
        try {
            Scanner myReader = new Scanner(tokenFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            inputFileSize = Files.size(filePath);
        }catch (IOException e){
            System.out.println("IO Exception");
            e.printStackTrace();
        }

        //uploadRequest
        try {
            UploadRequest.Builder dbld = UploadRequest.newBuilder();
            dbld.setClientIp(String.valueOf(InetAddress.getLocalHost()));
            dbld.setFilename(fileName);
            dbld.setFilesize(inputFileSize);
            dbld.setToken("");

            UploadResponse uploadRes = gatewayStub.getNodeForUpload(dbld.build());
            System.out.println("Upload File Node IP: " + uploadRes.getNodeip());
            System.out.println("Upload File Message: " + uploadRes.getMessage());
        } catch(UnknownHostException e){
            System.out.println("UploadRequest Error");
            e.printStackTrace();
        }



        //uploadFileRequest
        try{
            byte[] bytes = new byte[4096];
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
    }



}
