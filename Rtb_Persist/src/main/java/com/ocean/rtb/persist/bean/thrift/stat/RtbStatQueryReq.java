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
public class RtbStatQueryReq implements org.apache.thrift.TBase<RtbStatQueryReq, RtbStatQueryReq._Fields>, java.io.Serializable, Cloneable, Comparable<RtbStatQueryReq> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RtbStatQueryReq");

  private static final org.apache.thrift.protocol.TField SRC_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("srcType", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField SRC_IDS_FIELD_DESC = new org.apache.thrift.protocol.TField("srcIds", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new RtbStatQueryReqStandardSchemeFactory());
    schemes.put(TupleScheme.class, new RtbStatQueryReqTupleSchemeFactory());
  }

  /**
   * 
   * @see com.ocean.rtb.persist.bean.thrift.common.RtbSrcType
   */
  public com.ocean.rtb.persist.bean.thrift.common.RtbSrcType srcType; // required
  public List<String> srcIds; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see com.ocean.rtb.persist.bean.thrift.common.RtbSrcType
     */
    SRC_TYPE((short)1, "srcType"),
    SRC_IDS((short)2, "srcIds");

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
        case 1: // SRC_TYPE
          return SRC_TYPE;
        case 2: // SRC_IDS
          return SRC_IDS;
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
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SRC_TYPE, new org.apache.thrift.meta_data.FieldMetaData("srcType", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, com.ocean.rtb.persist.bean.thrift.common.RtbSrcType.class)));
    tmpMap.put(_Fields.SRC_IDS, new org.apache.thrift.meta_data.FieldMetaData("srcIds", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RtbStatQueryReq.class, metaDataMap);
  }

  public RtbStatQueryReq() {
  }

  public RtbStatQueryReq(
    com.ocean.rtb.persist.bean.thrift.common.RtbSrcType srcType,
    List<String> srcIds)
  {
    this();
    this.srcType = srcType;
    this.srcIds = srcIds;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RtbStatQueryReq(RtbStatQueryReq other) {
    if (other.isSetSrcType()) {
      this.srcType = other.srcType;
    }
    if (other.isSetSrcIds()) {
      List<String> __this__srcIds = new ArrayList<String>(other.srcIds);
      this.srcIds = __this__srcIds;
    }
  }

  public RtbStatQueryReq deepCopy() {
    return new RtbStatQueryReq(this);
  }

  @Override
  public void clear() {
    this.srcType = null;
    this.srcIds = null;
  }

  /**
   * 
   * @see com.ocean.rtb.persist.bean.thrift.common.RtbSrcType
   */
  public com.ocean.rtb.persist.bean.thrift.common.RtbSrcType getSrcType() {
    return this.srcType;
  }

  /**
   * 
   * @see com.ocean.rtb.persist.bean.thrift.common.RtbSrcType
   */
  public RtbStatQueryReq setSrcType(com.ocean.rtb.persist.bean.thrift.common.RtbSrcType srcType) {
    this.srcType = srcType;
    return this;
  }

  public void unsetSrcType() {
    this.srcType = null;
  }

  /** Returns true if field srcType is set (has been assigned a value) and false otherwise */
  public boolean isSetSrcType() {
    return this.srcType != null;
  }

  public void setSrcTypeIsSet(boolean value) {
    if (!value) {
      this.srcType = null;
    }
  }

  public int getSrcIdsSize() {
    return (this.srcIds == null) ? 0 : this.srcIds.size();
  }

  public java.util.Iterator<String> getSrcIdsIterator() {
    return (this.srcIds == null) ? null : this.srcIds.iterator();
  }

  public void addToSrcIds(String elem) {
    if (this.srcIds == null) {
      this.srcIds = new ArrayList<String>();
    }
    this.srcIds.add(elem);
  }

  public List<String> getSrcIds() {
    return this.srcIds;
  }

  public RtbStatQueryReq setSrcIds(List<String> srcIds) {
    this.srcIds = srcIds;
    return this;
  }

  public void unsetSrcIds() {
    this.srcIds = null;
  }

  /** Returns true if field srcIds is set (has been assigned a value) and false otherwise */
  public boolean isSetSrcIds() {
    return this.srcIds != null;
  }

  public void setSrcIdsIsSet(boolean value) {
    if (!value) {
      this.srcIds = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case SRC_TYPE:
      if (value == null) {
        unsetSrcType();
      } else {
        setSrcType((com.ocean.rtb.persist.bean.thrift.common.RtbSrcType)value);
      }
      break;

    case SRC_IDS:
      if (value == null) {
        unsetSrcIds();
      } else {
        setSrcIds((List<String>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case SRC_TYPE:
      return getSrcType();

    case SRC_IDS:
      return getSrcIds();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case SRC_TYPE:
      return isSetSrcType();
    case SRC_IDS:
      return isSetSrcIds();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof RtbStatQueryReq)
      return this.equals((RtbStatQueryReq)that);
    return false;
  }

  public boolean equals(RtbStatQueryReq that) {
    if (that == null)
      return false;

    boolean this_present_srcType = true && this.isSetSrcType();
    boolean that_present_srcType = true && that.isSetSrcType();
    if (this_present_srcType || that_present_srcType) {
      if (!(this_present_srcType && that_present_srcType))
        return false;
      if (!this.srcType.equals(that.srcType))
        return false;
    }

    boolean this_present_srcIds = true && this.isSetSrcIds();
    boolean that_present_srcIds = true && that.isSetSrcIds();
    if (this_present_srcIds || that_present_srcIds) {
      if (!(this_present_srcIds && that_present_srcIds))
        return false;
      if (!this.srcIds.equals(that.srcIds))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_srcType = true && (isSetSrcType());
    list.add(present_srcType);
    if (present_srcType)
      list.add(srcType.getValue());

    boolean present_srcIds = true && (isSetSrcIds());
    list.add(present_srcIds);
    if (present_srcIds)
      list.add(srcIds);

    return list.hashCode();
  }

  @Override
  public int compareTo(RtbStatQueryReq other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetSrcType()).compareTo(other.isSetSrcType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSrcType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.srcType, other.srcType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSrcIds()).compareTo(other.isSetSrcIds());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSrcIds()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.srcIds, other.srcIds);
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
    StringBuilder sb = new StringBuilder("RtbStatQueryReq(");
    boolean first = true;

    sb.append("srcType:");
    if (this.srcType == null) {
      sb.append("null");
    } else {
      sb.append(this.srcType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("srcIds:");
    if (this.srcIds == null) {
      sb.append("null");
    } else {
      sb.append(this.srcIds);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (srcType == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'srcType' was not present! Struct: " + toString());
    }
    if (srcIds == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'srcIds' was not present! Struct: " + toString());
    }
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RtbStatQueryReqStandardSchemeFactory implements SchemeFactory {
    public RtbStatQueryReqStandardScheme getScheme() {
      return new RtbStatQueryReqStandardScheme();
    }
  }

  private static class RtbStatQueryReqStandardScheme extends StandardScheme<RtbStatQueryReq> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RtbStatQueryReq struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SRC_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.srcType = com.ocean.rtb.persist.bean.thrift.common.RtbSrcType.findByValue(iprot.readI32());
              struct.setSrcTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SRC_IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.srcIds = new ArrayList<String>(_list0.size);
                String _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = iprot.readString();
                  struct.srcIds.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setSrcIdsIsSet(true);
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
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, RtbStatQueryReq struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.srcType != null) {
        oprot.writeFieldBegin(SRC_TYPE_FIELD_DESC);
        oprot.writeI32(struct.srcType.getValue());
        oprot.writeFieldEnd();
      }
      if (struct.srcIds != null) {
        oprot.writeFieldBegin(SRC_IDS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.srcIds.size()));
          for (String _iter3 : struct.srcIds)
          {
            oprot.writeString(_iter3);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RtbStatQueryReqTupleSchemeFactory implements SchemeFactory {
    public RtbStatQueryReqTupleScheme getScheme() {
      return new RtbStatQueryReqTupleScheme();
    }
  }

  private static class RtbStatQueryReqTupleScheme extends TupleScheme<RtbStatQueryReq> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RtbStatQueryReq struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.srcType.getValue());
      {
        oprot.writeI32(struct.srcIds.size());
        for (String _iter4 : struct.srcIds)
        {
          oprot.writeString(_iter4);
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RtbStatQueryReq struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.srcType = com.ocean.rtb.persist.bean.thrift.common.RtbSrcType.findByValue(iprot.readI32());
      struct.setSrcTypeIsSet(true);
      {
        org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
        struct.srcIds = new ArrayList<String>(_list5.size);
        String _elem6;
        for (int _i7 = 0; _i7 < _list5.size; ++_i7)
        {
          _elem6 = iprot.readString();
          struct.srcIds.add(_elem6);
        }
      }
      struct.setSrcIdsIsSet(true);
    }
  }

}

