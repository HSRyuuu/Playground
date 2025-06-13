package com.example.playground.stable.mock.test;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Data
public class MockTestDto {
    private int intField;
    private Integer integerField;
    private long longField;
    private Long longObjField;
    private boolean booleanField;
    private Boolean booleanObjField;
    private double doubleField;
    private Double doubleObjField;
    private float floatField;
    private Float floatObjField;

    private String stringField;
    private UUID uuidField;

    private SampleEnum enumField;

    private NestedDto nestedObject;
    private RecursiveDto recursiveObject;

    private List<String> listOfStrings;
    private List<NestedDto> listOfNested;
    private List<RecursiveDto> listOfRecursive;


    @Override
    public String toString() {
        return "MockTestDto {\n" +
                "  intField=" + intField + ",\n" +
                "  integerField=" + integerField + ",\n" +
                "  longField=" + longField + ",\n" +
                "  longObjField=" + longObjField + ",\n" +
                "  booleanField=" + booleanField + ",\n" +
                "  booleanObjField=" + booleanObjField + ",\n" +
                "  doubleField=" + doubleField + ",\n" +
                "  doubleObjField=" + doubleObjField + ",\n" +
                "  floatField=" + floatField + ",\n" +
                "  floatObjField=" + floatObjField + ",\n" +
                "  stringField='" + stringField + "',\n" +
                "  uuidField=" + uuidField + ",\n" +
                "  enumField=" + enumField + ",\n" +
                "  nestedObject=" + nestedObject + "\n" +
                "  recursiveObject=" + recursiveObject + "\n" +
                "  listOfStrings=" + listOfStrings + ",\n" +
                "  listOfNested=" + listOfNested + ",\n" +
                "  listOfRecursive=" + listOfRecursive + ",\n" +
                '}';
    }

}
