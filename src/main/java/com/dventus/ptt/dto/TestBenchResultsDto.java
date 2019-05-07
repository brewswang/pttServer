package com.dventus.ptt.dto;

import com.dventus.ptt.jaxb.messages.*;

import javax.annotation.CheckForNull;
import java.util.Set;

public class TestBenchResultsDto {

    @CheckForNull private Set<CalibrationPhaseTest> calibrationPhaseTest;
    @CheckForNull private Set<CalibrationNeutralTest> calibrationNeutralTest;
    @CheckForNull private Set<ShuntImpedanceTest> shuntImpedanceTest;
    @CheckForNull private Set<NeutralImpedanceTest> neutralImpedanceTest;
    @CheckForNull private Set<ActiveEnergyRegisterTest> activeEnergyRegisterTest;
    @CheckForNull private Set<ReactiveEnergyRegisterTest> reactiveEnergyRegisterTest;
    @CheckForNull private Set<OperatingCurrentTest> operatingCurrentTest;
    @CheckForNull private Set<StartingCurrentTest> startingCurrentTest;
    @CheckForNull private Set<NoLoadTest> noLoadTest;
    @CheckForNull private Set<OperatingCurrentTestReactive> operatingCurrentTestReactive;
    @CheckForNull private Set<StartingCurrentTestReactive> startingCurrentTestReactive;
    @CheckForNull private Set<NoLoadTestReactive> noLoadTestReactive;
    @CheckForNull private Set<AccuracyTestsNeutral> accuracyTestNeutral;
    @CheckForNull private Set<PowerLineCommunication> powerLineCommunication;
    @CheckForNull private Set<EnergyAndTamperClearTest> energyAndTamperClearTest;

    @CheckForNull private TestEvaluation calibrationPhaseEvaluation;
    @CheckForNull private TestEvaluation calibrationNeutralEvaluation;
    @CheckForNull private TestEvaluation shuntImpedanceEvaluation;
    @CheckForNull private TestEvaluation neutralImpedanceEvaluation;
    @CheckForNull private TestEvaluation activeEnergyRegisterEvaluation;
    @CheckForNull private TestEvaluation reactiveEnergyRegisterEvaluation;
    @CheckForNull private TestEvaluation operatingCurrentEvaluation;
    @CheckForNull private TestEvaluation startingCurrentEvaluation;
    @CheckForNull private TestEvaluation noLoadEvaluation;
    @CheckForNull private TestEvaluation operatingCurrentReactiveEvaluation;
    @CheckForNull private TestEvaluation startingCurrentReactiveEvaluation;
    @CheckForNull private TestEvaluation noLoadReactiveEvaluation;
    @CheckForNull private TestEvaluation accuracyNeutralEvaluation;
    @CheckForNull private TestEvaluation powerLineCommunicationEvaluation;
    @CheckForNull private TestEvaluation energyAndTamperClearEvaluation;
    @CheckForNull private String calibrationPhaseEndTime;
    @CheckForNull private String calibrationNeutralEndTime;
    @CheckForNull private String shuntImpedanceEndTime;
    @CheckForNull private String neutralImpedanceEndTime;
    @CheckForNull private String activeEnergyRegisterEndTime;
    @CheckForNull private String reactiveEnergyRegisterEndTime;
    @CheckForNull private String operatingCurrentEndTime;
    @CheckForNull private String startingCurrentEndTime;
    @CheckForNull private String noLoadEndTime;
    @CheckForNull private String operatingCurrentReactiveEndTime;
    @CheckForNull private String startingCurrentReactiveEndTime;
    @CheckForNull private String noLoadReactiveEndTime;
    @CheckForNull private String accuracyNeutralEndTime;
    @CheckForNull private String powerLineCommunicationEndTime;
    @CheckForNull private String energyAndTamperClearEndTime;

    public TestBenchResultsDto() {
    }

    @CheckForNull
    public Set<CalibrationPhaseTest> getCalibrationPhaseTest() {
        return calibrationPhaseTest;
    }

    public void setCalibrationPhaseTest(@CheckForNull Set<CalibrationPhaseTest> calibrationPhaseTest) {
        this.calibrationPhaseTest = calibrationPhaseTest;
    }

    @CheckForNull
    public Set<CalibrationNeutralTest> getCalibrationNeutralTest() {
        return calibrationNeutralTest;
    }

    public void setCalibrationNeutralTest(@CheckForNull Set<CalibrationNeutralTest> calibrationNeutralTest) {
        this.calibrationNeutralTest = calibrationNeutralTest;
    }

    @CheckForNull
    public Set<ShuntImpedanceTest> getShuntImpedanceTest() {
        return shuntImpedanceTest;
    }

