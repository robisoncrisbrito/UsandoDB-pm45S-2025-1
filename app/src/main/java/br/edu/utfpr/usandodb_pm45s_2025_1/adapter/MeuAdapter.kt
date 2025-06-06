package br.edu.utfpr.usandodb_pm45s_2025_1.adapter

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.edu.utfpr.usandodb_pm45s_2025_1.R
import br.edu.utfpr.usandodb_pm45s_2025_1.entity.Cadastro

class MeuAdapter( val contexto : Context , val lista : Cursor ) : BaseAdapter() {
    override fun getCount(): Int {
        return lista.count
    }

    override fun getItem(id: Int): Any? {
        lista.moveToPosition( id )

        val cadastro = Cadastro(
            lista.getInt( 0 ),
            lista.getString( 1 ),
            lista.getString( 2 )
        )

        return cadastro
    }

    override fun getItemId(id: Int): Long {
        lista.moveToPosition( id )

        return lista.getInt( 0 ).toLong()
    }

    override fun getView( id: Int, p1: View?, p2: ViewGroup? ): View? {

        //recuperar a referencia do arquivo xml do elemento lista
        val inflater = contexto.getSystemService( Context.LAYOUT_INFLATER_SERVICE ) as LayoutInflater
        val v = inflater.inflate( R.layout.elemento_lista, null )

        //recuperar os componentes visuais
        val tvNome = v.findViewById<TextView>( R.id.tvNomeElementoLista )
        val tvTelefone = v.findViewById<TextView>( R.id.tvTelefoneElementoLista )

        lista.moveToPosition( id )

        //preencher os componentes visuais
        tvNome.text = lista.getString( 1 )
        tvTelefone.text = lista.getString( 2 )

        return v

    }
}