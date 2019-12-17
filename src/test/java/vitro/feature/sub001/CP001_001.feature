Feature: Prueba vitro

Scenario Outline: Prueba vitro chrome

Given La aplicacion <app> con el identificador <identifier> usuario <user> y clave <pass>
When Navego al menu <menu1> y pulso la opcion <option>
  And Selecciono la opcion uno <combo1> opcion dos <combo2> y opcion tres <combo3>
Then Verifico que se muestran los resultados <total>
  And Selecciono el menu <menu2>
  And Pongo visible el componente <widget>
  And Cerrar sesion

Examples:
| app                     | identifier  | user | pass    | menu1              | option               | combo1    | combo2  | combo3               | total  | menu2  | widget   |
| http://v3.interqc.com/  | 3994        | test | interqc | Results and Lots  | Manual Results input  | FACSLyric | BM0619N | CD3+ abs-CD3+ [abs.] |  2     | Home   | Results  |
