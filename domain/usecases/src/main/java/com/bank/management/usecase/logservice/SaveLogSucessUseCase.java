package com.bank.management.usecase.logservice;

import com.bank.management.LogTransaction;
import com.bank.management.gateway.LogRepository;

public class SaveLogSucessUseCase {

    private final LogRepository logRepository;

    public SaveLogSucessUseCase(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void apply(LogTransaction trx) {
        logRepository.saveLogSuccess(trx);
    }
}
