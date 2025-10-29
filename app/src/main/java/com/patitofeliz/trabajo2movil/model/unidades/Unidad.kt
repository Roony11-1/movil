package com.patitofeliz.trabajo2movil.model.unidades

import com.patitofeliz.trabajo2movil.model.unidades.clases.Clase
import com.patitofeliz.trabajo2movil.model.unidades.clases.atributos.Estadisticas

class Unidad(var id: Int? = null,
             var nombre: String = "NN",
             var clase: Clase? = null,
             var nivel: Nivel,
             var idUsuario: Int? = null,
             var estadisticasUnidad: Estadisticas)
{

}

class Nivel(var nivel: Int,
            var experiencia: Int)