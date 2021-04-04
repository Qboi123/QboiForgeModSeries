package com.qtech.forgemods.core.common.interfaces;

public interface IVersion extends Comparable<IVersion> {
    boolean isStable();
    String toString();
    String toLocalizedString();
}
