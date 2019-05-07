package com.dventus.ptt.dto;

import com.dventus.ptt.jaxb.messages.CircuitBoard;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.modelmapper.ModelMapper;

public class CircuitBoardMapper implements Mapper<CircuitBoard, CircuitBoardDto> {

    private ModelMapper modelMapper;

    public CircuitBoardMapper(ModelMapper modelMapper) {

        this.modelMapper = modelMapper;

    }

    @Override
    public CircuitBoardDto convertToDto(CircuitBoard model) {

        return modelMapper.map(model, CircuitBoardDto.class);

    }

    @Override
    public CircuitBoard convertToEntity(CircuitBoardDto payload) {
        return modelMapper.map(payload, CircuitBoard.class);
    }

    @Override
    public CircuitBoard convertJsonToEntity(JsonElement payload) {

        CircuitBoardDto circuitBoardDto = new Gson().fromJson(payload, CircuitBoardDto.class);

        return this.modelMapper.map(circuitBoardDto, CircuitBoard.class);

    }
}
