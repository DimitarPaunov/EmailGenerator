package com.cleverpine.query.service.contract;

import java.util.Map;

public interface EmailGeneratorService {

    String generateEmail(String expression, Map<String, String> inputs);

}
