/**
    We initialize checked attributes in our check fields
*/
function initCheckBox(data) {

    for(i=0; i<data.length; i++) {
        var hash ='#';
        var check = hash.concat(data[i].toString());
        if(checkCkeckBox(check) == false) setCheckBox(check, true);
    }
    updateNumberOfSelectedCategories();
};

/**
    Check if boxes are checked
*/
function checkCkeckBox(checkbox) {
        if($(checkbox).is(':checked')) {
            return true;
        } else {
            return false;
        }
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
    var numberOfSelectedCategories = checkSelectedCheckBox();
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

    var result = 0;

    for(i=0; i<categories.length; i++) {
        if(checkCkeckBox(categories[i]))
            result++;
    }
    return result;
};