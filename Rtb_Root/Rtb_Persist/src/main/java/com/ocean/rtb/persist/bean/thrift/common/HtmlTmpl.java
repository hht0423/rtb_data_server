/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.ocean.rtb.persist.bean.thrift.common;

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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2019-3-22")
public class HtmlTmpl implements org.apache.thrift.TBase<HtmlTmpl, HtmlTmpl._Fields>, java.io.Serializable, Cloneable, Comparable<HtmlTmpl> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("HtmlTmpl");

  private static final org.apache.thrift.protocol.TField TMPL_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("tmpl_id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField TMPL_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("tmpl_type", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField URL_FIELD_DESC = new org.apache.thrift.protocol.TField("url", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new HtmlTmplStandardSchemeFactory());
    schemes.put(TupleScheme.class, new HtmlTmplTupleSchemeFactory());
  }

  public int tmpl_id; // required
  public int tmpl_type; // required
  public String url; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TMPL_ID((short)1, "tmpl_id"),
    TMPL_TYPE((short)2, "tmpl_type"),
    URL((short)3, "url");

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
        case 1: // TMPL_ID
          return TMPL_ID;
        case 2: // TMPL_TYPE
          return TMPL_TYPE;
        case 3: // URL
          return URL;
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
  private static final int __TMPL_ID_ISSET_ID = 0;
  private static final int __TMPL_TYPE_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TMPL_ID, new org.apache.thrift.meta_data.FieldMetaData("tmpl_id", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TMPL_TYPE, new org.apache.thrift.meta_data.FieldMetaData("tmpl_type", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.URL, new org.apache.thrift.meta_data.FieldMetaData("url", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(HtmlTmpl.class, metaDataMap);
  }

  public HtmlTmpl() {
  }

  public HtmlTmpl(
    int tmpl_id,
    int tmpl_type,
    String url)
  {
    this();
    this.tmpl_id = tmpl_id;
    setTmpl_idIsSet(true);
    this.tmpl_type = tmpl_type;
    setTmpl_typeIsSet(true);
    this.url = url;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public HtmlTmpl(HtmlTmpl other) {
    __isset_bitfield = other.__isset_bitfield;
    this.tmpl_id = other.tmpl_id;
    this.tmpl_type = other.tmpl_type;
    if (other.isSetUrl()) {
      this.url = other.url;
    }
  }

  public HtmlTmpl deepCopy() {
    return new HtmlTmpl(this);
  }

  @Override
  public void clear() {
    setTmpl_idIsSet(false);
    this.tmpl_id = 0;
    setTmpl_typeIsSet(false);
    this.tmpl_type = 0;
    this.url = null;
  }

  public int getTmpl_id() {
    return this.tmpl_id;
  }

  public HtmlTmpl setTmpl_id(int tmpl_id) {
    this.tmpl_id = tmpl_id;
    setTmpl_idIsSet(true);
    return this;
  }

  public void unsetTmpl_id() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TMPL_ID_ISSET_ID);
  }

  /** Returns true if field tmpl_id is set (has been assigned a value) and false otherwise */
  public boolean isSetTmpl_id() {
    return EncodingUtils.testBit(__isset_bitfield, __TMPL_ID_ISSET_ID);
  }

  public void setTmpl_idIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TMPL_ID_ISSET_ID, value);
  }

  public int getTmpl_type() {
    return this.tmpl_type;
  }

  public HtmlTmpl setTmpl_type(int tmpl_type) {
    this.tmpl_type = tmpl_type;
    setTmpl_typeIsSet(true);
    return this;
  }

  public void unsetTmpl_type() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TMPL_TYPE_ISSET_ID);
  }

  /** Returns true if field tmpl_type is set (has been assigned a value) and false otherwise */
  public boolean isSetTmpl_type() {
    return EncodingUtils.testBit(__isset_bitfield, __TMPL_TYPE_ISSET_ID);
  }

  public void setTmpl_typeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TMPL_TYPE_ISSET_ID, value);
  }

  public String getUrl() {
    return this.url;
  }

  public HtmlTmpl setUrl(String url) {
    this.url = url;
    return this;
  }

  public void unsetUrl() {
    this.url = null;
  }

  /** Returns true if field url is set (has been assigned a value) and false otherwise */
  public boolean isSetUrl() {
    return this.url != null;
  }

  public void setUrlIsSet(boolean value) {
    if (!value) {
      this.url = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TMPL_ID:
      if (value == null) {
        unsetTmpl_id();
      } else {
        setTmpl_id((Integer)value);
      }
      break;

    case TMPL_TYPE:
      if (value == null) {
        unsetTmpl_type();
      } else {
        setTmpl_type((Integer)value);
      }
      break;

    case URL:
      if (value == null) {
        unsetUrl();
      } else {
        setUrl((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TMPL_ID:
      return Integer.valueOf(getTmpl_id());

    case TMPL_TYPE:
      return Integer.valueOf(getTmpl_type());

    case URL:
      return getUrl();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TMPL_ID:
      return isSetTmpl_id();
    case TMPL_TYPE:
      return isSetTmpl_type();
    case URL:
      return isSetUrl();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof HtmlTmpl)
      return this.equals((HtmlTmpl)that);
    return false;
  }

  public boolean equals(HtmlTmpl that) {
    if (that == null)
      return false;

    boolean this_present_tmpl_id = true;
    boolean that_present_tmpl_id = true;
    if (this_present_tmpl_id || that_present_tmpl_id) {
      if (!(this_present_tmpl_id && that_present_tmpl_id))
        return false;
      if (this.tmpl_id != that.tmpl_id)
        return false;
    }

    boolean this_present_tmpl_type = true;
    boolean that_present_tmpl_type = true;
    if (this_present_tmpl_type || that_present_tmpl_type) {
      if (!(this_present_tmpl_type && that_present_tmpl_type))
        return false;
      if (this.tmpl_type != that.tmpl_type)
        return false;
    }

    boolean this_present_url = true && this.isSetUrl();
    boolean that_present_url = true && that.isSetUrl();
    if (this_present_url || that_present_url) {
      if (!(this_present_url && that_present_url))
        return false;
      if (!this.url.equals(that.url))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_tmpl_id = true;
    list.add(present_tmpl_id);
    if (present_tmpl_id)
      list.add(tmpl_id);

    boolean present_tmpl_type = true;
    list.add(present_tmpl_type);
    if (present_tmpl_type)
      list.add(tmpl_type);

    boolean present_url = true && (isSetUrl());
    list.add(present_url);
    if (present_url)
      list.add(url);

    return list.hashCode();
  }

  @Override
  public int compareTo(HtmlTmpl other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetTmpl_id()).compareTo(other.isSetTmpl_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTmpl_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tmpl_id, other.tmpl_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTmpl_type()).compareTo(other.isSetTmpl_type());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTmpl_type()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tmpl_type, other.tmpl_type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUrl()).compareTo(other.isSetUrl());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUrl()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.url, other.url);
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
    StringBuilder sb = new StringBuilder("HtmlTmpl(");
    boolean first = true;

    sb.append("tmpl_id:");
    sb.append(this.tmpl_id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("tmpl_type:");
    sb.append(this.tmpl_type);
    first = false;
    if (!first) sb.append(", ");
    sb.append("url:");
    if (this.url == null) {
      sb.append("null");
    } else {
      sb.append(this.url);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'tmpl_id' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'tmpl_type' because it's a primitive and you chose the non-beans generator.
    if (url == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'url' was not present! Struct: " + toString());
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class HtmlTmplStandardSchemeFactory implements SchemeFactory {
    public HtmlTmplStandardScheme getScheme() {
      return new HtmlTmplStandardScheme();
    }
  }

  private static class HtmlTmplStandardScheme extends StandardScheme<HtmlTmpl> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, HtmlTmpl struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TMPL_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.tmpl_id = iprot.readI32();
              struct.setTmpl_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TMPL_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.tmpl_type = iprot.readI32();
              struct.setTmpl_typeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // URL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.url = iprot.readString();
              struct.setUrlIsSet(true);
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
      if (!struct.isSetTmpl_id()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'tmpl_id' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetTmpl_type()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'tmpl_type' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, HtmlTmpl struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(TMPL_ID_FIELD_DESC);
      oprot.writeI32(struct.tmpl_id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TMPL_TYPE_FIELD_DESC);
      oprot.writeI32(struct.tmpl_type);
      oprot.writeFieldEnd();
      if (struct.url != null) {
        oprot.writeFieldBegin(URL_FIELD_DESC);
        oprot.writeString(struct.url);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class HtmlTmplTupleSchemeFactory implements SchemeFactory {
    public HtmlTmplTupleScheme getScheme() {
      return new HtmlTmplTupleScheme();
    }
  }

  private static class HtmlTmplTupleScheme extends TupleScheme<HtmlTmpl> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, HtmlTmpl struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.tmpl_id);
      oprot.writeI32(struct.tmpl_type);
      oprot.writeString(struct.url);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, HtmlTmpl struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.tmpl_id = iprot.readI32();
      struct.setTmpl_idIsSet(true);
      struct.tmpl_type = iprot.readI32();
      struct.setTmpl_typeIsSet(true);
      struct.url = iprot.readString();
      struct.setUrlIsSet(true);
    }
  }

}

