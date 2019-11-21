<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl">

  <div class="body">

    <!-- Provide a message to the user, if supplied. -->
    <#include "message.ftl">
    <#if isSignedIn>
      <#if hasPlayers>
          <h2>Players Online</h2>
          <form action="/game" method="GET"  >
            <#list listOfPlayers as player>
                <button type="submit" name="opponent" value=${player}  >${player}</p>
                <br>
            </#list>
          </form>
      <#else>
          <p>There are no other players available to play at this time.</p>
      </#if>
    <#else>
        <p>There are ${numberOfPlayers} player(s) online.</p>
    </#if>

    <!-- TODO: future content on the Home:
            to start games,
            spectating active games,
            or replay archived games
    -->

  </div>

</div>
</body>

</html>
