var itemsList = new Set;
/*$(document).ready(function () {
    sizeItem();
});*/


function sizeItem() {
    var ty = itemsList.size;
    var rsl = "Items " +  ty;
    document.getElementById('length').innerHTML = rsl;

}

function addItem(event) {
    event.preventDefault();
    var desc = $('#description').val();
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/todo/item.do',
        data: JSON.stringify({
            description: desc,
            created: new Date().toISOString(),
        }), dataType: 'text'
    }).done(function (data) {
        location.reload();
    })
}


function getAllItems() {
    itemsList.clear();
    $("#ti").empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/item.do',
        dataType: 'json'
    }).done(function (data) {
        for (var item of data) {
            itemsList.add(item);
            var date = new Date(item.created);
            var day = date.getDate();
            var mon = date.getMonth() + 1;
            var year = date.getFullYear();
            const full = day + ' ' + mon + ' ' + year;
            const done = (item.done === false) ? "check-mark" : "check-mark checked";
            const line = (item.done === false) ? "todo-text" : "todo-text checked";
            $('#ti').append(`<div class="todo-item">`
                + `<div class="check">`
                + `<div class="${done}"><img src="icons/icon-check.svg"></div>`
                + `</div>`
                + `<div class="${line}" style="width: 120px"><p class="descr">(${item.id}) ${full}</p></div>`
                + `<div class="${line}">${item.description}</div>`
                + `</div>`
            )
        }
        sizeItem();
    }).fail(function (err) {
        console.log(err);
    });
};


function getActive() {
    itemsList.clear();
    $("#ti").empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/active.do',
        dataType: 'json'
    }).done(function (data) {
        for (var item of data) {
            itemsList.add(item);
            var date = new Date(item.created);
            var day = date.getDate();
            var mon = date.getMonth() + 1;
            var year = date.getFullYear();
            const full = day + ' ' + mon + ' ' + year;
            const done = (item.done === false) ? "check-mark" : "check-mark checked";
            const line = (item.done === false) ? "todo-text" : "todo-text checked";
            $('#ti').append(`<div class="todo-item">`
                + `<div class="check">`
                + `<div class="${done}"><img src="icons/icon-check.svg"></div>`
                + `</div>`
                + `<div class="${line}" style="width: 90px"><p class="descr">${full}</p></div>`
                + `<div class="${line}">${item.description}</div>`
                + `</div>`
            )
        }
    }).fail(function (err) {
        console.log(err);
    });
};

function getComp() {
    $("#ti").empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/comp.do',
        dataType: 'json'
    }).done(function (data) {
        for (var item of data) {
            var date = new Date(item.created);
            var day = date.getDate();
            var mon = date.getMonth() + 1;
            var year = date.getFullYear();
            const full = day + ' ' + mon + ' ' + year;
            const done = (item.done === false) ? "check-mark" : "check-mark checked";
            const line = (item.done === false) ? "todo-text" : "todo-text checked";
            $('#ti').append(`<div class="todo-item">`
                + `<div class="check">`
                + `<div class="${done}"><img src="icons/icon-check.svg"></div>`
                + `</div>`
                + `<div class="${line}" style="width: 90px"><p class="descr">${full}</p></div>`
                + `<div class="${line}">${item.description}</div>`
                + `</div>`
            )
        }
    }).fail(function (err) {
        console.log(err);
    });
};

function deleteComp() {
    $("#ti").empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/del.do',
        dataType: 'json'
    }).done(function (data) {

    })
    location.reload();
};


function getItems() {
    $("#ti").empty();
    let items = [];
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/item.do',
        dataType: 'json'
    }).done(function (data) {
        for (var item of data) {
            items.push(item);
        }
    })
    generateItems(items);
}

function generateItems(items) {
    var dynamicHTML = '';
    for (var i = 0; i < items.length; i++) {
        let textr = items[i].description;
        dynamicHTML += '<div class="todo-item">'
            + '<div class="check">'
            + '<div class="check-mark"><img src="icons/icon-check.svg"></div>'
            + ' </div>'
            + '<div class="todo-text"> + items[i].description + </div>'
            + ' </div>';
    }
    $("todo-items").append(dynamicHTML);
}

function markCompleted(id){
    let item = db.collection("todo-items").doc(id);
    item.get().then(function(doc) {
        if (doc.exists) {
            if(doc.data().status == "active"){
                item.update({
                    status: "completed"
                })
            } else {
                item.update({
                    status: "active"
                })
            }
        }
    })
}

getAllItems();
