package com.parser.testparser;

import com.parser.testparser.service.ParserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@ActiveProfiles(profiles = "test")
class TestParserApplicationTests {
    @MockBean
    private ApplicationStartup applicationStartup;

    @Autowired
    private ParserService parserService;

    @Test
    public void testMakeListFromString() {
        String stringOfPage = "Opera, 34stat sigma";
        List<String> expectedList = List.of("OPERA", "STAT", "SIGMA");
        List<String> stringList = parserService.makeListFromString(stringOfPage);
        assertEquals("Метод \"parsingPage()\" не прошёл тестирование.", expectedList, stringList);
    }
    @Test
    public void testCalculateStatistic() {
        Map<String, Integer> expectedMap = new TreeMap<>();
        expectedMap.put("ATOM", 2);
        expectedMap.put("BETA", 1);
        expectedMap.put("GAMMA", 3);

        List<String> stringList = List.of("ATOM", "BETA", "ATOM", "GAMMA", "GAMMA", "GAMMA");
        Map<String, Integer> map = new TreeMap<>();
        map = parserService.calculateStatistic(stringList);
        assertEquals("Метод \"calculateStatistic()\" не прошёл тестирование.", expectedMap, map);
    }


}
