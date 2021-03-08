

```
EnsiasTEAMS est une platforme collaborative personnalisable (
semblable `a Microsoft Teams) qui int`egre le stockage et le transfert
de fichiers( Pas encore r´ealis´e ).
La messagerie instantan´ee permettant d’´echanger en ´equipe ou en
priv´e tout en conservant une trace de tous les ´echanges.
Cette platforme avait pour but de faciliter l’ajout des membres aux
´equipes un par un , qui est un travail r´ep´et´etif , alors notre platforme
se caract´erise par les ´etiquettes qui contient un Nom et des membres
associ´es `a elle ( exemple : ´etiquette GL qui contient les ´etudiants de
cette fili`ere alors l’ajout `a chaque groupe se fait juste en sp´ecifiant
cette ´etiquette ) 
```
#### ENSIAS TEAMS Platform
```


Change those informations up to your database informations : (dao.proprieties)

jdbc.driverClass=com.mysql.cj.jdbc.Driver

jdbc.schema=NameOFtheDB

jdbc.login=UserLogin

jdbc.password=UserPassword

jdbc.url=jdbc:mysql://localhost:3306/NameOFtheDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

langage=fr
```
                
###FlowChart

```flow
st=>start: Login
op=>operation: Login operation
cond=>condition: Successful Yes or No?
e=>end: To ENSIAS teams

st->op->cond
cond(yes)->e
cond(no)->op
```
> Diagramme de classe
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/ClasDiagram.jpg)
###Images

Image:
> Ajotrer des equipes au groupe
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/Add%20Equipes%20to%20group.PNG)

> Creer une equipe par des etiquettes
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/Add%20by%20tag.PNG)

> Ajouter des membres auxx etiquettes
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/Add%20members%20Etiquette.PNG)

> Ajouter des memebres
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/Ajout%20memebre%20to%20equipe%20deja%20creer.PNG)

> Ajouter des memebres
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/Ajouter%20memebre%20equipe.PNG)

> Creer une equipe
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/Creer%20equipe.PNG)

> Les etiquettes
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/Etiquettes.PNG)

> Deconnexion
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/Log%20out.PNG)

> Connexion
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/Login-exqmple.PNG)

> Mes groupes
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/Mes%20Groupes.PNG)

> Messagerie
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/Messagerie%20respond.PNG)

![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/Messagerie.PNG)

> Creer une nouvelle etiquette
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/Nouveau%20Etiquette.PNG)

> Creer un nouveau groupe
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/Nouveau%20groupe.PNG)

> Afficher le groupe
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/Show%20group.PNG)

> afficher l'equipe
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/Show%20team.PNG)

> Index
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/index.PNG)

> connexion version ordinateur
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/login-pc.PNG)

> Profile d'utilisateur
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/profile-pc.PNG)

> Creer un nouveau compte utilisateur
![](https://github.com/saidelaboudi/WebProject-jee/blob/main/screenshots/register.PNG)


###End
