package com.dventus.ptt.dto;

import com.dventus.ptt.jaxb.messages.Configuration;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.modelmapper.ModelMapper;

public class ConfigurationMapper implements Mapper<Configuration, ConfigurationDto>{

    private ModelMapper modelMapper;

    public ConfigurationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ConfigurationDto convertToDto(Configuration configuration) {

        return this.modelMapper.map(configuration, ConfigurationDto.class);

    }

    @Override
    public Configuration convertToEntity(ConfigurationDto payload) {

        return this.modelMapper.map(payload, Configuration.class);

    }

    @Override
    public Configuration convertJsonToEntity(JsonElement payload) {
        ConfigurationDto configurationDto = new Gson().fromJson(payload, ConfigurationDto.class);

        return this.modelMapper.map(configurationDto, Configuration.class);
    }


}
