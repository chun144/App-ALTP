package com.chun.ailatrieuphu1.datasqlife

import android.content.ContentValues
import android.content.Context
import android.content.res.AssetManager
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Environment
import android.util.Log
import com.chun.ailatrieuphu1.model.HighScore
import com.chun.ailatrieuphu1.model.Question
import java.io.File
import java.io.FileOutputStream

class ALTPDao {
    private val mContext: Context
    private var mSqlite: SQLiteDatabase? = null

    constructor(context: Context) {
        mContext = context
        copyDatabase()
    }


    private fun copyDatabase() {
        val path = Environment.getDataDirectory().path + File.separator + "data" +
                File.separator + mContext.packageName + File.separator + "Question"
        if (File(path).exists()) {
            return
        }
        //mở question ở trong access
        val accessManager: AssetManager = mContext.assets
        val input = accessManager.open("Question")

        val out = FileOutputStream(path)
        input.copyTo(out)
        input.close()
        out.close()
    }

    private fun openDatabase() {
        val path = Environment.getDataDirectory().path + File.separator + "data" +
                File.separator + mContext.packageName + File.separator + "Question"
        if (mSqlite != null && mSqlite!!.isOpen) {
            return
        }
        mSqlite = SQLiteDatabase.openDatabase(
            path, null,
            SQLiteDatabase.OPEN_READWRITE
        )
    }

    private fun closeDatabase() {
        if (mSqlite != null && mSqlite!!.isOpen) {
            mSqlite!!.close()
            mSqlite = null
        }
    }

    fun query15Question(): MutableList<Question> {
        openDatabase()

        val questions = mutableListOf<Question>()
        val query = "select * from (select  * from Question order by random()) as qti group by level"
        val cur: Cursor = mSqlite!!.rawQuery(query, null)
        cur.moveToFirst()

        val indexQuestion = cur.getColumnIndex("question")
        val indexLevel = cur.getColumnIndex("level")
        val indexCaseA = cur.getColumnIndex("casea")
        val indexCaseB = cur.getColumnIndex("caseb")
        val indexCaseC = cur.getColumnIndex("casec")
        val indexCaseD = cur.getColumnIndex("cased")
        val indexTrueCase = cur.getColumnIndex("truecase")
        while (!cur.isAfterLast) {
            val question = cur.getString(indexQuestion)
            val level = cur.getInt(indexLevel)
            val caseA = cur.getString(indexCaseA)
            val caseB = cur.getString(indexCaseB)
            val caseC = cur.getString(indexCaseC)
            val caseD = cur.getString(indexCaseD)
            val trueCase = cur.getInt(indexTrueCase)
            questions.add(Question(question, level, caseA, caseB, caseC, caseD, trueCase))
            //chuyen cursor xuong dong tiep
            cur.moveToNext()
        }

        closeDatabase()

        return questions
    }

    fun insertHighScore(name:String, money:String, level:Int){
        openDatabase()

        val keyValue = ContentValues()
        keyValue.put("name", name)
        keyValue.put("money", money)
        keyValue.put("level", level)

        //transaction mo
        mSqlite!!.beginTransaction()
        //insert/edit/delete
        //1. Kiem tien
        //2 Tru tien A
        //3. Cong tien cho B (loi)
        //4: Hoan thanh va thogn bao
        mSqlite!!.insert("HighScore",null, keyValue)
        mSqlite!!.setTransactionSuccessful();
        mSqlite!!.endTransaction()

        closeDatabase()
    }

    fun updateHighScore(id:Int, money:String, level:Int){
        openDatabase()

        mSqlite!!.beginTransaction()

        val contentValue = ContentValues()
        contentValue.put("money", money)
        contentValue.put("level", level)

        val arrayWhere = arrayOf(id.toString())
        mSqlite!!.update("HighScore", contentValue, "id = $id", null)
        mSqlite!!.setTransactionSuccessful()
        mSqlite!!.endTransaction()

        closeDatabase()
    }

    fun deleteHighScore(id:Int){
        openDatabase()

        mSqlite!!.beginTransaction()
        mSqlite!!.delete("HighScore", "id = $id", null)
        mSqlite!!.setTransactionSuccessful()
        mSqlite!!.endTransaction()

        closeDatabase()
    }

    fun query20HighScore(): MutableList<HighScore> {
        openDatabase()

        val highScores = mutableListOf<HighScore>()
        val query = "select * from HighScore order by money desc limit 20"
        val cur: Cursor = mSqlite!!.rawQuery(query, null)
        cur.moveToFirst()

        val indexName = cur.getColumnIndex("name")
        val indexScore = cur.getColumnIndex("money")
        while (!cur.isAfterLast) {
            val name = cur.getString(indexName)
            val score = cur.getString(indexScore)
            highScores.add(HighScore(name, score))
            //chuyen cursor xuong dong tiep
            cur.moveToNext()
        }

        closeDatabase()

        return highScores
    }

}