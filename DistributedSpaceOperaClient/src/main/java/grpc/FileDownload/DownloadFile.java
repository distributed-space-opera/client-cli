package grpc.FileDownload;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.client.protos.DownloadFileReply;
import org.client.protos.DownloadFileRequest;
import org.client.protos.StreamingGrpc;
import org.gateway.protos.AuthenticateGrpc;
import org.gateway.protos.DownloadRequest;
import org.gateway.protos.DownloadResponse;

import java.io.*;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;


public class DownloadFile {
    private static long clientID;
    private static int port;

    public static void main(String[] args) throws IOException {

        port = Integer.valueOf(args[1]);
        String ipAddress = args[2];
        String fileName = args[3];

        DownloadRequest.Builder dbld = DownloadRequest.newBuilder();
        dbld.setClientIp(String.valueOf(InetAddress.getLocalHost()));
        dbld.setFilename(fileName);
        dbld.setToken("");
        dbld.setTokenBytes(ByteString.fromHex(""));

        ManagedChannel gatewayChannel = ManagedChannelBuilder.forAddress(ipAddress, port).usePlaintext().build();
        AuthenticateGrpc.AuthenticateBlockingStub gatewayStub = AuthenticateGrpc.newBlockingStub(gatewayChannel);

        DownloadResponse res = gatewayStub.getNodeForDownload(dbld.build());
        System.out.println("Download File Node IP: " + res.getNodeip());
        System.out.println("Download File Message: " + res.getMessage());

        ManagedChannel nodeChannel = ManagedChannelBuilder.forAddress(res.getNodeip(), port).usePlaintext().build();
        StreamingGrpc.StreamingBlockingStub stub = StreamingGrpc.newBlockingStub(nodeChannel);
        FileInputStream fin = new FileInputStream("/Users/adarshpatil/Documents/Masters/Sem 2/275 Gash/Final Project/client-cli/DistributedSpaceOperaClient/src/main/java/grpc/FileDownload/testout.txt");

        String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        DownloadFileRequest.Builder bld = DownloadFileRequest.newBuilder();
        bld.setFilename(fileName);
        bld.setToken("");

        Iterator<DownloadFileReply> downloadFileReplyIterator;

        try {
            downloadFileReplyIterator = stub.downloadFile(bld.build());

            for (int i = 1; downloadFileReplyIterator.hasNext(); i++) {
                DownloadFileReply r = downloadFileReplyIterator.next();

                    writeToFile("./received/" + fileName + "_" + clientID + "_" + timestamp,
                        r.getPayload());
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
