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

public class FileUpload {

    private static InputStream inputStream;
    private static final int CHUNK_SIZE = 4096;

    public static void main(String[] args) throws InterruptedException {

        Logger logger = LoggerFactory.getLogger("FileUpload");

        int port = Integer.parseInt(args[1]);
        String ipAddress = args[0];
        String path = args[3];
        long inputFileSize = 0;
        String[] split = path.split("/");
        for (int i = 0; i < 10; i++) {
            String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
            String fileName = split[split.length - 1] + "_" + timestamp + "_" + i;
            logger.info("Filename: " + fileName);

            Path filePath = Paths.get(path);

            ManagedChannel gatewayChannel = ManagedChannelBuilder.forAddress(ipAddress, port).usePlaintext().build();
            AuthenticateGrpc.AuthenticateBlockingStub gatewayStub = AuthenticateGrpc.newBlockingStub(gatewayChannel);


            PropertiesHelper helper = new PropertiesHelper();
            String token = helper.getAuthProperty("jwtToken");

            if (!token.isBlank()) {
                logger.error("Please Login First");
            }

            try {
                inputFileSize = Files.size(filePath);
            } catch (IOException e) {
                logger.error("IO Exception");
                e.printStackTrace();
            }

            UploadResponse uploadRes = null;

            UploadRequest.Builder dbld = UploadRequest.newBuilder();
            dbld.setClientIp(args[2]);
            dbld.setFilename(fileName);
            dbld.setFilesize(inputFileSize);
            dbld.setToken(token);

            uploadRes = gatewayStub.getNodeForUpload(dbld.build());
            logger.info("Upload File Node IP: " + uploadRes.getNodeip());
            logger.info("Upload File Message: " + uploadRes.getMessage());

            gatewayChannel.shutdown();

            ManagedChannel channel = null;
            logger.info(uploadRes.getNodeip());

            String nodeip = uploadRes.getNodeip();

            channel = ManagedChannelBuilder.forAddress(nodeip, 6080)
                    .usePlaintext()
                    .build();
            System.out.println("channel" + channel);
            Uploader uploader = new Uploader(channel);
            uploader.processUpload(path, fileName);
            channel.shutdown();
        }
    }



}
