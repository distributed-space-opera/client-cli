// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: master-comm.proto

package org.master.protos;

public interface GetListOfFilesResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:stream.GetListOfFilesResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated string filenames = 1;</code>
   * @return A list containing the filenames.
   */
  java.util.List<java.lang.String>
      getFilenamesList();
  /**
   * <code>repeated string filenames = 1;</code>
   * @return The count of filenames.
   */
  int getFilenamesCount();
  /**
   * <code>repeated string filenames = 1;</code>
   * @param index The index of the element to return.
   * @return The filenames at the given index.
   */
  java.lang.String getFilenames(int index);
  /**
   * <code>repeated string filenames = 1;</code>
   * @param index The index of the value to return.
   * @return The bytes of the filenames at the given index.
   */
  com.google.protobuf.ByteString
      getFilenamesBytes(int index);
}