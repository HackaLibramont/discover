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

function setCursorPosition(latitude, longitude){
    console.log(latitude);
    $("#userLatitude1").val(latitude);
    $("#userLongitude1").val(longitude);
}

function findActivitiesAround(e)
{
    e.preventDefault();
    var checks;
   /* for (var i in $(["class^=filterCheckbox").(":checked"))
    {

    }*/
    var lat = $("#userLatitude1").val();
    var long = $("#userLongitude1").val();
    var dist = $("#userDistance1").val()*1000;

    $.ajax({
        url: "/entries",
        dataType: "json",
        method: "POST",
        data : {latitude:lat, longitude:long, maxTravelDistance:dist}
    }).done(function( data ){
            console.log( "Sample of data:", data.slice( 0, 100 ) );
    });
}