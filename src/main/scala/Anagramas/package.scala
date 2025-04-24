import scala.annotation.tailrec
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

  /**
   * Calcula la lista de ocurrencias complementaria usando recursión de cola.
   * Dadas lOc y slOc (donde slOc es sublista de lOc en términos de cuentas),
   * devuelve las ocurrencias restantes en lOc después de quitar las de slOc.
   *
   * Ejemplo: lOc = List(('a', 1), ('c', 2), ('o', 1)), slOc = List(('c', 1))
   * Resultado: List(('a', 1), ('c', 1), ('o', 1))
   *
   * @param lOc  Lista de ocurrencias total (ordenada por carácter).
   * @param slOc Sublista de ocurrencias a restar (ordenada por carácter).
   * @return La lista de ocurrencias resultante de la resta (ordenada por carácter).
   */
  def complemento(lOc: Ocurrencias, slOc: Ocurrencias): Ocurrencias = {

    /**
     * Función auxiliar recursiva de cola.
     *
     * @param remLOc  Parte restante de la lista lOc.
     * @param remSlOc Parte restante de la lista slOc.
     * @param acc     Acumulador que construye el resultado (en orden inverso).
     * @return La lista de ocurrencias complementaria completa (en orden correcto).
     */
    @tailrec
    def loop(remLOc: Ocurrencias, remSlOc: Ocurrencias, acc: Ocurrencias): Ocurrencias = {
      // Inspeccionar las cabezas de las listas restantes
      (remLOc, remSlOc) match {
        // --- Casos Base ---
        // 1. Si lOc se ha agotado, hemos terminado. Devolvemos el acumulador invertido.
        case (Nil, _) => acc.reverse

        // 2. Si slOc se ha agotado, pero lOc aún tiene elementos,
        //    significa que todos los elementos restantes de lOc van al resultado.
        //    Los añadimos al acumulador (que está invertido) y luego invertimos todo.
        //    Esta operación (acc.reverse ++ remainingLOc) no es una llamada recursiva.
        case (remainingLOc, Nil) => acc.reverse ++ remainingLOc

        // --- Paso Recursivo ---
        // Comparar los caracteres en la cabeza de ambas listas
        case ((lChar, lCount) :: tailLOc, (sChar, sCount) :: tailSlOc) =>

          if (lChar < sChar) {
            // El carácter de lOc es menor, por lo tanto no está en slOc (en esta parte).
            // Lo añadimos completo al acumulador y avanzamos solo en lOc.
            loop(tailLOc, remSlOc, (lChar, lCount) :: acc)
          }
          else if (lChar > sChar) {
            // El carácter de slOc es menor. Como slOc es sublista, este carácter
            // de slOc no corresponde al carácter actual de lOc. Avanzamos solo en slOc.
            loop(remLOc, tailSlOc, acc)
          }
          else { // lChar == sChar
            // Los caracteres coinciden. Calculamos la diferencia.
            val diffCount = lCount - sCount
            if (diffCount > 0) {
              // Si la cuenta es positiva, añadimos el par (carácter, diferencia) al acumulador.
              // Avanzamos en ambas listas.
              loop(tailLOc, tailSlOc, (lChar, diffCount) :: acc)
            } else {
              // Si la cuenta es 0 (o negativa, aunque no debería por la precondición),
              // este carácter se "cancela". No lo añadimos al acumulador.
              // Avanzamos en ambas listas.
              loop(tailLOc, tailSlOc, acc)
            }
          }
      }
    }

    // Iniciar la recursión llamando a la función auxiliar con las listas completas
    // y un acumulador vacío (Nil).
    loop(lOc, slOc, Nil)
  }


    /**
     * Encuentra todas las frases que son anagramas de la frase dada,
     * utilizando palabras del diccionario.
     *
     * @param frase La frase original (Lista de palabras).
     * @return Una lista de todas las frases anagramas posibles.
     */
    def anagramasDeFrase(frase: Frase): List[Frase] = {
        // Calcular las ocurrencias totales requeridas por la frase
        val ocurrenciasObjetivo = IOcFrase(frase)

        // Llamar a la función auxiliar recursiva que trabaja con ocurrencias
        buscarAnagramasRecursivo(ocurrenciasObjetivo)
    }

    /**
     * Función auxiliar recursiva para encontrar anagramas dado un conjunto de ocurrencias.
     * Intenta formar frases usando palabras del diccionario que coincidan con
     * subconjuntos de las ocurrencias dadas.
     *
     * @param ocurrenciasRestantes Las ocurrencias de caracteres que aún deben ser usadas.
     * @return Lista de frases (List[Palabra]) que usan exactamente estas ocurrencias.
     */
    private def buscarAnagramasRecursivo(ocurrenciasRestantes: Ocurrencias): List[Frase] = {
        // --- Caso Base ---
        // Si no quedan ocurrencias por usar, hemos formado una frase completa (vacía en este nivel).
        // Devolvemos una lista que contiene una única frase vacía (Nil).
        if (ocurrenciasRestantes.isEmpty) {
            List(Nil) // Una solución: la frase vacía
        } else {
            // --- Paso Recursivo ---
            // 1. Generar todas las posibles ocurrencias para la *primera* palabra de la frase.
            //    Se usa `combinaciones` sobre las ocurrencias restantes.
            val posiblesOcurrenciasPalabra = combinaciones(ocurrenciasRestantes)

            // 2. Usar for-comprehension para explorar las posibilidades:
            val solucionesParciales = for {
                // a. Tomar una combinación no vacía de ocurrencias para una palabra.
                ocPalabra <- posiblesOcurrenciasPalabra if ocPalabra.nonEmpty

                // b. Buscar en el diccionario palabras que *exactamente* tengan estas ocurrencias.
                palabra <- diccionarioPorOcurrencias.getOrElse(ocPalabra, Nil)

                // c. Calcular las ocurrencias que *quedarían* después de usar 'palabra'.
                ocSiguientes = complemento(ocurrenciasRestantes, ocPalabra)

                // d. Recursivamente, encontrar todas las frases que se pueden formar
                //    con las ocurrencias restantes ('ocSiguientes').
                fraseCola <- buscarAnagramasRecursivo(ocSiguientes)

                // e. Si la recursión encontró soluciones (fraseCola), construir la frase completa
                //    añadiendo la 'palabra' actual al inicio de cada 'fraseCola'.
            } yield palabra :: fraseCola

            // Devolver la lista acumulada de todas las frases completas encontradas.
            solucionesParciales
        }
    }
}
