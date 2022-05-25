package fr.epf.application

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.epf.application.Adapters.ToDoAdapterRappels
import fr.epf.application.Utils.DatabaseHandler2
import fr.epf.application.models.ToDoModel2
import java.util.*

class RappelsActivity : AppCompatActivity(), DialogCloseListener {
    private var db: DatabaseHandler2? = null
    private var rappelsRecyclerView: RecyclerView? = null
    private var rappelsAdapter: ToDoAdapterRappels? = null
    private var fabRappels: FloatingActionButton? = null
    private var rappelList: List<ToDoModel2?>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rappels)
        //Objects.requireNonNull(supportActionBar).hide()
        db = DatabaseHandler2(this)
        db!!.openDatabase()
        val rappelsRecyclerView = findViewById<RecyclerView>(R.id.rappelsRecyclerView)
        rappelsRecyclerView.setLayoutManager(LinearLayoutManager(this))
        rappelsAdapter = ToDoAdapterRappels(db, this@RappelsActivity)
        rappelsRecyclerView.setAdapter(rappelsAdapter)
        val itemTouchHelper = ItemTouchHelper(RecyclerItemTouchHelperRappels(rappelsAdapter))
        itemTouchHelper.attachToRecyclerView(rappelsRecyclerView)
        val fabRappels = findViewById<FloatingActionButton>(R.id.fabRappels)
        rappelList = db!!.allRappels
        Collections.reverse(rappelList)
        rappelsAdapter!!.setRappels(rappelList)
        fabRappels.setOnClickListener(View.OnClickListener {
            AddNewRappel.newInstance().show(
                supportFragmentManager, AddNewRappel.TAG
            )
        })
    }

    override fun handleDialogClose(dialog: DialogInterface) {
        rappelList = db!!.allRappels
        Collections.reverse(rappelList)
        rappelsAdapter!!.setRappels(rappelList)
        rappelsAdapter!!.notifyDataSetChanged()
    }

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