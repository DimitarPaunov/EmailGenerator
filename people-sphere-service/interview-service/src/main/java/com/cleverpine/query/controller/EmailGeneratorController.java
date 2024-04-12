package com.cleverpine.query.controller;

import com.cleverpine.cpspringerrorutil.util.ResponseEntityUtil;
import com.cleverpine.interview.api.EmailGeneratorApi;
import com.cleverpine.interview.model.EmailData;
import com.cleverpine.interview.model.EmailDataResponse;
import com.cleverpine.interview.model.EmailRequestData;
import com.cleverpine.query.service.contract.EmailGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailGeneratorController implements EmailGeneratorApi {

    private final EmailGeneratorService emailGeneratorService;
    private final ResponseEntityUtil<EmailDataResponse, EmailData> responseEntityUtil;

    @Override
    public ResponseEntity<EmailDataResponse> createEmail(EmailRequestData emailRequestData) {
        var email = emailGeneratorService.generateEmail(
                emailRequestData.getExpression(),
                emailRequestData.getInputs()
        );
        var emailDataPayload = new EmailData()
                .email(email);
        return responseEntityUtil.created(emailDataPayload);
    }

}
