<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Todo</title>
</head>
<body>
    <h1>Add Todo</h1>

    <form method="post" action="/addTodo">
        <label for="todo">Todo:</label>
        <input type="text" id="todo" name="todo" required>
        <br>
        <button type="submit">Add</button>
    </form>
</body>
</html>
