package com.bank.management.usecase.logservice;

import com.bank.management.Transaction;
import com.bank.management.gateway.LogRepository;

public class SaveLogUseCase {

    private final LogRepository logRepository;

    public SaveLogUseCase(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void apply(String trx) {
        logRepository.saveLog(trx);
    }
}
