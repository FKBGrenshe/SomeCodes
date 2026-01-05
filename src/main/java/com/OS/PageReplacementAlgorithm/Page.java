package com.OS.PageReplacementAlgorithm;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-01-04
 * @Description: 页面
 */
// Page.java
public final class Page {
    private final int pageId;

    // ---- writeback simulation ----
    private boolean dirty;

    // ---- for replacement algorithms ----
    private long lastAccessTime;   // LRU / generic timestamp
    private int frequency;         // LFU
    private boolean refBit;        // Clock (Second-Chance)

    public Page(int pageId) {
        this.pageId = pageId;
        this.dirty = false;
        this.lastAccessTime = 0L;
        this.frequency = 0;
        this.refBit = false;
    }

    public int getPageId() {
        return pageId;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void markDirty() {
        this.dirty = true;
    }

    public void clearDirty() {
        this.dirty = false;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public int getFrequency() {
        return frequency;
    }

    public void incFrequency() {
        this.frequency++;
    }

    public void resetFrequency() {
        this.frequency = 0;
    }

    public boolean getRefBit() {
        return refBit;
    }

    public void setRefBit(boolean refBit) {
        this.refBit = refBit;
    }

    @Override
    public String toString() {
        return "Page{id=" + pageId +
                ", dirty=" + dirty +
                ", last=" + lastAccessTime +
                ", freq=" + frequency +
                ", ref=" + refBit +
                "}";
    }
}

