package com.nwar.dsm.deanomoo_dsm.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.nwar.dsm.deanomoo_dsm.R
import java.util.regex.Pattern
import java.util.regex.Matcher

class LoginActivity : AppCompatActivity() {
    private lateinit var pref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        //Logout() // 테스트용
        val ID = pref.getString("ID", "")
        val PW = pref.getString("PW", "")
        Log.e("load shared ID", ID)
        Log.e("load shared PW", PW)
        if (ID != "" && PW != "") SignIn(ID, PW)
        setInputType()
        setClickListener()
    }

    private fun setInputType() {
        val ID = findViewById<EditText>(R.id.login_id)
        val PW = findViewById<EditText>(R.id.login_pw)
        ID.filters = arrayOf(filterAlphaNum())
        PW.filters = arrayOf(filterAlphaNum())
    }

    private fun setClickListener() { // 로그인 버튼 터치
        val loginButton = findViewById<TextView>(R.id.login_login_btn)
        val ID = findViewById<EditText>(R.id.login_id)
        val PW = findViewById<EditText>(R.id.login_pw)

        loginButton.setOnClickListener {
            val getID = ID.text.toString()
            val getPW = PW.text.toString()
            SignIn(getID, getPW)
        }
    }

    private fun SignIn(getID: String, getPW: String) { // 아이디 패스워드 일치여부 확인 후.
        val editor = pref.edit()
        if (isLoginSuccess(getID, getPW)) {
            editor.putString("ID", getID)
            editor.putString("PW", getPW)
            editor.commit()
            Log.e("sharedpreference ID", getID)
            Log.e("sharedpreference PW", getPW)

            Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            editor.putString("ID", "")
            editor.putString("PW", "")
            editor.commit()
            Toast.makeText(this, "ID 혹은 PW를 다시 확인해주십시오.", Toast.LENGTH_SHORT).show()
        }

    }

    private fun isLoginSuccess(ID: String, PW: String): Boolean { // 아이디 패스워드 일치여부
        return if (ID == "admin" && PW == "asdf") {
            true
        } else {
            false
        }
    }

    private fun Logout() {
        val editor = pref.edit()
        editor.putString("ID", "")
        editor.putString("PW", "")
        editor.commit()
    }

    fun filterAlphaNum(): InputFilter {
        var inputFilter: InputFilter = object : InputFilter {
            override fun filter(
                source: CharSequence?,
                start: Int,
                end: Int,
                dest: Spanned?,
                dstart: Int,
                dend: Int
            ): CharSequence? {
                val pattern: Pattern = Pattern.compile("^[a-zA-Z0-9]*$")
                if (!pattern.matcher(source).matches()) {
                    return ""
                }
                return null
            }
        }
        return inputFilter
    }
}