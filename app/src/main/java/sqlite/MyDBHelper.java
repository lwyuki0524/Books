package sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {

    private static String DATABASE_TABLE = "accounting_TABLE";

    // 資料庫名稱
    public static final String DATABASE_NAME = "mybook.db";
    // 資料庫版本，資料結構改變的時候要更改這個數字，通常是加一
    public static int VERSION = 1;
    SQLiteDatabase db;
    // 資料庫物件，固定的欄位變數
    private static SQLiteDatabase database;

    //context為當前activity的上下文,name為要操作的資料庫名稱,factory用來做深入查詢用,version為版本
    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    //只有在資料庫不存在時,才會呼叫onCreate()建立資料庫
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 建立要存放資料的資料表格
        String CREATE_TABLE =
                "CREATE TABLE accounting_TABLE (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "activity_type TEXT NOT NULL, "+
                        "type TEXT NOT NULL, " +
                        "money INTEGER NOT NULL, " +
                        "date DATE NOT NULL, " +
                        "time TIME NOT NULL," +
                        "image TEXT NOT NULL)";

        // 建立應用程式需要的表格
        db.execSQL(CREATE_TABLE);
    }


    //使用建構子時如果版本增加,便會呼叫onUpgrade()刪除舊的資料表與其內容,再重新呼叫onCreate()建立新的資料表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 刪除原有的表格
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        // 呼叫onCreate建立新版的表格
        onCreate(db);
    }

    public void insertData(String activity_type, String type, int money, String date, String time,String image) {

        db = this.getWritableDatabase();

        long id;
        ContentValues cv = new ContentValues();
        cv.put("activity_type", activity_type);
        cv.put("type", type);
        cv.put("money", money);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("image",image);
        id = db.insert(DATABASE_TABLE, null, cv);
        db.close();
        if(id==-1){
            Log.v("DB", "新增記錄失敗"+ id);
            Log.v("DB", "cv:"+ cv);
        }
        else {
            Log.v("DB", "新增記錄成功"+ id);
            Log.v("DB", "cv:"+ cv);
        }
    }

}
