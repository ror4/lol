/**
 * Created by Formation on 26/10/2016.
 */

$(document).ready(function() {

$( "button.toggleSuppression" ).click(function() {
    $( ".toggleSuppression" ).toggle();
});

$( "button.toggleClassement" ).click(function() {
    $( ".toggleClassement" ).toggle();
});

});



//$(function() {
//
//    $("select").click(function(){
//        $( "select option:selected").each(function(){
//            if($(this).attr("value")=="Aatrox"){
//                $("img").hide();
//                $( "img#1" ).show();}
//            if($(this).attr("value")=="Ahri"){
//                $("img").hide();
//                $( "img#2" ).show();}
//        });
//    }).change();
//
//    $("img.cachee").hide();
//});

