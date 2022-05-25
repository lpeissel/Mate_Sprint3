package fr.epf.application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class VerificationEmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_email)

        val boutonValider = findViewById<Button>(R.id.bouton_valider)
        val emailVerifieRecupere = intent.getStringExtra("emailColocataireChoisi").toString()
        val emailVerifie = findViewById<TextView>(R.id.rappel_email)

        emailVerifie.text = emailVerifieRecupere

        Log.d("patrick", boutonValider.toString())
        Log.d("patrick", emailVerifieRecupere)
        Log.d("patrick", emailVerifie.text as String)



        boutonValider.setOnClickListener {
            val codeVerifieRecupere = intent.getStringExtra("codeColocataireChoisi").toString()
            val codeVerifie = findViewById<EditText>(R.id.code_verification).text.toString()

            Log.d("patrick",codeVerifieRecupere)
            Log.d("patrick", codeVerifie)
            if(codeVerifieRecupere == codeVerifie) {

                val intent = Intent(this, ListeActivity::class.java)
                startActivity(intent)

            }
        }

    }
}

//PENSEZ A PASSER LA SESSION ACTIVVE EN TRUE POUR RECUP LES INFOS DU COLOC CONNECTE