package fr.epf.application.models

data class Colocataire(
    val nom: String,
    val id: Int,
    val code: String,
    val email: String,
    val isSuperColoc: Boolean,
    val isSessionActive: Boolean


){
    companion object{
        // Améliorations : pouvoir modifier le nb de colocataire
        fun bdd(nb: Int =3) =
            (1..nb).map{

                //Le premier colocataire est par défaut le super coloc (couronne)
                val isSuperColoc = if (it == 1) true else false

                Colocataire(
                    "Colocataire$it", 200 + it, "300" + "$it", "colocataire$it@gmail.com",
                    isSuperColoc, false //lors de la creation de cette bdd aucune co n'a été effectué donc tous les sessionActive sont false
                )
            }
    }

}