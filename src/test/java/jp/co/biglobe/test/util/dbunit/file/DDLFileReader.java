package jp.co.biglobe.test.util.dbunit.file;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class DDLFileReader {

    private final List<SQLFile> files;

    public List<SQLFile> getFiles() {
        return files;
    }

    public DDLFileReader(String ddlPathDir) {
        this.files = new LinkedList<>();
        setFiles(ddlPathDir);
    }

    /**
     * 対象ディレクトリのファイルリストを取得
     * @param ddlPathDir
     */
    private void setFiles(String ddlPathDir) {
        File dir = new File(ddlPathDir);
        File[] filelist = dir.listFiles();
        if(filelist == null)return;
        for (int i = 0; i < filelist.length; i++) {
            files.add(new SQLFile(filelist[i]));
        }
    }
}
