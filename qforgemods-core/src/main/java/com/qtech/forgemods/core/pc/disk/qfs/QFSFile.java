package com.qtech.forgemods.core.pc.disk.qfs;

import com.qtech.forgemods.core.common.IntSize;
import com.qtech.forgemods.core.pc.disk.AbstractFile;
import com.qtech.forgemods.core.pc.disk.Partition;

public class QFSFile extends AbstractFile {
    public QFSFile(Partition partition, String path) {
        super(partition, path);

        if (partition.getFileSystem() instanceof QFileSystem) {

        }
    }

    @Override
    public void rename(String to) {

    }

    @Override
    public void create(IntSize size) {

    }
}
