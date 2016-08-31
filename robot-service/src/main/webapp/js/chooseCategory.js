/**
    We initialize checked attributes in our check fields
*/
function initCheckBox(data) {

    for(var i=0; i<data.length; i++) {
        var hash ='#';
        var check = hash.concat(data[i].toString());
        if(checkCkeckBox(check) === false)
            setCheckBox(check, true);
    }
    updateNumberOfSelectedCategories();
};

/**
    Check if boxes are checked
*/
function checkCkeckBox(checkbox) {
        return ($(checkbox).is(':checked'));
};

/**
    Check or uncheck boxes
*/
function setCheckBox(checkbox, val) {
    $(checkbox).attr("checked", val);
};

/**
    Update field with number of selected boxes
*/
function updateNumberOfSelectedCategories() {
    var numberOfSelectedCategories = checkSelectedCheckBox().length;
    $('#selectedCategories').html("The number of selected categories: " + numberOfSelectedCategories);
};

function checkSelectedCheckBox() {
    var categories = [];
    categories.push('#EDUCATION');
    categories.push('#TRAVEL');
    categories.push('#LIVE_STYLE');
    categories.push('#SEX');
    categories.push('#MEDICINE');
    categories.push('#ADVENTURE');

    var selectedCategories = [];

    for(var i=0; i<categories.length; i++) {
        if(checkCkeckBox(categories[i]))
            selectedCategories.push(categories[i]);
    }
    return selectedCategories;
};

function removeHash(tab) {

    for(var i=0; i<tab.length; i++) {
        tab[i] = tab[i].substring(1, tab[i].length);
    }
    return tab;
};


function goToBookBrowser() {
        var tab = JSON.stringify(removeHash(checkSelectedCheckBox()));
        console.log(tab);

         $.ajax({
            method: "GET"
            , dataType: 'json'
            , url: "/service/books"
            , data: {
                "books" : tab
                }
            , success: function (data) {

                books = data.books;
                console.log(books);
                $(".main_c").load("/bookBrowser.html", function () {
                    loadBooks();
                });
            }
         });
}