package grpc.fileUpload;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.client.protos.StreamingGrpc;
import org.client.protos.UploadFileReply;
import org.client.protos.UploadFileRequest;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FileUpload {

    private static File tokenFile;

    public static void main(String[] args) {
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

        ManagedChannel ch = ManagedChannelBuilder.forAddress("3.144.5.32",  50051).usePlaintext().build();
        StreamObserver<UploadFileRequest> requestObserver = StreamingGrpc.newStub(ch).uploadFile(responseObserver);

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

        try{

            // input file for testing
            Path path = Paths.get("src/test/resources/input/java_input.pdf");


            // upload file as chunk
            InputStream inputStream = Files.newInputStream(path);
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
