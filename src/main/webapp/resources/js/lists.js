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


function findActivitesAround()
{
    var checks;
    for (var i : $(["class^=filterCheckbox").(":checked"))
    {

    }
    var lat = $("#userLatitude").val();
    var long = $("#userLongitude").val();
    var dist = $("#userDistance").val();
    $.ajax(
        url: "/entries",
        data : {latitude:lat, longitude:long, distance:dist, }
    ).done(data) {

    }
}