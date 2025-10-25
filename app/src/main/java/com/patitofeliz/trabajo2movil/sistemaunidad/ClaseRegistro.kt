import com.patitofeliz.trabajo2movil.model.unidades.clases.Clase
import kotlin.reflect.KClass

data class ClaseInfo(val nombre: String, val crear: () -> Clase)

object ClaseRegistro
{
    private val clases = mutableListOf<ClaseInfo>()

    fun registrar(nombre: String, crear: () -> Clase)
    {
        if (clases.none { it.nombre == nombre })
        {
            clases.add(ClaseInfo(nombre, crear))
        }
    }

    fun obtenerTodas(): List<ClaseInfo> = clases
}