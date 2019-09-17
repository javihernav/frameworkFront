var table;
$(document).ready(function () {
    table = $('#myAdvancedTable').DataTable();
    table.MakeCellsEditable({
        "onUpdate": myCallbackFunction,
        "inputCss":'my-input-class',
        "columns": [5,6,7,9],
        "allowNulls": {
            "columns": [9],
            "errorClass": 'error'
        },
        "confirmationButton": { // could also be true
            "confirmCss": 'btn btn-success',
            "cancelCss": 'my-cancel-class'
        },
        "inputTypes": [
            {
                "column": 5,
                "type": "textarea",
                "options": null
            }
        ]
    });

});

function myCallbackFunction (updatedCell, updatedRow, oldValue) {
    console.log("The new value for the cell is: " + updatedCell.data());
    console.log("The old value for that cell was: " + oldValue);
    console.log("The values for each cell in that row are: " + updatedRow.data());
}
