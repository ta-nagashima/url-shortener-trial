package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectperformance.db.typehandler;


import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlPolicy;
import jp.co.biglobe.lib.danger.charset.EucJpExtensionSingleton;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 既存テーブルからデータ取得時、enum型に変換したい場合に使用するTypeHandler。
 */
@MappedTypes(EnumConvertForDb.class)
public class ConnectControlPolicyTypeHandler extends BaseTypeHandler<ConnectControlPolicy> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ConnectControlPolicy e, JdbcType jdbcType) throws SQLException {
        String utf8String = e.getDbValue();
        if(utf8String == null) utf8String = "";

        byte[] b = utf8String.getBytes(EucJpExtensionSingleton.INSTANCE.getInstance());
        ps.setBytes(i, b);
    }

    @Override
    public ConnectControlPolicy getNullableResult(ResultSet rs, String columnName) throws SQLException {
        byte[] b = rs.getBytes(columnName);
        return ConnectControlPolicyConverter.convertForDb(eucToUtf8(b));
    }

    @Override
    public ConnectControlPolicy getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        byte[] b = rs.getBytes(columnIndex);
        return ConnectControlPolicyConverter.convertForDb(eucToUtf8(b));
    }

    @Override
    public ConnectControlPolicy getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        byte[] b = cs.getBytes(columnIndex);
        return ConnectControlPolicyConverter.convertForDb(eucToUtf8(b));
    }

    /**
     * EUC-JPのbyte配列からUTF-8のStringに変換する
     * @param b EUC-JPのbyte配列
     * @return UTF-8の文字列
     */
    private String eucToUtf8(byte[] b){
        if (b == null || b.length == 0) {
            return "";
        }
        return new String(b, EucJpExtensionSingleton.INSTANCE.getInstance());

    }

}
