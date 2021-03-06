/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.ocean.rtb.persist.bean.thrift.common;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum InteractionType implements org.apache.thrift.TEnum {
  NO_INTERACTION(0),
  BROWSE(1),
  DOWNLOAD(2);

  private final int value;

  private InteractionType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static InteractionType findByValue(int value) { 
    switch (value) {
      case 0:
        return NO_INTERACTION;
      case 1:
        return BROWSE;
      case 2:
        return DOWNLOAD;
      default:
        return null;
    }
  }
}
