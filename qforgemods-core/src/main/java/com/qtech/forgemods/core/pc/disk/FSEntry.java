package com.qtech.forgemods.core.pc.disk;

import com.qtech.forgemods.core.pc.IStream;

public interface FSEntry extends IStream {
    int length();
    void pos(int pos);
    int pos();
    byte read();
    void write(byte b);
}
