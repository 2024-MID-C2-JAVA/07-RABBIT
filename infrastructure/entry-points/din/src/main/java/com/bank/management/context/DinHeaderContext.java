package com.bank.management.context;

import com.bank.management.data.DinHeader;

public class DinHeaderContext {
    private static final ThreadLocal<DinHeader> context = new ThreadLocal<>();

    public static void setDinHeader(DinHeader dinHeader) {
        context.set(dinHeader);
    }

    public static DinHeader getDinHeader() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }
}
