package fr.epf.application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class PasserCouronneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passer_couronne)
        val boutonColocataire1 = findViewById<Button>(R.id.btn_couronne_coloc1)
        val boutonColocataire2 = findViewById<Button>(R.id.btn_couronne_coloc2)
        val boutonColocataire3 = findViewById<Button>(R.id.btn_couronne_coloc3)
        val boutonPasserCouronne = findViewById<Button>(R.id.btn_passer_couronne)


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

        boutonColocataire1.setOnClickListener {
            boutonColocataire1.setTextColor(resources.getColor(R.color.black))
            boutonColocataire2.setTextColor(resources.getColor(R.color.white))
            boutonColocataire3.setTextColor(resources.getColor(R.color.white))
        }

        boutonColocataire2.setOnClickListener {
            boutonColocataire1.setTextColor(resources.getColor(R.color.white))
            boutonColocataire2.setTextColor(resources.getColor(R.color.black))
            boutonColocataire3.setTextColor(resources.getColor(R.color.white))
        }

        boutonColocataire3.setOnClickListener {
            boutonColocataire1.setTextColor(resources.getColor(R.color.white))
            boutonColocataire2.setTextColor(resources.getColor(R.color.white))
            boutonColocataire3.setTextColor(resources.getColor(R.color.black))
        }

        boutonPasserCouronne.setOnClickListener {
            val intent = Intent(this,QuitterColocActivity::class.java)
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