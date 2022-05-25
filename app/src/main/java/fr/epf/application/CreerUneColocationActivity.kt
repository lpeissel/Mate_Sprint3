package fr.epf.application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import fr.epf.application.models.Colocataire
import fr.epf.application.models.Colocation
import org.w3c.dom.Text

class CreerUneColocationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creer_une_colocation)

        var i = 0
        val listColocataire: MutableList<Colocataire> = ArrayList()
        val nomColocataire = findViewById<EditText>(R.id.text_input_nom_colocataire1)
        val emailColocataire = findViewById<EditText>(R.id.text_input_email_colocataire)
        val affichageNomColocataire = findViewById<TextView>(R.id.text_affichage_nom_colocataire)
        val ajouterAutreColocataire = findViewById<Button>(R.id.ajouter_un_autre_colocataire)
        val suivant = findViewById<Button>(R.id.suivant_vers_choisir_colocataire)
        var nomColocationDansListe: String =""

        if (intent.hasExtra("nomColocation")){
            nomColocationDansListe = intent.getStringExtra("nomColocation").toString()
        }


        ajouterAutreColocataire.setOnClickListener {
            //Log.d("patrick", "${nomColocataire.text}")
            if (i==0) {
                affichageNomColocataire.text = ""
                affichageNomColocataire.text = "${nomColocataire.text}"
            }else{
                affichageNomColocataire.text = "${affichageNomColocataire.text}\n${nomColocataire.text}"
            }
            //Log.d("patrick", "${affichageNomColocataire.text}")

            val nomColocataireDansListe = nomColocataire.text.toString()
            val emailColocataireDansListe = emailColocataire.text.toString()
            val isSuperColoc = if (i == 0) true else false

            listColocataire.add(Colocataire(nomColocataireDansListe, 100 + i, "20102012" + "$i",  emailColocataireDansListe, isSuperColoc, false))
            i += 1

            nomColocataire.text = null
            emailColocataire.text = null
            Log.d("patrick", "$listColocataire")
        }

        val newColocation: Colocation = Colocation(nomColocationDansListe, 14022000, listColocataire)

        suivant.setOnClickListener {
            val intent = Intent(this, IdentificationColocataireActivity::class.java )
            intent.putExtra("colocataire1", listColocataire[0].nom)
            intent.putExtra("colocataire2", listColocataire[1].nom)
            intent.putExtra("colocataire3", listColocataire[2].nom)
            intent.putExtra("emailColocataire1", listColocataire[0].email)
            intent.putExtra("emailColocataire2", listColocataire[1].email)
            intent.putExtra("emailColocataire3", listColocataire[2].email)
            intent.putExtra("codeColocataire1", listColocataire[0].code)
            intent.putExtra("codeColocataire2", listColocataire[1].code)
            intent.putExtra("codeColocataire3", listColocataire[2].code)
            Log.d("patrick", "${newColocation.nom}\n${newColocation.id}\n${newColocation.colocataire}")
            Log.d("patrick", listColocataire[0].code)
            startActivity(intent)


        }

    }
}

