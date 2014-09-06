package jp.co.biglobe.isp.mobile.ltethreeg.datasource.ltethreegengagement.db

import jp.co.biglobe.test.util.dbunit.xml.Fixture
import jp.co.biglobe.test.util.dbunit.xml.FixtureChangeTarget
import org.joda.time.DateTime

class FixtureLteContractInfo {

    private final static String tableName = "LTE_CONTRACT_INFO";


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
                        1 : LteContractInfoInitialData.firstRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

        public static Map 申込中(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE4"), "10")
//            map.put(new FixtureChangeTarget(tableName, 1, "INDEX_FREE_DATE4"), "")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map プラン変更予約中(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE11"), "LTA00001")
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE12"), "LTA00003")
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE72"), new DateTime().plusMonths(1).toString("yyyyMMdd"))
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map プラン変更済み(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE11"), "LTA00001")
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE12"), "LTA00003")
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE72"), new DateTime().plusMonths(-1).toString("yyyyMMdd"))
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map キャンセル済み(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE4"), "80")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map 契約済み(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE4"), "50")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map スタンダードで契約済み(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE4"), "50")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map ライトMで契約済み(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE4"), "50")
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE12"), "LTA00003")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map ライトSで契約済み(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE4"), "50")
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE12"), "LTA00004")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map ライトSSで契約済み(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE4"), "50")
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE12"), "LTA00005")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map 契約済みでライトSSからスタンダードに変更予約中(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE11"), "LTA00005")
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE12"), "LTA00001")
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE72"), new DateTime().plusMonths(1).toString("yyyyMMdd"))
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map 契約済みでスタンダードからライトSSに変更予約中(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE11"), "LTA00001")
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE12"), "LTA00005")
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE72"), new DateTime().plusMonths(1).toString("yyyyMMdd"))
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map エントリで契約済み(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE4"), "50")
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE12"), "LTA00006")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map スタートで契約済み(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE4"), "50")
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE12"), "LTA00007")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map 解約済み(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE4"), "90")
            map.put(new FixtureChangeTarget(tableName, 1, "INDEX_FREE_DATE4"), "2001-01-01 00:00:00")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map 解約予約(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE4"), "90")
            map.put(new FixtureChangeTarget(tableName, 1, "INDEX_FREE_DATE4"), "2999-12-31 23:59:59")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map 通信制限を免除(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE91"), "1")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map 通信制限を免除しない_値はnull(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE91"), null)
            return Fixture.changeValueListForString(getDefaultData(), map)
        }

        public static Map 通信制限を免除しない_値はnull以外(){
            Map<FixtureChangeTarget, String> map = new HashMap<FixtureChangeTarget, String>()
            map.put(new FixtureChangeTarget(tableName, 1, "ITEM_FREE91"), "3")
            return Fixture.changeValueListForString(getDefaultData(), map)
        }


    }

    public static class Two{

        private final static Map value = [
                (tableName) :  [
                        1 : LteContractInfoInitialData.firstRegistration,
                        2 : LteContractInfoInitialData.secondRegistration
                ]
        ]

        public static Map getDefaultData(){
            return value
        }

    }

}
