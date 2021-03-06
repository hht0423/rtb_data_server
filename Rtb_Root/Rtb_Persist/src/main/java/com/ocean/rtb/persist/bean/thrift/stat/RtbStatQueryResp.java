/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.ocean.rtb.persist.bean.thrift.stat;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2019-3-27")
public class RtbStatQueryResp implements org.apache.thrift.TBase<RtbStatQueryResp, RtbStatQueryResp._Fields>, java.io.Serializable, Cloneable, Comparable<RtbStatQueryResp> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RtbStatQueryResp");

  private static final org.apache.thrift.protocol.TField ERROR_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("errorCode", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField ERROR_MSG_FIELD_DESC = new org.apache.thrift.protocol.TField("errorMsg", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField DATA_FIELD_DESC = new org.apache.thrift.protocol.TField("data", org.apache.thrift.protocol.TType.MAP, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new RtbStatQueryRespStandardSchemeFactory());
    schemes.put(TupleScheme.class, new RtbStatQueryRespTupleSchemeFactory());
  }

  public int errorCode; // required
  public String errorMsg; // optional
  public Map<String,RtbStatQueryData> data; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ERROR_CODE((short)1, "errorCode"),
    ERROR_MSG((short)2, "errorMsg"),
    DATA((short)3, "data");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ERROR_CODE
          return ERROR_CODE;
        case 2: // ERROR_MSG
          return ERROR_MSG;
        case 3: // DATA
          return DATA;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ERRORCODE_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.ERROR_MSG,_Fields.DATA};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ERROR_CODE, new org.apache.thrift.meta_data.FieldMetaData("errorCode", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.ERROR_MSG, new org.apache.thrift.meta_data.FieldMetaData("errorMsg", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DATA, new org.apache.thrift.meta_data.FieldMetaData("data", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, RtbStatQueryData.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RtbStatQueryResp.class, metaDataMap);
  }

  public RtbStatQueryResp() {
  }

  public RtbStatQueryResp(
    int errorCode)
  {
    this();
    this.errorCode = errorCode;
    setErrorCodeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RtbStatQueryResp(RtbStatQueryResp other) {
    __isset_bitfield = other.__isset_bitfield;
    this.errorCode = other.errorCode;
    if (other.isSetErrorMsg()) {
      this.errorMsg = other.errorMsg;
    }
    if (other.isSetData()) {
      Map<String,RtbStatQueryData> __this__data = new HashMap<String,RtbStatQueryData>(other.data.size());
      for (Map.Entry<String, RtbStatQueryData> other_element : other.data.entrySet()) {

        String other_element_key = other_element.getKey();
        RtbStatQueryData other_element_value = other_element.getValue();

        String __this__data_copy_key = other_element_key;

        RtbStatQueryData __this__data_copy_value = new RtbStatQueryData(other_element_value);

        __this__data.put(__this__data_copy_key, __this__data_copy_value);
      }
      this.data = __this__data;
    }
  }

  public RtbStatQueryResp deepCopy() {
    return new RtbStatQueryResp(this);
  }

  @Override
  public void clear() {
    setErrorCodeIsSet(false);
    this.errorCode = 0;
    this.errorMsg = null;
    this.data = null;
  }

  public int getErrorCode() {
    return this.errorCode;
  }

  public RtbStatQueryResp setErrorCode(int errorCode) {
    this.errorCode = errorCode;
    setErrorCodeIsSet(true);
    return this;
  }

  public void unsetErrorCode() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ERRORCODE_ISSET_ID);
  }

  /** Returns true if field errorCode is set (has been assigned a value) and false otherwise */
  public boolean isSetErrorCode() {
    return EncodingUtils.testBit(__isset_bitfield, __ERRORCODE_ISSET_ID);
  }

  public void setErrorCodeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ERRORCODE_ISSET_ID, value);
  }

  public String getErrorMsg() {
    return this.errorMsg;
  }

  public RtbStatQueryResp setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
    return this;
  }

  public void unsetErrorMsg() {
    this.errorMsg = null;
  }

  /** Returns true if field errorMsg is set (has been assigned a value) and false otherwise */
  public boolean isSetErrorMsg() {
    return this.errorMsg != null;
  }

  public void setErrorMsgIsSet(boolean value) {
    if (!value) {
      this.errorMsg = null;
    }
  }

  public int getDataSize() {
    return (this.data == null) ? 0 : this.data.size();
  }

  public void putToData(String key, RtbStatQueryData val) {
    if (this.data == null) {
      this.data = new HashMap<String,RtbStatQueryData>();
    }
    this.data.put(key, val);
  }

  public Map<String,RtbStatQueryData> getData() {
    return this.data;
  }

  public RtbStatQueryResp setData(Map<String,RtbStatQueryData> data) {
    this.data = data;
    return this;
  }

  public void unsetData() {
    this.data = null;
  }

  /** Returns true if field data is set (has been assigned a value) and false otherwise */
  public boolean isSetData() {
    return this.data != null;
  }

  public void setDataIsSet(boolean value) {
    if (!value) {
      this.data = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ERROR_CODE:
      if (value == null) {
        unsetErrorCode();
      } else {
        setErrorCode((Integer)value);
      }
      break;

    case ERROR_MSG:
      if (value == null) {
        unsetErrorMsg();
      } else {
        setErrorMsg((String)value);
      }
      break;

    case DATA:
      if (value == null) {
        unsetData();
      } else {
        setData((Map<String,RtbStatQueryData>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ERROR_CODE:
      return Integer.valueOf(getErrorCode());

    case ERROR_MSG:
      return getErrorMsg();

    case DATA:
      return getData();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ERROR_CODE:
      return isSetErrorCode();
    case ERROR_MSG:
      return isSetErrorMsg();
    case DATA:
      return isSetData();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof RtbStatQueryResp)
      return this.equals((RtbStatQueryResp)that);
    return false;
  }

  public boolean equals(RtbStatQueryResp that) {
    if (that == null)
      return false;

    boolean this_present_errorCode = true;
    boolean that_present_errorCode = true;
    if (this_present_errorCode || that_present_errorCode) {
      if (!(this_present_errorCode && that_present_errorCode))
        return false;
      if (this.errorCode != that.errorCode)
        return false;
    }

    boolean this_present_errorMsg = true && this.isSetErrorMsg();
    boolean that_present_errorMsg = true && that.isSetErrorMsg();
    if (this_present_errorMsg || that_present_errorMsg) {
      if (!(this_present_errorMsg && that_present_errorMsg))
        return false;
      if (!this.errorMsg.equals(that.errorMsg))
        return false;
    }

    boolean this_present_data = true && this.isSetData();
    boolean that_present_data = true && that.isSetData();
    if (this_present_data || that_present_data) {
      if (!(this_present_data && that_present_data))
        return false;
      if (!this.data.equals(that.data))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_errorCode = true;
    list.add(present_errorCode);
    if (present_errorCode)
      list.add(errorCode);

    boolean present_errorMsg = true && (isSetErrorMsg());
    list.add(present_errorMsg);
    if (present_errorMsg)
      list.add(errorMsg);

    boolean present_data = true && (isSetData());
    list.add(present_data);
    if (present_data)
      list.add(data);

    return list.hashCode();
  }

  @Override
  public int compareTo(RtbStatQueryResp other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetErrorCode()).compareTo(other.isSetErrorCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetErrorCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.errorCode, other.errorCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetErrorMsg()).compareTo(other.isSetErrorMsg());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetErrorMsg()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.errorMsg, other.errorMsg);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetData()).compareTo(other.isSetData());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetData()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.data, other.data);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("RtbStatQueryResp(");
    boolean first = true;

    sb.append("errorCode:");
    sb.append(this.errorCode);
    first = false;
    if (isSetErrorMsg()) {
      if (!first) sb.append(", ");
      sb.append("errorMsg:");
      if (this.errorMsg == null) {
        sb.append("null");
      } else {
        sb.append(this.errorMsg);
      }
      first = false;
    }
    if (isSetData()) {
      if (!first) sb.append(", ");
      sb.append("data:");
      if (this.data == null) {
        sb.append("null");
      } else {
        sb.append(this.data);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'errorCode' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RtbStatQueryRespStandardSchemeFactory implements SchemeFactory {
    public RtbStatQueryRespStandardScheme getScheme() {
      return new RtbStatQueryRespStandardScheme();
    }
  }

  private static class RtbStatQueryRespStandardScheme extends StandardScheme<RtbStatQueryResp> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RtbStatQueryResp struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ERROR_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.errorCode = iprot.readI32();
              struct.setErrorCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ERROR_MSG
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.errorMsg = iprot.readString();
              struct.setErrorMsgIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // DATA
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map18 = iprot.readMapBegin();
                struct.data = new HashMap<String,RtbStatQueryData>(2*_map18.size);
                String _key19;
                RtbStatQueryData _val20;
                for (int _i21 = 0; _i21 < _map18.size; ++_i21)
                {
                  _key19 = iprot.readString();
                  _val20 = new RtbStatQueryData();
                  _val20.read(iprot);
                  struct.data.put(_key19, _val20);
                }
                iprot.readMapEnd();
              }
              struct.setDataIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetErrorCode()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'errorCode' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, RtbStatQueryResp struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ERROR_CODE_FIELD_DESC);
      oprot.writeI32(struct.errorCode);
      oprot.writeFieldEnd();
      if (struct.errorMsg != null) {
        if (struct.isSetErrorMsg()) {
          oprot.writeFieldBegin(ERROR_MSG_FIELD_DESC);
          oprot.writeString(struct.errorMsg);
          oprot.writeFieldEnd();
        }
      }
      if (struct.data != null) {
        if (struct.isSetData()) {
          oprot.writeFieldBegin(DATA_FIELD_DESC);
          {
            oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRUCT, struct.data.size()));
            for (Map.Entry<String, RtbStatQueryData> _iter22 : struct.data.entrySet())
            {
              oprot.writeString(_iter22.getKey());
              _iter22.getValue().write(oprot);
            }
            oprot.writeMapEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RtbStatQueryRespTupleSchemeFactory implements SchemeFactory {
    public RtbStatQueryRespTupleScheme getScheme() {
      return new RtbStatQueryRespTupleScheme();
    }
  }

  private static class RtbStatQueryRespTupleScheme extends TupleScheme<RtbStatQueryResp> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RtbStatQueryResp struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.errorCode);
      BitSet optionals = new BitSet();
      if (struct.isSetErrorMsg()) {
        optionals.set(0);
      }
      if (struct.isSetData()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetErrorMsg()) {
        oprot.writeString(struct.errorMsg);
      }
      if (struct.isSetData()) {
        {
          oprot.writeI32(struct.data.size());
          for (Map.Entry<String, RtbStatQueryData> _iter23 : struct.data.entrySet())
          {
            oprot.writeString(_iter23.getKey());
            _iter23.getValue().write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RtbStatQueryResp struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.errorCode = iprot.readI32();
      struct.setErrorCodeIsSet(true);
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.errorMsg = iprot.readString();
        struct.setErrorMsgIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TMap _map24 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.data = new HashMap<String,RtbStatQueryData>(2*_map24.size);
          String _key25;
          RtbStatQueryData _val26;
          for (int _i27 = 0; _i27 < _map24.size; ++_i27)
          {
            _key25 = iprot.readString();
            _val26 = new RtbStatQueryData();
            _val26.read(iprot);
            struct.data.put(_key25, _val26);
          }
        }
        struct.setDataIsSet(true);
      }
    }
  }

}

