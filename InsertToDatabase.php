<?php

$servername = "localhost";
$username = "root";
$password = "toor";
$dbname = "eucip";

$connToDatabase = new mysqli($servername, $username, $password,$dbname);
$connToDatabase->set_charset('utf8');

if ($connToDatabase->connect_error) {
    die("Connection failed: " . $connToDatabase->connect_error);
}

$question = "Milliseid5 allpoolt loetletud tegevusi on sobiv läbi viia töökeskkonnas:";
$answer = "Koodi5 kirjutamine;Kasutajatega testimine;
kasutajate koolitus;koodi kompileeriminie;moodulite testimine;
kliendipoolne igapäev2atöö;regressioontestimine;koormustestimine;";
$correctAnswer = "Vää5r;Väär;Õige;Väär;Väär;Õige;Väär;Õige;";

$sqlQuery = "INSERT INTO questions ( question, answer, correctAnswer )
SELECT '$question','$answer','$correctAnswer'";

executecommand($connToDatabase,$sqlQuery);

$connToDatabase->close();


function executeCommand($conn, $sqlQuery){
    if ($conn->query($sqlQuery) === TRUE) {
        echo "Command executed successfully";
    }else {
        echo "Error: " . $sqlQuery . "<br>" . $conn->error;
    }
}
?>