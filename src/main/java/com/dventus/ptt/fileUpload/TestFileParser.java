package com.dventus.ptt.fileUpload;

import com.dventus.ptt.jaxb.messages.*;
import com.dventus.ptt.servlet.Context;
import com.dventus.ptt.servlet.QueryParameter;
import com.opencsv.CSVReader;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class TestFileParser {

    private static Logger logger = Logger.getLogger(TestFileParser.class);

    private int HEADER_ROW_VALUE = 1; // header row value is data about test session

    // header value constants
    private int TEST_BENCH_NAME = 0;
    private int SESSION_ID = 1;
    private int TEST_START = 2;
    private int TEST_END = 3;
    private int TEST_PERSONNEL = 4;
    private int TEST_PLACE = 5;
    private int SAMPLE_BASE = 6;
    private int COMMENT = 7;
    private int METER_TYPE = 8;


    // column value constants
    private int METER_POS = 0;
    private int METER_SERIAL = 1;
    private int TOTAL_EVAL = 2;
    private int CAL_PHASE = 3;
    private int CAL_NEUTRAL = 4;
    private int SHUNT_IMP = 5;
    private int NEUTRAL_IMP = 6;
    private int ACTIVE_ENERGY_REG = 7;
    private int REACTIVE_ENERGY_REG = 8;
    private int OPERATING_CURRENT = 9;
    private int STARTING_CURRENT = 10;
    private int NO_LOAD = 11;
    private int OPERATING_CURRENT_RE = 12;
    private int START_CURRENT_RE = 13;
    private int NO_LOAD_RE = 14;
    private int ACCURACY = 15;
    private int PLC = 16; // power line communication
    private int ENG_AND_TAMP_CL = 17; // energy and tamper clear test

    // calibration phase
    private int PH_CAL_RES = 18; // Phase - Calibration Result
    private int PH_E_Lo = 19; // Phase - E_Lo
    private int PH_E_Hi = 20; // Phase - E_Hi
    private int PH_G_Co = 21; // Phase - G_Co
    private int PH_P_Off = 22; // Phase - P_Off
    private int PH_Ph_Co = 23; // Phase - Ph_Co
    private int PH_ST_C_Int = 24; // Phase - St_C_Int
    private int PH_St_C_Fra = 25; // Phase - St_C_Fra
    private int PH_V_R = 26; // Phase - V_R
    private int PH_C_R = 27; // Phase - C_R
    private int PH_Ph_Co0 = 28; // Phase - Ph_Co0
    private int PH_Ph_Co1 = 29; // Phase - Ph_Co1
    private int PH_Ph_Co2 = 30; // Phase - Ph_Co2
    private int PH_Ph_Co3 = 31; // Phase - Ph_Co3
    private int PH_meterIDNum = 32; // Phase - meterIDNumeric
    private int PH_Errors = 33; // Phase - Errors
    private int PH_ID_Pwd_st = 34; // Phase - ID_Password_Status

    // Calibration - Neutral
    private int N_CAL_RES = 35; // Neutral - Calibration Result
    private int N_E_Lo = 36; // Neutral - E_Lo
    private int N_E_Hi = 37; // Neutral - E_Hi
    private int N_G_Co = 38; // Neutral - G_Co
    private int N_P_Off = 39; // Neutral - P_Off
    private int N_Ph_Co = 40; // Neutral - Ph_Co
    private int N_St_C_Int = 41; // Neutral - St_C_Int
    private int N_St_C_Fra = 42; // Neutral - St_C_Fra
    private int N_V_R = 43; // Neutral - V_R
    private int N_C_R = 44; // Neutral - C_R
    private int N_Ph_Co0 = 45; // Neutral - Ph_Co0
    private int N_Ph_Co1 = 46; // Neutral - Ph_Co1
    private int N_Ph_Co2 = 47; // Neutral - Ph_Co2
    private int N_Ph_Co3 = 48; // Neutral - Ph_Co3
    private int N_meterIDNum = 49; // Neutral - meterIDNumeric
    private int N_Errors = 50; // Neutral - Errors
    private int N_ID_Pwd_st = 51; // Neutral - ID_Password_Status

    // Shunt Impedance Test
    private int SI_Resistance1 = 52; // Resistance1
    private int SI_Resistance2 = 53; // Resistance2
    private int SI_Resistance3 = 54; // Resistance3
    private int SI_Temperature = 55; // Temperature

    // Neutral Impedance Test
    private int NI_ResistanceN = 56; // ResistanceN
    private int NI_Temperature = 57; // Temperature

    // Active Energy Register Te        st
    private int DOS_ENG_ACT = 58; // Dosage Energy
    private int STA_ENG_ACT = 59; // Start Energy
    private int END_ENG_ACT = 60; // End Energy
    private int DOS_PERC_ACT = 61; // Dosage Percent Error

    // Reactive Energy Register         Test
    private int DOS_ENG_RE = 62; // Dosage Reactive Energy
    private int STA_ENG_RE = 63; // Start Reactive Energy
    private int END_ENG_RE = 64; // End Reactive Energy
    private int DOS_PERC_RE = 65; // Dosage Reactive Percent Error

    // Operating Current Test
    private int ERR_PF_1_100 = 66; // % Error PF=1 100A 220V 50Hz
    private int ERR_PF_1_80 = 67; // % Error PF=1 80A 220V 50Hz
    private int ERR_PF_1_60 = 68; // % Error PF=1 60A 220V 50Hz
    private int ERR_PF_1_30 = 69; // % Error PF=1 30A 220V 50Hz
    private int ERR_PF_1_20 = 70; // % Error PF=1 20A 220V 50Hz
    private int ERR_PF_1_10 = 71; // % Error PF=1 10A 220V 50Hz
    private int ERR_PF_1_5 = 72; // % Error PF=1 5A 220V 50Hz
    private int ERR_PF_1_1 = 73; // % Error PF=1 1A 220V 50Hz
    private int ERR_PF_1_0_5 = 74; // % Error PF=1 0.5A 220V 50Hz
    private int ERR_PF_1_0_25 = 75; // % Error PF=1 0.25A 220V 50Hz
    private int ERR_PF_0_5_lag100 = 76; // % Error PF=0.5 lag 100A 220V 50Hz
    private int ERR_PF_0_5_lag80 = 77; // % Error PF=0.5 lag 80A 220V 50Hz
    private int ERR_PF_0_5_lag60 = 78; // % Error PF=0.5 lag 60A 220V 50Hz
    private int ERR_PF_0_5_lag30 = 79; // % Error PF=0.5 lag 30A 220V 50Hz
    private int ERR_PF_0_5_lag20 = 80; // % Error PF=0.5 lag 20A 220V 50Hz
    private int ERR_PF_0_5_lag10 = 81; // % Error PF=0.5 lag 10A 220V 50Hz
    private int ERR_PF_0_5_lag5 = 82; // % Error PF=0.5 lag 5A 220V 50Hz
    private int ERR_PF_0_5_lag1 = 83; // % Error PF=0.5 lag 1A 220V 50Hz
    private int ERR_PF_0_5_lag0_5 = 84; // % Error PF=0.5 lag 0.5A 220V 50Hz
    private int ERR_PF_0_8_lead100 = 85; // % Error PF=0.8 lead 100A 220V 50Hz
    private int ERR_PF_0_8_lead80 = 86; // % Error PF=0.8 lead 80A 220V 50Hz
    private int ERR_PF_0_8_lead60 = 87; // % Error PF=0.8 lead 60A 220V 50Hz
    private int ERR_PF_0_8_lead30 = 88; // % Error PF=0.8 lead 30A 220V 50Hz
    private int ERR_PF_0_8_lead20 = 89; // % Error PF=0.8 lead 20A 220V 50Hz
    private int ERR_PF_0_8_lead10 = 90; // % Error PF=0.8 lead 10A 220V 50Hz
    private int ERR_PF_0_8_lead5 = 91; // % Error PF=0.8 lead 5A 220V 50Hz
    private int ERR_PF_0_8_lead1 = 92; // % Error PF=0.8 lead 1A 220V 50Hz
    private int ERR_PF_0_8_lead0_5 = 93; // % Error PF=0.8 lead 0.5A 220V 50Hz
    private int MAX_ERR_0_1_PF_1 = 94; // Max-Error, 0.1Ib to Imax, PF=1
    private int MAX_ERR_0_05_PF_1 = 95; // Max-Error, 0.05Ib to 0.1Ib, PF=1
    private int MAX_ERR_0_2_PF_0_5 = 96; // Max-Error, 0.2Ib to Imax, PF=0.5 Ind
    private int MAX_ERR_0_1_PF_0_5 = 97; // Max-Error, 0.1Ib to 0.2Ib, PF=0.5 Ind
    private int MAX_ERR_0_2_PF_0_8 = 98; // Max-Error, 0.2Ib to Imax, PF=0.8 Cap
    private int MAX_ERR_0_1_PF_0_8 = 99; // Max-Error, 0.1Ib to 0.2Ib, PF=0.8 Cap

    // Starting Current Test
    private int STA_EDGES = 100; // Starting - Edges

    // No Load Test
    private int CRE_EDGES = 101; // Creeoing - Edges

    // Operating Current Test - Reactive
    private int ERR_SIN_1_100 = 102; // % Error sinα=1 100A 230V 50Hz
    private int ERR_SIN_1_70 = 103; // % Error sinα=1 70A 230V 50Hz
    private int ERR_SIN_1_40 = 104; // % Error sinα=1 40A 230V 50Hz
    private int ERR_SIN_1_20 = 105; // % Error sinα=1 20A 230V 50Hz
    private int ERR_SIN_1_10 = 106; // % Error sinα=1 10A 230V 50Hz
    private int ERR_SIN_1_2 = 107; // % Error sinα=1 2A 230V 50Hz
    private int ERR_SIN_1_1 = 108; // % Error sinα=1 1A 230V 50Hz
    private int ERR_SIN_1_0_5 = 109; // % Error sinα=1 0.5A 230V 50Hz
    private int ERR_SIN_0_25_100 = 110; // % Error sinα=0.25 100A 230V 50Hz
    private int ERR_SIN_0_25_70 = 111; // % Error sinα=0.25 70A 230V 50Hz
    private int ERR_SIN_0_25_40 = 112; // % Error sinα=0.25 40A 230V 50Hz
    private int ERR_SIN_0_25_20 = 113; // % Error sinα=0.25 20A 230V 50Hz
    private int ERR_SIN_0_25_10 = 114; // % Error sinα=0.25 10A 230V 50Hz
    private int ERR_SIN_0_25_2 = 115; // % Error sinα=0.25 2A 230V 50Hz
    private int ERR_SIN_0_25_1 = 116; // % Error sinα=0.25 1A 230V 50Hz
    private int ERR_SIN_N_0_25_100 = 117; // % Error sinα=-0.25 100A 230V 50Hz
    private int ERR_SIN_N_0_25_70 = 118; // % Error sinα=-0.25 70A 230V 50Hz
    private int ERR_SIN_N_0_25_40 = 119; // % Error sinα=-0.25 40A 230V 50Hz
    private int ERR_SIN_N_0_25_20 = 120; // % Error sinα=-0.25 20A 230V 50Hz
    private int ERR_SIN_N_0_25_10 = 121; // % Error sinα=-0.25 10A 230V 50Hz
    private int ERR_SIN_N_0_25_2 = 122; // % Error sinα=-0.25 2A 230V 50Hz
    private int ERR_SIN_N_0_25_1 = 123; // % Error sinα=-0.25 1A 230V 50Hz
    private int MAX_ERR_0_1_SIN_1 = 124; // Max-Error, 0.1Ib to Imax, sinα=1
    private int MAX_ERR_0_05_SIN_1 = 125; // Max-Error, 0.05Ib to 0.1Ib, sinα=1
    private int MAX_ERR_0_2_SIN_0_25 = 126; // Max-Error, 0.2Ib to Imax, sinα=0.25
    private int MAX_ERR_0_1_SIN_0_25 = 127; // Max-Error, 0.1Ib to 0.2Ib, sinα=0.25
    private int MAX_ERR_0_2_SIN_N_0_25 = 128; // Max-Error, 0.2Ib to Imax, sinα=-0.25
    private int MAX_ERR_0_1_SIN_N_0_25 = 129; // Max-Error, 0.1Ib to 0.2Ib, sinα=-0.25

    // Starting Current Test - Reactive
    private int STA_EDGES_RE = 130; // Starting Reactive Edges

    // No Load Test - Reactive
    private int CRE_EDGES_RE = 131; // Creeping Reactive Edges

    // Accuracy Tests - Neutral
    private int N_PERC_ERR = 132; // Neutral Percent Error

    // Power Line Communication
    private int PLC_TEST = 133; // Power Line Communication

    // Energy and Tamper Clear Test
    private int CLR_MTR_ST = 134; // Clear - Meter State
    private int SV = 135; // SV
    private int CLR_MTR_ID_PWD = 136; // Clear - Meter_ID_Password_Status_Text
    private int RES_LO = 137; // Error at resistive load
    private int IND_LO = 138; // Error at inductive load
    private int CAP_LO = 139; // Error at capacitive load


    private Response response;

    public TestFileParser() {
        this.response = new Response();
    }

    public Response parseTestBenchCSV(InputStream inputStream, Context context) throws IOException {

        EntityManager entityManager = context.getEntityManager();

        entityManager.getTransaction().begin();

        // read the csv file
        try (final CSVReader reader = new CSVReader(
                new InputStreamReader(inputStream, Charset.defaultCharset()), ',', '"', 0)) {

            final List<String[]> allRows = reader.readAll();

            for (int i = 4; i < allRows.size(); i++) {

                final String[] headerRow = allRows.get(HEADER_ROW_VALUE);

                final String[] meterTestData = allRows.get(i);

                String meterId = meterTestData[METER_SERIAL];

                // read the meter from database

                final String sqlString = (String) context.getQueries().get("METER");

                Query query = entityManager.createQuery(sqlString, Meter.class);

                query.setParameter(QueryParameter.id.name(), meterId);

                Meter meter = (Meter) query.getSingleResult();

                if (meter == null) {

                    // do not parse test for unrelated meter
                    response.setNotFound(meterId);

                    continue;

                }

                // check if the meter is unrelated from circuit board
                if (meter.getCircuitBoard() == null) {

                    response.setUnrelated(meterId);

                    continue;

                }

                // check if the meter has active remark and skip parsing
                if (meter.isActiveRemark()) {

                    response.setActiveRemark(meterId);

                    continue;

                }

                // test parsing parameters
                TestBenchResults previousTests = meter.getCircuitBoard().getTestBenchResults();
                if (previousTests == null) {
                    previousTests = new TestBenchResults();
                }
                String currentStartTime = headerRow[TEST_START];
                String currentEndTime = headerRow[TEST_END];

                // parse each test and add to the meter
                this.parse_T_1_CalibrationPhase(headerRow, meterTestData, meter, previousTests, currentStartTime, currentEndTime);
                this.parse_T_2_CalibrationNeutral(headerRow, meterTestData, meter, previousTests, currentStartTime, currentEndTime);
                this.parse_T_3_ShuntImpedanceTest(headerRow, meterTestData, meter, previousTests, currentStartTime, currentEndTime);
                this.parse_T_4_NeutralImpedanceTest(headerRow, meterTestData, meter, previousTests, currentStartTime, currentEndTime);
                this.parse_T_5_ActiveEnergyRegisterTest(headerRow, meterTestData, meter, previousTests, currentStartTime, currentEndTime);
                this.parse_T_6_ReactiveEnergyRegisterTest(headerRow, meterTestData, meter, previousTests, currentStartTime, currentEndTime);
                this.parse_T_7_OperatingCurrentTest(headerRow, meterTestData, meter, previousTests, currentStartTime, currentEndTime);
                this.parse_T_8_StartingCurrentTest(headerRow, meterTestData, meter, previousTests, currentStartTime, currentEndTime);
                this.parse_T_9_NoLoadTest(headerRow, meterTestData, meter, previousTests, currentStartTime, currentEndTime);
                this.parse_T_10_OperatingCurrentReactiveTest(headerRow, meterTestData, meter, previousTests, currentStartTime, currentEndTime);
                this.parse_T_11_StartingCurrentReactiveTest(headerRow, meterTestData, meter, previousTests, currentStartTime, currentEndTime);
                this.parse_T_12_NoLoadReactiveTest(headerRow, meterTestData, meter, previousTests, currentStartTime, currentEndTime);
                this.parse_T_13_AccuracyTest(headerRow, meterTestData, meter, previousTests, currentStartTime, currentEndTime, entityManager);
                this.parse_T_14_PLCTest(headerRow, meterTestData, meter, previousTests, currentStartTime, currentEndTime);
                this.parse_T_15_EnergyAndTamperingClearTest(headerRow, meterTestData, meter, previousTests, currentStartTime, currentEndTime);


                // add meterId to successful list after parsing all test
                response.setSuccessful(meterId);


                if(meterTestData[TOTAL_EVAL].equals(TestEvaluation.PASS.value())){
                    // validate all tests to affect meter pack flag
                    // two step validation of tests
                    // step 1: check all tests have passed for the meter type
                    // step 2: validate test order
                    if (isTestPass(previousTests, meter.getMeterType())) {

                        meter.setPackFlag(true);

                    } else {

                        meter.setPackFlag(false);

                    }
                }

                // Update meter data in database
                entityManager.merge(meter);

                entityManager.getTransaction().commit();

            }

        }
        catch (ArrayIndexOutOfBoundsException ex) {

            TestFileParser.logger.error(ex);

        }
        finally {

            if (entityManager.getTransaction().isActive()) {

                entityManager.getTransaction().rollback();

            }


            if (entityManager.isOpen()) {

                entityManager.close();

            }

        }

        return response;

    }

    private boolean isTestPass(TestBenchResults tests, String meterType) {
        TestEvaluation T_1_CalibrationPhase = tests.getCalibrationPhaseEvaluation();
        TestEvaluation T_2_CalibrationNeutral = tests.getCalibrationNeutralEvaluation();
        TestEvaluation T_3_ShuntImp = tests.getShuntImpedanceEvaluation();
        TestEvaluation T_4_NeutralImp = tests.getNeutralImpedanceEvaluation();
        TestEvaluation T_5_ActiveEng = tests.getActiveEnergyRegisterEvaluation();
        TestEvaluation T_6_ReactiveEng = tests.getReactiveEnergyRegisterEvaluation();
        TestEvaluation T_7_OperCur = tests.getOperatingCurrentEvaluation();
        TestEvaluation T_8_StartCur = tests.getStartingCurrentEvaluation();
        TestEvaluation T_9_NoLoad = tests.getNoLoadEvaluation();
        TestEvaluation T_10_OperCurRe = tests.getOperatingCurrentReactiveEvaluation();
        TestEvaluation T_11_StartCurRe = tests.getStartingCurrentReactiveEvaluation();
        TestEvaluation T_12_NoLoadRe = tests.getNoLoadReactiveEvaluation();
        TestEvaluation T_13_Accuracy = tests.getAccuracyNeutralEvaluation();
        TestEvaluation T_14_PLC_Eval = tests.getPowerLineCommunicationEvaluation();
        TestEvaluation T_15_Eng_Tamper = tests.getEnergyAndTamperClearEvaluation();

        // timestam of tests
        String T_1_time = tests.getCalibrationPhaseEndTime();
        String T_2_time = tests.getCalibrationNeutralEndTime();
        String T_3_time = tests.getShuntImpedanceEndTime();
        String T_4_time = tests.getNeutralImpedanceEndTime();
        String T_5_time = tests.getActiveEnergyRegisterEndTime();
        String T_6_time = tests.getReactiveEnergyRegisterEndTime();
        String T_7_time = tests.getOperatingCurrentEndTime();
        String T_8_time = tests.getStartingCurrentEndTime();
        String T_9_time = tests.getNoLoadEndTime();
        String T_10_time = tests.getOperatingCurrentReactiveEndTime();
        String T_11_time = tests.getStartingCurrentReactiveEndTime();
        String T_12_time = tests.getNoLoadReactiveEndTime();
        String T_13_time = tests.getAccuracyNeutralEndTime();
        String T_14_time = tests.getPowerLineCommunicationEndTime();
        String T_15_time = tests.getEnergyAndTamperClearEndTime();

        // test list for meter type
        List<TestEvaluation> DM801;
        List<TestEvaluation> SM501;
        List<TestEvaluation> all;

        DM801 = Arrays.asList(T_1_CalibrationPhase, T_2_CalibrationNeutral, T_3_ShuntImp, T_4_NeutralImp,
                T_5_ActiveEng, T_7_OperCur, T_8_StartCur, T_9_NoLoad,
                T_13_Accuracy, T_15_Eng_Tamper); //

        SM501 = Arrays.asList(T_1_CalibrationPhase, T_2_CalibrationNeutral, T_3_ShuntImp, T_4_NeutralImp,
                T_5_ActiveEng, T_7_OperCur, T_8_StartCur, T_9_NoLoad,
                T_13_Accuracy, T_14_PLC_Eval, T_15_Eng_Tamper);

        all = Arrays.asList(T_1_CalibrationPhase, T_2_CalibrationNeutral, T_3_ShuntImp, T_4_NeutralImp,
                T_5_ActiveEng, T_6_ReactiveEng, T_7_OperCur, T_8_StartCur, T_9_NoLoad, T_10_OperCurRe,
                T_11_StartCurRe, T_12_NoLoadRe, T_13_Accuracy, T_14_PLC_Eval, T_15_Eng_Tamper);


        // rules for checking order
        // 1. T_15_Eng_Tamper should be the last
        // 2. T_1_CalibrationPhase before T_5 to T_15
        // 3. T_2_CalibrationNeutral before T_13_Accuracy

        List<String> DM801_endTime = Arrays.asList(T_1_time, T_2_time, T_3_time, T_4_time, T_5_time,
                T_7_time, T_8_time, T_9_time, T_13_time, T_15_time);

        List<String> SM501_endTime = Arrays.asList(T_1_time, T_2_time, T_3_time, T_4_time, T_5_time,
                T_7_time, T_8_time, T_9_time, T_13_time, T_14_time, T_15_time);

        List<String> all_endTime = Arrays.asList(T_1_time, T_2_time, T_3_time, T_4_time, T_5_time,
                T_6_time, T_7_time, T_8_time, T_9_time, T_10_time, T_11_time, T_12_time, T_13_time,
                T_14_time, T_15_time);


        if (meterType.equalsIgnoreCase("DM801")) {
            for (TestEvaluation result : DM801) {
                if (result == TestEvaluation.FAILED || result == TestEvaluation.NOT_TESTED) {
                    return false;
                }
            }

            // check for order
            //rule 1
            if (!validateOrder(DM801_endTime.subList(0, 8), DM801_endTime.get(9))) {
                return false;
            }
            // rule 2
            if (!validateOrder(DM801_endTime.get(0), DM801_endTime.subList(1, 9))) {
                return false;
            }
        } else if (meterType.equalsIgnoreCase("DM801")) {
            for (TestEvaluation result : SM501) {
                if (result == TestEvaluation.FAILED || result == TestEvaluation.NOT_TESTED) {
                    return false;
                }
            }

            //check for order
            //rule 1
            if (!validateOrder(SM501_endTime.subList(0, 9), DM801_endTime.get(10))) {
                return false;
            }
            // rule 2
            if (!validateOrder(SM501_endTime.get(0), SM501_endTime.subList(1, 10))) {
                return false;
            }
        } else {
            for (TestEvaluation result : all) {
                if (result == TestEvaluation.FAILED || result == TestEvaluation.NOT_TESTED) {
                    return false;
                }
            }
            //check for order
            //rule 1
            if (!validateOrder(all_endTime.subList(0, 13), DM801_endTime.get(14))) {
                return false;
            }
            // rule 2
            if (!validateOrder(all_endTime.get(0), all_endTime.subList(1, 14))) {
                return false;
            }

        }

        //rule 3 apply for all kinds of meters
        if (!validateOrder(T_2_time, T_13_time)) {
            return false;
        }

        return false;
    }

    private boolean validateOrder(String beforeTestTime, List<String> afterTestTimes) {

        for (String after : afterTestTimes) {
            if (compareTestTime(beforeTestTime, after)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean validateOrder(List<String> beforeTestTimes, String afterTestTime) {

        for (String before : beforeTestTimes) {
            if (!validateOrder(before, afterTestTime)) {
                return false;
            }
        }
        return true;
    }

    private boolean validateOrder(String beforeTestTime, String afterTestTime) {
        if (compareTestTime(beforeTestTime, afterTestTime)) {
            return true;
        } else {
            return false;
        }
    }

    private String parseString(String input) {
        if (input.replaceAll("[^\\d.-]", "").isEmpty()) {
            return "0";
        }
        return input.replaceAll("[^\\d.-]", "");
    }

    private void setTestMetaData(String[] headerRow, TestBase test, TestEvaluation evaluation, String pos, Meter meter) {
        test.setTestBenchName(headerRow[TEST_BENCH_NAME]);
        test.setSessionID(headerRow[SESSION_ID]);
        test.setTestStartTime(headerRow[TEST_START]);
        test.setTestEndTime(headerRow[TEST_END]);
        test.setTestPersonnel(headerRow[TEST_PERSONNEL]);
        test.setTestPlace(headerRow[TEST_PLACE]);
        test.setSampleBased(headerRow[SAMPLE_BASE]);
        test.setComment(headerRow[COMMENT]);
        test.setEvaluation(evaluation.value());
        test.setMeterPosition(pos);
        if (meter.getMeterType() == null || meter.getMeterType().isEmpty()) {
            meter.setMeterType(headerRow[METER_TYPE]);
        }
    }

    private void parse_T_1_CalibrationPhase(String[] headerRow, String[] meterTestData, Meter meter,
                                              TestBenchResults previousTests, String currentStartTime,
                                              String currentEndTime) {

        // check if the test exist
        if (meterTestData[CAL_PHASE] != null && !meterTestData[CAL_PHASE].equals(TestEvaluation.NOT_TESTED.value())) {

            String previousEndTime = previousTests.getCalibrationPhaseEndTime();

            // check test is later than the latest test register on the meter
            if (this.compareTestTime(currentStartTime, previousEndTime)) {

                // update the latest test tracker on meter
                TestEvaluation currentResult = TestEvaluation.fromValue(meterTestData[CAL_PHASE]);

                // make current result the latest test result
                previousTests.setCalibrationPhaseEvaluation(currentResult);
                previousTests.setCalibrationPhaseEndTime(currentEndTime);

                // collect calibration neutral test
                final CalibrationPhaseTest cpt = new CalibrationPhaseTest();

                this.setTestMetaData(headerRow, cpt, currentResult, meterTestData[METER_POS], meter);

                if(meterTestData[PH_CAL_RES] != null) {

                    cpt.setPhaseCalibrationResult(meterTestData[PH_CAL_RES]);

                }

                if (meterTestData[PH_E_Lo] != null) {

                    cpt.setPhaseELo(Integer.parseInt(this.parseString(meterTestData[PH_E_Lo])));

                }

                if (meterTestData[PH_E_Hi] != null) {

                    cpt.setPhaseEHi(Integer.parseInt(this.parseString(meterTestData[PH_E_Hi])));

                }

                if (meterTestData[PH_G_Co] != null) {

                    cpt.setPhaseGCo(Integer.parseInt(this.parseString(meterTestData[PH_G_Co])));

                }

                if (meterTestData[PH_P_Off] != null) {

                    cpt.setPhasePOff(Integer.parseInt(this.parseString(meterTestData[PH_P_Off])));

                }

                if (meterTestData[PH_Ph_Co] != null) {

                    cpt.setPhasePhCo(Integer.parseInt(this.parseString(meterTestData[PH_Ph_Co])));

                }

                if (meterTestData[PH_ST_C_Int] != null) {

                    cpt.setPhaseStCInt(Integer.parseInt(this.parseString(meterTestData[PH_ST_C_Int])));

                }

                if (meterTestData[PH_St_C_Fra] != null) {

                    cpt.setPhaseStCFra(Integer.parseInt(this.parseString(meterTestData[PH_St_C_Fra])));

                }

                if (meterTestData[PH_V_R] != null) {

                    cpt.setPhaseVR(Integer.parseInt(this.parseString(meterTestData[PH_V_R])));

                }

                if (meterTestData[PH_C_R] != null) {

                    cpt.setPhaseCR(Integer.parseInt(this.parseString(meterTestData[PH_C_R])));

                }

                if (meterTestData[PH_Ph_Co0] != null) {

                    cpt.setPhasePhCo0(Integer.parseInt(this.parseString(meterTestData[PH_Ph_Co0])));

                }

                if (meterTestData[PH_Ph_Co1] != null) {

                    cpt.setPhasePhCo1(Integer.parseInt(this.parseString(meterTestData[PH_Ph_Co1])));

                }

                if (meterTestData[PH_Ph_Co2] != null) {

                    cpt.setPhasePhCo2(Integer.parseInt(this.parseString(meterTestData[PH_Ph_Co2])));

                }

                if (meterTestData[PH_Ph_Co3] != null) {

                    cpt.setPhasePhCo3(Integer.parseInt(this.parseString(meterTestData[PH_Ph_Co3])));

                }

                if(meterTestData[PH_meterIDNum] != null) {
                    cpt.setPhaseMeterIDNumeric(meterTestData[PH_meterIDNum]);
                }


                if(meterTestData[PH_Errors] != null) {
                    cpt.setPhaseErrors(meterTestData[PH_Errors]);
                }

                if(meterTestData[PH_ID_Pwd_st] != null) {
                    cpt.setPhaseIDPasswordStatus(meterTestData[PH_ID_Pwd_st]);
                }


                // add this test to previous tests on the meter
                List<CalibrationPhaseTest> cpts = previousTests.getCalibrationPhaseTest();

                if (cpts == null) {

                    cpts = new ArrayList<>();

                }

                cpts.add(cpt);

                previousTests.setCalibrationPhaseTest(cpts);

            }

        }


    }

    private void parse_T_2_CalibrationNeutral(String[] headerRow, String[] meterTestData, Meter meter,
                                              TestBenchResults previousTests, String currentStartTime,
                                              String currentEndTime) {

        // check if the test exist
        if (meterTestData[CAL_NEUTRAL] != null && !meterTestData[CAL_NEUTRAL].equals(TestEvaluation.NOT_TESTED.value())) {

            String previousEndTime = previousTests.getCalibrationNeutralEndTime();

            // check test is later than the latest test register on the meter
            if (this.compareTestTime(currentStartTime, previousEndTime)) {

                // update the latest test tracker on meter
                TestEvaluation currentResult = TestEvaluation.fromValue(meterTestData[CAL_NEUTRAL]);

                // make current result the latest test result
                previousTests.setCalibrationNeutralEvaluation(currentResult);
                previousTests.setCalibrationNeutralEndTime(currentEndTime);

                // collect calibration neutral test
                final CalibrationNeutralTest cnt = new CalibrationNeutralTest();

                this.setTestMetaData(headerRow, cnt, currentResult, meterTestData[METER_POS], meter);

                if(meterTestData[N_CAL_RES] != null) {

                    cnt.setNeutralCalibrationResult(meterTestData[N_CAL_RES]);
                }

                if (meterTestData[N_E_Lo] != null) {

                    cnt.setNeutralELo(Integer.parseInt(this.parseString(meterTestData[N_E_Lo])));

                }

                if (meterTestData[N_E_Hi] != null) {

                    cnt.setNeutralEHi(Integer.parseInt(this.parseString(meterTestData[N_E_Hi])));

                }

                if (meterTestData[N_G_Co] != null) {

                    cnt.setNeutralGCo(Integer.parseInt(this.parseString(meterTestData[N_G_Co])));

                }

                if (meterTestData[N_P_Off] != null) {

                    cnt.setNeutralPOff(Integer.parseInt(this.parseString(meterTestData[N_P_Off])));

                }

                if (meterTestData[N_Ph_Co] != null) {

                    cnt.setNeutralPhCo(Integer.parseInt(this.parseString(meterTestData[N_Ph_Co])));

                }

                if (meterTestData[N_St_C_Int] != null) {

                    cnt.setNeutralStCInt(Integer.parseInt(this.parseString(meterTestData[N_St_C_Int])));

                }

                if (meterTestData[N_St_C_Fra] != null) {

                    cnt.setNeutralStCFra(Integer.parseInt(this.parseString(meterTestData[N_St_C_Fra])));

                }

                if (meterTestData[N_V_R] != null) {

                    cnt.setNeutralVR(Integer.parseInt(this.parseString(meterTestData[N_V_R])));

                }

                if (meterTestData[N_C_R] != null) {

                    cnt.setNeutralCR(Integer.parseInt(this.parseString(meterTestData[N_C_R])));

                }

                if (meterTestData[N_Ph_Co0] != null) {

                    cnt.setNeutralPhCo0(Integer.parseInt(this.parseString(meterTestData[N_Ph_Co0])));

                }

                if (meterTestData[N_Ph_Co1] != null) {

                    cnt.setNeutralPhCo1(Integer.parseInt(this.parseString(meterTestData[N_Ph_Co1])));

                }

                if (meterTestData[N_Ph_Co2] != null) {

                    cnt.setNeutralPhCo2(Integer.parseInt(this.parseString(meterTestData[N_Ph_Co2])));

                }

                if (meterTestData[N_Ph_Co3] != null) {

                    cnt.setNeutralPhCo3(Integer.parseInt(this.parseString(meterTestData[N_Ph_Co3])));

                }

                if(meterTestData[N_meterIDNum] != null) {

                cnt.setNeutralMeterIDNumeric(meterTestData[N_meterIDNum]);
                }

                if(meterTestData[N_Errors] != null) {

                    cnt.setNeutralErrors(meterTestData[N_Errors]);
                }

                if(meterTestData[N_ID_Pwd_st] != null) {

                    cnt.setNeutralIDPasswordStatus(meterTestData[N_ID_Pwd_st]);
                }

                // add this test to previous tests on the meter
                List<CalibrationNeutralTest> cnts = previousTests.getCalibrationNeutralTest();

                if (cnts == null) {

                    cnts = new ArrayList<>();

                }

                cnts.add(cnt);

                previousTests.setCalibrationNeutralTest(cnts);

            }

        }


    }

    private void parse_T_3_ShuntImpedanceTest(String[] headerRow, String[] meterTestData, Meter meter,
                                              TestBenchResults previousTests, String currentStartTime,
                                              String currentEndTime) {

        // check the test exists
        if (meterTestData[SHUNT_IMP] != null && !meterTestData[SHUNT_IMP].equals(TestEvaluation.NOT_TESTED.value())) {

            String previousEndTime = previousTests.getShuntImpedanceEndTime();

            if (this.compareTestTime(currentStartTime, previousEndTime)) {
                TestEvaluation currentResult = TestEvaluation.fromValue(meterTestData[SHUNT_IMP]);


                // make current result the latest test result
                previousTests.setShuntImpedanceEvaluation(currentResult);
                previousTests.setShuntImpedanceEndTime(currentEndTime);


                // collect shunt impedance test from column 51(AZ) to column 54(BC)
                final ShuntImpedanceTest sit = new ShuntImpedanceTest();

                this.setTestMetaData(headerRow, sit, currentResult, meterTestData[METER_POS], meter);

                if (meterTestData[SI_Resistance1] != null) {
                    sit.setResistance1(Float.parseFloat(this.parseString(meterTestData[SI_Resistance1])));
                }

                if (meterTestData[SI_Resistance2] != null) {
                    sit.setResistance2(Float.parseFloat(this.parseString(meterTestData[SI_Resistance2])));
                }

                if (meterTestData[SI_Resistance3] != null) {
                    sit.setResistance3(Float.parseFloat(this.parseString(meterTestData[SI_Resistance3])));
                }

                if (meterTestData[SI_Temperature] != null) {
                    sit.setTemperature(Float.parseFloat(this.parseString(meterTestData[SI_Temperature])));
                }

                // append shunt impedance test to existing shunt impedance tests on the meter
                List<ShuntImpedanceTest> sits = previousTests.getShuntImpedanceTest();
                if (sits == null) {
                    sits = new ArrayList<>();
                }
                sits.add(sit);
                previousTests.setShuntImpedanceTest(sits);

            }

        }

    }

    private void parse_T_4_NeutralImpedanceTest(String[] headerRow, String[] meterTestData, Meter meter,
                                                TestBenchResults previousTests, String currentStartTime,
                                                String currentEndTime) {

        // check if the test exists
        if (meterTestData[NEUTRAL_IMP] != null && !meterTestData[NEUTRAL_IMP].equals(TestEvaluation.NOT_TESTED.value())) {

            String previousEndTime = previousTests.getNeutralImpedanceEndTime();

            if (this.compareTestTime(currentStartTime, previousEndTime)) {

                // update the latest test tracker on meter
                TestEvaluation currentResult = TestEvaluation.fromValue(meterTestData[NEUTRAL_IMP]);

                // make current result the latest test result
                previousTests.setNeutralImpedanceEvaluation(currentResult);
                previousTests.setNeutralImpedanceEndTime(currentEndTime);

                // collect neutral impedance test from column 55(BD) to column 56(BE)
                final NeutralImpedanceTest neutralImpedanceTest = new NeutralImpedanceTest();

                this.setTestMetaData(headerRow, neutralImpedanceTest, currentResult,
                        meterTestData[METER_POS], meter);

                if (meterTestData[NI_ResistanceN] != null) {
                    neutralImpedanceTest.setResistanceNeutral(Float.parseFloat(this.parseString(meterTestData[NI_ResistanceN])));
                }

                if (meterTestData[NI_Temperature] != null) {
                    neutralImpedanceTest.setTemperature(Float.parseFloat(this.parseString(meterTestData[NI_Temperature])));
                }

                // append neutral impedance test to existing shunt impedance tests on the meter
                List<NeutralImpedanceTest> nits = previousTests.getNeutralImpedanceTest();
                if (nits == null) {
                    nits = new ArrayList<>();
                }
                nits.add(neutralImpedanceTest);
                previousTests.setNeutralImpedanceTest(nits);

            }

        }
    }

    private void parse_T_5_ActiveEnergyRegisterTest(String[] headerRow, String[] meterTestData, Meter meter,
                                                    TestBenchResults previousTests, String currentStartTime,
                                                    String currentEndTime) {

        if (meterTestData[ACTIVE_ENERGY_REG] != null && !meterTestData[ACTIVE_ENERGY_REG].equals(TestEvaluation.NOT_TESTED.value())) {

            String previousEndTime = previousTests.getActiveEnergyRegisterEndTime();

            // check test is later than the latest test register on the meter
            if (this.compareTestTime(currentStartTime, previousEndTime)) {

                // update the latest test tracker on meter
                TestEvaluation currentResult = TestEvaluation.fromValue(meterTestData[ACTIVE_ENERGY_REG]);


                // make current result the latest test result
                previousTests.setActiveEnergyRegisterEvaluation(currentResult);
                previousTests.setActiveEnergyRegisterEndTime(currentEndTime);

                // collect Active energy register test
                final ActiveEnergyRegisterTest aert = new ActiveEnergyRegisterTest();

                this.setTestMetaData(headerRow, aert, currentResult, meterTestData[METER_POS], meter);

                if (meterTestData[DOS_ENG_ACT] != null) {
                    aert.setDosageEnergy(Float.parseFloat(this.parseString(meterTestData[DOS_ENG_ACT])));
                }

                if (meterTestData[STA_ENG_ACT] != null) {
                    aert.setStartEnergy(Float.parseFloat(this.parseString(meterTestData[STA_ENG_ACT])));
                }

                if (meterTestData[END_ENG_ACT] != null) {
                    aert.setEndEnergy(Float.parseFloat(this.parseString(meterTestData[END_ENG_ACT])));
                }

                if (meterTestData[DOS_PERC_ACT] != null) {
                    aert.setDosagePercentError(Float.parseFloat(this.parseString(meterTestData[DOS_PERC_ACT])));
                }

                // add this test to the previous tests on the meter
                List<ActiveEnergyRegisterTest> aerts = previousTests.getActiveEnergyRegisterTest();

                if (aerts == null) {

                    aerts = new ArrayList<>();

                }

                aerts.add(aert);

                previousTests.setActiveEnergyRegisterTest(aerts);

            }

        }
    }

    private void parse_T_6_ReactiveEnergyRegisterTest(String[] headerRow, String[] meterTestData, Meter meter,
                                                    TestBenchResults previousTests, String currentStartTime,
                                                    String currentEndTime) {

        if (meterTestData[REACTIVE_ENERGY_REG] != null && !meterTestData[REACTIVE_ENERGY_REG].equals(TestEvaluation.NOT_TESTED.value())) {

            String previousEndTime = previousTests.getReactiveEnergyRegisterEndTime();

            // check test is later than the latest test register on the meter
            if (this.compareTestTime(currentStartTime, previousEndTime)) {

                // update the latest test tracker on meter
                TestEvaluation currentResult = TestEvaluation.fromValue(meterTestData[REACTIVE_ENERGY_REG]);


                // make current result the latest test result
                previousTests.setReactiveEnergyRegisterEvaluation(currentResult);
                previousTests.setReactiveEnergyRegisterEndTime(currentEndTime);

                // collect Active energy register test
                final ReactiveEnergyRegisterTest raert = new ReactiveEnergyRegisterTest();

                this.setTestMetaData(headerRow, raert, currentResult, meterTestData[METER_POS], meter);

                if (meterTestData[DOS_ENG_RE] != null) {
                    raert.setDosageEnergy(Float.parseFloat(this.parseString(meterTestData[DOS_ENG_RE])));
                }

                if (meterTestData[STA_ENG_RE] != null) {
                    raert.setStartEnergy(Float.parseFloat(this.parseString(meterTestData[STA_ENG_RE])));
                }

                if (meterTestData[END_ENG_RE] != null) {
                    raert.setEndEnergy(Float.parseFloat(this.parseString(meterTestData[END_ENG_RE])));
                }

                if (meterTestData[DOS_PERC_RE] != null) {
                    raert.setDosagePercentError(Float.parseFloat(this.parseString(meterTestData[DOS_PERC_RE])));
                }

                // add this test to the previous tests on the meter
                List<ReactiveEnergyRegisterTest> raerts = previousTests.getReactiveEnergyRegisterTest();

                if (raerts == null) {

                    raerts = new ArrayList<>();

                }

                raerts.add(raert);

                previousTests.setReactiveEnergyRegisterTest(raerts);

            }

        }
    }

    private void parse_T_7_OperatingCurrentTest(String[] headerRow, String[] meterTestData, Meter meter,
                                                TestBenchResults previousTests, String currentStartTime,
                                                String currentEndTime) {

        String previousEndTime = previousTests.getOperatingCurrentEndTime();

        if (meterTestData[OPERATING_CURRENT] != null && !meterTestData[OPERATING_CURRENT].equals(TestEvaluation.NOT_TESTED.value())) {

            if(this.compareTestTime(currentStartTime, previousEndTime)) {

                TestEvaluation currentResult = TestEvaluation.fromValue(meterTestData[OPERATING_CURRENT]);

                previousTests.setOperatingCurrentEvaluation(currentResult);
                previousTests.setOperatingCurrentEndTime(currentEndTime);


                final OperatingCurrentTest operatingCurrentTest = new OperatingCurrentTest();

                this.setTestMetaData(headerRow, operatingCurrentTest, currentResult, meterTestData[METER_POS], meter);


                if (meterTestData[ERR_PF_1_100] != null) {
                    operatingCurrentTest.setErrorPF1100A220V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_PF_1_100])));
                }

                if (meterTestData[ERR_PF_1_80] != null) {
                    operatingCurrentTest.setErrorPF180A220V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_PF_1_80])));
                }

                if (meterTestData[ERR_PF_1_60] != null) {
                    operatingCurrentTest.setErrorPF160A220V50Hz007(Float.parseFloat(this.parseString(meterTestData[ERR_PF_1_60])));
                }

                if (meterTestData[ERR_PF_1_30] != null) {
                    operatingCurrentTest.setErrorPF130A220V50Hz010(Float.parseFloat(this.parseString(meterTestData[ERR_PF_1_30])));
                }

                if (meterTestData[ERR_PF_1_20] != null) {
                    operatingCurrentTest.setErrorPF120A220V50Hz029(Float.parseFloat(this.parseString(meterTestData[ERR_PF_1_20])));
                }

                if (meterTestData[ERR_PF_1_10] != null) {
                    operatingCurrentTest.setErrorPF110A220V50Hz022(Float.parseFloat(this.parseString(meterTestData[ERR_PF_1_10])));
                }

                if (meterTestData[ERR_PF_1_5] != null) {
                    operatingCurrentTest.setErrorPF15A220V50Hz004(Float.parseFloat(this.parseString(meterTestData[ERR_PF_1_5])));
                }

                if (meterTestData[ERR_PF_1_1] != null) {
                    operatingCurrentTest.setErrorPF11A220V50Hz008(Float.parseFloat(this.parseString(meterTestData[ERR_PF_1_1])));
                }

                if (meterTestData[ERR_PF_1_0_5] != null) {
                    operatingCurrentTest.setErrorPF105A220V50Hz023(Float.parseFloat(this.parseString(meterTestData[ERR_PF_1_0_5])));
                }

                if (meterTestData[ERR_PF_1_0_25] != null) {
                    operatingCurrentTest.setErrorPF1025A220V50Hz063(Float.parseFloat(this.parseString(meterTestData[ERR_PF_1_0_25])));
                }

                if (meterTestData[ERR_PF_0_5_lag100] != null) {
                    operatingCurrentTest.setErrorPF05Lag100A220V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_5_lag100])));
                }

                if (meterTestData[ERR_PF_0_5_lag80] != null) {
                    operatingCurrentTest.setErrorPF05Lag80A220V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_5_lag80])));
                }

                if (meterTestData[ERR_PF_0_5_lag60] != null) {
                    operatingCurrentTest.setErrorPF05Lag60A220V50Hz097(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_5_lag60])));
                }

                if (meterTestData[ERR_PF_0_5_lag30] != null) {
                    operatingCurrentTest.setErrorPF05Lag30A220V50Hz074(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_5_lag30])));
                }

                if (meterTestData[ERR_PF_0_5_lag20] != null) {
                    operatingCurrentTest.setErrorPF05Lag20A220V50Hz079(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_5_lag20])));
                }

                if (meterTestData[ERR_PF_0_5_lag10] != null) {
                    operatingCurrentTest.setErrorPF05Lag10A220V50Hz063(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_5_lag10])));
                }

                if (meterTestData[ERR_PF_0_5_lag5] != null) {
                    operatingCurrentTest.setErrorPF05Lag5A220V50Hz060(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_5_lag5])));
                }

                if (meterTestData[ERR_PF_0_5_lag1] != null) {
                    operatingCurrentTest.setErrorPF05Lag1A220V50Hz019(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_5_lag1])));
                }

                if (meterTestData[ERR_PF_0_5_lag0_5] != null) {
                    operatingCurrentTest.setErrorPF05Lag05A220V50Hz028(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_5_lag0_5])));
                }

                if (meterTestData[ERR_PF_0_8_lead100] != null) {
                    operatingCurrentTest.setErrorPF08Lead100A220V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_8_lead100])));
                }

                if (meterTestData[ERR_PF_0_8_lead80] != null) {
                    operatingCurrentTest.setErrorPF08Lead80A220V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_8_lead80])));
                }

                if (meterTestData[ERR_PF_0_8_lead60] != null) {
                    operatingCurrentTest.setErrorPF08Lead60A220V50Hz004(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_8_lead60])));
                }

                if (meterTestData[ERR_PF_0_8_lead30] != null) {
                    operatingCurrentTest.setErrorPF08Lead30A220V50Hz041(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_8_lead30])));
                }

                if (meterTestData[ERR_PF_0_8_lead20] != null) {
                    operatingCurrentTest.setErrorPF08Lead20A220V50Hz049(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_8_lead20])));
                }

                if (meterTestData[ERR_PF_0_8_lead10] != null) {
                    operatingCurrentTest.setErrorPF08Lead10A220V50Hz012(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_8_lead10])));
                }

                if (meterTestData[ERR_PF_0_8_lead5] != null) {
                    operatingCurrentTest.setErrorPF08Lead5A220V50Hz022(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_8_lead5])));
                }

                if (meterTestData[ERR_PF_0_8_lead1] != null) {
                    operatingCurrentTest.setErrorPF08Lead1A220V50Hz040(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_8_lead1])));
                }

                if (meterTestData[ERR_PF_0_8_lead0_5] != null) {
                    operatingCurrentTest.setErrorPF08Lead05A220V50Hz067(Float.parseFloat(this.parseString(meterTestData[ERR_PF_0_8_lead0_5])));
                }

                if (meterTestData[MAX_ERR_0_1_PF_1] != null) {
                    operatingCurrentTest.setMaxError01IbToImaxPF1(Float.parseFloat(this.parseString(meterTestData[MAX_ERR_0_1_PF_1])));
                }

                if (meterTestData[MAX_ERR_0_05_PF_1] != null) {
                    operatingCurrentTest.setMaxError005IbTo01IbPF1(Float.parseFloat(this.parseString(meterTestData[MAX_ERR_0_05_PF_1])));
                }

                if (meterTestData[MAX_ERR_0_2_PF_0_5] != null) {
                    operatingCurrentTest.setMaxError02IbToImaxPF05Ind(Float.parseFloat(this.parseString(meterTestData[MAX_ERR_0_2_PF_0_5])));
                }

                if (meterTestData[MAX_ERR_0_1_PF_0_5] != null) {
                    operatingCurrentTest.setMaxError01IbTo02IbPF05Ind(Float.parseFloat(this.parseString(meterTestData[MAX_ERR_0_1_PF_0_5])));
                }

                if (meterTestData[MAX_ERR_0_2_PF_0_8] != null) {
                    operatingCurrentTest.setMaxError02IbToImaxPF08Cap(Float.parseFloat(this.parseString(meterTestData[MAX_ERR_0_2_PF_0_8])));
                }

                if (meterTestData[MAX_ERR_0_1_PF_0_8] != null) {
                    operatingCurrentTest.setMaxError01IbTo02IbPF08Cap(Float.parseFloat(this.parseString(meterTestData[MAX_ERR_0_1_PF_0_8])));
                }

                List<OperatingCurrentTest> operatingCurrentTests = previousTests.getOperatingCurrentTest();
                if(operatingCurrentTests == null) {
                    operatingCurrentTests = new ArrayList<>();
                }
                operatingCurrentTests.add(operatingCurrentTest);
                previousTests.setOperatingCurrentTest(operatingCurrentTests);
            }
        }
    }

    private void parse_T_8_StartingCurrentTest(String[] headerRow, String[] meterTestData, Meter meter,
                                                   TestBenchResults previousTests, String currentStartTime,
                                                   String currentEndTime) {

        String previousEndTime = previousTests.getStartingCurrentEndTime();

        if (meterTestData[STARTING_CURRENT] != null && !meterTestData[STARTING_CURRENT].equals(TestEvaluation.NOT_TESTED.value())) {


            // check test is later than the latest test on the meter
            if(this.compareTestTime(currentStartTime, previousEndTime)){

                // update the latest test tracker on meter
                TestEvaluation currentResult = TestEvaluation.fromValue(meterTestData[STARTING_CURRENT]);

                previousTests.setStartingCurrentEvaluation(currentResult);
                previousTests.setStartingCurrentEndTime(currentEndTime);

                final StartingCurrentTest startingCurrentTest = new StartingCurrentTest();

                this.setTestMetaData(headerRow, startingCurrentTest, currentResult, meterTestData[METER_POS], meter);

                if (meterTestData[STA_EDGES] != null) {
                    startingCurrentTest.setEdgesCountStartingI(Integer.parseInt(this.parseString(meterTestData[STA_EDGES])));
                }

                List<StartingCurrentTest> scts = previousTests.getStartingCurrentTest();
                if(scts == null ) {
                    scts = new ArrayList<>();
                }
                scts.add(startingCurrentTest);
                previousTests.setStartingCurrentTest(scts);

            }

        }
    }

    private void parse_T_9_NoLoadTest(String[] headerRow, String[] meterTestData, Meter meter,
                                     TestBenchResults previousTests, String currentStartTime,
                                     String currentEndTime) {


        String previousEndTime = previousTests.getNoLoadEndTime();

        if (meterTestData[NO_LOAD] != null && !meterTestData[NO_LOAD].equals(TestEvaluation.NOT_TESTED.value())) {

            if(this.compareTestTime(currentStartTime, previousEndTime)){

                TestEvaluation currentResult = TestEvaluation.fromValue(meterTestData[NO_LOAD]);

                previousTests.setNoLoadEvaluation(currentResult);
                previousTests.setNoLoadEndTime(currentEndTime);


                final NoLoadTest noLoadTest = new NoLoadTest();

                this.setTestMetaData(headerRow, noLoadTest, currentResult, meterTestData[METER_POS], meter);

                if (meterTestData[CRE_EDGES] != null) {
                    noLoadTest.setEdgesCountNoLoad(Integer.parseInt(this.parseString(meterTestData[CRE_EDGES])));
                }

                List<NoLoadTest> noLoadTests = previousTests.getNoLoadTest();
                if(noLoadTests == null) {
                    noLoadTests = new ArrayList<>();
                }
                noLoadTests.add(noLoadTest);
                previousTests.setNoLoadTest(noLoadTests);

            }

        }
    }

    private void parse_T_10_OperatingCurrentReactiveTest(String[] headerRow, String[] meterTestData, Meter meter,
                                                 TestBenchResults previousTests, String currentStartTime,
                                                 String currentEndTime) {

        String previousEndTime = previousTests.getOperatingCurrentEndTime();

        if (meterTestData[OPERATING_CURRENT_RE] != null && !meterTestData[OPERATING_CURRENT_RE].equals(TestEvaluation.NOT_TESTED.value())) {

            if(this.compareTestTime(currentStartTime, previousEndTime)) {

                TestEvaluation currentResult = TestEvaluation.fromValue(meterTestData[OPERATING_CURRENT_RE]);

                previousTests.setOperatingCurrentEvaluation(currentResult);
                previousTests.setOperatingCurrentEndTime(currentEndTime);


                final OperatingCurrentTestReactive octr = new OperatingCurrentTestReactive();

                this.setTestMetaData(headerRow, octr, currentResult, meterTestData[METER_POS], meter);


                if (meterTestData[ERR_SIN_1_100] != null) {
                    octr.setErrorSIN1100A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_1_100])));
                }

                if (meterTestData[ERR_SIN_1_70] != null) {
                    octr.setErrorSIN170A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_1_70])));
                }

                if (meterTestData[ERR_SIN_1_40] != null) {
                    octr.setErrorSIN140A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_1_40])));
                }

                if (meterTestData[ERR_SIN_1_20] != null) {
                    octr.setErrorSIN120A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_1_20])));
                }

                if (meterTestData[ERR_SIN_1_10] != null) {
                    octr.setErrorSIN110A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_1_10])));
                }

                if (meterTestData[ERR_SIN_1_2] != null) {
                    octr.setErrorSIN12A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_1_2])));
                }

                if (meterTestData[ERR_SIN_1_1] != null) {
                    octr.setErrorSIN11A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_1_1])));
                }

                if (meterTestData[ERR_SIN_1_0_5] != null) {
                    octr.setErrorSIN105A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_1_0_5])));
                }

                if (meterTestData[ERR_SIN_0_25_100] != null) {
                    octr.setErrorSIN025100A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_0_25_100])));
                }

                if (meterTestData[ERR_SIN_0_25_70] != null) {
                    octr.setErrorSIN02570A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_0_25_70])));
                }

                if (meterTestData[ERR_SIN_0_25_40] != null) {
                    octr.setErrorSIN02540A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_0_25_40])));
                }

                if (meterTestData[ERR_SIN_0_25_20] != null) {
                    octr.setErrorSIN02520A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_0_25_20])));
                }

                if (meterTestData[ERR_SIN_0_25_10] != null) {
                    octr.setErrorSIN02510A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_0_25_10])));
                }

                if (meterTestData[ERR_SIN_0_25_2] != null) {
                    octr.setErrorSIN0252A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_0_25_2])));
                }

                if (meterTestData[ERR_SIN_0_25_1] != null) {
                    octr.setErrorSIN0251A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_0_25_1])));
                }

                if (meterTestData[ERR_SIN_N_0_25_100] != null) {
                    octr.setErrorSINN025100A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_N_0_25_100])));
                }

                if (meterTestData[ERR_SIN_N_0_25_70] != null) {
                    octr.setErrorSINN02570A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_N_0_25_70])));
                }

                if (meterTestData[ERR_SIN_N_0_25_40] != null) {
                    octr.setErrorSINN02540A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_N_0_25_40])));
                }

                if (meterTestData[ERR_SIN_N_0_25_20] != null) {
                    octr.setErrorSINN02520A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_N_0_25_20])));
                }

                if (meterTestData[ERR_SIN_N_0_25_10] != null) {
                    octr.setErrorSINN02510A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_N_0_25_10])));
                }

                if (meterTestData[ERR_SIN_N_0_25_2] != null) {
                    octr.setErrorSINN0252A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_N_0_25_2])));
                }

                if (meterTestData[ERR_SIN_N_0_25_1] != null) {
                    octr.setErrorSINN0251A230V50Hz(Float.parseFloat(this.parseString(meterTestData[ERR_SIN_N_0_25_1])));
                }

                if (meterTestData[MAX_ERR_0_1_SIN_1] != null) {
                    octr.setMaxError01IbToImaxSIN1(Float.parseFloat(this.parseString(meterTestData[MAX_ERR_0_1_SIN_1])));
                }

                if (meterTestData[MAX_ERR_0_05_SIN_1] != null) {
                    octr.setMaxError005IbTo01IbSIN1(Float.parseFloat(this.parseString(meterTestData[MAX_ERR_0_05_SIN_1])));
                }

                if (meterTestData[MAX_ERR_0_2_SIN_0_25] != null) {
                    octr.setMaxError02IbToImaxSIN025(Float.parseFloat(this.parseString(meterTestData[MAX_ERR_0_2_SIN_0_25])));
                }

                if (meterTestData[MAX_ERR_0_1_SIN_0_25] != null) {
                    octr.setMaxError01IbTo02IbSIN025(Float.parseFloat(this.parseString(meterTestData[MAX_ERR_0_1_SIN_0_25])));
                }

                if (meterTestData[MAX_ERR_0_2_SIN_N_0_25] != null) {
                    octr.setMaxError02IbToImaxSIN025(Float.parseFloat(this.parseString(meterTestData[MAX_ERR_0_2_SIN_N_0_25])));
                }

                if (meterTestData[MAX_ERR_0_1_SIN_N_0_25] != null) {
                    octr.setMaxError01IbTo02IbSINN025(Float.parseFloat(this.parseString(meterTestData[MAX_ERR_0_1_SIN_N_0_25])));
                }

                List<OperatingCurrentTestReactive> operatingCurrentTestReactives = previousTests.getOperatingCurrentTestReactive();
                if(operatingCurrentTestReactives == null) {
                    operatingCurrentTestReactives = new ArrayList<>();
                }
                operatingCurrentTestReactives.add(octr);
                previousTests.setOperatingCurrentTestReactive(operatingCurrentTestReactives);
            }
        }
    }

    private void parse_T_11_StartingCurrentReactiveTest(String[] headerRow, String[] meterTestData, Meter meter,
                                               TestBenchResults previousTests, String currentStartTime,
                                               String currentEndTime) {

        String previousEndTime = previousTests.getStartingCurrentReactiveEndTime();

        if (meterTestData[START_CURRENT_RE] != null && !meterTestData[START_CURRENT_RE].equals(TestEvaluation.NOT_TESTED.value())) {


            // check test is later than the latest test on the meter
            if(this.compareTestTime(currentStartTime, previousEndTime)){

                // update the latest test tracker on meter
                TestEvaluation currentResult = TestEvaluation.fromValue(meterTestData[START_CURRENT_RE]);

                previousTests.setStartingCurrentReactiveEvaluation(currentResult);
                previousTests.setStartingCurrentReactiveEndTime(currentEndTime);

                final StartingCurrentTestReactive startingCurrentTest = new StartingCurrentTestReactive();

                this.setTestMetaData(headerRow, startingCurrentTest, currentResult, meterTestData[METER_POS], meter);

                if (meterTestData[STA_EDGES_RE] != null) {
                    startingCurrentTest.setStartingReactiveEdges(Integer.parseInt(this.parseString(meterTestData[STA_EDGES_RE])));
                }

                List<StartingCurrentTestReactive> scts = previousTests.getStartingCurrentTestReactive();
                if(scts == null ) {
                    scts = new ArrayList<>();
                }
                scts.add(startingCurrentTest);
                previousTests.setStartingCurrentTestReactive(scts);

            }

        }
    }

    private void parse_T_12_NoLoadReactiveTest(String[] headerRow, String[] meterTestData, Meter meter,
                                      TestBenchResults previousTests, String currentStartTime,
                                      String currentEndTime) {


        String previousEndTime = previousTests.getNoLoadReactiveEndTime();

        if (meterTestData[NO_LOAD_RE] != null && !meterTestData[NO_LOAD_RE].equals(TestEvaluation.NOT_TESTED.value())) {

            if(this.compareTestTime(currentStartTime, previousEndTime)){

                TestEvaluation currentResult = TestEvaluation.fromValue(meterTestData[NO_LOAD_RE]);

                previousTests.setNoLoadReactiveEvaluation(currentResult);
                previousTests.setNoLoadReactiveEndTime(currentEndTime);


                final NoLoadTestReactive noLoadTest = new NoLoadTestReactive();

                this.setTestMetaData(headerRow, noLoadTest, currentResult, meterTestData[METER_POS], meter);

                if (meterTestData[CRE_EDGES_RE] != null) {
                    noLoadTest.setCreepingReactiveEdges(Integer.parseInt(this.parseString(meterTestData[CRE_EDGES_RE])));
                }

                List<NoLoadTestReactive> noLoadTests = previousTests.getNoLoadTestReactive();
                if(noLoadTests == null) {
                    noLoadTests = new ArrayList<>();
                }
                noLoadTests.add(noLoadTest);
                previousTests.setNoLoadTestReactive(noLoadTests);

            }

        }
    }

    private void parse_T_13_AccuracyTest(String[] headerRow, String[] meterTestData, Meter meter,
                                               TestBenchResults previousTests, String currentStartTime,
                                               String currentEndTime, EntityManager entityManager) {


        String previousEndTime = previousTests.getAccuracyNeutralEndTime();

        if (meterTestData[ACCURACY] != null && !meterTestData[ACCURACY].equals(TestEvaluation.NOT_TESTED.value())) {

            if(this.compareTestTime(currentStartTime, previousEndTime)){

                TestEvaluation currentResult = TestEvaluation.fromValue(meterTestData[ACCURACY]);

                previousTests.setAccuracyNeutralEvaluation(currentResult);
                previousTests.setAccuracyNeutralEndTime(currentEndTime);


                final AccuracyTestsNeutral accuracyTestsNeutral = new AccuracyTestsNeutral();

                this.setTestMetaData(headerRow, accuracyTestsNeutral, currentResult, meterTestData[METER_POS], meter);

                if (meterTestData[N_PERC_ERR] != null) {
                    accuracyTestsNeutral.setNeutralPercentError(Float.parseFloat(this.parseString(meterTestData[N_PERC_ERR])));
                }

                List<AccuracyTestsNeutral> accuracyTestsNeutrals = previousTests.getAccuracyTestNeutral();
                if(accuracyTestsNeutrals == null) {
                    accuracyTestsNeutrals = new ArrayList<>();
                }
                // add to database
                entityManager.persist(accuracyTestsNeutral);
                accuracyTestsNeutrals.add(accuracyTestsNeutral);
                previousTests.setAccuracyTestNeutral(accuracyTestsNeutrals);

            }

        }
    }

    private void parse_T_14_PLCTest(String[] headerRow, String[] meterTestData, Meter meter,
                                               TestBenchResults previousTests, String currentStartTime,
                                               String currentEndTime) {


        String previousEndTime = previousTests.getPowerLineCommunicationEndTime();

        if (meterTestData[PLC] != null && !meterTestData[PLC].equals(TestEvaluation.NOT_TESTED.value())) {

            if(this.compareTestTime(currentStartTime, previousEndTime)){

                TestEvaluation currentResult = TestEvaluation.fromValue(meterTestData[PLC]);

                previousTests.setPowerLineCommunicationEvaluation(currentResult);
                previousTests.setPowerLineCommunicationEndTime(currentEndTime);


                final PowerLineCommunication powerLineCommunication = new PowerLineCommunication();

                this.setTestMetaData(headerRow, powerLineCommunication, currentResult, meterTestData[METER_POS], meter);

                powerLineCommunication.setPowerLineCommunication(meterTestData[PLC_TEST]);

                List<PowerLineCommunication> powerLineCommunications = previousTests.getPowerLineCommunication();
                if(powerLineCommunications == null) {
                    powerLineCommunications = new ArrayList<>();
                }
                powerLineCommunications.add(powerLineCommunication);
                previousTests.setPowerLineCommunication(powerLineCommunications);

            }

        }
    }

    private void parse_T_15_EnergyAndTamperingClearTest(String[] headerRow, String[] meterTestData, Meter meter,
                                                       TestBenchResults previousTests, String currentStartTime,
                                                       String currentEndTime) {

        String previousEndTime = previousTests.getEnergyAndTamperClearEndTime();

        if (meterTestData[ENG_AND_TAMP_CL] != null && !meterTestData[ENG_AND_TAMP_CL].equals(TestEvaluation.NOT_TESTED.value())) {

            if(this.compareTestTime(currentStartTime, previousEndTime)){
                TestEvaluation currentResult = TestEvaluation.fromValue(meterTestData[ENG_AND_TAMP_CL]);

                previousTests.setEnergyAndTamperClearEvaluation(currentResult);
                previousTests.setEnergyAndTamperClearEndTime(currentEndTime);

                final EnergyAndTamperClearTest energyAndTamperClearTest = new EnergyAndTamperClearTest();

                this.setTestMetaData(headerRow, energyAndTamperClearTest, currentResult, meterTestData[METER_POS], meter);

                energyAndTamperClearTest.setMeterState(meterTestData[CLR_MTR_ST]);

                if (meterTestData[SV] != null) {
                    energyAndTamperClearTest.setSV(Integer.parseInt(this.parseString(meterTestData[SV])));
                }

                energyAndTamperClearTest.setMeterIDPasswordStatusText(meterTestData[CLR_MTR_ID_PWD]);

                if (meterTestData[RES_LO] != null) {
                    energyAndTamperClearTest.setErrorAtResistiveLoad(Float.parseFloat(this.parseString(meterTestData[RES_LO])));
                }

                if (meterTestData[IND_LO] != null) {
                    energyAndTamperClearTest.setErrorAtInductiveLoad(Float.parseFloat(this.parseString(meterTestData[IND_LO])));
                }

                if (meterTestData[CAP_LO] != null) {
                    energyAndTamperClearTest.setErrorAtCapacitiveLoad(Float.parseFloat(this.parseString(meterTestData[CAP_LO])));
                }

                List<EnergyAndTamperClearTest> energyAndTamperClearTests = previousTests.getEnergyAndTamperClearTest();
                if(energyAndTamperClearTests == null) {
                    energyAndTamperClearTests = new ArrayList<>();
                }
                energyAndTamperClearTests.add(energyAndTamperClearTest);
                previousTests.setEnergyAndTamperClearTest(energyAndTamperClearTests);
            }

        }
    }


    private boolean compareTestTime(String current, String previous) {

        final DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

        try {
            df.parse(current);
        } catch (final ParseException | NullPointerException e) {
            return false;
        }

        try {
            df.parse(previous);
        } catch (final ParseException | NullPointerException e) {
            return true;
        }

        Date newDate;
        try {
            newDate = df.parse(current);
            final Date oldDate = df.parse(previous);

            if (newDate.compareTo(oldDate) > 0) {
                return true;
            }
            return false;
        } catch (final ParseException e) {
            return false;
        }

    }

}