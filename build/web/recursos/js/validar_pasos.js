
function validar() {
    //Definicion de variables 
    var accion = document.getElementById('formulario:accionespaso').value;

    //Validacion Ingresar URL
    if (accion === "") {
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
    } else if (accion === "Ingresar URL") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "Ir Atras") {

        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Ir Adelante") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Refrescar") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Cerrar Navegador") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Cambiar Frame") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "Cambiar a Frame Principal") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Volver a Ventana Original") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Cambiar Pestana") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === 'Click Boton "Aceptar"') {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === 'Click Boton "Cancelar"') {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Ingresar Texto Alert") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "SendKey") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "Presionar Tecla") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "Limpiar Campo") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
    } else if (accion === "Marcar Todos CheckBox") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
    } else if (accion === "Desmarcar Todos CheckBox") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
    } else if (accion === "Seleccionar Elemento Lista(Index)") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "Seleccionar Elemento Lista (Value)") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "Seleccionar Elemento Lista (Visible Text)") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "Deseleccionar Elemento Lista (Index)") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "Deseleccionar Elemento Lista (value)") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "Deseleccionar Elemento Lista (Visible Text)") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "Click") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
    } else if (accion === "Doble Click") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
    } else if (accion === "Click Derecho") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
    } else if (accion === "Scroll") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.coorX').css('display', 'block');
        $('.coorY').css('display', 'block');
    } else if (accion === "Esperar (Millisegundos)") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "Tomar Evidencia") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Tomar Evidencia Elemento") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
    } else if (accion === "Enviar formulario") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
    } else if (accion === "Mover A Elemento Coordenadas") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
        $('.data').css('display', 'block');
        $('.coorX').css('display', 'block');
        $('.coorY').css('display', 'block');
    } else if (accion === "Esperar elemento") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "Ejecutar Aplicacion") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "Condicional") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "Mover A Elemento") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
    } else if (accion === "Funcion de teclado") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
    } else if (accion === "Scroll Elemento") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
        $('.data').css('display', 'block');
        $('.coorX').css('display', 'block');
        $('.coorY').css('display', 'block');
    } else if (accion === "Scroll Izquierda a Derecha") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.coorY').css('display', 'block');
    } else if (accion === "Scroll Derecha a Izquierda") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.coorY').css('display', 'block');
    } else if (accion === "Scroll Abajo a Arriba") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Scroll Arriba a Abajo") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Arrastrar y Soltar") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
        $('.data').css('display', 'block');
        $('.coorX').css('display', 'block');
        $('.coorY').css('display', 'block');
    } else if (accion === "Menu") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Ir Atras Navegador") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Calibrar Coordenadas") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.coorX').css('display', 'block');
        $('.coorY').css('display', 'block');
    } else if (accion === "Cambiar Navegador") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Ejecutar Paquete Aplicacion") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Sikuli Click" || accion === "Sikuli Doble Click" || accion === "Sikuli Click Robot" || accion === "Sikuli Click Derecho" || accion === "Sikuli Espera Imagen" || accion === "Sikuli Focus Imagen") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.valuestep').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "Sikuli Click Especial" || accion === "Sikuli Doble Click Especial") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.valuestep').css('display', 'block');
        $('.data').css('display', 'block');
        $('.coorX').css('display', 'block');
        $('.coorY').css('display', 'block');

        // Funciones de teclado o sikuli
    } else if (accion === "Sikuli Pegar Texto" || accion === "Sikuli Escribir Robot") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.valuestep').css('display', 'block');
    } else if (accion === "Enter" || accion === "Escape" || accion === "Tab" || accion === "BackSpace" || accion === "Home" || accion === "End" || accion === "Delete" || accion === "Copy" || accion === "Cut" || accion === "Paste" || accion === "Seleccionar todo (A)" || accion === "Seleccionar todo (E)" || accion === "Find (F)" || accion === "Find (B)" || accion === "Close Tab" || accion === "Next" || accion === "Back" || accion === "Close All" || accion === "AvPag" || accion === "RePag") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
    } else if (accion === "Finalizar Ejecucion") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Cerrar Aplicacion") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Nueva Pestana") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "SendKey Excel") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "Loop") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "EndLoop") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Fila Inicial Excel") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "Click JS") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
    } else if (accion === "Click Derecho En Pagina") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
    } else if (accion === "Click Mouse") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
    } else if (accion === "Click Robot") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.typestep').css('display', 'block');
        $('.valuestep').css('display', 'block');
    } else if (accion === "Borrar Cookies") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "SendKey Excel Robot") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "SendKey Robot") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "SendKey Excel Gui") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.data').css('display', 'block');
    } else if (accion === "SendKey Gui") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
        $('.navegador').css('display', 'block');
        $('.data').css('display', 'block');
    }
     else if (accion === "probar servicio web soap") {
        //se desactivan primero por si realiza algun cambio en el select
        $('.navegador').css('display', 'none');
        $('.typestep').css('display', 'none');
        $('.valuestep').css('display', 'none');
        $('.data').css('display', 'none');
        $('.coorX').css('display', 'none');
        $('.coorY').css('display', 'none');
        //Se realiza la respectiva habilitacion de los input que necesita esta accion
//        $('.valuestep').css('display', 'block');
        $('.data').css('display', 'block');
    }

}

