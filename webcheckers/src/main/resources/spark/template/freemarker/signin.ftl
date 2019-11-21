<!DOCTYPE html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
    <h1>${title}</h1>

    <div class="signin">
      <div class="heading">
        <form action="/signin" method="post">
      <#include "message.ftl">
        <form action="/signin" method="post">
          <div class="input-group input-group-lg">
            <span class="input-group-addon"><i class="fa fa-user"></i></span>
            <input type="text" class="form-control" name="username" placeholder="Username">
          </div>
          <button type="submit">Go!</button>
        </form>
      </div>
    </div>
