package com.application;





//Essa porcaria aqui é só pra quando for montado um jar a classe principal chamada do jar ser essa aqui.
//de forma que essa classe chama o main da verdadeira classe principal App, que faz o launch da aplicação.

//Resumindo so serve pq esse framework é esquisito e na hora de buildar o executavel .jar ele nao funciona sem essa classe.
public class Starter {

    public static void main(final String[] args) {
        App.main(args);
    }

}
