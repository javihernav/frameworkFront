function mostrarOpcionesSelect(elementId) {
    console.log('mostrar dropdown');
dropDown = function (elementId) {
    var dropdown = document.getElementById(elementId);
    try {
        showDropdown(dropdown);
    } catch(e) {

    }
    return false;
};

showDropdown = function (element) {
    var event;
    event = document.createEvent('MouseEvents');
    event.initMouseEvent('mousedown', true, true, window);
    element.dispatchEvent(event);
};
};