function refresh() {
    $('#divtable').load(' #divtable');
}

function despues() {
    alertify.set('notifier', 'position', 'bottom-right');
    var msg = alertify.success('Contraseña Cambiada');
    msg.delay(3).setContent();
    setTimeout("window.location.href = 'inicio.xhtml'", 4000);
}

function cargando() {
    $('.card-body').css('display', 'none');
    $('.imagenajax').css('display', 'block');
}

//function esServicio() {
////    debugger;
//    console.log('esServicio');
//    var ambiente = document.getElementsByClassName('ambienteescenario')[0].value;
//    console.log('ambiente: ' + ambiente);
//    if (ambiente === "4") {
//        console.log('ocultando no servicios');
//        $('.tituloAccion').css('display', 'none');
//        console.log('esta vacio txtAccion: ' + $('#txtAccion').attr('val'));
//        $('.busca').hide();
//        $('.accionespaso').hide();
//        $('.parametro').css('display','block');
//        $('.valuestep').show();
//    }
//}

function cargarDatosFuncion() {
    ruta = document.getElementsByClassName("txtparametro")[0].value;
    if (window.XMLHttpRequest)
    {
        // Objeto para IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {
        // Objeto para IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

// Abrimos el archivo que esta alojado en el servidor cd_catalog.xml
    xmlhttp.open("GET", ruta, false);
    xmlhttp.send();

// Obtenemos un objeto XMLDocument con el contenido del archivo xml del servidor
    xmlDoc = xmlhttp.responseXML;

// Obtenemos todos los nodos denominados element del archivo xml
    var elementos = xmlDoc.getElementsByTagName("element");
    console.log(elementos.getAttribute("name"));
// Hacemos un bucle por todos los elementos foro
    for (var i = 0; i < elementos.length; i++)
    {
        // Del elemento elemento, obtenemos del primer elemento denominado "titulo"
        // el valor del primer elemento interno
        titulo = elementos[i].getElementsByTagName("titulo")[0].childNodes[0].nodeValue

        url = elementos[i].getElementsByTagName("url")[0].childNodes[0].nodeValue

        document.write("<div>");
        document.write("<span>");
        document.write(titulo);
        document.write("</span><span>");
        document.write("<a href='" + url + "' target='_blank'>" + url + "</a>");
        document.write("</span>");
        document.write("</div>");
    }
}
//borra los datos del formulario despues de haber agreagado el paso
//la función no funciona con fases
//function borrarCampos(){
//    console.log("borrarCampos");
//    var accionespaso = document.getElementsByClassName("accionespaso")[0];
//    var typestep = document.getElementById("typestep");
//    var data = document.getElementById("data");//parametro
//    var coorY = document.getElementById("coorY");
//    var navegador = document.getElementById("navegador");
//    var valuestep = document.getElementById("valuestep");
//    var coorX= document.getElementById("coorX");
//    
//    accionespaso.innerHTML="";
//    typestep.innerHTML="";
//    data.innerHTML="";
//    coorY.innerHTML="";
//    navegador.innerHTML="";
//    valuestep.innerHTML="";
//    coorX.innerHTML="";
//} 



//funciones para escanear servicio soap
//function verDatosSOAP(metodo,valor)
//
//    {
//
//       var pl = new SOAPClientParameters();
//
//        aux = valor.split('|');
//
//        //alert(aux.length);
//
//        for (x=0;x<aux.length;x++) {
//
//            pl.add("in"+x, aux[x]);
//
//        }
//
//        SOAPClient.invoke(url, metodo, pl, true, mostrarDatosSOAP);
//
//    }

//    function mostrarDatosSOAP(r, soapResponse)
//
//    {
//
//        if (soapResponse.xml) {                // IE
//
//            respuesta = soapResponse.xml;
//
//        } else {                                       // OTROS
//
//            respuesta = (new XMLSerializer()).serializeToString(soapResponse);
//
//        }
//
//        alert(respuesta);
//
//    }

//    verDatosSOAP("listado","bbdd|codigo|valor");   // metodo, parametros
