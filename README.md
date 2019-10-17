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



Nuestra clase se inicia como data class, esta clase usará el paradigma relacional, es decir , cada entidad tendrá un id unico e irrepetible para que la base de datos no entre en conflicto a la hora de guardar datos.

![](.README_images/f93bce42.png)

A continucación creamos un Constructor,los cuales en Kotlin se colocan inmediatamente después de la declaración de la clase entre paréntesis, en este caso nuestro constructor tiene 3 argumentos.

![](.README_images/1f19f751.png)

El primer argumento nos indica que estamos declarando un valor definido con la sintaxis VAL, en ésta caso el nombre es ID y éste será de tipo String y marcamos el caso para las siguientes dos líneas.

![](.README_images/0e693172.png)

En este caso coincidió que los 3 valores son de tipo String, pero se pudo tener un valor Edad de tipo float. 

Ahora nos enfocaremos en la anotación @PrimaryKey , en donde sabemos que todas las bases de datos deben tener un identificador (id)como requisito, por lo que declaramos como PrimaryKey el ID, le colocamos información de la columna @ColumnInfo, indicando en la tabla creada como llamaremos a la columna que va a guardar el ID, en este caso llamaremos a la columna igual que los valores que declaramos.

![](.README_images/7fc71614.png)

Solo el identificador tendrá la anotación de @PrimaryKey.

El ID como String para este caso lo vamos a inicializar usando el paquete java.util.*, con la ayuda de la clase UUID la cual es de gran utilidad pues genera ID de forma aleatoria de tipo String con la finalidad de evitar duplicados, por esa razón lo inicializamos directamente.

![](.README_images/22e119da.png)

En el caso de que se quiera manejar ese ID como entero(int) solo necesitamos declararlo e inicalizarmos de la siguiente forma:

![](.README_images/4be42444.png)

# El Segundo paso de acuerdo al diagrama mostrado en el inicio, ahora es generar los DAOS.

El DAO es el acceso a la base de datos, lo cual es independiente de la interfaz de usuario. Lo cual hace el manejo de datos localmente. Sería un homologo a los REPO que se crean en MongoDB, pero en este caso se llaman DAO.

![](.README_images/926283b7.png)

En Kotlin creamos una interface con la misma nomenclatura, en este caso lo nombramos Alumno Dao, en este caso no es una clase como generalmente se crean, en este caso será una interface de acuerdo a la arquitectura de SQLite.

![](.README_images/7533075d.png)
![](.README_images/354efcce.png)

En este caso empezamos con la notación @Dao, la cual la importamos de la clase ROOM. La clase DAO unicamente contendrá el nombre de las operaciones que estaremos usando en SQL como por ejemplo: Insertar, Actualizar, Borrar, etc

![](.README_images/44a93db8.png)

En Base de datos relacionales, cuando necesitamos hacer una consulta o una petición lo invocamos con la palabra QUERY , con SELECT hacemos una búsqueda por lo tanto la notación es como se indica a continucación: @QUERY seguida de SELECT, entre comillas colocamos la consulta como se realiza en SQL, si en SQL u ORACLE anotamos lo que escribimos entre comillas nos hara la búsqueda solicitada.

![](.README_images/dc056cbc.png)

Una vez que se hace la búsqueda en la tabla alumnos y nos regrese el nombre de los alumnos en forma ascendente, colocamos la función con el nombre del método con el tipo de retorno, agregamos LiveData que previamente importamos, ésta nos será de utilidad ya que nos ayudará a estar refrescando los datos cada que se haga una captura de información. Para el tipo de retorno indicamos que es un List (que es el padre de los Arraylist) que es de tipo generico de AlumnoEntity (que fue lo que previamente indicamos que ibamos a guardar) y le indicamos que lo interprete como datos en vivo (livedata).

![](.README_images/ef722640.png)

Para insertar datos es con @INSERT,en esta linea de código le estamos indicando que en caso que haya un clonflicto por el hecho que nos aparezca un "Nombre" repetido no haga caso o lo ignore.

![](.README_images/f4d381e7.png)

La siguiente linea se refiere a la acción que se va a ejecutar en la base de datos, es decir, si se está guardando información en la base de datos que ésta se suspenda hasta que no se actualice y entonces pueda continuar.

![](.README_images/f2fd632a.png)

La siguiente linea de código es un QUERY de BORRADO, en este caso se indica que se borre todo lo de la base de datos. En este caso no usaremos esta instrucción pues no nos interesa borrar toda la información.

