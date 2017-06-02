Galaxia
=====

Ejercicio de simulacion de clima de una galaxia.

Ejecutables
============
1. com.ml.Main.java - Realiza una simulacion sin necesidad de un servidor ni base de datos.
2. com.ml.MainSpringBoot.java - Levanta la aplicacion en modo servidor con conexion a un MySQL (ver parametros en src/main/resources/application.properties)
  Al levantar ejecuta un job que limpia la DB, carga los datos inicia y realiza la simulacion a 10 años.
  Expone 2 servicios REST que retornan JSON:
     1. GET /clima?dia=NUMERO_DIA    ejemplo GET /clima?dia=566
     2. GET /estadisticas