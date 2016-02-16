/**
 * Created by HP on 18.11.2015.
 */

function valid (f){
    if (f.style.backgroundColor == 'rgb(254, 176, 170)') {
        f.style.backgroundColor = 'white';
        f.style.border = '1px solid grey';
    }
    switch (f.name) {
        case "surname":
            if (checkName(f)) {
                return false;
            }
            break;
        case "name":
            if (checkName(f)) {
                return false;
            }
            break;
        case "birthday":
            if (checkDate(f)) {
                return false;
            }
            break;
        case "passportNumber":
            if (checkInt(f)) {
                return false;
            }
            break;
        case "passportSeries":
            if (checkInt(f)) {
                return false;
            }
            break;
        case "gradeBookId":
            if (checkInt(f)) {
                return false;
            }
            break;
        case "averageScore":
            if (checkFloat(f)) {
                return false;
            }
            break;
    }
    return true;
}

function validate_name (f){
    var errMSG = "";
    for (var i = 0; i<f.elements.length; i++) {
        if (f.elements[i].value == "" && f.attributes.id.value!="bulk") {
            errMSG += "  " + f.elements[i].name + ", ";
            f.elements[i].style.backgroundColor = '#feb0aa';
            f.elements[i].style.border = '2px solid red';
        }
        else {
            if(valid(f.elements[i])==false){
                return false;
            }
        }
    }
    if ("" != errMSG) {

        alert("Please, fill in the fields: "+ errMSG.substring(0, errMSG.length - 2) +".");
        return false;
    }
    return true;
}

function checkName(f){
    for (var i=0; i< f.value.length; i++){
        var n= f.value[i].charCodeAt(0);
        if((f.value[i]==' ') || (n>=65 && n<=90) || (n>=97 && n<=122)){
            continue;
        }
        else{
            f.style.backgroundColor = '#feb0aa';
            f.style.border = '2px solid red';
            alert("Fields " + f.name + " can only contain letters of the English alphabet.");
            return true;
        }
    }
    return false;
}

function checkDate(date){
    var num=date.value.split("-");
    var flag=false;
    if(date.value!="") {
        if (num.length != 3) {
            date.style.backgroundColor = '#feb0aa';
            date.style.border = '2px solid red';
            alert("Field " + date.name + " has a date format type YYYY-MM-DD.");
            return true;
        }
        else {
            var dat = new Date();
            if ((parseInt(num[0], 10) <= 1900) || (parseInt(num[0], 10) >= dat.getFullYear() - 14)) {
                date.style.backgroundColor = '#feb0aa';
                date.style.border = '2px solid red';
                alert("Field " + date.name + " has a date format from 1900 to" + dat.getFullYear());
                return true;
            }
            if ((new Date(num[0], num[1], num[2]) == 'Invalid Date') || ((num[2] > (new Date(num[0], num[1], 0)).getDate()))) {
                date.style.backgroundColor = '#feb0aa';
                date.style.border = '2px solid red';
                alert("In the " + date.name + " of an error: this date does not exist.");
                return true;
            }
        }
    }
    return false;
}

function checkFloat(f){
    if (f.value!="") {
        var num = f.value;
        +num == parseFloat(num);
        if ((isNaN(+num)) || (num < 0) || (num > 5)) {
            f.style.backgroundColor = '#feb0aa';
            f.style.border = '2px solid red';
            alert("Field " + f.name + " type is a real number (floating point) no more than \"5\" with two characters after the decimal point.");
            return true;
        }
        else {
            var x = f.value.split(".");
            if (f.value.split('.')[1].length != 2) {
                f.style.backgroundColor = '#feb0aa';
                f.style.border = '2px solid red';
                alert("Value in the field " + f.name + " can only contain 2 decimal places.");
                return true;
            }
            return false;
        }
    }
    return false;
}

function checkInt(n){
    if(n.value!="") {
        var num = n.value;
        +num == parseInt(n.value, 10);
        if (isNaN(+num) || num < 0) {
            n.style.backgroundColor = '#feb0aa';
            n.style.border = '2px solid red';
            alert("Field " + n.name + " is of type a positive integer");
            return true;
        }
        else {
            var count = 0;
            while (num > 1) {
                num = num / 10;
                count++;
            }
            switch (n.name) {
                case "passportNumber":
                    if (count != 4) {
                        n.style.backgroundColor = '#feb0aa';
                        n.style.border = '2px solid red';
                        alert("Field " + n.name + " can contain only 4 figures.");
                        return true;
                    }
                    break;
                default:
                    if (count != 6) {
                        n.style.backgroundColor = '#feb0aa';
                        n.style.border = '2px solid red';
                            alert("Field " + n.name + " can contain only 6 figures.");
                        return true;
                    }
                    break;
            }
        }
    }
    return false;
}

function checked(form){
    var m=document.getElementsByName('id');
    var j=0;
    for (var i=0; i< m.length; i++){
        if (m[i].checked){ j++; }
        if (j==1) {
            window.directories.href = "/person?edit=true";
            return true;
        }
    }
    window.directories.href = "/person?bulkedit=true";
    alert("To continue, you must select at least one checkbox.");
    return false;
}

