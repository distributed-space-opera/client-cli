// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client-comm.proto

package org.client.protos;

public interface DownloadFileRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:stream.DownloadFileRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *  TODO add auth jwt token
   * </pre>
   *
   * <code>string filename = 1;</code>
   * @return The filename.
   */
  java.lang.String getFilename();
  /**
   * <pre>
   *  TODO add auth jwt token
   * </pre>
   *
   * <code>string filename = 1;</code>
   * @return The bytes for filename.
   */
  com.google.protobuf.ByteString
      getFilenameBytes();

  /**
   * <code>string token = 2;</code>
   * @return The token.
   */
  java.lang.String getToken();
  /**
   * <code>string token = 2;</code>
   * @return The bytes for token.
   */
  com.google.protobuf.ByteString
      getTokenBytes();
}
