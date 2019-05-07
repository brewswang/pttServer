package com.dventus.ptt.dto;

import javax.annotation.CheckForNull;

public class CircuitBoardDto {

    @CheckForNull private String backCoverSN;
    private boolean assembleStatus;
    private boolean assembleFlag;
    @CheckForNull private TestBenchResultsDto testBenchResults;

    public CircuitBoardDto() {
    }

    @CheckForNull
    public String getBackCoverSN() {
        return backCoverSN;
    }

    public void setBackCoverSN(@CheckForNull String backCoverSN) {
        this.backCoverSN = backCoverSN;
    }

    public boolean isAssembleStatus() {
        return assembleStatus;
    }

    public void setAssembleStatus(boolean assembleStatus) {
        this.assembleStatus = assembleStatus;
    }

    public boolean isAssembleFlag() {
        return assembleFlag;
    }

    public void setAssembleFlag(boolean assembleFlag) {
        this.assembleFlag = assembleFlag;
    }

    @CheckForNull
    public TestBenchResultsDto getTestBenchResults() {
        return testBenchResults;
    }

    public void setTestBenchResults(@CheckForNull TestBenchResultsDto testBenchResults) {
        this.testBenchResults = testBenchResults;
    }
}
