/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.biglobe.test.util.usecase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class AspFunctionCallUseCase {

    private String BOBIO_SNR_BASE = System.getProperty("user.dir") + "/etc/script/local/";
    private final String ASP_FUNCTION_CALL_MEM_LEGM = "AspFunctionCall_mem_legm";

    public void set() throws IOException {
        setAspFunctionCall("Remote");
    }

    public void unset() throws IOException {
        setAspFunctionCall("Local");
    }

    /**
     * UCに該当するファイルを設置する
     *  @param usecase
     **/
    private void setAspFunctionCall(String usecase) throws IOException {

        //UCの結果ファイル
        String UseCaseFile = BOBIO_SNR_BASE + ASP_FUNCTION_CALL_MEM_LEGM + "_" + usecase + ".sh";
        FileChannel UseCaseChannel = getInputChannel(UseCaseFile);

        //実行される結果ファイル
        String CallFile = BOBIO_SNR_BASE + ASP_FUNCTION_CALL_MEM_LEGM + ".sh";
        FileChannel callChannel = getOutputChannel(CallFile);
             
        //ファイルの置き換え(UCファイル⇒実行ファイル)
        try {
            UseCaseChannel.transferTo(0, UseCaseChannel.size(), callChannel);
        } finally {
            UseCaseChannel.close();
            callChannel.close();
        }        
    }

    /**
     * 入力先ファイルのチャネルを取得する
     **/
    private FileChannel getInputChannel(String TargetFile) throws FileNotFoundException {
        FileInputStream UseCaseStream = new FileInputStream(TargetFile);
        return UseCaseStream.getChannel();
    }

    /**
     * 出力先ファイルのチャネルを取得する
     **/
    private FileChannel getOutputChannel(String TargetFile) throws FileNotFoundException {
        FileOutputStream UseCaseStream = new FileOutputStream(TargetFile);
        return UseCaseStream.getChannel();
    }    
  
}
