<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.15.4/css/fontawesome.min.css">
    <link rel="stylesheet" href="src/main/java/css/style.css">
    <title>TODO list</title>
</head>


        <div class="card-header">
            Список заданий
        </div>

        <div class="row rowBg align-items-center">
            <div class="card-body">
                <form>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Добавить новую заявку</label>
                        <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Описание заявки">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>

            <div class="card-body">
                <form>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Отредактировать заявку</label>
                        <input type="text" class="form-control" id="edit" aria-describedby="emailHelp" placeholder="Номер заявки">
                        <label for="exampleInputEmail1">Введите имя заявки</label>
                        <input type="text" class="form-control" id="edit2" aria-describedby="emailHelp" placeholder="Описание">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>

        <div class="card-body">
            <table class="table">
                <thead>
                <tr>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Описание</th>
                    <th scope="col">Дата</th>
                    <th scope="col">Статус</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${items}" var="item">
                    <tr>
                        <td><c:out value="${item.id}"/></td>
                        <td><c:out value="${item.description}"/></td>
                        <td><c:out value="${item.created}"/></td>
                        <td><c:out value="${item.done}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
</div>
