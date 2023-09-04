package org.example;

import java.util.ArrayList;

public class Time {

    private String nome;

    private ArrayList<Jogador> jogadores;

    private Jogador[] jogadoresEmCampo;
    private double idadeMedia;

    private double pesoMedio;

    private double alturaMedia;

    public static final byte MAX_JOGADORES_EM_CAMPO = 11;

    /**
     * Inicializa um novo Time
     */
    public Time() {
        nome = "";
        jogadores = new ArrayList<Jogador>();
        jogadoresEmCampo = new Jogador[MAX_JOGADORES_EM_CAMPO];
        idadeMedia = 0.0;
        pesoMedio = 0.0;
        alturaMedia = 0.0;
    }

    /**
     * Inicializa um novo Time
     *
     * @param nome o nome oficial do time de futebol
     */
    public Time(String nome) {
        this.nome = nome;
        jogadores = new ArrayList<Jogador>();
        jogadoresEmCampo = new Jogador[MAX_JOGADORES_EM_CAMPO];
        idadeMedia = 0.0;
        pesoMedio = 0.0;
        alturaMedia = 0.0;
    }


    /**
     * Obtém o nome oficial do time
     *
     * @return o nome do time
     */
    public String getNome() {
        return nome;
    }

    public double getMediaIdade(){
        return idadeMedia;
    }

    public double getMediaAltura(){
        return alturaMedia;
    }




    /**
     * Atualiza o nome oficial do time
     *
     * @param nome o novo nome do time
     */
    public void setNome(String nome) {

        if (nome == null)
            throw new IllegalArgumentException("O nome não pode ser null.");

        if (!nome.matches("^[a-zA-Z'-]+$"))
            throw new IllegalArgumentException("O nome possui caracteres inválidos! São aceitos somente caracteres de a-z ou A-Z, além de apóstrofo (') e traço (-)");

        this.nome = nome.trim();
    }

    /**
     * Obtém a lista de jogadores em campo
     *
     * @return os jogadores em campo
     */
    public Jogador[] getJogadoresEmCampo() {
        return jogadoresEmCampo;
    }


    /**
     * Atualiza a lista de jogadores em campo
     *
     * @param jogadoresEmCampo um vetor (array) com número total de elementos igual à {@link Time#MAX_JOGADORES_EM_CAMPO}
     * @throws IllegalArgumentException se o vetor (array) de jogadores for <code>null</code> ou se o número de elementos for diferente de {@link Time#MAX_JOGADORES_EM_CAMPO}
     * @see Time#MAX_JOGADORES_EM_CAMPO
     */
    public void setJogadoresEmCampo(Jogador[] jogadoresEmCampo) throws IllegalArgumentException {

        if (jogadoresEmCampo == null)
            throw new IllegalArgumentException("A lista de jogadores em campo não pode ser null.");

        if (jogadoresEmCampo.length != MAX_JOGADORES_EM_CAMPO)
            throw new IllegalArgumentException(String.format("O número de total de jogadores em campo deve ser igual à %d.", MAX_JOGADORES_EM_CAMPO));

        this.jogadoresEmCampo = jogadoresEmCampo;
    }

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
        this.idadeMedia = calcularMediaIdade();
        this.pesoMedio = calcularMediaPeso();
        this.alturaMedia= calcularMediaAltura();

    }

    private double calcularMediaIdade() {
        int somaDasIdades = 0;

        for (int i = 0; i < jogadores.size(); i++) {
            somaDasIdades += jogadores.get(i).getIdade();


        }return somaDasIdades / jogadores.size();
    }

    public double calcularMediaPeso() {
        int somaDoPeso = 0;

        for (int i = 0; i < jogadores.size(); i++) {
            somaDoPeso += jogadores.get(i).getPeso();


        }return somaDoPeso / jogadores.size();
    }

    private double calcularMediaAltura() {
        int somaDaAltura = 0;

        for (int i = 0; i < jogadores.size(); i++) {
            somaDaAltura += jogadores.get(i).getAltura();


        }return somaDaAltura / jogadores.size();
    }

    public double calcularMenoresDeIdade() {
        int idade = 0;
        int menor=0;

        for (int i = 0; i < jogadores.size(); i++) {
            idade = jogadores.get(i).getIdade();
            if (idade < 18)
                menor++;


        }return menor;
    } // retorna o total de jogadores menor de 18 anos.

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }
} // Fim da classe Time
