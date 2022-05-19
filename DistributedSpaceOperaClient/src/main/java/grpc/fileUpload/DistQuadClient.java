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

public class DistQuadClient {
    private static final Logger logger = Logger.getLogger(DistQuadClient.class.getName());
    private final StreamingGrpc.StreamingStub streamingStub;

    public DistQuadClient(ManagedChannel channel) {
        streamingStub = StreamingGrpc.newStub(channel);

    }

    public static void main(String[] args) throws Exception {
        // Access a service running on the local machine on port 50051
        String target = "localhost:50051";

        // Create a communication channel to the server, known as a Channel. Channels are thread-safe
        // and reusable. It is common to create channels at the beginning of your application and reuse
        // them until the application shuts down.
        ManagedChannel channel = null;
        try {
            channel = ManagedChannelBuilder.forTarget(target)
                    // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                    // needing certificates.
                    .usePlaintext()
                    .build();
//            new DistQuadClient(channel).processUpload("test");
        } finally {
            // ManagedChannels use resources like threads and TCP connections. To prevent leaking these
            // resources the channel should be shut down when it will no longer be used. If it may be used
            // again leave it running.
//            channel.shutdownNow().awaitTermination(500, TimeUnit.SECONDS);
        }

    }

    public void processDownload() {
        try {
            DownloadFileRequest downloadFileRequest = DownloadFileRequest.newBuilder().setFilename("San Jose State University - Connect2.pdf").build();
            StreamObserver<DownloadFileReply> downloadFileReplyStreamObserver = new StreamObserver<DownloadFileReply>() {
                FileOutputStream fileOutputStream;

                @Override
                public void onNext(DownloadFileReply downloadFileReply) {
                    logger.log(Level.INFO, "onNext called");
                    try {
                        if (Objects.isNull(fileOutputStream)) {
                            fileOutputStream = new FileOutputStream("./DOWNLOADS/" + downloadFileRequest.getFilename());
                        }
                        fileOutputStream.write(downloadFileReply.getPayload().toByteArray());
                    } catch (FileNotFoundException e) {
                        logger.log(Level.SEVERE, "FileNotFoundException occurred :{0}", e);
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "IOException occurred :{0}", e);
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onError(Throwable throwable) {
                    logger.log(Level.SEVERE, "onError called with error:{0}", throwable);
                }

                @Override
                public void onCompleted() {
                    logger.log(Level.INFO, "onCompleted called");
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "IOException occurred :{0}", e);
                        throw new RuntimeException(e);
                    }

                }
            };
            streamingStub.downloadFile(downloadFileRequest, downloadFileReplyStreamObserver);
            Thread.sleep(100000);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred :{0}", e);
        } finally {

        }
    }

    public void processUpload(String filePath, String fileName) throws InterruptedException {


        try {
//            String filePath = "/Users/harsha/Desktop/San Jose State University - Connect2.pdf";
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
