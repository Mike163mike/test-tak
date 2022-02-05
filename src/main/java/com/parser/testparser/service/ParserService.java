package com.parser.testparser.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class ParserService {

    public Map<String, Integer> getStatistic(String string) {
        String parsingPage = parsePage(string);
        List<String> words = makeListFromString(parsingPage);
        return calculateStatistic(words);
    }

    private String parsePage(String fileName) {
        log.debug("Пробуем распарсить HTML - документ (web -страницу с URL: " + fileName);
        try {
            Document document = Jsoup.connect(fileName).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)").get();
            return document.text();
        } catch (IOException e) {
            log.warn("Не удалось распарсить HTML - документ");
            throw new RuntimeException("Invalid address");
        }
    }

    public List<String> makeListFromString(String stringOfPage) {
        List<String> stringList = new ArrayList<>();
        String normString = stringOfPage.replaceAll("[.,:;\\-'!?«»()\"\\[\\]{}\\d]", "");
        String[] strings = normString.split(" ");
        for (String str : strings) {
            if (str.length() > 1) {
                stringList.add(str.trim().toUpperCase());
            } else if (str.length() == 1 && str.matches("\\w")) {
                stringList.add(str.trim().toUpperCase());
            }
        }
        log.debug("Записали все слова с web - страницы в ArrayList \"stringList\"");
        return stringList;
    }

    public Map<String, Integer> calculateStatistic(List<String> tempListStr) {
        Map<String, Integer> statisticMap = new TreeMap<>();
        int count;
        for (String str : tempListStr) {
            count = Collections.frequency(tempListStr, str);
            statisticMap.put(str, count);
        }
        log.debug("Посчитали частоту появления слов в \"tempListStr\"  и положили их в TreeMap \"statisticMap\"");
        return statisticMap;
    }

    public void print(Map<String, Integer> map) {
        StringBuilder tempStr = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            tempStr.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }
        System.out.println(tempStr);
        log.debug("Распечатали мапу \"'map'\"");
    }
}
