package com.dventus.ptt.dto;

import com.dventus.ptt.jaxb.messages.Meter;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.modelmapper.ModelMapper;

public class MeterMapper implements Mapper<Meter, MeterDto>{

    private ModelMapper modelMapper;

    public MeterMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public MeterDto convertToDto(Meter model) {

        return modelMapper.map(model, MeterDto.class);

    }

    @Override
    public Meter convertToEntity(MeterDto payload) {

        return this.modelMapper.map(payload, Meter.class);

    }

    @Override
    public Meter convertJsonToEntity(JsonElement payload) {

        MeterDto meterDto = new Gson().fromJson(payload, MeterDto.class);

        MeterMapper.logger.info(meterDto.toString());

        return this.modelMapper.map(meterDto, Meter.class);
    }

}
