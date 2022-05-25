package fr.epf.application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class IdentificationColocataireActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identification_colocataire)

        val boutonColocataire1 = findViewById<Button>(R.id.btn_coloc1)
        val boutonColocataire2 = findViewById<Button>(R.id.btn_coloc2)
        val boutonColocataire3 = findViewById<Button>(R.id.btn_coloc3)
        val boutonVerificationMail = findViewById<Button>(R.id.btn_verifier_mail)
        var emailColocataireChoisi: String =""
        var codeColocataireChoisi: String =""

        /*
        boutonColocataire1.text = "Axel"
        boutonColocataire2.text = "Berrut"
        boutonColocataire3.text = "Land"
         */
        if (intent.hasExtra("colocataire1")) {
            boutonColocataire1.text =
                intent.getStringExtra("colocataire1").toString()
        }

        if (intent.hasExtra("colocataire2")) {
            boutonColocataire2.text =
                intent.getStringExtra("colocataire2").toString()
        }

        if (intent.hasExtra("colocataire3")) {
            boutonColocataire3.text =
                intent.getStringExtra("colocataire3").toString()
        }


        //Log.d("patrick", "${boutonColocataire1.text} ${boutonColocataire2.text} ${boutonColocataire3.text}")

        boutonColocataire1.setOnClickListener {
            boutonColocataire1.setTextColor(resources.getColor(R.color.black))
            boutonColocataire2.setTextColor(resources.getColor(R.color.white))
            boutonColocataire3.setTextColor(resources.getColor(R.color.white))
            emailColocataireChoisi = intent.getStringExtra("emailColocataire1").toString()
            codeColocataireChoisi = intent.getStringExtra("codeColocataire1").toString()

            Log.d("patrick", emailColocataireChoisi)
            Log.d("patrick", codeColocataireChoisi)
        }

        boutonColocataire2.setOnClickListener {
            boutonColocataire1.setTextColor(resources.getColor(R.color.white))
            boutonColocataire2.setTextColor(resources.getColor(R.color.black))
            boutonColocataire3.setTextColor(resources.getColor(R.color.white))
            emailColocataireChoisi = intent.getStringExtra("emailColocataire2").toString()
            codeColocataireChoisi = intent.getStringExtra("codeColocataire2").toString()

            Log.d("patrick", emailColocataireChoisi)
            Log.d("patrick", codeColocataireChoisi)
        }

        boutonColocataire3.setOnClickListener {
            boutonColocataire1.setTextColor(resources.getColor(R.color.white))
            boutonColocataire2.setTextColor(resources.getColor(R.color.white))
            boutonColocataire3.setTextColor(resources.getColor(R.color.black))
            emailColocataireChoisi = intent.getStringExtra("emailColocataire3").toString()
            codeColocataireChoisi = intent.getStringExtra("codeColocataire3").toString()

            Log.d("patrick", emailColocataireChoisi)
            Log.d("patrick", codeColocataireChoisi)
        }

        boutonVerificationMail.setOnClickListener {
            val intent = Intent(this,VerificationEmailActivity::class.java)
            intent.putExtra("emailColocataireChoisi", emailColocataireChoisi)
            intent.putExtra("codeColocataireChoisi", codeColocataireChoisi)
            Log.d("patrick", emailColocataireChoisi)
            Log.d("patrick", codeColocataireChoisi)
            startActivity(intent)
        }
    }
}