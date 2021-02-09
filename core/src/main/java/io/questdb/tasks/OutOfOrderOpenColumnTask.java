/*******************************************************************************
 *     ___                  _   ____  ____
 *    / _ \ _   _  ___  ___| |_|  _ \| __ )
 *   | | | | | | |/ _ \/ __| __| | | |  _ \
 *   | |_| | |_| |  __/\__ \ |_| |_| | |_) |
 *    \__\_\\__,_|\___||___/\__|____/|____/
 *
 *  Copyright (c) 2014-2019 Appsicle
 *  Copyright (c) 2019-2020 QuestDB
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 ******************************************************************************/

package io.questdb.tasks;

import io.questdb.cairo.TableWriter;
import io.questdb.mp.SOUnboundedCountDownLatch;
import io.questdb.std.FilesFacade;

import java.util.concurrent.atomic.AtomicInteger;

public class OutOfOrderOpenColumnTask {
    private CharSequence pathToTable;
    private FilesFacade ff;
    private AtomicInteger columnCounter;
    private long txn;
    private int openColumnMode;
    private CharSequence columnName;
    private int columnType;
    private boolean isIndexed;
    private long srcTimestampFd;
    private long srcTimestampAddr;
    private long srcTimestampSize;
    private long timestampMergeIndexAddr;
    private long srcOooFixAddr;
    private long srcOooFixSize;
    private long srcOooVarAddr;
    private long srcOooVarSize;
    private long srcDataMax;
    private long tableFloorOfMaxTimestamp;
    private long dataTimestampHi;
    private long srcOooLo;
    private long srcOooHi;
    private long srcOooMax;
    private long oooTimestampMin;
    private long oooTimestampMax;
    private long oooTimestampLo;
    private long oooTimestampHi;
    private int prefixType;
    private long prefixLo;
    private long prefixHi;
    private int mergeType;
    private long mergeDataLo;
    private long mergeDataHi;
    private long mergeOOOLo;
    private long mergeOOOHi;
    private int suffixType;
    private long suffixLo;
    private long suffixHi;
    private long activeFixFd;
    private long activeVarFd;
    private long activeTop;
    private TableWriter tableWriter;
    private SOUnboundedCountDownLatch doneLatch;

    public long getActiveFixFd() {
        return activeFixFd;
    }

    public long getActiveTop() {
        return activeTop;
    }

    public long getActiveVarFd() {
        return activeVarFd;
    }

    public AtomicInteger getColumnCounter() {
        return columnCounter;
    }

    public CharSequence getColumnName() {
        return columnName;
    }

    public int getColumnType() {
        return columnType;
    }

    public long getDataTimestampHi() {
        return dataTimestampHi;
    }

    public SOUnboundedCountDownLatch getDoneLatch() {
        return doneLatch;
    }

    public FilesFacade getFf() {
        return ff;
    }

    public long getMergeDataHi() {
        return mergeDataHi;
    }

    public long getMergeDataLo() {
        return mergeDataLo;
    }

    public long getMergeOOOHi() {
        return mergeOOOHi;
    }

    public long getMergeOOOLo() {
        return mergeOOOLo;
    }

    public int getMergeType() {
        return mergeType;
    }

    public long getOooTimestampHi() {
        return oooTimestampHi;
    }

    public long getOooTimestampLo() {
        return oooTimestampLo;
    }

    public long getOooTimestampMax() {
        return oooTimestampMax;
    }

    public long getOooTimestampMin() {
        return oooTimestampMin;
    }

    public int getOpenColumnMode() {
        return openColumnMode;
    }

    public CharSequence getPathToTable() {
        return pathToTable;
    }

    public long getPrefixHi() {
        return prefixHi;
    }

    public long getPrefixLo() {
        return prefixLo;
    }

    public int getPrefixType() {
        return prefixType;
    }

    public long getSrcDataMax() {
        return srcDataMax;
    }

    public long getSrcOooFixAddr() {
        return srcOooFixAddr;
    }

    public long getSrcOooFixSize() {
        return srcOooFixSize;
    }

    public long getSrcOooHi() {
        return srcOooHi;
    }

    public long getSrcOooLo() {
        return srcOooLo;
    }

    public long getSrcOooMax() {
        return srcOooMax;
    }

