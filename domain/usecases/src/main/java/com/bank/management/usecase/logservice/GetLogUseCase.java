package com.bank.management.usecase.logservice;

import com.bank.management.gateway.LogRepository;
import com.bank.management.Log;

public class GetLogUseCase {

    private final LogRepository logRepository;

    public GetLogUseCase(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public Log apply() {
        return logRepository.getLog();
    }
}
