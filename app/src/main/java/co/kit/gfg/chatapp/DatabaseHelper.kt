package co.kit.gfg.chatapp

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import androidx.core.content.ContextCompat.startActivity

class DatabaseHelper(context: Context):SQLiteOpenHelper(context,dbname,factory, version) {
    companion object{
        val dbname="userDB"
        val factory =null
        val version=1



    }
    //Creating database

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE userDB(id INTEGER PRIMARY KEY AUTOINCREMENT,bluetoothname VARCHAR(50), username VARCHAR(30), password VARCHAR(20))")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    //Inserting data into database
    fun insertUserData(bluetoothname:String,username:String,password:String)
    {
        val db:SQLiteDatabase=writableDatabase
        val values:ContentValues=ContentValues()
        values.put("bluetoothname",bluetoothname)
        values.put("username",username)
        values.put("password",password)
        db.insert("userDB",null,values)
        db.close()
    }

    //Checking if user present
    fun userPresent(b:String,u:String,p:String):Boolean
    {
        val db=writableDatabase
        val query="select * from userDB where bluetoothname='$b' and username='$u' and password='$p'"
        val cursor=db.rawQuery(query,null)

        if(cursor.count<=0)
        {
            cursor.close()
            return false

        }
        cursor.close()
        return true

    }
}