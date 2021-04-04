package com.qtech.forgemods.core.pc;

public abstract class BiosError {
    public boolean canContinue() {
        return false;
    }

    public abstract int getTicksBeforeAutoRestart();
}
