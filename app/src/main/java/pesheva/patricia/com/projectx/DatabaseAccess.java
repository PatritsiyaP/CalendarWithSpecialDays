package pesheva.patricia.com.projectx;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseAccess {
    private DatabaseOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c =null;

    //private constructor
    private DatabaseAccess(Context context){
        this.openHelper= new DatabaseOpenHelper(context);
    }

    //return single instance
    public static DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance=new DatabaseAccess(context);
        }
        return instance;
    }

    // to open database
    public void open(){
        this.db=openHelper.getWritableDatabase();
    }

    // close database
    public void close(){
        if(db!=null){
            this.db.close();
        }
    }
    // query to database

    public String gettitle(String name){
        Log.d("pp","getAddress "+name);
        String qr = "select title FROM SpecialDays where data = '"+name+"'";
        c=db.rawQuery(qr,new String[]{});
       // Log.d("pp","rawQuery "+"select adres from table1 where name = '"+name+"'");
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String cat=c.getString(0);
            Log.d("pp","adres2 "+cat);
            buffer.append(""+cat);
        }
        return buffer.toString();
    }
    public String getdesc(String name){
        Log.d("pp","getAddress "+name);
        String qr = "select description FROM SpecialDays where data = '"+name+"'";
        c=db.rawQuery(qr,new String[]{});
        // Log.d("pp","rawQuery "+"select adres from table1 where name = '"+name+"'");
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String cat=c.getString(0);
            Log.d("pp","adres2 "+cat);
            buffer.append(""+cat);
        }
        return buffer.toString();
    }
    public String getitembyitemT(int name){
        Log.d("pp","getAddress "+name);
        String qr = "select title FROM SpecialDays where id = '"+name+"'";
        c=db.rawQuery(qr,new String[]{});
        // Log.d("pp","rawQuery "+"select adres from table1 where name = '"+name+"'");
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String cat=c.getString(0);
            Log.d("pp","adres2 "+cat);
            buffer.append(""+cat);
        }
        return buffer.toString();
    }
    public String getitembyitemD(int name){
        Log.d("pp","getAddress "+name);
        String qr = "select description FROM SpecialDays where id = '"+name+"'";
        c=db.rawQuery(qr,new String[]{});
        // Log.d("pp","rawQuery "+"select adres from table1 where name = '"+name+"'");
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String cat=c.getString(0);
            Log.d("pp","adres2 "+cat);
            buffer.append(""+cat);
        }
        return buffer.toString();
    }
    public String getitembyitemDD(int name){
        Log.d("pp","getAddress "+name);
        String qr = "select data FROM SpecialDays where id = '"+name+"'";
        c=db.rawQuery(qr,new String[]{});
        // Log.d("pp","rawQuery "+"select adres from table1 where name = '"+name+"'");
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String cat=c.getString(0);
            Log.d("pp","adres2 "+cat);
            buffer.append(""+cat);
        }
        return buffer.toString();
    }
}
