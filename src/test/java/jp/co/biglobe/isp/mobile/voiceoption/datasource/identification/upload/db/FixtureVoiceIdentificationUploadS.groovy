package jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.upload.db

import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget
import org.joda.time.DateTime

class FixtureVoiceIdentificationUploadS {

    private final static String tableName = "voice_identification_upload_s";


    public static class Nothing{

        private final static Map value = [
                (tableName) : [
                        1 : [],
                ]
        ]

        public static Map getDefaultData(){
            return value
        }
    }


    public static class One{

        private final static Map value = [
                (tableName) :  [
                        1 : IdentificationUploadInitialData.firstRegistration
                ]
        ]

        /**
         * デフォルト
         * （今日の日付、０回）
         */
        public static Map getDefaultData(){
            return value
        }

        /**
         * 本人確認書類の初回アップロード済み
         */
        public static Map getForFirstUploaded(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "first_upload_date_time"), new DateTime().toString("yyyy-MM-dd 00:00:00"))
            map.put(new FixtureChangeTarget(tableName, 1, "upload_count"), 1)
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 本人確認書類の２回目アップロード済み
         */
        public static Map getForSecondUploaded(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "first_upload_date_time"), new DateTime().toString("yyyy-MM-dd 00:00:00"))
            map.put(new FixtureChangeTarget(tableName, 1, "upload_count"), 2)
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 本人確認書類の３回目アップロード済み
         */
        public static Map getForMaxUploadCount(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "first_upload_date_time"), new DateTime().toString("yyyy-MM-dd 00:00:00"))
            map.put(new FixtureChangeTarget(tableName, 1, "upload_count"), 3)
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 本人確認書類のアップロードが期限切れ直前（初回アップロード日時）
         * 　利用する場合はテスト側で現在時刻を固定化すること
         * 　固定化した時刻から「制限時間(3時間)の直前(5分前)」となる時刻をセット
         */
        public static Map getForInDeadLineUpload(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "first_upload_date_time"), new DateTime().minusHours(3).plusMinutes(5).toString("yyyy-MM-dd HH:mm:00"))
            map.put(new FixtureChangeTarget(tableName, 1, "upload_count"), 1)
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        /**
         * 本人確認書類のアップロードが期限切れ（初回アップロード日時）
         * 　利用する場合はテスト側で現在時刻を固定化すること
         * 　固定化した時刻から「制限時間(3時間)の直後(5分後)」となる時刻をセット
         */
        public static Map getForExpirationUpload(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "first_upload_date_time"), new DateTime().minusHours(3).minusMinutes(5).toString("yyyy-MM-dd HH:mm:00"))
            map.put(new FixtureChangeTarget(tableName, 1, "upload_count"), 1)
            return Fixture.changeValueListForString(getDefaultData(), map)
        }
    }

}
