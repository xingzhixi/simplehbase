package com.alipay.simplehbase.myrecord;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;

import org.junit.Before;
import org.junit.BeforeClass;

import com.alipay.simplehbase.client.SimpleHbaseAdminClient;
import com.alipay.simplehbase.client.SimpleHbaseClient;
import com.alipay.simplehbase.client.rowkey.RowKeyUtil;

import com.alipay.simplehbase.config.Config;
import com.alipay.simplehbase.config.HBaseTableConfigParser;

import com.alipay.simplehbase.hql.HBaseQuery;
import com.alipay.simplehbase.literal.LiteralValueInterpreter;


/**
 * @author xinzhi
 */
public class MyRecordTestBase {

    protected static Log                    log       = LogFactory
                                                              .getLog(MyRecordTestBase.class);
    protected static SimpleHbaseClient      simpleHbaseClient;
    protected static SimpleHbaseAdminClient simpleHbaseAdminClient;
    protected static String                 TestHqlId = "TestHqlId";

    static {
        simpleHbaseClient = Config.getSimpleHbaseClient();
        simpleHbaseAdminClient = Config.getSimpleHbaseAdminClient();
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        Config.beforeClass();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        Config.afterClass();
    }

    @Before
    public void before() {
        deleteRecords();
    }

    @After
    public void after() {
        deleteRecords();
    }

    private void deleteRecords() {
        simpleHbaseClient.deleteObjectList(RowKeyUtil.START_ROW,
                RowKeyUtil.END_ROW);
    }

    protected void addHql(String hql) {
        List<HBaseQuery> hbaseQueryList = HBaseTableConfigParser
                .parseHBaseQueryWithRawHQL(hql, TestHqlId);
        simpleHbaseClient.getHbaseTableConfig().addHBaseQueryList(
                hbaseQueryList);
    }

    protected MyRecord mockSlim(int id) {
        return parseSlim("id=" + id + ",name=allen_" + id
                + ",date=2012-01-01,gender=MALE,version=0");
    }

    protected MyRecord[] mockSlims(int size) {
        MyRecord[] myRecords = new MyRecord[size];
        for (int i = 0; i < myRecords.length; i++) {
            myRecords[i] = mockSlim(i);
        }
        return myRecords;
    }

    protected void putMockSlims(int size) {
        MyRecord[] myRecords = mockSlims(size);
        for (int i = 0; i < myRecords.length; i++) {
            putRecord(mockSlim(i));
        }
    }

    protected void putSlim(String str) {
        MyRecord myRecord = parseSlim(str);
        MyRecordRowKey myRecordRowKey = new MyRecordRowKey(myRecord.getId());
        simpleHbaseClient.putObject(myRecordRowKey, myRecord);
    }

    protected MyRecord parseSlim(String str) {

        MyRecord record = new MyRecord();

        String[] parts = str.split("[=,]");

        for (int i = 0; i < parts.length; i += 2) {

            if (parts[i].equals("id")) {
                record.setId((Integer) (LiteralValueInterpreter
                        .convertToObject(int.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("name")) {
                record.setName(parts[i + 1]);
                continue;
            }

            if (parts[i].equals("date")) {
                record.setDate((Date) (LiteralValueInterpreter.convertToObject(
                        Date.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("gender")) {
                record.setGender((Gender) (LiteralValueInterpreter
                        .convertToObject(Gender.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("age")) {
                record.setAge((Long) (LiteralValueInterpreter.convertToObject(
                        long.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("version")) {
                record.setVersion((Long) (LiteralValueInterpreter
                        .convertToObject(long.class, parts[i + 1])));
                continue;
            }

        }

        return record;
    }

    protected void putFat(String str) {
        MyFatRecord myRecord = parseFat(str);
        MyRecordRowKey myRecordRowKey = new MyRecordRowKey(myRecord.getId());
        simpleHbaseClient.putObject(myRecordRowKey, myRecord);
    }

    protected MyFatRecord parseFat(String str) {

        MyFatRecord record = new MyFatRecord();

        String[] parts = str.split("[=,]");

        for (int i = 0; i < parts.length; i += 2) {

            if (parts[i].equals("id")) {
                record.setId((Integer) (LiteralValueInterpreter
                        .convertToObject(int.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("name")) {
                record.setName(parts[i + 1]);
                continue;
            }

            if (parts[i].equals("date")) {
                record.setDate((Date) (LiteralValueInterpreter.convertToObject(
                        Date.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("gender")) {
                record.setGender((Gender) (LiteralValueInterpreter
                        .convertToObject(Gender.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("age")) {
                record.setAge((Long) (LiteralValueInterpreter.convertToObject(
                        long.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("version")) {
                record.setVersion((Long) (LiteralValueInterpreter
                        .convertToObject(long.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("fatid")) {
                record.setFatid((Integer) (LiteralValueInterpreter
                        .convertToObject(int.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("fatname")) {
                record.setFatname(parts[i + 1]);
                continue;
            }

            if (parts[i].equals("fatdate")) {
                record.setFatdate((Date) (LiteralValueInterpreter
                        .convertToObject(Date.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("fatgender")) {
                record.setFatgender((Gender) (LiteralValueInterpreter
                        .convertToObject(Gender.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("fatage")) {
                record.setFatage((Long) (LiteralValueInterpreter
                        .convertToObject(long.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("fatversion")) {
                record.setFatversion((Long) (LiteralValueInterpreter
                        .convertToObject(long.class, parts[i + 1])));
                continue;
            }

        }

        return record;
    }

    protected void putRecord(MyRecord myRecord) {
        MyRecordRowKey myRecordRowKey = new MyRecordRowKey(myRecord.getId());
        simpleHbaseClient.putObject(myRecordRowKey, myRecord);
    }

    protected void putRecord(MyFatRecord myFatRecord) {
        MyRecordRowKey myRecordRowKey = new MyRecordRowKey(myFatRecord.getId());
        simpleHbaseClient.putObject(myRecordRowKey, myFatRecord);
    }

    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }
}
