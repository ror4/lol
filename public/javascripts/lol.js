/**
 * Created by Formation on 26/10/2016.
 */

$(function() {

    $("select").click(function(){
        $( "select option:selected").each(function(){
            if($(this).attr("value")=="Aatrox"){
                $("img").hide();
                $( "img#1" ).show();}
            if($(this).attr("value")=="Ahri"){
                $("img").hide();
                $( "img#2" ).show();}
        });
    }).change();

    $("img").hide();

    // $('#texteJQ').html('Hello world. Ce texte est affiché par jQuery.');

    // $('.rouge').css('background','red');
    // $('.rouge').css('color','yellow');

    // $('select').css('color','blue');
});

