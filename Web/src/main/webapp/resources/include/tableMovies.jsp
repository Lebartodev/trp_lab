<%@ page import="model.MovieItem" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Nastya
  Date: 20.03.2016
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<!--
Material Design Lite
Copyright 2015 Google Inc. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License
-->
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="A front-end template that helps you build fast, modern mobile web apps.">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>Employees List</title>

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="images/android-desktop.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Material Design Lite">
    <link rel="apple-touch-icon-precomposed" href="images/ios-desktop.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="images/touch/ms-touch-icon-144x144-precomposed.png">
    <meta name="msapplication-TileColor" content="#3372DF">

    <link rel="shortcut icon" href="images/favicon.png">

    <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
    <!--
    <link rel="canonical" href="http://www.example.com/">
    -->

    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.deep_purple-pink.min.css">
    <link rel="stylesheet" href="resources/css/styles.css">
    <style>
        #view-source {
            position: fixed;
            display: block;
            right: 0;
            bottom: 0;
            margin-right: 40px;
            margin-bottom: 40px;
            z-index: 900;
        }
    </style>
</head>
<body class="mdl-demo mdl-color--grey-100 mdl-color-text--grey-700 mdl-base">
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <header class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
        <div class="mdl-layout--large-screen-only mdl-layout__header-row">
        </div>
        <div class="mdl-layout--large-screen-only mdl-layout__header-row">
            <h3>Movie Catalog
            </h3>
        </div>
        <div class="mdl-layout--large-screen-only mdl-layout__header-row">
        </div>
        <div class="mdl-layout__tab-bar mdl-js-ripple-effect mdl-color--primary-dark">

            <a href="" class="mdl-layout__tab">Home</a>
            <form action="MovieCatalog">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable">
                    <label class="mdl-button mdl-js-button mdl-button--icon" for="inputSearch">
                        <i class="material-icons">search</i>
                    </label>
                    <div class="mdl-textfield__expandable-holder">
                        <input type="hidden" name="command" value="search">
                        <input class="mdl-textfield__input" name="inputSearch" type="text" id="inputSearch">
                    </div>
                </div>
            </form>
            <a href="?command=startCreateMovie">
                <button class="mdl-button mdl-js-button mdl-button--fab mdl-js-ripple-effect mdl-button--colored mdl-shadow--4dp mdl-color--accent"
                        id="add">

                    <i class="material-icons" role="presentation">add</i>
                    <span class="visuallyhidden">Add</span>

                </button>
            </a>
        </div>
    </header>
    <main class="mdl-layout__content">
        <div class="mdl-layout__tab-panel is-active" id="overview">
            <%
                List<MovieItem> movieItems = (List<MovieItem>) request.getAttribute("moviesList");
                for (MovieItem movie : movieItems) {

                    out.println("<section class=\"section--center mdl-grid mdl-grid--no-spacing mdl-shadow--2dp\">\n" +
                            "<header class=\"section__play-btn mdl-cell mdl-cell--3-col-desktop mdl-cell--2-col-tablet mdl-cell--4-col-phone mdl-color--teal-100 mdl-color-text--white\">\n" +
                            "<i class=\"material-icons\">play_circle_filled</i>\n" +
                            "</header>" +
                            "<div class=\"mdl-card mdl-cell mdl-cell--9-col-desktop mdl-cell--6-col-tablet mdl-cell--4-col-phone\">\n" +
                            "<div class=\"mdl-card__supporting-text\">" + "<h4>" + movie.getName() + "</h4> " +
                            "<br> Budget: "
                            + movie.getBudget() + " $" +
                            "<br> Year: "
                            + movie.getYear() + " " +
                            "<br> Description: "
                            + movie.getDescription() + " " +
                            "</div>" +
                            " <div class=\"mdl-card__actions\">\n" +
                            "<form method=\"post\" action=\"MovieCatalog\">\n" +

                            "<input type=\"hidden\" name=\"command\" value=\"startEditMovie\">" +
                            " <input type=\"hidden\" name=\"movieId\" value = \"" + movie.getId() + "\"/> \n" +
                            "<input type=\"submit\" class=\"mdl-button\"  value = \"Edit\">" +
                            "</form>" +
                            "<form method=\"post\" action=\"MovieCatalog\">\n" +

                            "<input type=\"hidden\" name=\"command\" value=\"deleteMovie\">" +
                            " <input type=\"hidden\" name=\"movieId\" value = \"" + movie.getId() + "\"/> \n" +
                            " <input type=\"hidden\" name=\"movieGenreId\" value = \"" + movie.getGenreId() + "\"/> \n" +
                            "<input type=\"submit\" class=\"mdl-button\"  value = \"Delete\">" +
                            "</form>" +


                            "</div>" +


                            "</div>" +
                            " </section>"
                    );
                }
            %>


        </div>

        <footer class="mdl-mega-footer">
            <div class="mdl-mega-footer--middle-section">
            </div>
            <div class="mdl-mega-footer--bottom-section">
            </div>
        </footer>
    </main>
</div>
<script src="https://code.getmdl.io/1.1.3/material.min.js"></script>
</body>
</html>
