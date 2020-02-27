package com.example.dell.beekeeping.DataBase;

import android.provider.BaseColumns;

public class hive_contract {

    public final static class  HiveEntris implements BaseColumns {
        public final static String HIVE_TABLE_NAME = "hiveTable";
        public static final String col_6 = " hiveNo ";
        public static final String col_7 = "noOfCombs";
        public static final String col_8 = "dateOfcolonisation";
        public static final String col_9 = "dateOfHarvest";
        public static final String col_10 = "noOfwax";
        public static final String col_11 = "quantityOfHoney";
        public static final String col_12 = "noOfPropolis";
        public static final String col_13 = "latitude";
        public static final String col_longitude = "longitude";
        public static final String col_timestamp = "timeStamp";
    }
}
