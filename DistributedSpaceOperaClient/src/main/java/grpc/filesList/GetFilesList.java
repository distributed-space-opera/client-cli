package grpc.filesList;

import org.master.protos.GetListOfFilesResponse;

import java.io.IOException;

public class GetFilesList {

    public static void main(String[] args) {
        GetListOfFilesResponse.Builder filesBld = GetListOfFilesResponse.newBuilder();
        System.out.println("Get List of Files: " + filesBld.getFilenames(1));
    }
}
