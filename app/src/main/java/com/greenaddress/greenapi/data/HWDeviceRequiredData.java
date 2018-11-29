package com.greenaddress.greenapi.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HWDeviceRequiredData extends JSONData {
    private String action;
    private HWDeviceDetailData device;
    private List<List<Integer>> paths;
    private List<Integer> path;
    private String message;
    private ObjectNode transaction;
    private List<String> signingAddressTypes;
    private List<InputOutputData> signingInputs;

    private List<InputOutputData> transactionOutputs;
    private Map<String, String> signingTransactions;

    HWDeviceRequiredData() { }

    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public HWDeviceDetailData getDevice() {
        return device;
    }

    public void setDevice(final HWDeviceDetailData device) {
        this.device = device;
    }

    public List<List<Integer>> getPaths() {
        return paths;
    }

    public void setPaths(final List<List<Integer>> paths) {
        this.paths = paths;
    }

    public List<Integer> getPath() {
        return path;
    }

    public void setPath(final List<Integer> path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public ObjectNode getTransaction() {
        return transaction;
    }

    public void setTransaction(final ObjectNode transaction) { this.transaction = transaction; }

    public List<InputOutputData> getTransactionOutputs() { return transactionOutputs; }

    public void setTransactionOutputs(final List<InputOutputData> transactionOutputs) {
        this.transactionOutputs = transactionOutputs;
    }

    public List<String> getSigningAddressTypes() {
        return signingAddressTypes;
    }

    public void setSigningAddressTypes(final List<String> signingAddressTypes) {
        this.signingAddressTypes = signingAddressTypes;
    }

    public List<InputOutputData> getSigningInputs() {
        return signingInputs;
    }

    public void setSigningInputs(final List<InputOutputData> signingInputs) {
        this.signingInputs = signingInputs;
    }

    public Map<String, String> getSigningTransactions() {
        return signingTransactions;
    }

    public void setSigningTransactions(final Map<String, String> signingTransactions) {
        this.signingTransactions = signingTransactions;
    }
}
