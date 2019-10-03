# ROOM 
SQL Lite con  ROOM, digrama de la arquitectura, obtenido de Google

![](.README_images/53996cb8.png)


Tutorial basado en la documentacion mas reciente obtenida de google:
[sitio oficial](https://developer.android.com/training/data-storage/room/index.html)


Con ROOm guaradermos información localmente en el celular, esto con la finalidad de no desperdiciar recursos en una base de datos de nube.

Hay varios niveles para guardar infromación; la primera de éstas se llama Preferencias compartidas (Shared Preferences) esta limitada ya que es unicamente para guardar un solo objeto como por ejemplo Un Usuario, un correo electrónico, nombre, password, etc

Esto se usa generalmente cuando en una aplicación ingresamos continuamente, para evitar estar ingresando id y contraseña se guarda el dato , en esta forma unicamente podemos guardar objetos sencillos como estos.

Para guardar arreglos como Historial de examenes, áreas, fechas de ejecución, calificación final etc usaremos ROOM, es como si se usará SQL pero en el celular, haciendo las mismas funcionalidades. Room tiene la ventaja que se puede exportar a MongoDB  etc

El tercer sistema para guardar información es denominado Sistemas de Archivos pero este no es muy recomendado y este no se puede exportar a una base de datos.

En este caso usaremos ROOM, por su funcionalidad y gran eficacia, ya que por ejemplo un ajustador que se encuentra usando una aplicación para levantar un siniestro en un lugar donde no se tiene señal de internet, con ROOM podemos capturar los datos del siniestro en el celular y una vez que se tenga señal se puede indicar a la aplicación que dispare la información a un servidor donde se guardará la información capturada.

Room es coloquialmente un término para decir de manera informal " dar espacio".

Para poder usar ROOM en un proyecto debemos agregar las dependencias en el build.gradle (Module: app), por lo que nos vamos  a agregar primero:

![](.README_images/547a3ea9.png)


Dentro del build.gradle podemos añadir código, declarar variables, etc. Por lo que en este caso vamos a declarar una variable en la parte de dependencias, ésta variable la usaremos para indicar la versión que estamos usando y la ocuparemos posteriormente.

![](.README_images/962fc815.png)


Las siguientes 3 lineas de código indican lo siguiente:

En la primer linea implementamos de la dependencia androidx runtime, en la segunda linea implementamos la biblioteca ktx y por último la tercer línea añadimos el paquete de testing, en éstas tres lineas usamos la variable que declaramos previamente para idnciar la versión de room que se esta usando.

![](.README_images/b00ab27e.png)

Con el signo de $ indicamos que vamos a tomar la variable declarada con valor "2.1.0"

Posteriormente agregamos las siguientes lineas de código, la cual servirá para indicar que usaremos el MVVM (Model View View Model)Modelo Vista Vista Modelo, para que podamos hacer uso de este modelo anexamos las siguentes líneas de código

![](.README_images/1fb3442f.png)

Con este modelo podremos enlazar los datos con la interfaz de usuario, estas son la última versión a usar.

La línea de código con la instrucción "courutine" se ejecutará una vez que haya un cambio en los datos que se ingresen en la interfaz de usuario, refrescará los datos.

![](.README_images/3b9fb809.png)


Una vez que ya tenemos agregadas las dependencias, lo que procede es empezar con lo que indica el diagrama de la arquitectura.

![](.README_images/53996cb8.png)

El primer paso de crear las entidades, éstas dependen de lo que voy a guardar, en est caso lo nombramos como AlumnoEntity

![](.README_images/7c73dfec.png)

Importamos 3 elementos que serán de utilidad para poder usar nuestra base de datos, los cuales se marcan en amarillo.

![](.README_images/42ddc31f.png)

Hacemos una anotación inicializando con un "@", en este caso la nombramos como @Entity, la cual para poder utilizar la importamos dentro de los 3 elementos que se mencionó anteriormente. Todas las clases que vayan a ser entidades tienen que estar anotadas con esa anotación, por lo que para poder guardarlas deben tener esa anotación, A continuación se le coloca el nombre de la tabla donde se guardará la información que se solicitará en el celular.

![](.README_images/7a6e5485.png)



Nuestra clase se inicia como data class, esta clase usará el paradigma relacional, es decir , se tendrá un id unico e irrepetible