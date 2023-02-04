package com.example.basicApp.model.Response;

public class OperationStatusModel {
    private String operationName;
    private String operationResult;

    public OperationStatusModel() {
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }
}
