package com.intuit.player;

import java.util.Optional;

public record Player(String playerID, Optional<Integer> birthYear, Optional<Integer> birthMonth,
        Optional<Integer> birthDay, String birthCountry, String birthState, String birthCity) // ,deathYear,deathMonth,deathDay,deathCountry,deathState,deathCity,nameFirst,nameLast,nameGiven,weight,height,bats,throws,debut,finalGame,retroID,bbrefID
{

}