    public long getSrcOooVarAddr() {
        return srcOooVarAddr;
    }

    public long getSrcOooVarSize() {
        return srcOooVarSize;
    }

    public long getSrcTimestampAddr() {
        return srcTimestampAddr;
    }

    public long getSrcTimestampFd() {
        return srcTimestampFd;
    }

    public long getSrcTimestampSize() {
        return srcTimestampSize;
    }

    public long getSuffixHi() {
        return suffixHi;
    }

    public long getSuffixLo() {
        return suffixLo;
    }

    public int getSuffixType() {
        return suffixType;
    }

    public long getTableFloorOfMaxTimestamp() {
        return tableFloorOfMaxTimestamp;
    }

    public TableWriter getTableWriter() {
        return tableWriter;
    }

    public long getTimestampMergeIndexAddr() {
        return timestampMergeIndexAddr;
    }

    public long getTxn() {
        return txn;
    }

    public boolean isIndexed() {
        return isIndexed;
    }

    public void of(
            int openColumnMode,
            FilesFacade ff,
            CharSequence pathToTable,
            CharSequence columnName,
            AtomicInteger columnCounter,
            int columnType,
            long timestampMergeIndexAddr,
            long srcOooFixAddr,
            long srcOooFixSize,
            long srcOooVarAddr,
            long srcOooVarSize,
            long srcOooLo,
            long srcOooHi,
            long srcOooMax,
            long oooTimestampMin,
            long oooTimestampMax,
            long oooTimestampLo,
            long oooTimestampHi,
            long srcDataMax,
            long tableFloorOfMaxTimestamp,
            long dataTimestampHi,
            long txn,
            int prefixType,
            long prefixLo,
            long prefixHi,
            int mergeType,
            long mergeDataLo,
            long mergeDataHi,
            long mergeOOOLo,
            long mergeOOOHi,
            int suffixType,
            long suffixLo,
            long suffixHi,
            long srcTimestampFd,
            long srcTimestampAddr,
            long srcTimestampSize,
            boolean isIndexed,
            long activeFixFd,
            long activeVarFd,
            long activeTop,
            TableWriter tableWriter,
            SOUnboundedCountDownLatch doneLatch
    ) {
        this.openColumnMode = openColumnMode;
        this.ff = ff;
        this.pathToTable = pathToTable;
        this.columnCounter = columnCounter;
        this.columnName = columnName;
        this.columnType = columnType;
        this.timestampMergeIndexAddr = timestampMergeIndexAddr;
        this.srcOooFixAddr = srcOooFixAddr;
        this.srcOooFixSize = srcOooFixSize;
        this.srcOooVarAddr = srcOooVarAddr;
        this.srcOooVarSize = srcOooVarSize;
        this.srcOooLo = srcOooLo;
        this.srcOooHi = srcOooHi;
        this.srcOooMax = srcOooMax;
        this.oooTimestampMin = oooTimestampMin;
        this.oooTimestampMax = oooTimestampMax;
        this.oooTimestampLo = oooTimestampLo;
        this.oooTimestampHi = oooTimestampHi;
        this.srcDataMax = srcDataMax;
        this.tableFloorOfMaxTimestamp = tableFloorOfMaxTimestamp;
        this.dataTimestampHi = dataTimestampHi;
        this.txn = txn;
        this.prefixType = prefixType;
        this.prefixLo = prefixLo;
        this.prefixHi = prefixHi;
        this.mergeType = mergeType;
        this.mergeDataLo = mergeDataLo;
        this.mergeDataHi = mergeDataHi;
        this.mergeOOOLo = mergeOOOLo;
        this.mergeOOOHi = mergeOOOHi;
        this.suffixType = suffixType;
        this.suffixLo = suffixLo;
        this.suffixHi = suffixHi;
        this.srcTimestampFd = srcTimestampFd;
        this.srcTimestampAddr = srcTimestampAddr;
        this.srcTimestampSize = srcTimestampSize;
        this.isIndexed = isIndexed;
        this.activeFixFd = activeFixFd;
        this.activeVarFd = activeVarFd;
        this.activeTop = activeTop;
        this.tableWriter = tableWriter;
        this.doneLatch = doneLatch;
    }
}
