package com.parser.testparser.util;

import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Slf4j
public class ValidatorUtils {

    public static boolean isURLValid(String url) {
        log.debug("Проверяем на валидность адрес страницы");
        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            log.warn("Адрес - не валиден");
            return false;
        }
        log.debug("Адрес - валиден");
        return true;
    }
}