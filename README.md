#Prueba Vitro
##Sumario
- [Descripción](#descripcion)
- [Estructura](#estructura)
- [Prerequisitos](#prerequisitos)
- [Serenity](#serenity)
- [Jenkins](#jenkins)

###Descripción
Proyecto realizado por Francisco López Baena para el uso solo de la prueba de selección QA pruebas automáticas para la empresa Vitro


###Estructura
La estructura de paquetes es la siguiente:
- El directorio con el nombre feature que contiene los casos de pruebas con sus escenarios. Estos casos de pruebas son archivo de texto donde se escriben los criterios de aceptación en formato Gherkin.
- Otra carpeta con el nombre stepdefinition donde Cucumber va a poder asociar qué acciones ejecutar asociadas a cada paso de cada criterio de aceptación definido en las distintas features.
- La carpeta step contiene la descripción detallada de los pasos definidos en stepdefinition.
- En el directorio pageobject se guardará las clases que contiene el DOM de los elementos de la aplicación
- Y en la carpeta entidad se crearan las clases de los objetos
- La carpeta testrunnere contiene la clase runner que es el orquestador. Aquí está configurado que se utilice serenity para mostrar el resultado de la ejecución.

###Prerequisitos
El proyecto creará un carpeta llamada Logs en la ruta C:\Logs. En ella se creará el ejecutable de selenium webdriver y el fichero log (un por cada ejecución). Hay que tener permisos para poder crear carpetas en C:\. Si el Sistema operativo es Linux la ruta será $HOME/Logs

###Documentación
La documentación del proyecto creada con javadoc está en la ruta target\site\documentacion

###Serenity
Se ha integrado la prueba con Serenity para la presentación del resultado del test, el resultado se guardará en el directorio target\site\serenity de la carpeta del proyecto

###Jenkins

