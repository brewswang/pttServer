package com.dventus.ptt.dto;

import com.dventus.ptt.jaxb.messages.Remark;

import javax.annotation.CheckForNull;
import java.util.Set;

public class MeterDto {

    @CheckForNull private String frontCoverSN;
    @CheckForNull private String propertyNumber;
    @CheckForNull private String modelNumber;
    @CheckForNull private String macAddress;
    @CheckForNull private String boxNumber;
    private boolean packStatus;
    private boolean packFlag;
    @CheckForNull private CircuitBoardDto circuitBoard;
    @CheckForNull private Set<Remark> remark;

    public MeterDto() {
    }

    public boolean isPackStatus() {
        return packStatus;
    }

    public void setPackStatus(boolean packStatus) {
        this.packStatus = packStatus;
    }

    public boolean isPackFlag() {
        return packFlag;
    }

    public void setPackFlag(boolean packFlag) {
        this.packFlag = packFlag;
    }

    @CheckForNull
    public String getFrontCoverSN() {
        return frontCoverSN;
    }

    public void setFrontCoverSN(@CheckForNull String frontCoverSN) {
        this.frontCoverSN = frontCoverSN;
    }

    @CheckForNull
    public String getPropertyNumber() {
        return propertyNumber;
    }

    public void setPropertyNumber(@CheckForNull String propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    @CheckForNull
    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(@CheckForNull String modelNumber) {
        this.modelNumber = modelNumber;
    }

    @CheckForNull
    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(@CheckForNull String macAddress) {
        this.macAddress = macAddress;
    }

    @CheckForNull
    public String getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(@CheckForNull String boxNumber) {
        this.boxNumber = boxNumber;
    }

    @CheckForNull
    public CircuitBoardDto getCircuitBoard() {
        return circuitBoard;
    }

    public void setCircuitBoard(@CheckForNull CircuitBoardDto circuitBoard) {
        this.circuitBoard = circuitBoard;
    }

    @CheckForNull
    public Set<Remark> getRemark() {
        return remark;
    }

    public void setRemark(@CheckForNull Set<Remark> remark) {
        this.remark = remark;
    }
}
