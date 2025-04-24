import Anagramas._

println("--- Iniciando Pruebas para Anagramas ---")

// --- 1. Pruebas para IOcPal ---
println("--- Probando IOcPal ---")

// Caso 1.1: Palabra simple
val p1_1 = "Roca"
val e1_1 = List(('a', 1), ('c', 1), ('o', 1), ('r', 1))
val r1_1 = IOcPal(p1_1)
assert(r1_1 == e1_1)

// Caso 1.2: Palabra con letras repetidas
val p1_2 = "Mocosos"
val e1_2 = List(('c', 1), ('m', 1), ('o', 3), ('s', 2))
val r1_2 = IOcPal(p1_2)
assert(r1_2 == e1_2)

// Caso 1.3: Palabra vacia
val p1_3 = ""
val e1_3 = List()
val r1_3 = IOcPal(p1_3)
assert(r1_3 == e1_3)

// Caso 1.4: Ignorar no-letras y mayúsculas
val p1_4 = "Oca_sos!"
val e1_4 = List(('a', 1), ('c', 1), ('o', 2), ('s', 2))
val r1_4 = IOcPal(p1_4)
assert(r1_4 == e1_4)

// Caso 1.5: Palabra mas larga
val p1_5 = "Anagrama"
val e1_5 = List(('a', 4), ('g', 1), ('m', 1), ('n', 1), ('r', 1))
val r1_5 = IOcPal(p1_5)
assert(r1_5 == e1_5)

// Caso 1.6: Palabra corta en mayusculas
val p1_6 = "SOS"
val e1_6 = List(('o', 1), ('s', 2))
val r1_6 = IOcPal(p1_6)
assert(r1_6 == e1_6)

println("Pruebas IOcPal OK.")

// --- 2. Pruebas para IOcFrase ---
println("--- Probando IOcFrase ---")

// Caso 2.1: Frase simple
val f2_1 = List("Yo", "como")
val e2_1 = List(('c', 1), ('m', 1), ('o', 3), ('y', 1))
val r2_1 = IOcFrase(f2_1)
assert(r2_1 == e2_1)

// Caso 2.2: Frase con palabras repetidas
val f2_2 = List("Moco", "moco")
val e2_2 = List(('c', 2), ('m', 2), ('o', 4))
val r2_2 = IOcFrase(f2_2)
assert(r2_2 == e2_2)

// Caso 2.3: Frase vacia
val f2_3 = List()
val e2_3 = List()
val r2_3 = IOcFrase(f2_3)
assert(r2_3 == e2_3)

// Caso 2.4: Frase con strings vacios
val f2_4 = List("Roca", "", "y", "")
val e2_4 = List(('a', 1), ('c', 1), ('o', 1), ('r', 1), ('y', 1))
val r2_4 = IOcFrase(f2_4)
assert(r2_4 == e2_4)

// Caso 2.5: Frase mas compleja
val f2_5 = List("Ocasos", "y", "Cayo")
val e2_5 = List(('a', 2), ('c', 2), ('o', 3), ('s', 2), ('y', 2))
val r2_5 = IOcFrase(f2_5)
assert(r2_5 == e2_5)

// Caso 2.6: Frase con ocurrencias equivalentes a una palabra
val f2_6 = List("moco", "sos")
val e2_6 = List(('c', 1), ('m', 1), ('o', 3), ('s', 2)) // Igual que IOcPal("mocosos")
val r2_6 = IOcFrase(f2_6)
assert(r2_6 == e2_6)

println("Pruebas IOcFrase OK.")

// --- 3. Pruebas para anagramasDePalabra ---
println("--- Probando anagramasDePalabra ---")

// Caso 3.1: Palabra en diccionario
val p3_1 = "roca"
val e3_1 = List("roca")
val r3_1 = anagramasDePalabra(p3_1)
assert(r3_1.toSet == e3_1.toSet)

// Caso 3.2: Búsqueda insensible a mayusculas
val p3_2 = "Roca"
val e3_2 = List("roca")
val r3_2 = anagramasDePalabra(p3_2)
assert(r3_2.toSet == e3_2.toSet)

