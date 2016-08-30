function enterAsGuest() {
    console.log("Guest");
        $(".main_c").load("/profile.html", function () {
            console.log("Load profile.html");
    });
};

function enterAsUser() {
    console.log("User");
    var username = $("#usr").val();
    var password = $("#pwd").val();
    console.log(username);
    console.log(password);

     $.ajax({
            method: "GET"
            , dataType: 'json'
            , url: "/service/login"
            , data: {
                "username" : username
                , "password" : password
            }
            , success: function (data) {
            console.log(data);
                $(".main_c").load("/profile.html", function () {
                console.log("Load profile.html");
            }
    );
    }
});
};

function register() {
    console.log("Register");
};