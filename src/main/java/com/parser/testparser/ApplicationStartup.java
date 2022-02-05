package com.parser.testparser;

import com.parser.testparser.domain.Site;
import com.parser.testparser.domain.WordsStatistic;
import com.parser.testparser.service.ParserService;
import com.parser.testparser.service.SiteService;
import com.parser.testparser.util.ValidatorUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@AllArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final SiteService siteService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        String fileNameForParsing = "";
//        String test = "https://www.simbirsoft.com/"; //test*******************************************

        log.debug("Считываем с консоли адрес web - страницы.");
        System.out.println("Введите адрес web - страницы для парсинга: ");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String tempPath = reader.readLine();
                if (ValidatorUtils.isURLValid(tempPath)) {
                    fileNameForParsing = tempPath;
                    break;
                }
                log.debug("Невалидный URL");
                System.out.println("Невалидный URL. Повторите ввод.");
            }
        } catch (IOException e) {
            log.error("Ошибка ввода - вывода.");
            System.err.println(e.getMessage());
        }

        ParserService parserService = new ParserService();
        Map<String, Integer> tempMap = parserService.getStatistic(fileNameForParsing);
        log.debug("Сохранение статистики.");
        siteService.save(map(tempMap, fileNameForParsing));
        parserService.print(tempMap);
    }

    private Site map(Map<String, Integer> map, String url) {
        Site site = new Site();
        site.setName(url);

        List<WordsStatistic> wordsStatistics = new ArrayList<>();
        map.forEach((word, count) -> {
            WordsStatistic wordsStatistic = new WordsStatistic();
            wordsStatistic.setSite(site);
            wordsStatistic.setWord(word);
            wordsStatistic.setCount(Long.valueOf(count));
            wordsStatistics.add(wordsStatistic);
        });

        site.setStatistics(wordsStatistics);
        return site;
    }
}
