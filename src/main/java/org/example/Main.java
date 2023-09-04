package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    static ArrayList<String> arquivoBruto;

    static ArrayList<Time> times = new ArrayList<Time>();
    static ArrayList<Jogador> jogadores = new ArrayList<Jogador>();

    /**
     * Lê o arquivo de dados
     * @return uma lista com todas as linhas do arquivo
     * @throws IOException se houver algum erro de leitura
     */
    static ArrayList<String> lerArquivo() throws IOException {

        Charset charset = StandardCharsets.UTF_8;
        ArrayList<String> linhas = new ArrayList<String>();

        try (BufferedReader leitor = Files.newBufferedReader(Paths.get("src/main/resources/data/dados_fifa.csv"), charset)) {

            String linha = leitor.readLine();

            while (linha != null) {

                linhas.add(linha);
                linha = leitor.readLine();

            }

        } catch (IOException e) {
            System.err.format("IOException: %s %n", e);
        }

        return linhas;

    } // Fim do método lerArquivo()

    /**
     * Verifica se o time está cadastrado no vetor
     * @param timePesquisado time que serã pesquisado na memória
     * @return <code>true</code> se o time estiver presente na lista, caso contrário retorna <code>false</code>
     */
    static boolean temTimeCadastrado(Time timePesquisado) {

        for (Time time : times) {

            if (time.getNome().compareTo(timePesquisado.getNome()) == 0)
                return true;
        }

        return false;

    } // Fim do método temTimeCadastrado()

    /**
     * Obtém o índice do time na lista
     * @param nomeDoTime nome do time
     * @return o índice do time na lista, caso contrário -1
     */
    static int indiceDo(String nomeDoTime) {

        for (int i = 0; i < times.size(); i++) {

            if (times.get(i).getNome().compareTo(nomeDoTime) == 0)
                return i;

        }

        return -1;

    } // Fim do método indiceDo()

    /**
     * Carrega na memória todos os times do arquivo
     */
    static void carregarTimes() {

        for (int i = 1; i < arquivoBruto.size(); i++) {

            String[] campos = arquivoBruto.get(i).split(",");

            String nomeDoTime = campos[6];
            String nomeDoJogador = campos[1];
            byte idadeDoJogador = Byte.parseByte(campos[2]);
            float alturaDoJogador = Float.parseFloat(campos[9]);
            float pesoDoJogador = Float.parseFloat(campos[10]);

            Time time = new Time(nomeDoTime);
            Jogador jogador = new Jogador(nomeDoJogador, idadeDoJogador, alturaDoJogador, pesoDoJogador);

            if (temTimeCadastrado(time)) {

                int indiceTime = indiceDo(nomeDoTime);
                times.get(indiceTime).adicionarJogador(jogador);

            } else {
                //jogador.setNome(nomeDoJogador);
                //jogador.setIdade(idadeDoJogador);
                //jogador.setAltura(alturaDoJogador);
                //jogador.setPeso(pesoDoJogador);
                time.adicionarJogador(jogador);
                times.add(time);
            }
        }

    } // Fim do método carregarTimes()

    static void carregarJogador() {

        for (int i = 1; i < arquivoBruto.size(); i++) {

            String[] campos = arquivoBruto.get(i).split(",");

            String nomeDoTime = campos[6];
            String nomeDoJogador = campos[1];
            byte idadeDoJogador = Byte.parseByte(campos[2]);
            float alturaDoJogador = Float.parseFloat(campos[9]);
            float pesoDoJogador = Float.parseFloat(campos[10]);

            Time time = new Time(nomeDoTime);
            Jogador jogador = new Jogador(nomeDoJogador, idadeDoJogador, alturaDoJogador, pesoDoJogador);

            if (temTimeCadastrado(time)) {

                int indiceTime = indiceDo(nomeDoTime);
                times.get(indiceTime).adicionarJogador(jogador);
                jogadores.add(jogador);
            } else {
                //jogador.setNome(nomeDoJogador);
                //jogador.setIdade(idadeDoJogador);
                //jogador.setAltura(alturaDoJogador);
                //jogador.setPeso(pesoDoJogador);
                time.adicionarJogador(jogador);
                times.add(time);
                jogadores.add(jogador);
            }
        }

    }

    public static void main(String[] args) throws IOException {

        arquivoBruto = lerArquivo();
        carregarTimes();
        carregarJogador();
        System.out.printf("%d times cadastrados.%n", times.size());
        int jogadoresMenores =0;
        double mediaAlturaJogadoresCampeonato=0;
        float jogadoresACimaDoPeso =0;

        for (int i =1; i < times.size();i++){

            if (times.get(i).calcularMenoresDeIdade() >= 1)
                jogadoresMenores++;
        }//calcular quantos jogadores menores de idade
        System.out.println("Quantidade de jogadores menores de 18 anos: " + jogadoresMenores);


        for (int i =0; i <times.size();i++){
            System.out.println("Time: " + times.get(i).getNome());
            System.out.println("lista de jogadores:");
            for(int j=0; j < times.get(i).getJogadores().size();j++){
                System.out.println( times.get(i).getJogadores().get(j).getNome());
            }
        }


        System.out.println("Media altura jogadores do campeonato " + times.get(0).getMediaAltura() );




        for (int i =1; i < jogadores.size(); i++) {
            double peso = jogadores.get(i).getPeso();
            if (peso >= 80) {
                jogadoresACimaDoPeso++;
            }
        }

        System.out.println("Total de jogadores acima de 80kg: " +  jogadoresACimaDoPeso) ;
        System.out.println("A média de peso é " + times.get(0).calcularMediaPeso());

    } // Fim do método main

} // Fim da classe Main