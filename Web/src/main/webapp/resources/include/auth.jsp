<%--
  Created by IntelliJ IDEA.
  User: Nastya
  Date: 20.03.2016
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<link href="resources/css/signin.css" rel="stylesheet">

<script type="text/javascript">
    $(window, document, undefined).ready(function() {

        $('input').blur(function() {
            var $this = $(this);
            if ($this.val())
                $this.addClass('used');
            else
                $this.removeClass('used');
        });

        var $ripples = $('.ripples');

        $ripples.on('click.Ripples', function(e) {

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

        $ripples.on('animationend webkitAnimationEnd mozAnimationEnd oanimationend MSAnimationEnd', function(e) {
            $(this).removeClass('is-active');
        });

    });
</script>

<hgroup>
    <h1>TaskManager</h1>
    <h3>Login</h3>
</hgroup>
<form action="TaskManager" method="POST">
    <div class="group">
        <input type="hidden" name="command" value="auth">
        <input type="name" id="inputEmail" name="name" class="form-control"required autofocus placeholder="Name"><span class="highlight"></span><span class="bar"></span>
    </div>
    <div class="group">
        <input type="password" id="inputPassword" name="password" class="form-control" required placeholder="Password"><span class="highlight"></span><span class="bar"></span>
    </div>
    <button type="submit" class="button buttonBlue">Sign in
        <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
    </button>
</form>
<footer>
    <a href="http://www.polymer-project.org/" target="_blank"><img src="https://www.polymer-project.org/images/logos/p-logo.svg"></a>
    <p>You Gotta Love <a href="http://www.polymer-project.org/" target="_blank">Google</a></p>
</footer>
<div class="container">

    <!-- <form class="form-signin" action="TaskManager" method="POST">
         <h2 class="form-signin-heading">Please sign in</h2>
         <label for="inputEmail" class="sr-only">Name</label>
         <input type="hidden" name="command" value="auth">
         <input type="name" id="inputEmail" name="name" class="form-control" placeholder="Name" required autofocus>
         <label for="inputPassword" class="sr-only">Password</label>
         <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
         <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
     </form>-->

 </div> <!-- /container -->
