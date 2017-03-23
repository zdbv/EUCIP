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
readFileContents($connToDatabase);

$connToDatabase->close();


function executeCommand($conn, $sqlQuery){
    if ($conn->query($sqlQuery) === TRUE) {
        echo "Command executed successfully";
    }else {
        echo "Error: " . $sqlQuery . "<br>" . $conn->error;
    }
}
function readFileContents($connToDatabase){
    
	$fileContents = file_get_contents('data.txt', true);
	$fileData = explode("\n", $fileContents);
	foreach ($fileData as $question) {
        $seperateQuestion = explode("►", $question); 
        $sqlQuery = "INSERT INTO arendus ( question, answer, correctAnswer )
            SELECT '$seperateQuestion[0]','$seperateQuestion[1]','$seperateQuestion[2]'";
            
        executecommand($connToDatabase,$sqlQuery);   
		echo $question."<br><br>";
	}
}
?>
