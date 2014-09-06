package jp.co.biglobe.isp.mobile.extension.domain;

public interface CommonValidEntity<V> extends CommonEntity {
    /**
     * データ変更時に、Update でなく、Delete & Insert する必要があるか、判定する（外部参照キーが違えば true）
     */
    public boolean requiredDeleteAndInsert(V validEntity);

    /**
     * 子エンティティを除く値が同じか
     */
    public boolean equalsExcludeChild(V validEntity);
}
