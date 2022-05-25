package fr.epf.application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import fr.epf.application.models.Colocation
import fr.epf.application.models.Colocataire

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var verificationIdColocation = findViewById<EditText>(R.id.identifiant_coloc).text
        val rejoindreColocation = findViewById<Button>(R.id.btn_rejoindre_coloc)
        val creerColocation = findViewById<Button>(R.id.btn_creer_coloc)

        rejoindreColocation.setOnClickListener {
            val RS = verificationIdColocation.toString()
            val lol = RS.toInt()
            //Log.d("patrick", "$lol")

            for (i in 0..2) {
            val colocation = Colocation.bdd(3)[i].id
            if (colocation == lol) {
                val intent = Intent(this,IdentificationColocataireActivity::class.java)
                startActivity(intent)
            }else{

            }
            //Log.d("patrick",  "$colocation")
            }
        }

        creerColocation.setOnClickListener {
            val intent = Intent(this,NommerColocationActivity::class.java)
            startActivity(intent)
        }


    }
}






