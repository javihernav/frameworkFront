/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $('#gesti').on('click', function () {
        $('#gestion').css('display', 'block');
        $('#img').css('display', 'none');
    });



    //variable para saber si envia datos android o no
    var data_android = 'no';
    //Paso uno
    $('#Paso_uno').on('click', function () {
        //Obtener valores del formulario del paso uno
        var name = document.getElementById('input_formulario:idnamesuit').value;
        var description = document.getElementById('input_formulario:iddescriptionsuit').value;
        var user = document.getElementById('formulario:idusuariosuitInner').value;
        var state = document.getElementById('formulario:idestadosuitInner').value;
        var paso_uno = 0;

        //limpiar los div de error
        $('#descriptionsuit').empty();
        $('#namesuit').empty();
        $('#usersuitdiv').empty();
        $('#statesuitdiv').empty();


        //Condiciones
        if (name === "") {
            $('#namesuit').append('<p>La caja de texto Nombre Suit no debe estar vacia.</p>');
        } else {
            var patron = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]*$/;
            if (name.search(patron)) {
                $('#namesuit').append('<p>No se permiten valores númericos en la nombre de Suit.</p>');
            } else {
                paso_uno += 1;
            }
        }

        if (description === "") {
            $('#descriptionsuit').append('<p>La caja de texto Descripción no debe estar vacio.</p>');
        } else {
            paso_uno += 1;
        }

        if (user === "") {
            $('#usersuitdiv').append('<p>Por favor seleccione un usuario para la suit.</p>');
        } else {
            paso_uno += 1;
        }

        if (state === "") {
            $('#statesuitdiv').append('<p>Por favor seleccione el estado de la suit.</p>')
        } else {
            paso_uno += 1;

        }


        //condicion para el paso dos
        if (paso_uno === 4) {
            $('#step-1').css('display', 'none');
            $('#step-2').css('display', 'block');
            $('#uno').addClass('disabled');
            $('#uno').removeClass('btn-primary');
            $('#dos').removeClass('disabled');
            $('#dos').addClass('btn-primary');
            alertify.set('notifier', 'position', 'bottom-right');
            var msg = alertify.success('Datos de la suite ¡¡Correctos!!');
            msg.delay(4).setContent();
        } else {
            alertify.set('notifier', 'position', 'bottom-right');
            var msg = alertify.error('Por favor verificar todos los datos');
            msg.delay(4).setContent();
        }
    });


    //Paso dos

    //devolverse al paso uno
    $('#anterior_uno').on('click', function () {
        $('#step-2').css('display', 'none');
        $('#step-1').css('display', 'block');
        $('#dos').addClass('disabled');
        $('#dos').removeClass('btn-primary');
        $('#uno').removeClass('disabled');
        $('#uno').addClass('btn-primary');
    });

    //validaciones paso dos
    $('#Paso_dos').on('click', function () {
        var nameescenario = document.getElementById('input_formulario:nombreescenario').value;
        var descriptionesce = document.getElementById('input_formulario:descriptionescenario').value;
        var ambienteesce = document.getElementById('formulario:ambienteescenarioInner').value;
        var estadoescenario = document.getElementById('formulario:estadoescenarioInner').value;
        var paso2 = 0;
        var num_dispo = 0;
        var completo = 0;
        var finaliza_2 = 'no';
        //Limpiar los div de error
        $('#nombreescenario').empty();
        $('#escenariodivdes').empty();
        $('#escenariodivambiente').empty();
        $('#estadodivescenario').empty();

        if (nameescenario === "") {
            $('#nombreescenario').append('<p>La caja de texto Nombre del Escenario no debe estar vacia.</p>');
        } else {
            var patron = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]*$/;
            if (nameescenario.search(patron)) {
                $('#nombreescenario').append('<p>No se admiten valores númericos en el nombre del escenario.</p>');
            } else {
                paso2 += 1;
            }
        }

        if (descriptionesce === "") {
            $('#escenariodivdes').append('<p>El campo Descripción no debe estar vacio.</p>');
        } else {
            paso2 += 1;
        }

        if (ambienteesce === "") {
            $('#escenariodivambiente').append('<p>Selecciones un ambiente para el escenario</p>');
        } else if (ambienteesce === "3" && ambienteesce !== null) {
            data_android = 'yes';
            paso2 += 1;
        } else {
            $('#btn-android').css('display', 'none');
            data_android = 'no';
            paso2 += 1;
        }



        if (estadoescenario === "") {
            $('#estadodivescenario').append('<p>Por favor seleccione un estado para el escenario.</p>');
        } else {
            paso2 += 1;
        }

        //Si android llega 'yes' hace las respectivas validaciones --------------- si llega 'no' vacia los campos y no hace validaciones
        if (data_android === 'yes') {
            var namedispo = document.getElementById('input_formulario:name_dispo').value;
            var puerto_appium = document.getElementById('input_formulario:puerto_appium').value;
            var plataforma_dispo = document.getElementById('input_formulario:plataforma_dispo').value;
            var appacti_dispo = document.getElementById('input_formulario:appacti_dispo').value;
            var device_dispo = document.getElementById('input_formulario:device_dispo').value;
            var version_dispo = document.getElementById('input_formulario:version_dispo').value;
            var app_dispo = document.getElementById('input_formulario:app_dispo').value;
            var urlapp_dispo = document.getElementById('input_formulario:urlapp_dispo').value;

            //Limpiar diverror del modal
            $('#diverrorappacti').empty();
            $('#diverrorapppackage').empty();
            $('#diverrordevice').empty();
            $('#diverrordisponame').empty();
            $('#diverrorplataform').empty();
            $('#diverrorpuerto').empty();
            $('#diverrorurlapp').empty();
            $('#diverrorversion').empty();

            //validacdion del modal
            if (namedispo === "") {
                $('#diverrordisponame').append('<p>La caja de texto Nombre no puede estar vacia.</p>');
                $('.modales').modal('show');
            } else {
                num_dispo += 1;
            }

            if (puerto_appium === "") {
                $('#diverrorpuerto').append('<p>La caja de texto Puerto Appium no puede estar vacia.</p>');
                $('.modales').modal('show');
            } else {
                num_dispo += 1;
            }

            if (plataforma_dispo === "") {
                $('#diverrorplataform').append('<p>La caja de texto Plataforma del dispositivo no puede estar vacia</p>');
                $('.modales').modal('show');
            } else {
                num_dispo += 1;
            }

            if (appacti_dispo === "") {
                $('#diverrorappacti').append('<p>La caja de texto App Activity no puede estar vacia</p>');
                $('.modales').modal('show');
            } else {
                num_dispo += 1;
            }

            if (device_dispo === "") {
                $('#diverrordevice').append('<p>La caja de texto App Activity no puede estar vacia</p>');
                $('.modales').modal('show');
            } else {
                num_dispo += 1;
            }

            if (version_dispo === "") {
                $('#diverrorversion').append('<p>La caja de texto App Activity no puede estar vacia</p>');
                $('.modales').modal('show');
            } else {
                num_dispo += 1;
            }

            if (app_dispo === "") {
                $('#diverrorapppackage').append('<p>La caja de texto App Package no puede estar vacia</p>');
                $('.modales').modal('show');
            } else {
                num_dispo += 1;
            }

            if (urlapp_dispo === "") {
                $('#diverrorurlapp').append('<p>La caja de texto URL App no puede estar vacia</p>');
                $('.modales').modal('show');
            } else {
                num_dispo += 1;
            }

        } else {
            $('#formulario\\:name_dispo').val("");
            $('#formulario\\:puerto_appium').val("");
            $('#formulario\\:plataforma_dispo').val("");
            $('#formulario\\:appacti_dispo').val("");
            $('#formulario\\:device_dispo').val("");
            $('#formulario\\:version_dispo').val("");
            $('#formulario\\:app_dispo').val("");
            $('#formulario\\:urlapp_dispo').val("");

        }


        if (data_android === "yes") {
            completo = paso2 + num_dispo;
        }


        if (data_android === "yes" && completo === 12) {
            finaliza_2 = 'yes';
            //Visualizar en boton de envio acorde a si envia datos android o no
            $('.btn-envio-con').css('display', 'block');
            $('.btn-envio-sin').css('display', 'none');
        } else if (data_android === "no" && paso2 === 4) {
            finaliza_2 = 'yes';
            $('.btn-envio-sin').css('display', 'block');
            $('.btn-envio-con').css('display', 'none');
        } else {
            finaliza_2 = "no";
        }

        if (finaliza_2 === 'yes') {
            $('#step-2').css('display', 'none');
            $('#step-3').css('display', 'block');
            $('#dos').addClass('disabled');
            $('#dos').removeClass('btn-primary');
            $('#tres').removeClass('disabled');
            $('#tres').addClass('btn-primary');

            alertify.set('notifier', 'position', 'bottom-right');
            var msg = alertify.success('Datos del Escenario ¡¡Correctos!!');
            msg.delay(4).setContent();
        } else {
            alertify.set('notifier', 'position', 'bottom-right');
            var msg = alertify.error('Por favor verificar todos los datos');
            msg.delay(4).setContent();
        }
    });


    //Paso tres
    //Devolverse al paso 2
    $('#anterior_dos').on('click', function () {
        $('#step-3').css('display', 'none');
        $('#step-2').css('display', 'block');
        $('#tres').addClass('disabled');
        $('#tres').removeClass('btn-primary');
        $('#dos').removeClass('disabled');
        $('#dos').addClass('btn-primary');
    });

    //Validaciones paso 3
    $('#Paso_tres').on('click', function () {
        var namecaso = document.getElementById('input_formulario:namecaso').value;
        var estadocaso = document.getElementById('formulario:estadocasoInner').value;
        var paso3 = 0;
        //Limpiar los div de error
        $('#diverrorcasonombre').empty();
        $('#diverrorestadocaso').empty();

        //validaciones de formulario
        if (namecaso === "") {
            $('#diverrorcasonombre').append('<p>La caja de texto Nombre Caso no debe estar vacia</p>');
        } else {
            var patron = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]*$/;
            if (namecaso.search(patron)) {
                $('#diverrorcasonombre').append('<p>No se admiten valores númericos en el Nombre Caso.</p>');
            } else {
                paso3 += 1;
            }
        }

        if (estadocaso === "") {
            $('#diverrorestadocaso').append('<p>Por favor seleccione un estado para el caso.</p>')
        } else {
            paso3 += 1;
        }

        if (paso3 === 2) {
            $('#step-3').css('display', 'none');
            $('#step-4').css('display', 'block');
            $('#tres').addClass('disabled');
            $('#tres').removeClass('btn-primary');
            $('#cuatro').removeClass('disabled');
            $('#cuatro').addClass('btn-primary');
            alertify.set('notifier', 'position', 'bottom-right');
            var msg = alertify.success('Datos del Caso ¡¡Correctos!!');
            msg.delay(4).setContent();
        } else {
            alertify.set('notifier', 'position', 'bottom-right');
            var msg = alertify.error('Por favor verificar los datos ¡¡Correctos!!');
            msg.delay(4).setContent();
        }

    });




    //Paso cuatro
    //Devolverse al paso 3
    $('#anterior_tres').on('click', function (e) {
        $('#step-4').css('display', 'none');
        $('#step-3').css('display', 'block');
        $('#cuatro').addClass('disabled');
        $('#cuatro').removeClass('btn-primary');
        $('#tres').removeClass('disabled');
        $('#tres').addClass('btn-primary');
        e.preventDefault();
    });



    //Filtro de items 
    var busqueda = $('#search'), titulo = $('ul li a');

    $(titulo).each(function () {
        var li = $(this);

        $(busqueda).keyup(function () {
            this.value = this.value.toLowerCase();

            var clase = $('.search i');

            if ($(busqueda).val() != '') {
                $(clase).attr('class', 'fa fa-times');
            } else {
                $(clase).attr('class', 'fa fa-search');
            }

            if ($(clase).hasClass('fa fa-search')) {
                $(clase).click(function () {
                    $(busqueda).val('');
                    $(li).parent().show();
                    $(clase).attr('class', 'fa fa-search');
                });
            }

            $(li).parent().hide();
            var txt = $(this).val();

            if ($(li).text().toLowerCase().indexOf(txt) > -1) {
                $(li).parent().show();
            }
        });
    });

    //Codigo ver contraseña actual_contraseña
    var input_actual = document.getElementById('input_actual');
    $('#ojoactual').on('click', function () {
        $('.ojo1').css('display', 'none');
        $('.ojo2').css('display', 'block');
        input_actual.type = 'text';
    });

    $('#ojoactual2').on('click', function () {
        $('.ojo1').css('display', 'block');
        $('.ojo2').css('display', 'none');
        input_actual.type = 'password';
    });
    
    //Codigo ver contraseña nueva_contrasñea
    var input_nueva = document.getElementById('input_nueva');
    $('#ojonuevo').on('click', function () {
        $('.ojo3').css('display', 'none');
        $('.ojo4').css('display', 'block');
        input_nueva.type = 'text';
    });

    $('#ojonuevo2').on('click', function () {
        $('.ojo3').css('display', 'block');
        $('.ojo4').css('display', 'none');
        input_nueva.type = 'password';
    });

});

