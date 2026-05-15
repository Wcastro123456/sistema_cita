const mysql = require('mysql2');

    // Crear la conexión
    const connection = mysql.createConnection({
      host: 'localhost',
      port: 3306,          // puerto de MySQL
      user: 'root',      // usuario de MySQL
      password: 'root',      // contraseña
      database: 'sistema_citas'
    });

    // Conectar
    connection.connect((err) => {
      if (err) {
        console.error('Error conectando: ' + err.stack);
        return;
      }
      console.log('Conectado con éxito a MySQL');
    });
  javascript
const agendarCita = async (datos) => {
    try {
        const response = await fetch('http://localhost:8080/api/citas', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams(datos) // Envía nombre y fecha
        });

        const resultado = await response.json();
        console.log(resultado.message); // "Cita agendada" o el error que envíe el Java
    } catch (error) {
        console.error("Error al conectar con el servidor Java:", error);
    }
}