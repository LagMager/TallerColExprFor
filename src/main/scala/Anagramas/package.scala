package object Anagramas {
    type Palabra = String
    type Frase = List[Palabra]
    type Ocurrencias = List[(Char,Int)]

    val diccionario: List[Palabra] = List ("cosas", "como", "yo", "y", "ocasos", "cayo", "mocosos", "roca", "moco", "sos")

    def IOcPal(p: Palabra) : Ocurrencias ={
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

    lazy val diccionarioPorOcurrencias : Map[Ocurrencias, List[Palabra]] = {
        diccionario.groupBy(IOcPal)
    }
/*
    def anaagramasDePalabra(palabra: Palabra): List [Palabra] = {

      // usar diccionarioPorOcurrencias,get, IOcPal
    }

    def combinaciones(Iocurrencias: Ocurrencias) : List[Ocurrencias] = {
      // usar una expresion for para producir el resultado
    }

*/
}
