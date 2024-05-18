<?php
include '../config/db_config.php';
$sql = "SELECT * FROM book";
$result = $conn->query($sql);
?>
    <!DOCTYPE html>
    <html>
    <head>
        <title>Book List</title>
        <link rel="stylesheet" type="text/css" href="../css/styles.css">
    </head>
    <body>
    <h1></h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Tittle</th>
            <th>Author</th>
            <th>Publication date</th>
            <th>ISBN</th>
            <th>Available</th>
            <th>Number of pages</th>
        </tr>
        <?php
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                echo "<tr><td>" . $row["id"]. "</td><td>" . $row["title"]. "</td><td>" . $row["author"]. "</td><td>" . $row["publishedDate"]. "</td><td>" . $row["ISBN"]. "</td><td>" . ($row["available"] ? 'Sí' : 'No'). "</td><td>" . $row["pageCount"]. "</td></tr>";
            }
        } else {
            echo "<tr><td colspan='7'>No books available</td></tr>";
        }
        ?>
    </table>
    </body>
    </html>
<?php
$conn->close();
?>