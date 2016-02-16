/**
 * Created by Елена on 15.12.2015.
 */

function bulkCheck(obj){
    // Получаем NodeList дочерних элементов input формы:
    var items = obj.form.getElementsByTagName("input");
    // цикл по элементам формы:
    for (var i = 0; i < items.length; i++) {
        if (items.item(i).type == "checkbox") {
           if (obj.checked) {
              // Отмечаем все чекбоксы...
              items.item(i).checked = true;
           } else {
             // Иначе снимаем отметки со всех чекбоксов:
             items.item(i).checked = false;
           }
        }
    }
}
