// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client-comm.proto

package org.client.protos;

public interface UploadFileRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:stream.UploadFileRequest)
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
   * <code>bytes payload = 2;</code>
   * @return The payload.
   */
  com.google.protobuf.ByteString getPayload();
}