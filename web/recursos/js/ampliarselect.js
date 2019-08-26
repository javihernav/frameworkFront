/**
 * Funciones para ampliar y reducir el select de pasos 
 */
$(document).ready(function(){
    console.log('ampliar select');
    $('.busca').focus(function(){
        $('#accionespaso').attr('size',$('#accionespaso option').length);
    });
});