// Caso 3.3: Otra palabra del diccionario
val p3_3 = "Cayo"
val e3_3 = List("cayo")
val r3_3 = anagramasDePalabra(p3_3)
assert(r3_3.toSet == e3_3.toSet)

// Caso 3.4: Palabra sin anagramas en diccionario
val p3_4 = "Pato"
val e3_4 = List()
val r3_4 = anagramasDePalabra(p3_4)
assert(r3_4.toSet == e3_4.toSet)

// Caso 3.5: Palabra vacía
val p3_5 = ""
val e3_5 = List()
val r3_5 = anagramasDePalabra(p3_5)
assert(r3_5.toSet == e3_5.toSet)

// Caso 3.6: Palabra corta insensible a mayusculas
val p3_6 = "SoS"
val e3_6 = List("sos")
val r3_6 = anagramasDePalabra(p3_6)
assert(r3_6.toSet == e3_6.toSet)

// Caso 3.7: Palabra casi anagrama pero no igual
val p3_7 = "Ocmoso" // IOcPal -> [('c',1),('m',1),('o',3),('s',1)]
val e3_7 = List()
val r3_7 = anagramasDePalabra(p3_7)
assert(r3_7.toSet == e3_7.toSet)

println("Pruebas anagramasDePalabra OK.")

// --- 4. Pruebas para combinaciones ---
println("--- Probando combinaciones ---")

// Caso 4.1: Ocurrencias vacias
val oc4_1 = List()
val e4_1 = List(List())
val r4_1 = combinaciones(oc4_1)
assert(r4_1.toSet == e4_1.toSet)

// Caso 4.2: Una letra, una ocurrencia
val oc4_2 = List(('a', 1))
val e4_2 = List(List(), List(('a', 1)))
val r4_2 = combinaciones(oc4_2)
assert(r4_2.toSet == e4_2.toSet)

// Caso 4.3: Una letra, multiples ocurrencias
val oc4_3 = List(('a', 2))
val e4_3 = List(List(), List(('a', 1)), List(('a', 2)))
val r4_3 = combinaciones(oc4_3)
assert(r4_3.toSet == e4_3.toSet)

// Caso 4.4: Dos letras, una ocurrencia cada una
val oc4_4 = List(('a', 1), ('b', 1))
val e4_4 = List(List(), List(('a', 1)), List(('b', 1)), List(('a', 1), ('b', 1)))
val r4_4 = combinaciones(oc4_4)
assert(r4_4.toSet == e4_4.toSet)

// Caso 4.5: Dos letras, diferentes ocurrencias
val oc4_5 = List(('a', 2), ('b', 1))
val e4_5 = List(List(), List(('a', 1)), List(('a', 2)), List(('b', 1)), List(('a', 1), ('b', 1)), List(('a', 2), ('b', 1)))
val r4_5 = combinaciones(oc4_5)
assert(r4_5.toSet == e4_5.toSet)

// Caso 4.6: Tres letras
val oc4_6 = List(('a', 1), ('b', 1), ('c', 1))
val e4_6 = List(List(), List(('a',1)), List(('b',1)), List(('c',1)), List(('a',1),('b',1)), List(('a',1),('c',1)), List(('b',1),('c',1)), List(('a',1),('b',1),('c',1)))
val r4_6 = combinaciones(oc4_6)
assert(r4_6.toSet == e4_6.toSet)

println("Pruebas combinaciones OK.")

// --- 5. Pruebas para complemento ---
println("--- Probando complemento ---")


// Caso 5.1: Resta simple
val lOc5_1 = List(('a', 2), ('b', 1))
val slOc5_1 = List(('a', 1))
val e5_1 = List(('a', 1), ('b', 1))
val r5_1 = complemento(lOc5_1, slOc5_1)
assert(r5_1 == e5_1)

// Caso 5.2: Resta completa de un elemento
val lOc5_2 = List(('a', 2), ('b', 1))
val slOc5_2 = List(('a', 2))
val e5_2 = List(('b', 1))
val r5_2 = complemento(lOc5_2, slOc5_2)
assert(r5_2 == e5_2)