    public void setShuntImpedanceTest(@CheckForNull Set<ShuntImpedanceTest> shuntImpedanceTest) {
        this.shuntImpedanceTest = shuntImpedanceTest;
    }

    @CheckForNull
    public Set<NeutralImpedanceTest> getNeutralImpedanceTest() {
        return neutralImpedanceTest;
    }

    public void setNeutralImpedanceTest(@CheckForNull Set<NeutralImpedanceTest> neutralImpedanceTest) {
        this.neutralImpedanceTest = neutralImpedanceTest;
    }

    @CheckForNull
    public Set<ActiveEnergyRegisterTest> getActiveEnergyRegisterTest() {
        return activeEnergyRegisterTest;
    }

    public void setActiveEnergyRegisterTest(@CheckForNull Set<ActiveEnergyRegisterTest> activeEnergyRegisterTest) {
        this.activeEnergyRegisterTest = activeEnergyRegisterTest;
    }

    @CheckForNull
    public Set<ReactiveEnergyRegisterTest> getReactiveEnergyRegisterTest() {
        return reactiveEnergyRegisterTest;
    }

    public void setReactiveEnergyRegisterTest(@CheckForNull Set<ReactiveEnergyRegisterTest> reactiveEnergyRegisterTest) {
        this.reactiveEnergyRegisterTest = reactiveEnergyRegisterTest;
    }

    @CheckForNull
    public Set<OperatingCurrentTest> getOperatingCurrentTest() {
        return operatingCurrentTest;
    }

    public void setOperatingCurrentTest(@CheckForNull Set<OperatingCurrentTest> operatingCurrentTest) {
        this.operatingCurrentTest = operatingCurrentTest;
    }

    @CheckForNull
    public Set<StartingCurrentTest> getStartingCurrentTest() {
        return startingCurrentTest;
    }

    public void setStartingCurrentTest(@CheckForNull Set<StartingCurrentTest> startingCurrentTest) {
        this.startingCurrentTest = startingCurrentTest;
    }

    @CheckForNull
    public Set<NoLoadTest> getNoLoadTest() {
        return noLoadTest;
    }

    public void setNoLoadTest(@CheckForNull Set<NoLoadTest> noLoadTest) {
        this.noLoadTest = noLoadTest;
    }

    @CheckForNull
    public Set<OperatingCurrentTestReactive> getOperatingCurrentTestReactive() {
        return operatingCurrentTestReactive;
    }

    public void setOperatingCurrentTestReactive(@CheckForNull Set<OperatingCurrentTestReactive> operatingCurrentTestReactive) {
        this.operatingCurrentTestReactive = operatingCurrentTestReactive;
    }

    @CheckForNull
    public Set<StartingCurrentTestReactive> getStartingCurrentTestReactive() {
        return startingCurrentTestReactive;
    }

    public void setStartingCurrentTestReactive(@CheckForNull Set<StartingCurrentTestReactive> startingCurrentTestReactive) {
        this.startingCurrentTestReactive = startingCurrentTestReactive;
    }

    @CheckForNull
    public Set<NoLoadTestReactive> getNoLoadTestReactive() {
        return noLoadTestReactive;
    }

    public void setNoLoadTestReactive(@CheckForNull Set<NoLoadTestReactive> noLoadTestReactive) {
        this.noLoadTestReactive = noLoadTestReactive;
    }

    @CheckForNull
    public Set<AccuracyTestsNeutral> getAccuracyTestNeutral() {
        return accuracyTestNeutral;
    }

    public void setAccuracyTestNeutral(@CheckForNull Set<AccuracyTestsNeutral> accuracyTestNeutral) {
        this.accuracyTestNeutral = accuracyTestNeutral;
    }

    @CheckForNull
    public Set<PowerLineCommunication> getPowerLineCommunication() {
        return powerLineCommunication;
    }

    public void setPowerLineCommunication(@CheckForNull Set<PowerLineCommunication> powerLineCommunication) {
        this.powerLineCommunication = powerLineCommunication;
    }

    @CheckForNull
    public Set<EnergyAndTamperClearTest> getEnergyAndTamperClearTest() {
        return energyAndTamperClearTest;
    }

    public void setEnergyAndTamperClearTest(@CheckForNull Set<EnergyAndTamperClearTest> energyAndTamperClearTest) {
        this.energyAndTamperClearTest = energyAndTamperClearTest;
    }

    @CheckForNull
    public TestEvaluation getCalibrationPhaseEvaluation() {
        return calibrationPhaseEvaluation;
    }

    public void setCalibrationPhaseEvaluation(@CheckForNull TestEvaluation calibrationPhaseEvaluation) {
        this.calibrationPhaseEvaluation = calibrationPhaseEvaluation;
    }

    @CheckForNull
    public TestEvaluation getCalibrationNeutralEvaluation() {
        return calibrationNeutralEvaluation;
    }

