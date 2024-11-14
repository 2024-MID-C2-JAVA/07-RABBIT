package com.bank.management.data;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class RolesConverter implements AttributeConverter<List<String>, String> {

    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(List<String> roles) {
        return roles != null ? String.join(SEPARATOR, roles) : "";
    }

    @Override
    public List<String> convertToEntityAttribute(String rolesString) {
        return rolesString != null && !rolesString.isEmpty()
                ? Arrays.stream(rolesString.split(SEPARATOR))
                .map(String::trim)
                .collect(Collectors.toList())
                : List.of();
    }
}
