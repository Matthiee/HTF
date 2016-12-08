package be.hogent.hackthefuture.databank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yannick on 8/12/2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "HTF";

    // Table names
    //private static final String TABLE_RESEARCHERS = "researchers";
    private static final String TABLE_PHOTOS = "photos";
    private static final String TABLE_SAMPLES = "samples";


    private static final String KEY_PHOTOS_ID = "id";
    private static final String KEY_PHOTOS_NAME = "name";
    private static final String KEY_PHOTOS_VALUE = "value";
    private static final String KEY_PHOTOS_REMARK = "remark";
    private static final String KEY_PHOTOS_DATETIME = "datetime";
    private static final String KEY_PHOTOS_RESEARCHER = "researcher";

    private static final String KEY_SAMPLES_ID = "id";
    private static final String KEY_SAMPLES_NAME = "name";
    private static final String KEY_SAMPLES_VALUE = "value";
    private static final String KEY_SAMPLES_REMARK = "remark";
    private static final String KEY_SAMPLES_DATETIME = "datetime";
    private static final String KEY_SAMPLES_RESEARCHER = "researcher";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PHOTOS_TABLE = "CREATE TABLE " + TABLE_PHOTOS + "("
                + KEY_PHOTOS_ID + " INTEGER PRIMARY KEY," + KEY_PHOTOS_NAME + " TEXT,"
                + KEY_PHOTOS_VALUE + " TEXT," + KEY_PHOTOS_REMARK + " TEXT,"
                + KEY_PHOTOS_DATETIME + " TEXT," + KEY_PHOTOS_RESEARCHER + " TEXT)";


        String CREATE_SAMPLES_TABLE = "CREATE TABLE " + TABLE_SAMPLES + "("
                + KEY_SAMPLES_ID + " INTEGER PRIMARY KEY," + KEY_SAMPLES_NAME + " TEXT,"
                + KEY_SAMPLES_VALUE + " TEXT," + KEY_SAMPLES_REMARK + " TEXT,"
                + KEY_SAMPLES_DATETIME + " TEXT," + KEY_SAMPLES_RESEARCHER + " TEXT)";



        //db.execSQL(CREATE_RESEARCHERS_TABLE);
        db.execSQL(CREATE_PHOTOS_TABLE);
        db.execSQL(CREATE_SAMPLES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESEARCHERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAMPLES);

        // Create tables again
        onCreate(db);
    }
}