// Caso 5.3: Resta de multiples elementos no contiguos
val lOc5_3 = List(('a', 2), ('b', 1), ('c', 3))
val slOc5_3 = List(('a', 1), ('c', 2))
val e5_3 = List(('a', 1), ('b', 1), ('c', 1))
val r5_3 = complemento(lOc5_3, slOc5_3)
assert(r5_3 == e5_3)

// Caso 5.4: Ejemplo documentación
val lOc5_4 = List(('a', 1), ('c', 2), ('o', 1))
val slOc5_4 = List(('c', 1))
val e5_4 = List(('a', 1), ('c', 1), ('o', 1))
val r5_4 = complemento(lOc5_4, slOc5_4)
assert(r5_4 == e5_4)

// Caso 5.5: Restar lista vacía
val lOc5_5 = List(('a', 1), ('b', 2))
val slOc5_5 = List()
val e5_5 = List(('a', 1), ('b', 2))
val r5_5 = complemento(lOc5_5, slOc5_5)
assert(r5_5 == e5_5)

// Caso 5.6: Restar todo
val lOc5_6 = List(('a', 1), ('b', 2))
val slOc5_6 = List(('a', 1), ('b', 2))
val e5_6 = List()
val r5_6 = complemento(lOc5_6, slOc5_6)
assert(r5_6 == e5_6)

// Caso 5.7: Restar elemento intermedio
val lOc5_7 = List(('a', 1), ('b', 2), ('c', 1))
val slOc5_7 = List(('b', 2))
val e5_7 = List(('a', 1), ('c', 1))
val r5_7 = complemento(lOc5_7, slOc5_7)
assert(r5_7 == e5_7)

println("Pruebas complemento OK.")

// --- 6. Pruebas para anagramasDeFrase ---
println("--- Probando anagramasDeFrase ---")

// Caso 6.1: Frase de una palabra del diccionario
val f6_1 = List("Roca")
val e6_1 = List(List("roca"))
val r6_1 = anagramasDeFrase(f6_1)
assert(r6_1.toSet == e6_1.toSet)

// Caso 6.2: Anagrama simple de dos palabras
val f6_2 = List("Yo", "Roca")
val e6_2 = List(List("roca", "yo"), List("yo", "roca"))
val r6_2 = anagramasDeFrase(f6_2)
assert(r6_2.toSet == e6_2.toSet)

// Caso 6.3: Palabra del diccionario y combinación de palabras
val f6_3 = List("Moco", "Sos")
val e6_3 = List(List("mocosos"), List("sos", "moco"))
val r6_3 = anagramasDeFrase(f6_3)
assert(r6_3.toSet == e6_3.toSet)

// Caso 6.4: Frase vacia
val f6_4 = List()
val e6_4 = List(List()) // Una lista que contiene una frase vacía
val r6_4 = anagramasDeFrase(f6_4)
assert(r6_4.toSet == e6_4.toSet)

// Caso 6.5: Frase sin anagramas posibles
val f6_5 = List("Hola", "Mundo")
val e6_5 = List()
val r6_5 = anagramasDeFrase(f6_5)
assert(r6_5.toSet == e6_5.toSet)

// Caso 6.6: Frase con ocurrencias no formables
val f6_6 = List("y", "y")
val e6_6 = List()
val r6_6 = anagramasDeFrase(f6_6)
assert(r6_6.toSet == e6_6.toSet)

// Caso 6.7: Otra frase sin solucion
val f6_7 = List("Cosas", "y") // IOcF = [('a',1),('c',1),('o',1),('s',2),('y',1)]
val e6_7 = List()
val r6_7 = anagramasDeFrase(f6_7)
assert(r6_7.toSet == e6_7.toSet)

// Caso 6.8: Anagrama de dos palabras (variante)
val f6_8 = List("Moco", "Cayo")
val e6_8 = List(List("moco", "cayo"), List("cayo", "moco"))
val r6_8 = anagramasDeFrase(f6_8)
assert(r6_8.toSet == e6_8.toSet)

println("Pruebas anagramasDeFrase OK.")

println()
println("--- Pruebas Anagramas finalizadas exitosamente! ---")