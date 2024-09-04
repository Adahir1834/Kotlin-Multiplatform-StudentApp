import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.Alignment
import database.Estudiante

@Composable
fun EditStudentForm(
    estudiante: Estudiante,
    onDismiss: () -> Unit,
    onSubmit: (Estudiante) -> Unit
) {
    var nombre by remember { mutableStateOf(estudiante.nombre) }
    var edad by remember { mutableStateOf(estudiante.edad.toString()) }
    var grupo by remember { mutableStateOf(estudiante.grupo) }
    var promediogeneral by remember { mutableStateOf(estudiante.promediogeneral.toString()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Editar Estudiante",
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = edad,
                    onValueChange = { edad = it },
                    label = { Text("Edad") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = grupo,
                    onValueChange = { grupo = it },
                    label = { Text("Grupo") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = promediogeneral,
                    onValueChange = { promediogeneral = it },
                    label = { Text("Promedio General") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val edadInt = edad.toIntOrNull() ?: 0
                val promedioDouble = promediogeneral.toDoubleOrNull() ?: 0.0
                val updatedEstudiante = estudiante.copy(
                    nombre = nombre,
                    edad = edadInt,
                    grupo = grupo,
                    promediogeneral = promedioDouble
                )
                onSubmit(updatedEstudiante)
            }) {
                Text("Actualizar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
