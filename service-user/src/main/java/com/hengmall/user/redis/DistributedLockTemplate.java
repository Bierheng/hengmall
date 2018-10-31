package com.hengmall.user.redis;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁操作模板
 */
public interface DistributedLockTemplate {
    /**
     * 使用分布式锁，使用锁默认超时时间。
     *
     * @param callback
     * @return
     */
    public <T> T lock(DistributedLockCallback<T> callback);

    /**
     * 使用分布式锁。自定义锁的超时时间
     *
     * @param callback
     * @param leaseTime 锁超时时间。超时后自动释放锁。
     * @param timeUnit
     * @return
     */
    public <T> T lock(DistributedLockCallback<T> callback, long leaseTime, TimeUnit timeUnit);
}
