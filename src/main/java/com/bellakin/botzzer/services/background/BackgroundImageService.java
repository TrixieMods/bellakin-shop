package com.bellakin.botzzer.services.background;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dan.rees.thomas@gmail.com
 */
@Service
@EnableScheduling
public class BackgroundImageService implements BackgroundProvider<String> {

  private final String defaultBackground = "bg-1.jpeg";

  private final URI bgFolder;

  private String currentBackground = defaultBackground;

  public BackgroundImageService(@Value("${app.services.bgService.bgPath}") String bgFilesPath) throws URISyntaxException {
    URL bgPath = getClass().getResource(bgFilesPath);
    if(bgPath == null) {
      throw new IllegalArgumentException("Background folder not found: " + bgFilesPath);
    }
    bgFolder = bgPath.toURI();
  }

  @Override
  public String next() {
    return currentBackground;
  }

  @Scheduled(fixedDelay = 5_000, initialDelay = 1_000)
  public void update() {
    List<String> fileNames = getFileNames();

    String newBg = currentBackground;
    int tries = 0;
    do {
      Collections.shuffle(fileNames);
      currentBackground = fileNames.stream().findFirst().orElse(defaultBackground);
    } while (newBg.equals(currentBackground) && ++tries < 5);
  }

  public List<String> getFileNames() {
    try {
      return Files.list(Paths.get(bgFolder))
        .map(Path::getFileName)
        .map(Path::toString)
        .collect(Collectors.toList());
    } catch (IOException e) {
      return new ArrayList<>();
    }
  }

}
