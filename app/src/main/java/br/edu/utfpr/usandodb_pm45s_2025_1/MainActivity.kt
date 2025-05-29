package br.edu.utfpr.usandodb_pm45s_2025_1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btIncluirOnClick(view: View) {}
    fun btAlterarOnClick(view: View) {}
    fun btExcluirOnClick(view: View) {}
    fun btPesquisarOnClick(view: View) {}
    fun btListarOnClick(view: View) {}
}