    public void setCalibrationNeutralEvaluation(@CheckForNull TestEvaluation calibrationNeutralEvaluation) {
        this.calibrationNeutralEvaluation = calibrationNeutralEvaluation;
    }

    @CheckForNull
    public TestEvaluation getShuntImpedanceEvaluation() {
        return shuntImpedanceEvaluation;
    }

    public void setShuntImpedanceEvaluation(@CheckForNull TestEvaluation shuntImpedanceEvaluation) {
        this.shuntImpedanceEvaluation = shuntImpedanceEvaluation;
    }

    @CheckForNull
    public TestEvaluation getNeutralImpedanceEvaluation() {
        return neutralImpedanceEvaluation;
    }

    public void setNeutralImpedanceEvaluation(@CheckForNull TestEvaluation neutralImpedanceEvaluation) {
        this.neutralImpedanceEvaluation = neutralImpedanceEvaluation;
    }

    @CheckForNull
    public TestEvaluation getActiveEnergyRegisterEvaluation() {
        return activeEnergyRegisterEvaluation;
    }

    public void setActiveEnergyRegisterEvaluation(@CheckForNull TestEvaluation activeEnergyRegisterEvaluation) {
        this.activeEnergyRegisterEvaluation = activeEnergyRegisterEvaluation;
    }

    @CheckForNull
    public TestEvaluation getReactiveEnergyRegisterEvaluation() {
        return reactiveEnergyRegisterEvaluation;
    }

    public void setReactiveEnergyRegisterEvaluation(@CheckForNull TestEvaluation reactiveEnergyRegisterEvaluation) {
        this.reactiveEnergyRegisterEvaluation = reactiveEnergyRegisterEvaluation;
    }

    @CheckForNull
    public TestEvaluation getOperatingCurrentEvaluation() {
        return operatingCurrentEvaluation;
    }

    public void setOperatingCurrentEvaluation(@CheckForNull TestEvaluation operatingCurrentEvaluation) {
        this.operatingCurrentEvaluation = operatingCurrentEvaluation;
    }

    @CheckForNull
    public TestEvaluation getStartingCurrentEvaluation() {
        return startingCurrentEvaluation;
    }

    public void setStartingCurrentEvaluation(@CheckForNull TestEvaluation startingCurrentEvaluation) {
        this.startingCurrentEvaluation = startingCurrentEvaluation;
    }

    @CheckForNull
    public TestEvaluation getNoLoadEvaluation() {
        return noLoadEvaluation;
    }

    public void setNoLoadEvaluation(@CheckForNull TestEvaluation noLoadEvaluation) {
        this.noLoadEvaluation = noLoadEvaluation;
    }

    @CheckForNull
    public TestEvaluation getOperatingCurrentReactiveEvaluation() {
        return operatingCurrentReactiveEvaluation;
    }

    public void setOperatingCurrentReactiveEvaluation(@CheckForNull TestEvaluation operatingCurrentReactiveEvaluation) {
        this.operatingCurrentReactiveEvaluation = operatingCurrentReactiveEvaluation;
    }

    @CheckForNull
    public TestEvaluation getStartingCurrentReactiveEvaluation() {
        return startingCurrentReactiveEvaluation;
    }

    public void setStartingCurrentReactiveEvaluation(@CheckForNull TestEvaluation startingCurrentReactiveEvaluation) {
        this.startingCurrentReactiveEvaluation = startingCurrentReactiveEvaluation;
    }

    @CheckForNull
    public TestEvaluation getNoLoadReactiveEvaluation() {
        return noLoadReactiveEvaluation;
    }

    public void setNoLoadReactiveEvaluation(@CheckForNull TestEvaluation noLoadReactiveEvaluation) {
        this.noLoadReactiveEvaluation = noLoadReactiveEvaluation;
    }

    @CheckForNull
    public TestEvaluation getAccuracyNeutralEvaluation() {
        return accuracyNeutralEvaluation;
    }

    public void setAccuracyNeutralEvaluation(@CheckForNull TestEvaluation accuracyNeutralEvaluation) {
        this.accuracyNeutralEvaluation = accuracyNeutralEvaluation;
    }

    @CheckForNull
    public TestEvaluation getPowerLineCommunicationEvaluation() {
        return powerLineCommunicationEvaluation;
    }

    public void setPowerLineCommunicationEvaluation(@CheckForNull TestEvaluation powerLineCommunicationEvaluation) {
        this.powerLineCommunicationEvaluation = powerLineCommunicationEvaluation;
    }

    @CheckForNull
    public TestEvaluation getEnergyAndTamperClearEvaluation() {
        return energyAndTamperClearEvaluation;
    }

    public void setEnergyAndTamperClearEvaluation(@CheckForNull TestEvaluation energyAndTamperClearEvaluation) {
        this.energyAndTamperClearEvaluation = energyAndTamperClearEvaluation;
    }

