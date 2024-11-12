package com.blockshe.nomeplataforma.service;

import com.blockshe.nomeplataforma.model.User;
import com.blockshe.nomeplataforma.model.UserLogin;
import com.blockshe.nomeplataforma.repository.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class UserService {

    /*
     * Regras de negócio
     * 1 - Não podem ser cadastrados dois e-mails iguais
     * 2 - Não pode ser cadastrado um user menor de idade (-18)
     */

    @Autowired
    private UserRepository userRepository;

    public Optional<User> CadastrarUser(User user){

        //Checa se o Cliente existe na DBanco, se não existir retorna o Optional vazio
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            return Optional.empty();

        //Se o cliente não existir a senha sera criptografada
        user.setSenha(criptografarSenha(user.getSenha()));

        //O retorno do método save será retornado dentro de um Optional, com o Usuario persistido no Banco de Dados
        return Optional.of(userRepository.save(user));
    }

    private String criptografarSenha(String senha) {

        //Instancia um objeto da Classe BCryptPasswordEncoder para criptografar a senha do user
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //O método encode retorna a senha criptografada no formato BCrypt
        return encoder.encode(senha);
    }

    public Optional<User> atualizarUser(User user){

        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            /*
             * Se o Cliente existir no Banco de dados e o Id do Cliente encontrado no Banco for
             * diferente do usuário do Id do Cliente enviado na requisição, a Atualização dos
             * dados do Usuário não pode ser realizada.
             */

            Optional<User> buscaUser = userRepository.findByEmail(user.getEmail());
            if((buscaUser.isPresent()) && (buscaUser.get().getIdUser() != user.getIdUser()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe.", null);

            //Se o cliente existir e o Id for o mesmo a nova senha será criptografada
            user.setSenha(criptografarSenha(user.getSenha()));

            /*
             * ofNullable -> Se um valor estiver presente, retorna um Optional com o valor,
             * caso contrário, retorna um Optional vazio.
             */
            return Optional.ofNullable(userRepository.save(user));
        }
        return Optional.empty();

    }


    public Optional<UserLogin> Logar(Optional<UserLogin> userLogin){

        Optional<User> user = userRepository.findByEmail(userLogin.get().getEmail());

        if(user.isPresent()) {
            if(compararSenhas(userLogin.get().getSenha(), user.get().getSenha())) {

                //Se forem iguais retorna objeto com os dados recuperados do Banco de Dados, além de inserir o token gerado
                userLogin.get().setEmail(user.get().getEmail());
                userLogin.get().setSenha(user.get().getSenha());
                userLogin.get().setToken(gerarBasicToken(user.get().getEmail(), UserLogin.get().getSenha()));
                userLogin.get().setSenha(user.get().getSenha());

                //Retorna o objeto atualizado para a classe controller
                return userLogin;
            }
        }

        //empty -> Retorna uma instância de Optional vazia, caso o usuário não seja encontrado.

        return Optional.empty();

    }

    private boolean compararSenhas(String senhaDigitada, String senhaBanco) {

        //Instancia o objeto BCrypt novamente para criptografar a senha digitada
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        /*
        * Verifica se a senha criptografada digitada é igual a senha do banco (tambem criptografada),
        * retorna verdadeiro se assenhas coincidem e falso se não coincidem
        */
        return encoder.matches(senhaDigitada, senhaBanco);
    }

    private String gerarBasicToken(String cliente, String senha) {

        //Monta uma string - o token - concatenando <username>:<password>
        String token = cliente + ":" + senha;

        /* Fazendo a codificação em Base 64
         * Base64.encodeBase64 -> aplica o algoritmo de codificação do Código Decimal para Base64,
         * que foi gerado no próximo método.
         * Charset.forName("US-ASCII") -> Retorna o codigo ASCII (formato Decimal) de cada
         * caractere da String
         */

        byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));

        byte[] encodedBytes = Base64.encodeBase64("Test".getBytes());
        System.out.println("encodedBytes " + new String(encodedBytes));
        byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
        System.out.println("decodedBytes " + new String(decodedBytes));

        //Retorna token acrescendo "Basic" a frente além de converter ele para String novamente

        return "Basic " + new String(tokenBase64);

    }

}
