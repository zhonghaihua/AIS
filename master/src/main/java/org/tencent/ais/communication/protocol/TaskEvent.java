/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.tencent.ais.communication.protocol;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-03-15")
public class TaskEvent implements org.apache.thrift.TBase<TaskEvent, TaskEvent._Fields>, java.io.Serializable, Cloneable, Comparable<TaskEvent> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TaskEvent");

  private static final org.apache.thrift.protocol.TField EVENT_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("eventType", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField TASK_MESSAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("taskMessage", org.apache.thrift.protocol.TType.STRUCT, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TaskEventStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TaskEventTupleSchemeFactory();

  public String eventType; // required
  public TaskMessage taskMessage; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    EVENT_TYPE((short)1, "eventType"),
    TASK_MESSAGE((short)2, "taskMessage");

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
        case 1: // EVENT_TYPE
          return EVENT_TYPE;
        case 2: // TASK_MESSAGE
          return TASK_MESSAGE;
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
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.EVENT_TYPE, new org.apache.thrift.meta_data.FieldMetaData("eventType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TASK_MESSAGE, new org.apache.thrift.meta_data.FieldMetaData("taskMessage", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TaskMessage.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TaskEvent.class, metaDataMap);
  }

  public TaskEvent() {
  }

  public TaskEvent(
    String eventType,
    TaskMessage taskMessage)
  {
    this();
    this.eventType = eventType;
    this.taskMessage = taskMessage;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TaskEvent(TaskEvent other) {
    if (other.isSetEventType()) {
      this.eventType = other.eventType;
    }
    if (other.isSetTaskMessage()) {
      this.taskMessage = new TaskMessage(other.taskMessage);
    }
  }

  public TaskEvent deepCopy() {
    return new TaskEvent(this);
  }

  @Override
  public void clear() {
    this.eventType = null;
    this.taskMessage = null;
  }

  public String getEventType() {
    return this.eventType;
  }

  public TaskEvent setEventType(String eventType) {
    this.eventType = eventType;
    return this;
  }

  public void unsetEventType() {
    this.eventType = null;
  }

  /** Returns true if field eventType is set (has been assigned a value) and false otherwise */
  public boolean isSetEventType() {
    return this.eventType != null;
  }

  public void setEventTypeIsSet(boolean value) {
    if (!value) {
      this.eventType = null;
    }
  }

  public TaskMessage getTaskMessage() {
    return this.taskMessage;
  }

  public TaskEvent setTaskMessage(TaskMessage taskMessage) {
    this.taskMessage = taskMessage;
    return this;
  }

  public void unsetTaskMessage() {
    this.taskMessage = null;
  }

  /** Returns true if field taskMessage is set (has been assigned a value) and false otherwise */
  public boolean isSetTaskMessage() {
    return this.taskMessage != null;
  }

  public void setTaskMessageIsSet(boolean value) {
    if (!value) {
      this.taskMessage = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case EVENT_TYPE:
      if (value == null) {
        unsetEventType();
      } else {
        setEventType((String)value);
      }
      break;

    case TASK_MESSAGE:
      if (value == null) {
        unsetTaskMessage();
      } else {
        setTaskMessage((TaskMessage)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case EVENT_TYPE:
      return getEventType();

    case TASK_MESSAGE:
      return getTaskMessage();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case EVENT_TYPE:
      return isSetEventType();
    case TASK_MESSAGE:
      return isSetTaskMessage();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TaskEvent)
      return this.equals((TaskEvent)that);
    return false;
  }

  public boolean equals(TaskEvent that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_eventType = true && this.isSetEventType();
    boolean that_present_eventType = true && that.isSetEventType();
    if (this_present_eventType || that_present_eventType) {
      if (!(this_present_eventType && that_present_eventType))
        return false;
      if (!this.eventType.equals(that.eventType))
        return false;
    }

    boolean this_present_taskMessage = true && this.isSetTaskMessage();
    boolean that_present_taskMessage = true && that.isSetTaskMessage();
    if (this_present_taskMessage || that_present_taskMessage) {
      if (!(this_present_taskMessage && that_present_taskMessage))
        return false;
      if (!this.taskMessage.equals(that.taskMessage))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetEventType()) ? 131071 : 524287);
    if (isSetEventType())
      hashCode = hashCode * 8191 + eventType.hashCode();

    hashCode = hashCode * 8191 + ((isSetTaskMessage()) ? 131071 : 524287);
    if (isSetTaskMessage())
      hashCode = hashCode * 8191 + taskMessage.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TaskEvent other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetEventType()).compareTo(other.isSetEventType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEventType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.eventType, other.eventType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTaskMessage()).compareTo(other.isSetTaskMessage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTaskMessage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.taskMessage, other.taskMessage);
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
    StringBuilder sb = new StringBuilder("TaskEvent(");
    boolean first = true;

    sb.append("eventType:");
    if (this.eventType == null) {
      sb.append("null");
    } else {
      sb.append(this.eventType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("taskMessage:");
    if (this.taskMessage == null) {
      sb.append("null");
    } else {
      sb.append(this.taskMessage);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (taskMessage != null) {
      taskMessage.validate();
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TaskEventStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TaskEventStandardScheme getScheme() {
      return new TaskEventStandardScheme();
    }
  }

  private static class TaskEventStandardScheme extends org.apache.thrift.scheme.StandardScheme<TaskEvent> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TaskEvent struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // EVENT_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.eventType = iprot.readString();
              struct.setEventTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TASK_MESSAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.taskMessage = new TaskMessage();
              struct.taskMessage.read(iprot);
              struct.setTaskMessageIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TaskEvent struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.eventType != null) {
        oprot.writeFieldBegin(EVENT_TYPE_FIELD_DESC);
        oprot.writeString(struct.eventType);
        oprot.writeFieldEnd();
      }
      if (struct.taskMessage != null) {
        oprot.writeFieldBegin(TASK_MESSAGE_FIELD_DESC);
        struct.taskMessage.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TaskEventTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TaskEventTupleScheme getScheme() {
      return new TaskEventTupleScheme();
    }
  }

  private static class TaskEventTupleScheme extends org.apache.thrift.scheme.TupleScheme<TaskEvent> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TaskEvent struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetEventType()) {
        optionals.set(0);
      }
      if (struct.isSetTaskMessage()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetEventType()) {
        oprot.writeString(struct.eventType);
      }
      if (struct.isSetTaskMessage()) {
        struct.taskMessage.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TaskEvent struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.eventType = iprot.readString();
        struct.setEventTypeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.taskMessage = new TaskMessage();
        struct.taskMessage.read(iprot);
        struct.setTaskMessageIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

