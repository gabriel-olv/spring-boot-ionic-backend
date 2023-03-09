package com.gabrieldeoliveira.cursospring.resources.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class URL {

    public static String decodeParam(String string) {
        return URLDecoder.decode(string, StandardCharsets.UTF_8);
    }
    
    public static List<Integer> decodeIntList(String string) {
        try {
            return Arrays.asList(string.split(",")).stream().map(x -> Integer.parseInt(x)).toList();
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
