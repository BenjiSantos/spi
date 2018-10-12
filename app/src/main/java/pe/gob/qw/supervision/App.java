package pe.gob.qw.supervision;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

public class App extends Application {
    public static final boolean ENCRYPTED = true;

    //private DaoSession daoSession;
    //private DaoSession aw;

    @Override
    public void onCreate() {
        super.onCreate();
        /*
        DatabaseUpgradeHelper helper = new DatabaseUpgradeHelper(this, ENCRYPTED ? "qwiiee-e" : "qwiiee");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("qw11ee") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();*/
    }
/*
    public DaoSession getDaoSession() {
        return daoSession;
    }
*/
}