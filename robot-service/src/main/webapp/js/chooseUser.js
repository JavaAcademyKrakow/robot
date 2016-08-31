/**
    Communicate with guest service
*/
function enterAsGuest() {

     $.ajax({
        method: "GET"
        , url: "/service/guest"
        , success: function (data) {
            goToProfile(data.categories);
        }
     });
};

/**
    Communicate with login service
*/
function enterAsUser() {

    var username = $("#usr").val();
    var password = $("#pwd").val();

     $.ajax({
        method: "POST"
        , dataType: 'json'
        , url: "/service/login"
        , data: {
            "username" : username
            , "password" : password
            }
        , success: function (data) {
            goToProfile(data.categories);
        }
     });
};

/**
    Communicate with register service
*/
function register() {

    var email = $('#email').val();
    var username = $("#usr_re").val();
    var password = $("#pwd_re").val();

    $.ajax({
        method: "POST"
        , dataType: 'json'
        , url: "/service/register"
        , data: {
            "email" : email
            , "username" : username
            , "password" : password
        }
        , success: function (data) {
            goToProfile(data.categories);
        }
    });
};

/**
    We go to profile. We need to load user profiles to select it in new view.
*/
function goToProfile(data) {
    $(".main_c").load("/profile.html", function () {
        initCheckBox(data);
    });
};
