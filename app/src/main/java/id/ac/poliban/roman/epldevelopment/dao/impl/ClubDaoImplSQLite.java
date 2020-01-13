package id.ac.poliban.roman.epldevelopment.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import id.ac.poliban.roman.epldevelopment.dao.ClubDao;
import id.ac.poliban.roman.epldevelopment.domain.Club;

public class ClubDaoImplSQLite extends SQLiteOpenHelper implements ClubDao {
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "epl.db";

    public ClubDaoImplSQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists club (" +
                "id integer primary key autoincrement," +
                "name text not null," +
                "founded text," +
                "grounded text," +
                "manager text," +
                "description text," +
                "logo text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            String sql = "drop table if exists club";
            db.execSQL(sql);
            onCreate(db);
        }
    }

    @Override
    public void insert(Club c) {
        String sql = "insert into club values(?,?,?,?,?,?,?)";
        getWritableDatabase().execSQL(sql, new Object[]{
                null,
                c.getClubName(),
                c.getFounded(),
                c.getGrounded(),
                c.getManager(),
                c.getDescription(),
                c.getUrlLogo()
        });
    }

    @Override
    public void update(Club c) {
        String sql = "update club set name=?, founded=?, grounded=?, manager=?, description=?, logo=? where id=?";
        getWritableDatabase().execSQL(sql, new Object[]{
                c.getClubName(),
                c.getFounded(),
                c.getGrounded(),
                c.getManager(),
                c.getDescription(),
                c.getUrlLogo(),
                c.getId()
        });
    }

    @Override
    public void delete(int id) {
        String sql = "delete from club where id=?";
        getWritableDatabase().execSQL(sql, new Object[]{id});
    }

    @Override
    public Club getClubById(int id) {
        Club result = null;
        String sql = "select * from club where id=?";
        Cursor cursor = getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(id)});
        if(cursor.moveToFirst())
            result = new Club(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
        cursor.close();
        return result;
    }

    @Override
    public List<Club> getAllClub() {
        List<Club> result = new ArrayList<>();
        String sql = "select * from club";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        while(cursor.moveToNext())
            result.add(new Club(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            ));
        cursor.close();
        return result;
    }
}
