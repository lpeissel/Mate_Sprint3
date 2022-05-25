package fr.epf.application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class QuitterColocActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quitter_coloc)


        //Si user clique sur oui alors on le supprime de la bdd à relier au BACK
            //on supprime les données : voir avec Back comment faire
        val btnQuitterOuiColoc =
            findViewById<Button>(R.id.btn_quitter_oui)

        btnQuitterOuiColoc.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            Toast.makeText(this,R.string.colocSupp, Toast.LENGTH_LONG).show()
            startActivity(intent)
        }


        //si user clique sur annuler alors il retourne à la page paramètre
        val btnAnnulerQuitterColoc =
            findViewById<Button>(R.id.btn_annuler_quitter)

        btnAnnulerQuitterColoc.setOnClickListener{
            val intent = Intent(this,ReglagesActivity::class.java)
            startActivity(intent)
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