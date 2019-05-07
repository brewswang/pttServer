package com.dventus.ptt.dto;

import com.dventus.ptt.jaxb.messages.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.modelmapper.ModelMapper;

public class UserMapper implements Mapper<User, UserDto> {

    private ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto convertToDto(User user) {

        UserDto userDto = this.modelMapper.map(user, UserDto.class);

        return userDto;

    }

    @Override
    public User convertToEntity(UserDto payload) {

        return this.modelMapper.map(payload, User.class);

    }

    @Override
    public User convertJsonToEntity(JsonElement payload) {
        UserDto userDto = new Gson().fromJson(payload, UserDto.class);

        return this.modelMapper.map(userDto, User.class);
    }
}
