package com.bank.management.gateway;

import com.bank.management.Log;
import com.bank.management.LogTransaction;

import java.util.List;

public interface LogRepository {
    void saveLogSuccess(LogTransaction trx);
    void saveLogError(String errorMessage);
    Log getLog();
}
