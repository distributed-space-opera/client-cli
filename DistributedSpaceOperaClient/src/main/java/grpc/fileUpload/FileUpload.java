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

public class FileUpload {

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

        ManagedChannel ch = ManagedChannelBuilder.forAddress("localhost",  ).usePlaintext().build();
        StreamObserver<UploadFileRequest> requestObserver = StreamingGrpc.newStub(ch).uploadFile(responseObserver);
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
