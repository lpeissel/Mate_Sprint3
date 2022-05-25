package fr.epf.application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import fr.epf.application.models.Colocataire

class ReglagesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reglages)

        //afficher la position du colocataire connecté ie isSessionActive == true

        var idColocCo: Int = 0;

        // on créé récupére le booleen de la session de 3 colocataires
        for (i in 0..2) {
            val colocataireCo = Colocataire.bdd(3)[i].isSessionActive
            if (colocataireCo) { //si session active alors on sauvegarde l'id
                idColocCo = i
            }
        }

        // on affiche les infos du coloc actif grace à l'id sauvegardé
        val nomTextView= findViewById<TextView>(R.id.nom_coloc_value)

        val nom = Colocataire.bdd(3)[idColocCo].nom
        nomTextView.text = nom

        val mailTextView = findViewById<TextView>(R.id.mail_coloc_value)

        val mail = Colocataire.bdd(3)[idColocCo].email
        mailTextView.text = mail


        //Le colocataire détient il la couronne ?
        val couronne = Colocataire.bdd(3)[idColocCo].isSuperColoc
        val passerCouronneImageView = findViewById<ImageView>(R.id.passer_ma_couronne_image)
        val btnQuitterColoc =findViewById<Button>(R.id.btn_quitter_coloc)
        val listColocataire: MutableList<Colocataire> = ArrayList()

        btnQuitterColoc.setOnClickListener{
            if(couronne){
                val intent = Intent(this,PasserCouronneActivity::class.java)
                startActivity(intent)
                intent.putExtra("colocataire1", listColocataire[0].nom)
                intent.putExtra("colocataire2", listColocataire[1].nom)
                intent.putExtra("colocataire3", listColocataire[2].nom)
            }
            else{
                val intent = Intent(this,QuitterColocActivity::class.java)
                startActivity(intent)
            }
        }

        if (!couronne){
            passerCouronneImageView.setVisibility(ImageView.INVISIBLE);
        }


    }


    //rajouter items d'autres classes
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //trouver le contenu du menu qui se trouve dans list_clients.xml et on souhaite le mettre dans menu
        menuInflater.inflate(R.menu.top, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //actions quand on clique sur un élément du menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId //recup id de l'element qui a été cliqué
        when(id){ //qd on clique sur ce bouton on lance la fonction delete (pop up/ boite de dialogue)
            R.id.rappels_action -> ouvrirRappels()
            R.id.depenses_action -> ouvrirBudget()
            R.id.liste_action -> ouvrirListe()
            R.id.parametres_action -> ouvrirReglages()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun ouvrirRappels() {
        val intent = Intent(this, RappelsActivity::class.java )
        startActivity(intent)
    }
    private fun ouvrirBudget() {
        val intent = Intent(this, BudgetActivity::class.java )
        startActivity(intent)
    }
    private fun ouvrirListe() {
        val intent = Intent(this, ListeActivity::class.java )
        startActivity(intent)
    }
    private fun ouvrirReglages() {
        val intent = Intent(this, ReglagesActivity::class.java )
        startActivity(intent)
    }

}