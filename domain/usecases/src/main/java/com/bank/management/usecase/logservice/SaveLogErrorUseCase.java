package com.bank.management.usecase.logservice;

import com.bank.management.gateway.LogRepository;

public class SaveLogErrorUseCase {

    private final LogRepository logRepository;

    public SaveLogErrorUseCase(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void apply(String trx) {
        logRepository.saveLogError(trx);
    }
}
