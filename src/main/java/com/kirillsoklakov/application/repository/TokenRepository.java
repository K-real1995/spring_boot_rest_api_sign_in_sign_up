package com.kirillsoklakov.application.repository;

import com.kirillsoklakov.application.models.entity.OAuthAccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
//Репозиторий токенов для связи с бд
@Repository
public interface TokenRepository extends CrudRepository<OAuthAccessToken, Long> {

    Optional<OAuthAccessToken> findByUsername(String username);

    Optional<OAuthAccessToken> findByAccessToken(String accessToken);
}
