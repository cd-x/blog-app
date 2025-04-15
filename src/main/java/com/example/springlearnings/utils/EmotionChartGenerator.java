package com.example.springlearnings.utils;

import com.example.springlearnings.services.models.EmotionScore;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;

import java.awt.*;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class EmotionChartGenerator {
    public static String generateEmotionChartBase64(List<EmotionScore> scores) throws Exception {
        // Filter top 10 scores for readability
        List<EmotionScore> topScores = scores.stream()
                .sorted((a, b) -> Double.compare(b.getScore(), a.getScore()))
                .limit(10)
                .toList();

        List<String> labels = topScores.stream().map(EmotionScore::getLabel).collect(Collectors.toList());
        List<Double> values = topScores.stream().map(EmotionScore::getScore).collect(Collectors.toList());

        CategoryChart chart = new CategoryChartBuilder()
                .width(800).height(400)
                .title("Top Emotions")
                .xAxisTitle("Emotion")
                .yAxisTitle("Score")
                .build();

        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setChartTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        chart.getStyler().setAxisTitleFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        chart.getStyler().setChartBackgroundColor(Color.WHITE);

        chart.addSeries("Emotion Scores", labels, values);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BitmapEncoder.saveBitmap(chart, baos, BitmapEncoder.BitmapFormat.PNG);

        return "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}
