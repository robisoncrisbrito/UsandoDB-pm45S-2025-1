package br.edu.utfpr.usandodb_pm45s_2025_1.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler (context : Context ) : SQLiteOpenHelper (context, "dbfile.sqlite", null, 1 )  {
    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}