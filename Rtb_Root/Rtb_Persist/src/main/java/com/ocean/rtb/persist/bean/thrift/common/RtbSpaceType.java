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

public enum RtbSpaceType implements org.apache.thrift.TEnum {
  BANNER(1),
  OPENING(2),
  INTERSTITIAL(3),
  INFOFLOW(4),
  TEXTLINK(5),
  LOCKSCREEN(6);

  private final int value;

  private RtbSpaceType(int value) {
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
  public static RtbSpaceType findByValue(int value) { 
    switch (value) {
      case 1:
        return BANNER;
      case 2:
        return OPENING;
      case 3:
        return INTERSTITIAL;
      case 4:
        return INFOFLOW;
      case 5:
        return TEXTLINK;
      case 6:
        return LOCKSCREEN;
      default:
        return null;
    }
  }
}
