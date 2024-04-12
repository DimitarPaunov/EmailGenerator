package com.cleverpine.query.config;

import com.cleverpine.cpspringerrorutil.util.GenericResponseEntityUtil;
import com.cleverpine.cpspringerrorutil.util.ResponseEntityUtil;
import com.cleverpine.interview.model.EmailData;
import com.cleverpine.interview.model.EmailDataResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResponseEntityUtilConfig {

    @Bean
    public GenericResponseEntityUtil genericResponseEntityUtil() {
        return new GenericResponseEntityUtil();
    }

    @Bean
    ResponseEntityUtil<EmailDataResponse, EmailData> emailDataResponseEntityUtil() {
        return new ResponseEntityUtil<>(EmailDataResponse.class, EmailData.class);
    }

}