![](.README_images/05bf5ef8.png)


# El Tercer paso de acuerdo al diagrama mostrado en el inicio, ahora es generar la BASE DE DATOS.

![](.README_images/7bd92d36.png)

Una vez que tenemos el DAO ( la interface que tendrá las operaciones), vamos a crear una archivo para poder configurar la base de datos, en este caso creremos el archivo AlumnoRoomDatabase, pero en este caso lo ideal hubiese sido nombrar este archivo ControlEscolarRoomDatabase ya que esta base contendrá tanto alumnos, profesores, materias, etc. Lo dejaremos con el nombre dado previamente pero es importante nombrar las bases de datos de forma general y no en forma particular.

![](.README_images/669dff0b.png)

Vemos en primera instancia que es una clase abstracta, es decir, no podemos crear objetos a partir de ella, por lo tanto para que podamos hacer uso de ella debemos implementarla. La clase AlumnoRoomDatabase hereda de la clase RoomDatabase.

![](.README_images/76ec0342.png)

Una vez que ponemos la herencia de la clase RoomDatabase, esto nos indicará que y se convierte en una base de datos lo que marcamos en amarillo.

![](.README_images/74e7c63b.png)

Primero colocamos la notación @Database para la base de datos, tal notación viene desde la clase android Room. Dentro de los parentesis colocamos las entities que ocuparemos, éstas son las Tablas que creamos por ejemplo en el código tenemos la entitie "AlumnoEntity::class", en caso de que tuvieramos más entidades como por ejemplo "Profesores::class" y "Materias::class", etc tendríamos que colocar éstas entidades dentro del corchete separados cada una de las entidades separadas de ",".

![](.README_images/22099d4b.png)

La siguiente parte del código es "version=1", es importante saber que el número de versión nos indicará los cambios que hacemos en nuestras entidades, por ejemplo, si requerimos anexar una neva entidad de nombre "Calificaciones", tendremos que ahora colocar como número de version=2, en caso de no hacer éstos cambios nos marcará error.

![](.README_images/00030f50.png)

Despues de declarar el constructor AlumnoRoomDatabasevamos a crear el método abstracto alumnoDao el cual tiene un tipo de retorno AlumnoDao el cual fue creado previamente.

![](.README_images/64efe8a5.png)

NOta: Si tuvieramos mas entidades como por ejemplo "Profesor" y "Materias", tendríamos que declararlos como: abstract fun profesorDao(): ProfesorDao y abstract fun materiasDao(): MateriasDao.

A continuación tenemos el objeto companion el cual es acompañante de la clase abstracta AlumnoRoomDatabase,  lo hacemos de esta forma por que dentro de la clase abstracta generamos ahi mismo un objeto ,en teoria de esta clase unicamente debemos tener 1 objeto, no podemos repetir las bases de datos repetidas.

![](.README_images/9e7253c0.png)

Tenemos declarado un @Volatile el cual se usa en kotlin para indicar que la base de datos que inicializa en null es la misma que declaramos en el número 1, su función además es realizar todos los cambios en automático en esas otras vistas o clases, es decir si hay un cambio que se ejecuten sin necesidad de "refrescar" absolutamente nada.

![](.README_images/ce45a459.png)

En la siguiente linea de código tenemos la función getDatabase que asi la nombramos la cual observamos que recibe dos atributos o parametros y el tipo de retorno es AlumnoRoomDatabase, es decir más adelante invocaremos el método getDatabase dentro del ViewModel.

![](.README_images/010756ff.png)

El primer atributo es el contexto el cua indica donde vamos a invocar la base de datos , el activity es donde lo vamos a invocar, el segundo atributo es el scope: CoroutineScope cuya función es refrescar de inmediato los cambios que se hagan. 

La siguiente linea de código indica la INSTANCIA la cual es la base de datos como lo declaramos previamente, lo declaramos con mayúsculas por que de esta manera nos cersioramos que solo habra una base de datos, el signo de interrogación pregunta si la base de datos ya existe, en caso de que no exista se espera hasta que sea creada tal base de datos, eso me garantizará que no exista un error y se cierre la aplicación inmediatamente, le indicamos el tipo de retorno que en este caso invocamos un método que se llama syncronized el cual supervisará que cualquier cambio que se ejecute se sincronice, es decir

![](.README_images/5259e588.png)

