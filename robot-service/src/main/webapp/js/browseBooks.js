var books = [];
var numberOfPages;
var currentPage = 1;

var firstRow = "#first_row";
var secondRow = "#second_row";
var thirdRow = "#third_row";

function loadBooks() {

    for(var i=0; i<12; i++) {

        var row = firstRow;
        if(i>7)
            row = thirdRow;
        else if(i>3)
            row = secondRow;

        var s = row.concat(' > .book:nth-child(').concat((i%4)+1).concat(')');
        $(s).html("");

        if(i < books.length) {
            $(s).prepend('<div style="padding-left: 5px; padding-top: 10px; padding-bottom: 10px"><img id="theImg" src="book.png" width="100" height="100" /></div>')
            $(s).append('<div><strong>Title: </strong>'.concat(books[i].title).concat('</div>'));
            $(s).append('<div><strong>Print House: </strong>'.concat(books[i].printHouse).concat('</div>'));
            $(s).append('<div><strong>Year: </strong>'.concat(books[i].year).concat('</div>'));

            for(var j=0; j<books[i].authors.length; j++) {
                $(s).append('<div><strong>Author: </strong>'.concat(books[i].authors[j]).concat('</div>'));
            }

            $(s).append('<div><strong>Category: </strong>'.concat(books[i].category).concat('</div>'));
            $(s).append('<div><strong>Year: </strong>'.concat(books[i].year).concat('</div>'));
            $(s).append('<div><strong>Old Price: </strong>'.concat(books[i].oldPrice).concat('</div>'));
            $(s).append('<div><strong>New Price: </strong>'.concat(books[i].newPrice).concat('</div>'));
            $(s).append('<div><strong>Currency: </strong>'.concat(books[i].currency).concat('</div>'));
            $(s).append('<button><strong><a id="description'.concat(i).concat('">Description</a></strong></button>'));
            $(s).append('<div><a href="'.concat(books[i].link).concat('">Go To Page</div>'));
        }
    }
};

function getNumberOfPages() {

         $.ajax({
            method: "GET"
            , url: "/service/numberofpages"
            , success: function (data) {
                numberOfPages = data;
                $(function() {
                    $("#nav_books").pagination({
                        items: 200,
                        itemsOnPage: 12,
                        pages: data,
                        cssStyle: 'dark-theme',
                        onPageClick: function(pageNum){
                            changePage(pageNum);
                        }
                    });
                });
            }
         });
};

function changePage(pageNumber) {
        var tab = JSON.stringify(removeHash(checkSelectedCheckBox()));

         $.ajax({
            method: "GET"
            , dataType: 'json'
            , url: "/service/categories"
            , data: {
                "categories" : tab
                , "pageNumber" : pageNumber
                }
            , success: function (data) {
                books = data.books;
            },
         }).done(function() {
                loadBooks();
         });
};
