package jp.co.biglobe.isp.mobile.extension.datasource;

import jp.co.biglobe.isp.mobile.extension.domain.CommonEntity;
import jp.co.biglobe.isp.mobile.extension.domain.CommonValidEntity;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.lib.plugin.event.RequestEventProcess;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @param <E> エンティティ（インターフェース）
 * @param <V> データが存在しているエンティティ
 */
@Repository
public abstract class BaseRepositoryDb<E extends CommonEntity, V extends CommonValidEntity<V>> {

    @Autowired
    protected CommonRegisterMapper<V> queryMapper;

    @Autowired
    private RequestEventTime requestEventTime;

    @Autowired
    private RequestEventProcess requestEventProcess;

    /**
     * 追加する（Entity）
     */
    public void insert(E after) {
        if (after.isExist() == false) {
            return;
        }
        insert((V) after);
    }

    /**
     * 追加する（ValidEntity）
     */
    public void insert(V after) {
        databaseInsert(after);

        // 子エンティティがあれば、ここで追加する
        childInsert(after);
    }

    /**
     * 子エンティティを追加する
     */
    protected abstract void childInsert(V after);

    /**
     * 削除する（Entity）
     */
    public void delete(E before) {
        if (before.isExist() == false) {
            return;
        }
        delete((V) before);
    }

    /**
     * 削除する（ValidEntity）
     */
    public void delete(V before) {
        // 子エンティティがあれば、親エンティティの削除に先立って削除する
        childDelete(before);
        databaseDelete(before);
    }

    /**
     * 親エンティティから呼び出されて削除する（削除された場合は true を返す）
     */
    public boolean delete(E before, E after) {
        // before が無ければ、削除できない
        if (before.isExist() == false) {
            return false;
        }
        // before があって、after がなければ削除する
        if (after.isExist() == false) {
            delete((V) before);
            return true;
        }
        // 更新前後を比較して、削除の必要があれば削除する
        if (((V) before).requiredDeleteAndInsert((V) after)) {
            delete((V) before);
            return true;
        }

        // 子エンティティがあれば、必要に応じて削除する
        return childDelete((V) before, (V) after);
    }

    /**
     * 子エンティティを削除する
     */
    protected abstract void childDelete(V before);

    /**
     * 子エンティティを削除する（削除された場合は true を返す）
     */
    protected abstract boolean childDelete(V before, V after);

    /**
     * 更新する（ValidEntity）
     */
    public void update(V after) {
        // 更新後のプライマリキーで、更新前のエンティティを取得する
        V before = databaseSelect(after);

        // 参照整合の関係により、先に削除が必要な子エンティティは、ここで削除する
        boolean deleted = childDelete(before, after);
        if (deleted) {
            before = databaseSelect(after);
        }

        update(before, after);
    }

    /**
     * 更新する（ValidEntity → ValidEntity）
     */
    private void update(V before, V after) {
        // 更新前後を比較（子エンティティを除く）して不一致なら更新する
        if (before.equalsExcludeChild(after) == false) {
            // 更新前後を比較して、更新ではなく、削除＆挿入が必要か、判定する
            if (before.requiredDeleteAndInsert(after)) {
                databaseDelete(before);
                databaseInsert(after);
            } else {
                databaseUpdate(before, after);
            }
        }

        // 子エンティティを追加・更新する
        childInsertOrUpdate(before, after);
    }

    /**
     * 親エンティティから呼び出されて子エンティティを追加・更新する
     */
    public void insertOrUpdate(E before, E after) {
        if (after.isExist() == false) {
            return;
        }
        if (before.isExist()) {
            update((V) before, (V) after);
            return;
        }

        insert((V) after);
    }

    /**
     * 子エンティティを追加・更新する
     */
    protected abstract void childInsertOrUpdate(V before, V after);

    /**
     * Select
     */
    private V databaseSelect(V before) {
        V newBefore = queryMapper.selectState(before);
        if (newBefore == null) {
            throw new SystemCheckException(
                    "データ不整合が発生しています。更新前データの検索ができませんでした。", VoiceOptionAlarmIdentifier.DEFAULT
            );
        }
        return newBefore;
    }

    /**
     * Insert
     */
    private void databaseInsert(V after) {
        queryMapper.insertEvent(
                EventType.INSERT,
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                after
        );
        queryMapper.insertState(after);
    }

    /**
     * Update
     */
    private void databaseUpdate(V before, V after) {
        queryMapper.insertEvent(
                EventType.UPDATE,
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                after
        );
        int c = queryMapper.updateState(before, after);
        if (c != 1) {
            throw new SystemCheckException(
                    "データ不整合が発生しています。データ更新が " + c + " 件でした。", VoiceOptionAlarmIdentifier.DEFAULT
            );
        }
    }

    /**
     * Delete
     */
    private void databaseDelete(V before) {
        queryMapper.insertEvent(
                EventType.DELETE,
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                before
        );
        int c = queryMapper.deleteState(before);
        if (c != 1) {
            throw new SystemCheckException(
                    "データ不整合が発生しています。データ削除が " + c + " 件でした。", VoiceOptionAlarmIdentifier.DEFAULT
            );
        }
    }
}
