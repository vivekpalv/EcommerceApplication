package com.ecom.amazon.DTO.Request;

public class AttributeFieldDTO {

    private String fieldKey;
    private String fieldValue;

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String toString() {
        return "AttributeFieldDTO{" +
                "fieldKey='" + fieldKey + '\'' +
                ", fieldValue='" + fieldValue + '\'' +
                '}';
    }
}
