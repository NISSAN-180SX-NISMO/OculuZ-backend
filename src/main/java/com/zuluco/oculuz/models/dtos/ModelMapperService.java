package com.zuluco.oculuz.models.dtos;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ModelMapperService {
    public static <T> T convertToDto(Object object, Class<T> clazz) {
        return new ModelMapper().map(object, clazz);
    }

    public static <T> T convertToEntity(Object object, Class<T> clazz) {
        return new ModelMapper().map(object, clazz);
    }

    public static <T> List<T> convertToListDto(List<?> list, Class<T> clazz) {
        return list.stream().map(element -> convertToDto(element, clazz)).collect(Collectors.toList());
    }

    public static <T> List<T> convertToListEntity(List<?> list, Class<T> clazz) {
        return list.stream().map(element -> convertToEntity(element, clazz)).collect(Collectors.toList());
    }
}
