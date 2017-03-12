/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.tencent.ais.communication.protocol;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-03-08")
public class ExecutorMessage implements org.apache.thrift.TBase<ExecutorMessage, ExecutorMessage._Fields>, java.io.Serializable, Cloneable, Comparable<ExecutorMessage> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ExecutorMessage");

  private static final org.apache.thrift.protocol.TField EXECUTOR_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("executorId", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField EXECUTOR_HOSTNAME_FIELD_DESC = new org.apache.thrift.protocol.TField("executorHostname", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField TASK_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("taskId", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField LAST_UPDATE_FIELD_DESC = new org.apache.thrift.protocol.TField("lastUpdate", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField RESOURCE_MESSAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("resourceMessage", org.apache.thrift.protocol.TType.STRUCT, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ExecutorMessageStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ExecutorMessageTupleSchemeFactory();

  public String executorId; // required
  public String executorHostname; // required
  public String taskId; // required
  public long lastUpdate; // required
  public ResourceMessage resourceMessage; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    EXECUTOR_ID((short)1, "executorId"),
    EXECUTOR_HOSTNAME((short)2, "executorHostname"),
    TASK_ID((short)3, "taskId"),
    LAST_UPDATE((short)4, "lastUpdate"),
    RESOURCE_MESSAGE((short)5, "resourceMessage");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // EXECUTOR_ID
          return EXECUTOR_ID;
        case 2: // EXECUTOR_HOSTNAME
          return EXECUTOR_HOSTNAME;
        case 3: // TASK_ID
          return TASK_ID;
        case 4: // LAST_UPDATE
          return LAST_UPDATE;
        case 5: // RESOURCE_MESSAGE
          return RESOURCE_MESSAGE;
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
  private static final int __LASTUPDATE_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.EXECUTOR_ID, new org.apache.thrift.meta_data.FieldMetaData("executorId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.EXECUTOR_HOSTNAME, new org.apache.thrift.meta_data.FieldMetaData("executorHostname", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TASK_ID, new org.apache.thrift.meta_data.FieldMetaData("taskId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.LAST_UPDATE, new org.apache.thrift.meta_data.FieldMetaData("lastUpdate", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.RESOURCE_MESSAGE, new org.apache.thrift.meta_data.FieldMetaData("resourceMessage", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ResourceMessage.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ExecutorMessage.class, metaDataMap);
  }

  public ExecutorMessage() {
  }

  public ExecutorMessage(
    String executorId,
    String executorHostname,
    String taskId,
    long lastUpdate,
    ResourceMessage resourceMessage)
  {
    this();
    this.executorId = executorId;
    this.executorHostname = executorHostname;
    this.taskId = taskId;
    this.lastUpdate = lastUpdate;
    setLastUpdateIsSet(true);
    this.resourceMessage = resourceMessage;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ExecutorMessage(ExecutorMessage other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetExecutorId()) {
      this.executorId = other.executorId;
    }
    if (other.isSetExecutorHostname()) {
      this.executorHostname = other.executorHostname;
    }
    if (other.isSetTaskId()) {
      this.taskId = other.taskId;
    }
    this.lastUpdate = other.lastUpdate;
    if (other.isSetResourceMessage()) {
      this.resourceMessage = new ResourceMessage(other.resourceMessage);
    }
  }

  public ExecutorMessage deepCopy() {
    return new ExecutorMessage(this);
  }

  @Override
  public void clear() {
    this.executorId = null;
    this.executorHostname = null;
    this.taskId = null;
    setLastUpdateIsSet(false);
    this.lastUpdate = 0;
    this.resourceMessage = null;
  }

  public String getExecutorId() {
    return this.executorId;
  }

  public ExecutorMessage setExecutorId(String executorId) {
    this.executorId = executorId;
    return this;
  }

  public void unsetExecutorId() {
    this.executorId = null;
  }

  /** Returns true if field executorId is set (has been assigned a value) and false otherwise */
  public boolean isSetExecutorId() {
    return this.executorId != null;
  }

  public void setExecutorIdIsSet(boolean value) {
    if (!value) {
      this.executorId = null;
    }
  }

  public String getExecutorHostname() {
    return this.executorHostname;
  }

  public ExecutorMessage setExecutorHostname(String executorHostname) {
    this.executorHostname = executorHostname;
    return this;
  }

  public void unsetExecutorHostname() {
    this.executorHostname = null;
  }

  /** Returns true if field executorHostname is set (has been assigned a value) and false otherwise */
  public boolean isSetExecutorHostname() {
    return this.executorHostname != null;
  }

  public void setExecutorHostnameIsSet(boolean value) {
    if (!value) {
      this.executorHostname = null;
    }
  }

  public String getTaskId() {
    return this.taskId;
  }

  public ExecutorMessage setTaskId(String taskId) {
    this.taskId = taskId;
    return this;
  }

  public void unsetTaskId() {
    this.taskId = null;
  }

  /** Returns true if field taskId is set (has been assigned a value) and false otherwise */
  public boolean isSetTaskId() {
    return this.taskId != null;
  }

  public void setTaskIdIsSet(boolean value) {
    if (!value) {
      this.taskId = null;
    }
  }

  public long getLastUpdate() {
    return this.lastUpdate;
  }

  public ExecutorMessage setLastUpdate(long lastUpdate) {
    this.lastUpdate = lastUpdate;
    setLastUpdateIsSet(true);
    return this;
  }

  public void unsetLastUpdate() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __LASTUPDATE_ISSET_ID);
  }

  /** Returns true if field lastUpdate is set (has been assigned a value) and false otherwise */
  public boolean isSetLastUpdate() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __LASTUPDATE_ISSET_ID);
  }

  public void setLastUpdateIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __LASTUPDATE_ISSET_ID, value);
  }

  public ResourceMessage getResourceMessage() {
    return this.resourceMessage;
  }

  public ExecutorMessage setResourceMessage(ResourceMessage resourceMessage) {
    this.resourceMessage = resourceMessage;
    return this;
  }

  public void unsetResourceMessage() {
    this.resourceMessage = null;
  }

  /** Returns true if field resourceMessage is set (has been assigned a value) and false otherwise */
  public boolean isSetResourceMessage() {
    return this.resourceMessage != null;
  }

  public void setResourceMessageIsSet(boolean value) {
    if (!value) {
      this.resourceMessage = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case EXECUTOR_ID:
      if (value == null) {
        unsetExecutorId();
      } else {
        setExecutorId((String)value);
      }
      break;

    case EXECUTOR_HOSTNAME:
      if (value == null) {
        unsetExecutorHostname();
      } else {
        setExecutorHostname((String)value);
      }
      break;

    case TASK_ID:
      if (value == null) {
        unsetTaskId();
      } else {
        setTaskId((String)value);
      }
      break;

    case LAST_UPDATE:
      if (value == null) {
        unsetLastUpdate();
      } else {
        setLastUpdate((Long)value);
      }
      break;

    case RESOURCE_MESSAGE:
      if (value == null) {
        unsetResourceMessage();
      } else {
        setResourceMessage((ResourceMessage)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case EXECUTOR_ID:
      return getExecutorId();

    case EXECUTOR_HOSTNAME:
      return getExecutorHostname();

    case TASK_ID:
      return getTaskId();

    case LAST_UPDATE:
      return getLastUpdate();

    case RESOURCE_MESSAGE:
      return getResourceMessage();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case EXECUTOR_ID:
      return isSetExecutorId();
    case EXECUTOR_HOSTNAME:
      return isSetExecutorHostname();
    case TASK_ID:
      return isSetTaskId();
    case LAST_UPDATE:
      return isSetLastUpdate();
    case RESOURCE_MESSAGE:
      return isSetResourceMessage();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ExecutorMessage)
      return this.equals((ExecutorMessage)that);
    return false;
  }

  public boolean equals(ExecutorMessage that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_executorId = true && this.isSetExecutorId();
    boolean that_present_executorId = true && that.isSetExecutorId();
    if (this_present_executorId || that_present_executorId) {
      if (!(this_present_executorId && that_present_executorId))
        return false;
      if (!this.executorId.equals(that.executorId))
        return false;
    }

    boolean this_present_executorHostname = true && this.isSetExecutorHostname();
    boolean that_present_executorHostname = true && that.isSetExecutorHostname();
    if (this_present_executorHostname || that_present_executorHostname) {
      if (!(this_present_executorHostname && that_present_executorHostname))
        return false;
      if (!this.executorHostname.equals(that.executorHostname))
        return false;
    }

    boolean this_present_taskId = true && this.isSetTaskId();
    boolean that_present_taskId = true && that.isSetTaskId();
    if (this_present_taskId || that_present_taskId) {
      if (!(this_present_taskId && that_present_taskId))
        return false;
      if (!this.taskId.equals(that.taskId))
        return false;
    }

    boolean this_present_lastUpdate = true;
    boolean that_present_lastUpdate = true;
    if (this_present_lastUpdate || that_present_lastUpdate) {
      if (!(this_present_lastUpdate && that_present_lastUpdate))
        return false;
      if (this.lastUpdate != that.lastUpdate)
        return false;
    }

    boolean this_present_resourceMessage = true && this.isSetResourceMessage();
    boolean that_present_resourceMessage = true && that.isSetResourceMessage();
    if (this_present_resourceMessage || that_present_resourceMessage) {
      if (!(this_present_resourceMessage && that_present_resourceMessage))
        return false;
      if (!this.resourceMessage.equals(that.resourceMessage))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetExecutorId()) ? 131071 : 524287);
    if (isSetExecutorId())
      hashCode = hashCode * 8191 + executorId.hashCode();

    hashCode = hashCode * 8191 + ((isSetExecutorHostname()) ? 131071 : 524287);
    if (isSetExecutorHostname())
      hashCode = hashCode * 8191 + executorHostname.hashCode();

    hashCode = hashCode * 8191 + ((isSetTaskId()) ? 131071 : 524287);
    if (isSetTaskId())
      hashCode = hashCode * 8191 + taskId.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(lastUpdate);

    hashCode = hashCode * 8191 + ((isSetResourceMessage()) ? 131071 : 524287);
    if (isSetResourceMessage())
      hashCode = hashCode * 8191 + resourceMessage.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(ExecutorMessage other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetExecutorId()).compareTo(other.isSetExecutorId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExecutorId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.executorId, other.executorId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetExecutorHostname()).compareTo(other.isSetExecutorHostname());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExecutorHostname()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.executorHostname, other.executorHostname);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTaskId()).compareTo(other.isSetTaskId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTaskId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.taskId, other.taskId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLastUpdate()).compareTo(other.isSetLastUpdate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLastUpdate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.lastUpdate, other.lastUpdate);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetResourceMessage()).compareTo(other.isSetResourceMessage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResourceMessage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.resourceMessage, other.resourceMessage);
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
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ExecutorMessage(");
    boolean first = true;

    sb.append("executorId:");
    if (this.executorId == null) {
      sb.append("null");
    } else {
      sb.append(this.executorId);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("executorHostname:");
    if (this.executorHostname == null) {
      sb.append("null");
    } else {
      sb.append(this.executorHostname);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("taskId:");
    if (this.taskId == null) {
      sb.append("null");
    } else {
      sb.append(this.taskId);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("lastUpdate:");
    sb.append(this.lastUpdate);
    first = false;
    if (!first) sb.append(", ");
    sb.append("resourceMessage:");
    if (this.resourceMessage == null) {
      sb.append("null");
    } else {
      sb.append(this.resourceMessage);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (executorId == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'executorId' was not present! Struct: " + toString());
    }
    if (executorHostname == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'executorHostname' was not present! Struct: " + toString());
    }
    if (taskId == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'taskId' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'lastUpdate' because it's a primitive and you chose the non-beans generator.
    if (resourceMessage == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'resourceMessage' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (resourceMessage != null) {
      resourceMessage.validate();
    }
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

  private static class ExecutorMessageStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ExecutorMessageStandardScheme getScheme() {
      return new ExecutorMessageStandardScheme();
    }
  }

  private static class ExecutorMessageStandardScheme extends org.apache.thrift.scheme.StandardScheme<ExecutorMessage> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ExecutorMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // EXECUTOR_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.executorId = iprot.readString();
              struct.setExecutorIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // EXECUTOR_HOSTNAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.executorHostname = iprot.readString();
              struct.setExecutorHostnameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TASK_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.taskId = iprot.readString();
              struct.setTaskIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // LAST_UPDATE
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.lastUpdate = iprot.readI64();
              struct.setLastUpdateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // RESOURCE_MESSAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.resourceMessage = new ResourceMessage();
              struct.resourceMessage.read(iprot);
              struct.setResourceMessageIsSet(true);
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
      if (!struct.isSetLastUpdate()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'lastUpdate' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ExecutorMessage struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.executorId != null) {
        oprot.writeFieldBegin(EXECUTOR_ID_FIELD_DESC);
        oprot.writeString(struct.executorId);
        oprot.writeFieldEnd();
      }
      if (struct.executorHostname != null) {
        oprot.writeFieldBegin(EXECUTOR_HOSTNAME_FIELD_DESC);
        oprot.writeString(struct.executorHostname);
        oprot.writeFieldEnd();
      }
      if (struct.taskId != null) {
        oprot.writeFieldBegin(TASK_ID_FIELD_DESC);
        oprot.writeString(struct.taskId);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(LAST_UPDATE_FIELD_DESC);
      oprot.writeI64(struct.lastUpdate);
      oprot.writeFieldEnd();
      if (struct.resourceMessage != null) {
        oprot.writeFieldBegin(RESOURCE_MESSAGE_FIELD_DESC);
        struct.resourceMessage.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ExecutorMessageTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ExecutorMessageTupleScheme getScheme() {
      return new ExecutorMessageTupleScheme();
    }
  }

  private static class ExecutorMessageTupleScheme extends org.apache.thrift.scheme.TupleScheme<ExecutorMessage> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ExecutorMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeString(struct.executorId);
      oprot.writeString(struct.executorHostname);
      oprot.writeString(struct.taskId);
      oprot.writeI64(struct.lastUpdate);
      struct.resourceMessage.write(oprot);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ExecutorMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.executorId = iprot.readString();
      struct.setExecutorIdIsSet(true);
      struct.executorHostname = iprot.readString();
      struct.setExecutorHostnameIsSet(true);
      struct.taskId = iprot.readString();
      struct.setTaskIdIsSet(true);
      struct.lastUpdate = iprot.readI64();
      struct.setLastUpdateIsSet(true);
      struct.resourceMessage = new ResourceMessage();
      struct.resourceMessage.read(iprot);
      struct.setResourceMessageIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
