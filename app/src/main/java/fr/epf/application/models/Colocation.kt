package fr.epf.application.models

class Colocation(
    var nom: String,
    var id: Int,
    var colocataire: List<Colocataire>,
    ){
    operator fun get(listcolocation: List<Colocation> ): Int {
        return id
    }

    companion object{
        fun bdd(nb: Int =3) =
            (1..nb).map{

                val coloc = Colocataire.bdd(3)
                Colocation(
                    "Colocation$it", 100 + it, coloc
                )
            }
    }

}








