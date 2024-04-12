package com.cleverpine.query.service.impl;

import com.cleverpine.query.service.contract.EmailGeneratorService;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class EmailGeneratorServiceImpl implements EmailGeneratorService {

    @Override
    public String generateEmail(String expression, Map<String, String> inputs) {
        var email = expression;

        // Process each component based on regex matching and replace directly in the string
        email = processEachWordFirstChars(email, inputs);
        email = processLastWords(email, inputs);

        // Replace input placeholders with actual values
        for (Map.Entry<String, String> entry : inputs.entrySet()) {
            email = email.replaceAll(entry.getKey(), entry.getValue());
        }

        // Remove concatenation symbols and ensure all characters are appropriate for an email
        email = email.replace("~", "")
                .replaceAll("\\s+", "")
                .replaceAll("[^a-zA-Z0-9@.]", "")
                .toLowerCase();

        // Deduplicate the TLD if necessary
        email = deduplicateTld(email);

        return email;
    }

    private String processEachWordFirstChars(String expression, Map<String, String> inputs) {
        var pattern = Pattern.compile("(input\\d+)\\.eachWordFirstChars\\((\\d+)\\)");
        var matcher = pattern.matcher(expression);
        var sb = new StringBuilder();
        while (matcher.find()) {
            var key = matcher.group(1);
            var numChars = Integer.parseInt(matcher.group(2));
            var words = inputs.getOrDefault(key, "");
            var result = Arrays.stream(words.split("[\\s-]+"))
                    .map(word -> word.substring(0, Math.min(word.length(), numChars)))
                    .collect(Collectors.joining());
            matcher.appendReplacement(sb, result);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private String processLastWords(String expression, Map<String, String> inputs) {
        var pattern = Pattern.compile("(input\\d+)\\.lastWords\\((\\d+)\\)");
        var matcher = pattern.matcher(expression);
        while (matcher.find()) {
            var key = matcher.group(1);
            int n = Integer.parseInt(matcher.group(2));
            String[] words = inputs.getOrDefault(key, "").split("\\s+");
            if (words.length >= n) {
                var result = words[words.length - n];
                expression = expression.replace(matcher.group(0), result);
            }
        }
        return expression;
    }

    private String deduplicateTld(String email) {
        var pattern = Pattern.compile("(\\.[a-z]+)\\1$");
        var matcher = pattern.matcher(email);
        if (matcher.find()) {
            // Replace the duplicate TLD with a single instance
            email = matcher.replaceAll("$1");
        }
        return email;
    }

}
