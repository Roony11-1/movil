package com.patitofeliz.trabajo2movil.model.unidades

import com.patitofeliz.trabajo2movil.model.unidades.clases.Clase
import com.patitofeliz.trabajo2movil.model.unidades.clases.atributos.Estadisticas

class Unidad(var id: Int? = null,
             var nombre: String = "NN",
             var clase: Clase? = null,
             var nivel: Int = 1,
             var idUsuario: Int? = null,
             var estadisticasUnidad: Estadisticas)
{

}