import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender

import static ch.qos.logback.classic.Level.ERROR

// ログの出力フォーマット
def LOG_FORMAT = "%d{yyyy/MM/dd HH:mm:ss.SSS} %-5p [%t] %c:%M\\(%F:%L\\) %p %m%n"

// ログファイルへのログ出力設定
appender("FILE", RollingFileAppender) {
    file = "build/tomcat/logs/test.log"
    encoder(PatternLayoutEncoder) {
        pattern = LOG_FORMAT
    }
}

// 標準出力へのログ出力設定
appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = LOG_FORMAT
    }
}

// ログレベル設定
root(ERROR, ["FILE", "STDOUT"])