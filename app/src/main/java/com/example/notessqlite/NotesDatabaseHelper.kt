package com.example.notessqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotesDatabaseHelper (contex: Context) :SQLiteOpenHelper(contex, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        private const val DATABASE_NAME = "notesapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allnotes"
        private const val COULMN_ID = "id"
        private const val COULMN_TITLE = "title"
        private const val COULMN_CONTENT = "content"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME($COULMN_ID INTEGER PRIMARY KEY, $COULMN_TITLE TEXT, $COULMN_CONTENT TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }
    fun  insertNote(note: Note){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COULMN_TITLE, note.title)
            put(COULMN_CONTENT, note.content)
        }
        db.insert(TABLE_NAME, null , values)
        db.close()
    }
}