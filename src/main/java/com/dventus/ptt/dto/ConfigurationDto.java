package com.dventus.ptt.dto;

import javax.annotation.CheckForNull;

public class ConfigurationDto {

    @CheckForNull private String configurationName;
    @CheckForNull private String configuredValue;

    public ConfigurationDto() {
    }

    @CheckForNull
    public String getConfigurationName() {
        return configurationName;
    }

    public void setConfigurationName(@CheckForNull String configurationName) {
        this.configurationName = configurationName;
    }

    @CheckForNull
    public String getConfiguredValue() {
        return configuredValue;
    }

    public void setConfiguredValue(@CheckForNull String configuredValue) {
        this.configuredValue = configuredValue;
    }
}
