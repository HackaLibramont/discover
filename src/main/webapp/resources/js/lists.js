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
    $("#userLatitude2").val(latitude);
    $("#userLongitude2").val(longitude);
}

function findActivitiesAround(e)
{
    e.preventDefault();
    var checks = null;
    $('#checkboxes input:checked').each(function() {
        console.log($(this));
        checks.push($(this).val());
    });
    var lat = $("#userLatitude1").val();
    var long = $("#userLongitude1").val();
    var dist = $("#userDistance1").val();

    $.ajax({
        url: "/activities",
        dataType: "json",
        method: "POST",
        contentType: "application/json; charset=utf-8",
        data : JSON.stringify({
            latitude:lat,
            longitude:long,
            maxTravelDistance:dist,
            checkedCategories:checks
        })
    }).done(function( data ){
        //console.log( "Sample of data:", data.slice( 0, 100 ) );
        proximityMap([lat,long], data);
    });
}