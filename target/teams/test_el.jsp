<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test des expressions EL</title>
    </head>
    <body>
    <p>
        <!-- Initialisation d'un bean de type Coyote avec une action standard, pour l'exemple : -->
        <jsp:useBean id="coyote" class="ensias.teams.buzinessLayer.User" />
        <!-- Initialisation de sa propriété 'prénom' : -->
        <jsp:setProperty name="coyote" property="firstName" value="YASSSER"/>
        <!-- Et affichage de sa valeur : -->
        <jsp:getProperty name="coyote" property="firstName" />
    </p>
    </body>
</html>