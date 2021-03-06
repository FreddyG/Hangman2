package com.example.hangman;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


//deze class specifiseert de database naam en de versie

public class MySQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_COMMENTS = "locations";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_LOCATION = "location";
  public static final String COLUMN_ADDRESS = "address";

  private static final String DATABASE_NAME = "highscores.db";
  private static final int DATABASE_VERSION = 1;

  // onCreate string
  private static final String DATABASE_CREATE = "create table "
      + TABLE_COMMENTS + "(" + COLUMN_ID
      + " integer primary key autoincrement, " + COLUMN_LOCATION
      + " text not null, " + COLUMN_ADDRESS + " text not null);";

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }
  
  
  //Override de onCreate methode en de onUpgrade methode, bij onUpgrade wordt
  //gekozen om de huidige database te behouden en bij te werken
  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
    onCreate(db);
  }

} 


