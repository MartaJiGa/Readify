<?php
include '../config/db_config.php';
$sql = "SELECT * FROM user";
$result = $conn->query($sql);
?>
    <!DOCTYPE html>
    <html>
    <head>
        <title>Users List</title>
        <link rel="stylesheet" type="text/css" href="../css/styles.css">
    </head>
    <body>
    <h1></h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>MemberShipDate</th>
            <th>Active</th>
        </tr>
        <?php
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                echo "<tr><td>" . $row["id"]. "</td><td>" . $row["name"]. "</td><td>" . $row["email"]. "</td><td>" . $row["membershipDate"]. "</td><td>" . ($row["active"] ? 'Sí' : 'No'). "</td></tr>";
            }
        } else {
            echo "<tr><td colspan='5'>There aren´t no registered users</td></tr>";
        }
        ?>
    </table>
    </body>
    </html>
<?php
$conn->close();
?>
<?php