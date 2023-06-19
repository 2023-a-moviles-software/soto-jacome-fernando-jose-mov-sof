import com.google.gson.Gson

fun main(args: Array<String>) {

    val gson = Gson()
    val crud = Crud(gson)
    val console : Console = Console(crud)

    console.mostrarMenuPrincipal()



}


