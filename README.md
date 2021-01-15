# Site de pandémies

Projet universitaire proposé dans la matière Méthodes de Conception en L3 Informatique par l'Université de Caen Normandie afin de nous faire découvrir l'implémentation des design pattern au sein d'une application simple.

<img src="logo-UNICAEN.jpg" style="width: 100px;" />

## Table des matières

- [Table des matières](#table-des-matières)
- [Introduction](#introduction)
- [Setup](#setup)
- [Commandes](#commandes)
- [Auteurs du projet](#auteurs-du-projet)

## Introduction

Le but du projet est de réaliser une application desktop sous forme d'un petit tetris modifié en implémentant et utilisant au mieux différents design pattern vu en cours (Observer, Factory, Strategy, Chain Of Responsability, etc).
Le jeu se joue avec la souris pour déplacer les différentes pièces sur le plateau. On peut tourner les pièces vers la gauche avec la touche <code>A</code> ou la faire tourner vers la droite avec la touche <code>E</code>.
Le but du jeu est de minimiser l'aire pris par le plus petit rectangle contenant toutes les pièces.

## Setup

Vous devez installer Java et Ant sur votre machine.
Pour linux :
```shell
$ sudo apt update
$ sudo apt install default-jre default-jdk
$ sudo apt install ant
```

Pour Windows :
- Java : https://www.java.com/en/download/manual.jsp
- Ant : https://ant.apache.org/bindownload.cgi

## Commandes
Pour les exécuter, déplacez vous dans le dossier `livraison`.

Les commandes principales sont :

- `$ ant` pour compiler et lancer le projet
- `$ ant dist` pour compiler la distribution du projet
- `$ ant javadoc` pour compiler la documentation du projet
- `$ ant test` pour lancer les classes de test du projet

## Auteurs du projet

- [BOCAGE Arthur](https://github.com/TurluTwoD)
- [PIERRE Corentin](https://github.com/coco-ia)
- [PIGNARD Alexandre](https://github.com/Myrani)
- [LETELLIER Guillaume](https://github.com/Guigui14460)
