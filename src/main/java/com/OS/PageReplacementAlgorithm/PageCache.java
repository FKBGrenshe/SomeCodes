package com.OS.PageReplacementAlgorithm;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-01-04
 * @Description: 页面缓存容器
 */
public class PageCache {


    /**
     * Replacement Policy SPI
     * u can implement FIFO/LRU/Clock/LFU by implementing this interface
     */
    public interface ReplacementPolicy{
        // called when a page is hit (already resident)
        /**
         * 访问命中 - page 已在 resident之后，策略得到通知，用于更新策略状态
         * @param page
         * @param now
         */
        void onHit(Page page, long now);

        // called after a new page is loaded into cache
        /**
         * 缺页后新页成功装入resident，策略得到通知
         * @param page
         * @param now
         */
        void onLoad(Page page, long now);

        // called right before a page is removed from cache
        /**
         * victim即将被从resident移除之前，策略得到通知做内部状态清理
         * @param victim
         * @param now
         */
        void onEvict(Page victim, long now);

        // pick a victim to evict when cache is full
        // must retuan a pageid that currently exists in resident set
        /**
         * 当缓存满的时候，让策略从当前resident中选出一个应淘汰的victim pageId
         * @param resident
         * @param now
         * @return
         */
        int pickVictim(Map<Integer, Page> resident, long now);
    }





    // ---- config ----
    private final int capacity;
    private final ReplacementPolicy policy;
    // ---- state ----
    private final Map<Integer, Page> resident;
    private long now;
    // ---- metrics ----
    private long hits;
    private long faults;
    private long evictions;
    private long writeBacks;

    public PageCache(int capacity, ReplacementPolicy policy){
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        this.capacity = capacity;
        this.policy = Objects.requireNonNull(policy, "policy");
        this.resident = new HashMap<>(capacity * 2);
        this.now = 0L;
    }

    /**
     * 访问一个页面
     * @param pageId
     * @param isWrite
     * @return true 命中cache，false 发生页错误未找到
     */
    public boolean access(int pageId, boolean isWrite){
        now++;

        Page p = resident.get(pageId);
        if (p != null) {
            // hit
            hits++;
            touch(p,isWrite,now);
            policy.onHit(p,now);
            return true;
        }
        // ---- miss ---
        faults++;

        // need eviction?
        if (resident.size() >= capacity) {
            int victimId = policy.pickVictim(Collections.unmodifiableMap(resident), now);
            Page victim = resident.get(victimId);
            if (victim == null) {
                throw new IllegalStateException("Policy picked non-resident victimId=" + victimId);
            }
            // writeback if dirty
            if (victim.isDirty()){
                writeBacks++;
                // simulate: write back then mark clean
                victim.clearDirty();
            }
            policy.onEvict(victim,now);
            resident.remove(victimId);
            evictions++;
        }
        // load new page
        Page loaded = new Page(pageId);
        touch(loaded,isWrite,now);
        resident.put(pageId,loaded);
        policy.onLoad(loaded,now);

        return false;
    }

    private static void touch(Page p, boolean isWrite, long now){
        p.setLastAccessTime(now);
        p.setRefBit(true);  // for Clock-like policies
        p.incFrequency(); // for LFU-like policies
        if (isWrite) {
            p.markDirty();
        }
    }
    // ---- getters ----
    public int getCapacity() {
        return capacity;
    }

    public int size() {
        return resident.size();
    }

    public long getNow() {
        return now;
    }

    public long getHits() {
        return hits;
    }

    public long getFaults() {
        return faults;
    }

    public long getEvictions() {
        return evictions;
    }

    public long getWriteBacks() {
        return writeBacks;
    }

    public double hitRate() {
        long total = hits + faults;
        return total == 0 ? 0.0 : (double) hits / (double) total;
    }

    public Map<Integer, Page> snapshotResident() {
        return Collections.unmodifiableMap(resident);
    }

    @Override
    public String toString() {
        return "PageCache{cap=" + capacity +
                ", size=" + resident.size() +
                ", hits=" + hits +
                ", faults=" + faults +
                ", evictions=" + evictions +
                ", writeBacks=" + writeBacks +
                ", hitRate=" + String.format("%.4f", hitRate()) +
                "}";
    }

}
