package com.dventus.ptt.dto;

import com.google.gson.JsonElement;
import org.apache.log4j.Logger;

public interface Mapper<T, R> {

    static final Logger logger = Logger.getLogger(Mapper.class);

    R convertToDto(T model);

    T convertToEntity(R payload);

    T convertJsonToEntity(JsonElement payload);

}
