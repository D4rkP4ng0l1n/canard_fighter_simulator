### 1. **Quelles classes pourraient être abstraites ?**

Les classes `Canard` et `Objet` peuvent être abstraites. En effet, un canard en tant que tel n'existe pas. Pour exister, il doit être défini par un type, qui est représenté par les différentes classes filles telles que `CanardFeu`, `CanardEau`, etc. Il en est de même pour un objet.

### 2. **Quels comportements communs pourraient être définis dans une interface ?**

Etant donné que les capacités spéciales sont toutes définies pour un type précis, on peut définir la méthode permettant d'appliquer l'effet de cette capacité dans une interface.

### 3. **Comment représenter un changement de statut (par exemple, brûlé ou paralysé) dans la modélisation ?**

Pour représenter ces changements de statut, j'ai défini une énumération dans laquelle j'ai créé les différents statuts. Ensuite, ces statuts sont appliqués au canard via une méthode.

### 4. **Quels seraient les avantages d’utiliser une classe ou une interface supplémentaire pour gérer les capacités spéciales ?**

Cela permettrait de créer de nouvelles capacités spéciales de manière plus rapide et efficace. De plus, cela rendrait le code plus lisible, car la capacité spéciale serait séparée des autres classes (par exemple, un canard ne devrait pas s'occuper d'afficher les objets).

### 5. **Quels défis sont associés à l’extensibilité de votre modèle pour ajouter de nouveaux types de canards ou de nouvelles capacités ?**

Chaque nouveau type de canard nécessite la création d'une nouvelle sous-classe, ce qui peut devenir long et compliqué avec le temps. De plus, il y aura beaucoup de duplication de code, et à chaque nouveau type, il faudra compléter la table des types.


### Fonctionnalités avancées ###

Pour rajouter les effets de statuts, j'ai fait une enumeration comportant les differents statuts que je souhaitais implémenter. Ensuite, pour appliquer ces effets, cela se fait via la classe Canard où l'on va appliquer un statut à un attribut d'instance. Les effets des Statuts quand à eux vont se faire soit à la fin du tour durant un combat, soit pendant l'attaque selon le statut.
Pour les points d'énergies, on regarde si le canard qui attaque en possède assez pour lancer son attaque. Si il n'a pas assez de points alors l'attaque échoue. Pour se faire, dans la classe Canard, il y a un attribut qui est initialisé avec la valeur d'une constante correspondant au maximum de point d'Energie
Pour le coup critique, j'ai utilisé une constante qui est initialisé à 10%. A chaque attaque, on génère un nombre entre 1 et 10, si le nombre est 1 alors l'attaque recevra un multiplicateur pour le coup critique.
