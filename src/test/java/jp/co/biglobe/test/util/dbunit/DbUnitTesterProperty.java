/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.co.biglobe.test.util.dbunit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DbUnitTesterProperty {

    @Value("${jdbc.dbName}")
    private String dbName;
    @Value("${jdbc.ddl.createTablePath}")
    private String createTableDDLPath;
    @Value("${jdbc.ddl.createIndexPath}")
    private String createIndexDDLPath;
    @Value("${jdbc.ddl.createSequencePath}")
    private String createSequenceDDLPath;
    @Value("${jdbc.ddl.alterTablePath}")
    private String alterTableDDLPath;
    @Value("${jdbc.ddl.tableSpaceDDLPath}")
    private String tableSpaceDDLPath;
    @Value("${jdbc.ddl.proceduresPath}")
    private String proceduresDDLPath;
    @Value("${jdbc.ddl.devCreateTablePath}")
    private String devCreateTableDDLPath;
    @Value("${jdbc.ddl.devCreateIndexPath}")
    private String devCreateIndexDDLPath;

    public String getDbName(){
        return dbName;
    }

    public String getCreateTableDDLPath() {
        return createTableDDLPath;
    }

    public String getCreateIndexDDLPath() {
        return createIndexDDLPath;
    }

    public String getCreateSequenceDDLPath() {
        return createSequenceDDLPath;
    }

    public String getAlterTableDDLPath() {
        return alterTableDDLPath;
    }

    public String getProceduresDDLPath() {
        return proceduresDDLPath;
    }

    public String getTableSpaceDDLPath() {
        return tableSpaceDDLPath;
    }

    public String getDevCreateTableDDLPath() {
        return devCreateTableDDLPath;
    }

    public String getDevCreateIndexDDLPath() {
        return devCreateIndexDDLPath;
    }
}
