package com.cp.mylibrary.db;

import android.content.Context;

import org.kymjs.kjframe.KJDB;
import org.kymjs.kjframe.database.DaoConfig;

/**
 * Created by Jerry on 2016/7/6.
 */
public class MyDbUtil {



    public KJDB getKJDB(Context context,int version ,String dbName) {

        DaoConfig daoConfig = new DaoConfig();

        daoConfig.setDbVersion(version);
        daoConfig.setDbName(dbName);
        daoConfig.setContext(context);

        KJDB kjdb = KJDB.create(daoConfig);



        return kjdb;

    }



}
