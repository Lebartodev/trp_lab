<%@ page import="model.CategoryItem" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<html lang="en">
<head>
    <link href="resources/css/signin.css" rel="stylesheet">


    <script src="https://code.getmdl.io/1.1.3/material.min.js"></script>
    <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.indigo-pink.min.css">
    <!-- Material Design icon font -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="A front-end template that helps you build fast, modern mobile web apps.">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>Material Design Lite</title>

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

        #rightcol {
            position: absolute; /* Абсолютное позиционирование */
            right: 0; /* Положение от правого края окна */
            top: 30px; /* Положение от верхнего края */
            width: 200px; /* Ширина колонки */
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

            <a href="?command=listCategories" class="mdl-layout__tab is-active">Home</a>


        </div>
    </header>
    <main class="mdl-layout__content">
        <div class="mdl-layout__tab-panel is-active" id="overview">
            <hgroup>
                <h3>Add Movie</h3>
            </hgroup>
            <script type="text/javascript">
                $(window, document, undefined).ready(function () {

                    $('input').blur(function () {
                        var $this = $(this);
                        if ($this.val())
                            $this.addClass('used');
                        else
                            $this.removeClass('used');
                    });

                    var $ripples = $('.ripples');

                    $ripples.on('click.Ripples', function (e) {

                        var $this = $(this);
                        var $offset = $this.parent().offset();
                        var $circle = $this.find('.ripplesCircle');

                        var x = e.pageX - $offset.left;
                        var y = e.pageY - $offset.top;

                        $circle.css({
                            top: y + 'px',
                            left: x + 'px'
                        });

                        $this.addClass('is-active');

                    });

                    $ripples.on('animationend webkitAnimationEnd mozAnimationEnd oanimationend MSAnimationEnd', function (e) {
                        $(this).removeClass('is-active');
                    });

                });
            </script>
            <form action="MovieCatalog" method="POST">
                <div class="group">
                    <input type="hidden" name="command" value="endCreateMovie">
                    <input type="name" id="movieName" name="movieName" class="form-control" required autofocus
                           placeholder="Name"><span class="highlight"></span><span class="bar"></span>
                </div>
                <div class="group">
                    <input type="text" id="movieYear" onkeypress='return event.charCode >= 48 && event.charCode <= 57'
                           name="movieYear" class="form-control" placeholder="Year" required><span
                        class="highlight"></span><span class="bar"></span>
                </div>
                <div class="group">
                    <input type="text" id="movieBudget" onkeypress='return event.charCode >= 48 && event.charCode <= 57'
                           name="movieBudget" class="form-control" placeholder="Budget" required><span
                        class="highlight"></span><span class="bar"></span>
                </div>
                <div class="group">
                    <input type="text" id="movieDescription" name="movieDescription" class="form-control"
                           placeholder="Description"><span
                        class="highlight"></span><span class="bar"></span>
                </div>
                <div class="group">
                    <p><select name="movieGenreId" class="form-control" required>
                        <%
                            List<CategoryItem> categoryItems = (List<CategoryItem>) request.getAttribute("categoryList");
                            for (CategoryItem categoryItem : categoryItems) {
                                out.print("<option value=\"" + categoryItem.getId() + "\">" + categoryItem.getName() + "</option>");
                            }
                        %>
                    </select>

                </div>
                <button type="submit" class="button buttonBlue">Add Movie
                    <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
                </button>
            </form>

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
