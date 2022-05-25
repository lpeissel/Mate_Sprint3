package fr.epf.application.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fr.epf.application.models.ToDoModel2;

public class DatabaseHandler2 extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "rappelListDatabase";
    private static final String RAPPEL_TABLE = "rappel";
    private static final String ID = "id";
    private static final String RAPPELTEXT = "rappel";
    private static final String RAPPELTITRE = "rappel";
    private static final String STATUS = "status";
    private static final String CREATE_RAPPEL_TABLE = "CREATE TABLE " + RAPPEL_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + RAPPELTITRE + "TEXT, " + RAPPELTEXT + " TEXT, "
            + STATUS + " INTEGER)";

    private SQLiteDatabase db;

    public DatabaseHandler2(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RAPPEL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + RAPPEL_TABLE);
        // Create tables again
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    public void insertRappel(ToDoModel2 rappel){
        ContentValues cv = new ContentValues();
        cv.put(RAPPELTITRE, rappel.getRappelTitre());
        //cv.put(RAPPELTEXT, rappel.getRappelText());
        cv.put(STATUS, 0);
        db.insert(RAPPEL_TABLE, null, cv);
    }

    public List<ToDoModel2> getAllRappels(){
        List<ToDoModel2> rappelList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(RAPPEL_TABLE, null, null, null, null, null, null, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        ToDoModel2 rappel = new ToDoModel2();
                        rappel.setId(cur.getInt(cur.getColumnIndex(ID)));
                        rappel.setRappelTitre(cur.getString(cur.getColumnIndex(RAPPELTITRE)));
                        rappel.setRappelText(cur.getString(cur.getColumnIndex(RAPPELTEXT)));
                        rappel.setStatus(cur.getInt(cur.getColumnIndex(STATUS)));
                        rappelList.add(rappel);
                    }
                    while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        return rappelList;
    }

    public void updateStatus(int id, int status){
        ContentValues cv = new ContentValues();
        cv.put(STATUS, status);
        db.update(RAPPEL_TABLE, cv, ID + "= ?", new String[] {String.valueOf(id)});
    }

    public void updateRappel(int id, String rappelTitre, String rappelText) {
        ContentValues cv = new ContentValues();
        cv.put(RAPPELTITRE, rappelTitre);
        cv.put(RAPPELTEXT, rappelText);
        db.update(RAPPEL_TABLE, cv, ID + "= ?", new String[] {String.valueOf(id)});
    }

    public void deleteRappel(int id){
        db.delete(RAPPEL_TABLE, ID + "= ?", new String[] {String.valueOf(id)});
    }
}
