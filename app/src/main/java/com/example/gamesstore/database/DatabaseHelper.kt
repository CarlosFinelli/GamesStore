package com.example.gamesstore.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, "MyDatabase.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableUser = "CREATE TABLE user (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "nome TEXT, email TEXT, senha TEXT, genero INT)"
        db?.execSQL(createTableUser)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS user")
    }
}