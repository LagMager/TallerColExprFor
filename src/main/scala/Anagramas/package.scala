package object Anagramas {
    type Palabra = String
    type Frase = List[Palabra]
    type Ocurrencias = List[(Char, Int)]

    val diccionario: List[Palabra] = List("cosas", "como", "yo", "y", "ocasos", "cayo", "mocosos", "roca", "moco", "sos")

    def IOcPal(p: Palabra): Ocurrencias = {
        val letras = p.toLowerCase().filter(_.isLetter)

        val l_group = letras.groupBy(c => c)

        val ocurrencias = for {
            (l, lista) <- l_group
        } yield (l, lista.length)
        ocurrencias.toList.sortBy(_._1)
    }

    def IOcFrase(f: Frase): Ocurrencias = {
        val f_unida = f.mkString

        IOcPal(f_unida)
    }

    lazy val diccionarioPorOcurrencias: Map[Ocurrencias, List[Palabra]] = {
        diccionario.groupBy(IOcPal)
    }

    def anagramasDePalabra(palabra: Palabra): List[Palabra] = {

        val ocurrencias = IOcPal(palabra)
        diccionarioPorOcurrencias.getOrElse(ocurrencias, Nil)
        // usar diccionarioPorOcurrencias,get, IOcPal
    }

    def combinaciones(Iocurrencias: Ocurrencias): List[Ocurrencias] = {
        val opcionesPorLetra: List[List[Ocurrencias]] = Iocurrencias.map {
            case (letra, n) =>
                val ocurrencias = (1 to n).map(i => List((letra, i))).toList
                Nil :: ocurrencias 
        }

        opcionesPorLetra.foldRight(List(List.empty[(Char, Int)])) {
            (opcionesLetra, combinacionesParciales) =>
                for {
                    opcion <- opcionesLetra
                    combinacion <- combinacionesParciales
                } yield opcion ++ combinacion
        }
    }
}
