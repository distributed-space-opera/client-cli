// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: gateway-comm.proto

package org.gateway.protos;

public interface DownloadRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:stream.DownloadRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string client_ip = 1;</code>
   * @return The clientIp.
   */
  java.lang.String getClientIp();
  /**
   * <code>string client_ip = 1;</code>
   * @return The bytes for clientIp.
   */
  com.google.protobuf.ByteString
      getClientIpBytes();

  /**
   * <code>string filename = 2;</code>
   * @return The filename.
   */
  java.lang.String getFilename();
  /**
   * <code>string filename = 2;</code>
   * @return The bytes for filename.
   */
  com.google.protobuf.ByteString
      getFilenameBytes();

  /**
   * <code>string token = 3;</code>
   * @return The token.
   */
  java.lang.String getToken();
  /**
   * <code>string token = 3;</code>
   * @return The bytes for token.
   */
  com.google.protobuf.ByteString
      getTokenBytes();
}
