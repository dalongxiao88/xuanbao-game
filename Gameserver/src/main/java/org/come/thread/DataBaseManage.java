package org.come.thread;

/**
 * Redis 到数据库同步队列的最小抽象。
 * 当前仍保留 `add/del/upd/ClearList` 这一组历史方法名以兼容既有实现类，
 * 但外部控制层统一改用语义化默认方法，避免继续传播反编译式命名。
 */
public interface DataBaseManage {
    int BATCH_SIZE = 100;
    @Deprecated
    int a = BATCH_SIZE;

    /**
     * 兼容旧实现：将新增实体压入待同步队列。
     */
    void add(Object entity);

    /**
     * 兼容旧实现：将待删除主键压入待同步队列。
     */
    void del(Object primaryKey);

    /**
     * 兼容旧实现：将更新后的实体压入待同步队列。
     */
    void upd(Object entity);

    /**
     * 兼容旧实现：立即冲刷当前待同步队列。
     */
    void ClearList();

    default void queueInsert(Object entity) {
        add(entity);
    }

    default void queueDelete(Object primaryKey) {
        del(primaryKey);
    }

    default void queueUpdate(Object entity) {
        upd(entity);
    }

    default void flushPendingChanges() {
        ClearList();
    }
}
