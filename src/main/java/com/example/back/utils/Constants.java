package com.example.back.utils;

import com.google.common.collect.Lists;
import java.util.List;

public class Constants {

    public static final String SPACE_SEPARATOR = " ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String DATE_REGEX="^(0[1-9]|[12][0-9]|[3][01])/(0[1-9]|1[012])/\\d{4}$";

    public static final List<String> GLOBAL_RESPONSE_IGNORE_PATHS = Lists.newArrayList("/v3/api-docs", "/v3/api-docs/swagger-config");
}