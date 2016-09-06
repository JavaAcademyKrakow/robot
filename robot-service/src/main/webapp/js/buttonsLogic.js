$(document).ready(function() {
$('#refresh').click(function(){
    $.ajax({
        method: "GET"
        , url: "http://localhost:8090/reset"
        , dataType: 'jsonp'
        , jsonpCallback: "myFunc",
        success: function (data) {
            console.log(data);
        }
    });
});
});
