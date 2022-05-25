package fr.epf.application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class NommerColocationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nommer_colocation)

        val nomColocation = findViewById<EditText>(R.id.nommage_colocation)
        val suivant = findViewById<Button>(R.id.bouton_suivant_vers_ajout_colocataire)

        suivant.setOnClickListener {
            val nomColocationDansListe = nomColocation.text.toString()
            val intent = Intent(this, CreerUneColocationActivity::class.java)
            intent.putExtra("nomColocation", nomColocationDansListe)
            startActivity(intent)
            Log.d("patrick", nomColocationDansListe)
        }

    }
}