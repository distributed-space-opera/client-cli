package grpc.fileUpload;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.client.protos.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Uploader {
    private static final Logger logger = Logger.getLogger(Uploader.class.getName());
    private final StreamingGrpc.StreamingStub streamingStub;

    public Uploader(ManagedChannel channel) {
        streamingStub = StreamingGrpc.newStub(channel);

    }


    public void processUpload(String filePath, String fileName) throws InterruptedException {
        try {
            String[] fileArray = filePath.split("/");
            //String fileName = fileArray[fileArray.length - 1] + "5";
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ByteString fileBytes = ByteString.readFrom(fileInputStream);
            StreamObserver<UploadFileReply> uploadFileReplyStreamObserver = new StreamObserver<>() {
                @Override
                public void onNext(UploadFileReply uploadFileReply) {
                    logger.log(Level.INFO, "onNext called");
                    logger.log(Level.INFO, "Got response from server, file upload successfull,status {0}", uploadFileReply.getStatus());
                }

                @Override
                public void onError(Throwable throwable) {
                    logger.log(Level.SEVERE, "onError called with error:{0}", throwable);
                }

                @Override
                public void onCompleted() {
                    logger.log(Level.INFO, "onCompleted called");
                }
            };

            StreamObserver<UploadFileRequest> uploadFileRequestStreamObserver = streamingStub.uploadFile(uploadFileReplyStreamObserver);
            int byteStart = 0;
            int byteSize = 4000000;
            while (byteStart < fileBytes.size()) {
                ByteString tempBuffer = fileBytes.substring(byteStart, Math.min(byteStart + byteSize, fileBytes.size()));
                UploadFileRequest uploadFileRequest = UploadFileRequest.newBuilder().setFilename(fileName).setPayload(tempBuffer).build();
                uploadFileRequestStreamObserver.onNext(uploadFileRequest);
                byteStart += byteSize;
            }
            uploadFileRequestStreamObserver.onCompleted();
            fileInputStream.close();

            Thread.sleep(100000);
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "FileNotFoundException occurred :{0}", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException occurred :{0}", e);
            throw new RuntimeException(e);
        }
    }

}
