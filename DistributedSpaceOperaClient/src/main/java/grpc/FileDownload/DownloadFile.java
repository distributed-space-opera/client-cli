package grpc.FileDownload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import com.google.protobuf.ByteString;

import grpc.PropertiesHelper;
import org.client.protos.DownloadFileReply;
import org.client.protos.DownloadFileRequest;
import org.client.protos.StreamingGrpc;
import org.gateway.protos.AuthenticateGrpc;
import org.gateway.protos.DownloadRequest;
import org.gateway.protos.DownloadResponse;
import org.master.protos.GetListOfFilesResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DownloadFile {
    private static long clientID;
    private static int port;

    public static void main(String[] args) throws IOException {

        Logger logger = LoggerFactory.getLogger("FileDownload");

        port = Integer.parseInt(args[1]);
        String ipAddress = args[0];
        String fileName = args[2];
//        String clientIp = String.valueOf(InetAddress.getLocalHost());
        String clientIp = args[3];

        PropertiesHelper helper = new PropertiesHelper();
        String token = helper.getAuthProperty("jwtToken");

        if (!token.isBlank()) {
            logger.error("Please Login First");
        }

        DownloadRequest.Builder dbld = DownloadRequest.newBuilder();
        dbld.setClientIp(clientIp);
        dbld.setFilename(fileName);
        dbld.setToken(token);

        ManagedChannel gatewayChannel = ManagedChannelBuilder.forAddress(ipAddress, port).usePlaintext().build();
        AuthenticateGrpc.AuthenticateBlockingStub gatewayStub = AuthenticateGrpc.newBlockingStub(gatewayChannel);

        DownloadResponse res = gatewayStub.getNodeForDownload(dbld.build());
        logger.info("Download File Node IP: " + res.getNodeip());
        logger.info("Download File Message: " + res.getMessage());


        ManagedChannel nodeChannel = ManagedChannelBuilder.forAddress(res.getNodeip(), 6080).usePlaintext().build();
        StreamingGrpc.StreamingBlockingStub stub = StreamingGrpc.newBlockingStub(nodeChannel);

        String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        DownloadFileRequest.Builder bld = DownloadFileRequest.newBuilder();
        bld.setFilename(fileName);
        bld.setToken(token);

        Iterator<DownloadFileReply> downloadFileReplyIterator;

        try {
            downloadFileReplyIterator = stub.downloadFile(bld.build());

            for (int i = 1; downloadFileReplyIterator.hasNext(); i++) {
                DownloadFileReply r = downloadFileReplyIterator.next();

                writeToFile("./received/" + fileName + "_" + clientID + "_" + timestamp, r.getPayload());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        gatewayChannel.shutdown();
        nodeChannel.shutdown();
    }

    static void writeToFile(String path, ByteString data) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException();
            }
        }
        FileOutputStream output = new FileOutputStream(path, true);
        output.write(data.toByteArray());
        output.flush();
        output.close();
    }
}
