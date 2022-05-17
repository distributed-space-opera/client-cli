package grpc.FileDownload;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.client.protos.DownloadFileReply;
import org.client.protos.DownloadFileRequest;
import org.client.protos.StreamingGrpc;
import org.gateway.protos.Request;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;


public class DownloadFile {
    private static long clientID;
    private static int port;

    public static void main(String[] args) {

        port = Integer.valueOf(args[1]);
        clientID = Long.valueOf(args[2]);
        String fileName = args[3];
        int algorithm = Integer.valueOf(args[4]);

        ManagedChannel ch = ManagedChannelBuilder.forAddress(args[0], DownloadFile.port).usePlaintext().build();

        StreamingGrpc.StreamingBlockingStub stub = StreamingGrpc.newBlockingStub(ch);

//        boolean last = forAddressalse;
//        long offset = -1;
//        long destination = 0;
//        long responseTime = 200;
//        long chunkSize = 4096;
//        int index = 0;
//        boolean slowStartCompleted = false;
//        long timeSum = 0;
//        double averageTime = 0;
//        double averageChunkSize = 0;
        String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
//        while (!last) {
            DownloadFileRequest.Builder bld = DownloadFileRequest.newBuilder();
            bld.setFilename(fileName);
            long start = System.currentTimeMillis();
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
            //System.out.println("Client: " + r.getLast());
            //last = r.getLast();
//        }

        ch.shutdown();
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
