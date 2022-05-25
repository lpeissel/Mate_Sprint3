package fr.epf.application

import androidx.appcompat.app.AppCompatActivity
import fr.epf.application.DialogCloseListener
import fr.epf.application.Utils.DatabaseHandler
import androidx.recyclerview.widget.RecyclerView
import fr.epf.application.Adapters.ToDoAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.epf.application.models.ToDoModel
import android.os.Bundle
import fr.epf.application.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import fr.epf.application.RecyclerItemTouchHelper
import fr.epf.application.AddNewTask
import android.content.DialogInterface
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import java.util.*

class ListeActivity : AppCompatActivity(), DialogCloseListener {
    private var db: DatabaseHandler? = null
    private var tasksRecyclerView: RecyclerView? = null
    private var tasksAdapter: ToDoAdapter? = null
    private var fab: FloatingActionButton? = null
    private var taskList: List<ToDoModel?>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste)
        //Objects.requireNonNull(supportActionBar).hide()
        db = DatabaseHandler(this)
        db!!.openDatabase()
        val tasksRecyclerView = findViewById<RecyclerView>(R.id.tasksRecyclerView)
        tasksRecyclerView.setLayoutManager(LinearLayoutManager(this))
        tasksAdapter = ToDoAdapter(db, this@ListeActivity)
        tasksRecyclerView.setAdapter(tasksAdapter)
        val itemTouchHelper = ItemTouchHelper(RecyclerItemTouchHelper(tasksAdapter))
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        taskList = db!!.allTasks
        Collections.reverse(taskList)
        tasksAdapter!!.setTasks(taskList)
        fab.setOnClickListener(View.OnClickListener {
            AddNewTask.newInstance().show(
                supportFragmentManager, AddNewTask.TAG
            )
        })
    }

    override fun handleDialogClose(dialog: DialogInterface) {
        taskList = db!!.allTasks
        Collections.reverse(taskList)
        tasksAdapter!!.setTasks(taskList)
        tasksAdapter!!.notifyDataSetChanged()
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