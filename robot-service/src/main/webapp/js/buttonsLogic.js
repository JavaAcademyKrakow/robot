$(document).ready(function() {
$('#refresh').click(function(){
    $.ajax({
        method: "GET"
        , url: "http://10.30.0.103:8080/reset"
        , dataType: 'jsonp'
        , jsonpCallback: "myFunc",
        success: function (data) {
            console.log(data);
        }
    });
});
});

