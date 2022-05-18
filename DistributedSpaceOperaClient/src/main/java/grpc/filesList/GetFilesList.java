package grpc.filesList;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.gateway.protos.AuthenticateGrpc;
import org.master.protos.GetListOfFilesRequest;
import org.master.protos.GetListOfFilesResponse;
import org.master.protos.ReplicationGrpc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetFilesList {

    public static void main(String[] args) {

        String connectionAddr = args[0];
        int port = Integer.parseInt(args[1]);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(connectionAddr, port).usePlaintext().build();
        ReplicationGrpc.ReplicationBlockingStub replicationBlockingStub = ReplicationGrpc.newBlockingStub(channel);

        GetListOfFilesRequest.Builder builder = GetListOfFilesRequest.newBuilder();
        if (args.length == 2) {
            builder.addAllNodeips(new ArrayList<>());
        } else {
            builder.addAllNodeips(List.of(args[2].split(",")));
        }
        GetListOfFilesResponse response = replicationBlockingStub.getListOfFiles(builder.build());
        for (String filename : response.getFilenamesList()) {
            System.out.println(filename);
        }
        channel.shutdown();
    }
}
