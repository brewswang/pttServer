package com.dventus.ptt.dto;

import com.dventus.ptt.jaxb.messages.Remark;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.modelmapper.ModelMapper;

public class RemarkMapper implements Mapper<Remark, RemarkDto>{
    ModelMapper modelMapper;

    public RemarkMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RemarkDto convertToDto(Remark model) {

        return this.modelMapper.map(model, RemarkDto.class);

    }

    @Override
    public Remark convertToEntity(RemarkDto payload) {

        return this.modelMapper.map(payload, Remark.class);

    }

    @Override
    public Remark convertJsonToEntity(JsonElement payload) {

        RemarkDto remarkDto = new Gson().fromJson(payload, RemarkDto.class);

        remarkDto.setCurrentTime();

        remarkDto.setActiveStatus(true);

        return this.modelMapper.map(remarkDto, Remark.class);

    }
}
