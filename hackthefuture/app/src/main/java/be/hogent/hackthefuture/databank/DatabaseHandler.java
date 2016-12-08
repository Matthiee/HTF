package be.hogent.hackthefuture.databank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import be.hogent.hackthefuture.domein.Photo;
import be.hogent.hackthefuture.domein.Sample;

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

        onCreate(db);
    }

    public void addPhoto(Photo ph) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PHOTOS_NAME, ph.getName());
        values.put(KEY_PHOTOS_REMARK, ph.getRemark());
        values.put(KEY_PHOTOS_VALUE, ph.getValue());
        values.put(KEY_PHOTOS_DATETIME, ph.getDatetime());
        values.put(KEY_PHOTOS_RESEARCHER, ph.getResearcher());

        db.insert(TABLE_PHOTOS, null, values);
        db.close();
    }

    public void addSample(Sample sam) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SAMPLES_NAME, sam.getName());
        values.put(KEY_SAMPLES_REMARK, sam.getRemark());
        values.put(KEY_SAMPLES_VALUE, sam.getValue());
        values.put(KEY_SAMPLES_DATETIME, sam.getDatetime());
        values.put(KEY_SAMPLES_RESEARCHER, sam.getResearcher());

        db.insert(TABLE_SAMPLES, null, values);
        db.close();
    }

    public List<Photo> getPhotos()
    {
        List<Photo> photos = new ArrayList<Photo>();
        String selectQuery = "SELECT  * FROM " + TABLE_PHOTOS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Photo ph = new Photo();
                ph.setName(cursor.getString(1));
                ph.setValue(cursor.getString(2));
                ph.setRemark(cursor.getString(2));
                ph.setDatetime(cursor.getString(2));
                ph.setResearcher(cursor.getString(2));

                photos.add(ph);
            } while (cursor.moveToNext());
        }
        return photos;
    }

    public List<Sample> getSamples()
    {
        List<Sample> samples = new ArrayList<Sample>();
        String selectQuery = "SELECT  * FROM " + TABLE_SAMPLES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Sample sam = new Sample();
                sam.setName(cursor.getString(1));
                sam.setValue(cursor.getString(2));
                sam.setRemark(cursor.getString(2));
                sam.setDatetime(cursor.getString(2));
                sam.setResearcher(cursor.getString(2));

                samples.add(sam);
            } while (cursor.moveToNext());
        }
        return samples;
    }
}
