const allItems = new Map;
const bool = new Map;
let usName = localStorage.getItem("user");
let client = null;
const ctList = new Map;

$(document).on("click", "#all", function () {
    getAllItems();
});

$(document).on("click", "#act", function () {
    getActive();
});

$(document).on("click", "#comp", function () {
    getComp();
});

$(document).on("click", "#cleanDone", function () {
    deleteComp();
});

$(document).on("click", "#exitId", function () {
    client = null;
    localStorage.removeItem("user");
    window.location.href = "http://localhost:8080/todo/log.do";
});


function sizeItem() {
    let ty = allItems.size;
    document.getElementById('length').innerHTML = ty + " item(s)";
}

function showUser() {
    document.getElementById('username').innerHTML = usName;
}

function getUser() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/user.do',
        dataType: 'json'
    }).done(function (data) {
        client = data;
    })
}


function addItem(event) {
    event.preventDefault();
    let desc = $('#description').val();
    let categoriesList = $('#cat')
        .find(":selected")
        .map(function () {
        return $(this).val();
    }).get().map(function (key) {
        return ctList.get(parseInt(key));
    });
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/todo/item.do',
        data: JSON.stringify({
            description: desc,
            created: new Date().toISOString(),
            user: client,
            categories: categoriesList
        }), dataType: 'text'
    }).done(function (data) {
        if (data === "200 OK")
            document.getElementById('description').value = '';
        getAllItems();
    })
}

function divFun(id) {
    const ap = bool.get(id);
    let urlI = (ap === true) ? 'http://localhost:8080/todo/not.do' : 'http://localhost:8080/todo/done.do';
    let temp = allItems.get(id);
    $.ajax({
        type: 'POST',
        url: urlI,
        data: JSON.stringify(temp),
        dataType: 'text'
    }).done(function (data) {
        getAllItems();
    })
}

function category() {
    $("option:not(:first)").remove();
    ctList.clear();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/todo/category.do',
        dataType: 'json'
    }).done(function (data) {
        for (let cat of data) {
            ctList.set(cat.id, cat);
            $('#cat').append($('<option>', {
                value: cat.id,
                text: cat.name,
            }));
        }
    });
}

function getList(item) {
    return item.categories.map(function (category) {
        return category['name'];
    }).join(',' + '\n');
}

function getItems(url) {
    allItems.clear();
    $("#ti").empty();
    $.ajax({
        type: 'GET',
        url: url,
        dataType: 'json'
    }).done(function (data) {
        for (var item of data) {
            let categories = getList(item);
            let index = item.id;
            let st = item.done;
            allItems.set(index, item);
            bool.set(index, st);
            let date = new Date(item.created);
            let day = date.getDate();
            let mon = date.getMonth() + 1;
            let year = date.getFullYear();
            const full = day + ' ' + mon + ' ' + year;
            const done = (st === false) ? "check-mark" : "check-mark checked";
            const line = (st === false) ? "todo-text" : "todo-text checked";
            $('#ti').append(`<div class="todo-item">`
                + `<div class="check">`
                + `<div class="${done}" id="${index}" onclick="divFun(${index})"><img src="icons/icon-check.svg"></div>`
                + `</div>`
                + `<div class="${line}">${item.description}</div>`
                + `<div class="${line}" style="width: 300px"><p class="descr">${categories}</p></div>`
                + `<div class="${line}" style="width: 150px"><p class="descr">${full}</p></div>`
                + `</div>`
            )
        }
        sizeItem();
        showUser();
        getUser();
        category();
    });
};

function getAllItems() {
    getItems('http://localhost:8080/todo/item.do');
    $("#all").attr('class', 'active');
    $("#act").attr('class', 'non');
    $("#comp").attr('class', 'non');
}

function getActive() {
    getItems('http://localhost:8080/todo/active.do');
    $("#act").attr('class', 'active');
    $("#all").attr('class', 'non');
    $("#comp").attr('class', 'non');
}

function getComp() {
    getItems('http://localhost:8080/todo/comp.do');
    $("#comp").attr('class', 'active');
    $("#act").attr('class', 'non');
    $("#all").attr('class', 'non');
}


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

getAllItems();


