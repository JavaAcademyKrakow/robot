books = [];

var firstRow = "#first_row";
var secondRow = "#second_row";
var thirdRow = "#third_row";

function loadBooks() {

    for(var i=0; i<books.length; i++) {

        var row = firstRow;
        if(i>7)
            row = thirdRow;
        else if(i>3)
            row = secondRow;

        var s = row.concat(' > .book:nth-child(').concat((i%4)+1).concat(')');
        $(s.concat(" > div.bookId")).html(books[i].bookID);
        $(s.concat(" > div.bookName")).html(books[i].title);
        //$(s).prepend('<img id="theImg" src="book.png" />')
    }
}