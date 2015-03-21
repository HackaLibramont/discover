/**
 * Created by Meesh on 20-03-15.
 */

function testGet(){
    alert( "Passé dans le get" );
    $.get( "http://localhost:8080/index", function( data ) {
        $("#c").html( data );
        alert( "Load ok" );
    });
}