    @CheckForNull
    public String getCalibrationPhaseEndTime() {
        return calibrationPhaseEndTime;
    }

    public void setCalibrationPhaseEndTime(@CheckForNull String calibrationPhaseEndTime) {
        this.calibrationPhaseEndTime = calibrationPhaseEndTime;
    }

    @CheckForNull
    public String getCalibrationNeutralEndTime() {
        return calibrationNeutralEndTime;
    }

    public void setCalibrationNeutralEndTime(@CheckForNull String calibrationNeutralEndTime) {
        this.calibrationNeutralEndTime = calibrationNeutralEndTime;
    }

    @CheckForNull
    public String getShuntImpedanceEndTime() {
        return shuntImpedanceEndTime;
    }

    public void setShuntImpedanceEndTime(@CheckForNull String shuntImpedanceEndTime) {
        this.shuntImpedanceEndTime = shuntImpedanceEndTime;
    }

    @CheckForNull
    public String getNeutralImpedanceEndTime() {
        return neutralImpedanceEndTime;
    }

    public void setNeutralImpedanceEndTime(@CheckForNull String neutralImpedanceEndTime) {
        this.neutralImpedanceEndTime = neutralImpedanceEndTime;
    }

    @CheckForNull
    public String getActiveEnergyRegisterEndTime() {
        return activeEnergyRegisterEndTime;
    }

    public void setActiveEnergyRegisterEndTime(@CheckForNull String activeEnergyRegisterEndTime) {
        this.activeEnergyRegisterEndTime = activeEnergyRegisterEndTime;
    }

    @CheckForNull
    public String getReactiveEnergyRegisterEndTime() {
        return reactiveEnergyRegisterEndTime;
    }

    public void setReactiveEnergyRegisterEndTime(@CheckForNull String reactiveEnergyRegisterEndTime) {
        this.reactiveEnergyRegisterEndTime = reactiveEnergyRegisterEndTime;
    }

    @CheckForNull
    public String getOperatingCurrentEndTime() {
        return operatingCurrentEndTime;
    }

    public void setOperatingCurrentEndTime(@CheckForNull String operatingCurrentEndTime) {
        this.operatingCurrentEndTime = operatingCurrentEndTime;
    }

    @CheckForNull
    public String getStartingCurrentEndTime() {
        return startingCurrentEndTime;
    }

    public void setStartingCurrentEndTime(@CheckForNull String startingCurrentEndTime) {
        this.startingCurrentEndTime = startingCurrentEndTime;
    }

    @CheckForNull
    public String getNoLoadEndTime() {
        return noLoadEndTime;
    }

    public void setNoLoadEndTime(@CheckForNull String noLoadEndTime) {
        this.noLoadEndTime = noLoadEndTime;
    }

    @CheckForNull
    public String getOperatingCurrentReactiveEndTime() {
        return operatingCurrentReactiveEndTime;
    }

    public void setOperatingCurrentReactiveEndTime(@CheckForNull String operatingCurrentReactiveEndTime) {
        this.operatingCurrentReactiveEndTime = operatingCurrentReactiveEndTime;
    }

    @CheckForNull
    public String getStartingCurrentReactiveEndTime() {
        return startingCurrentReactiveEndTime;
    }

    public void setStartingCurrentReactiveEndTime(@CheckForNull String startingCurrentReactiveEndTime) {
        this.startingCurrentReactiveEndTime = startingCurrentReactiveEndTime;
    }

    @CheckForNull
    public String getNoLoadReactiveEndTime() {
        return noLoadReactiveEndTime;
    }

    public void setNoLoadReactiveEndTime(@CheckForNull String noLoadReactiveEndTime) {
        this.noLoadReactiveEndTime = noLoadReactiveEndTime;
    }

    @CheckForNull
    public String getAccuracyNeutralEndTime() {
        return accuracyNeutralEndTime;
    }

    public void setAccuracyNeutralEndTime(@CheckForNull String accuracyNeutralEndTime) {
        this.accuracyNeutralEndTime = accuracyNeutralEndTime;
    }

    @CheckForNull
    public String getPowerLineCommunicationEndTime() {
        return powerLineCommunicationEndTime;
    }

    public void setPowerLineCommunicationEndTime(@CheckForNull String powerLineCommunicationEndTime) {
        this.powerLineCommunicationEndTime = powerLineCommunicationEndTime;
    }

    @CheckForNull
    public String getEnergyAndTamperClearEndTime() {
        return energyAndTamperClearEndTime;
    }

    public void setEnergyAndTamperClearEndTime(@CheckForNull String energyAndTamperClearEndTime) {
        this.energyAndTamperClearEndTime = energyAndTamperClearEndTime;
    }
}
