import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import database.Estudiante

@Composable
fun StudentDetailsDialog(
    estudiante: Estudiante,
    onDismiss: () -> Unit,
    onEdit: (Estudiante) -> Unit,
    onDelete: (Int) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Detalles del Estudiante",
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
            )
        },
        text = {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Nombre: ${estudiante.nombre}")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Edad: ${estudiante.edad}")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Grupo: ${estudiante.grupo}")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Promedio General: ${estudiante.promediogeneral}")
            }
        },
        confirmButton = {
            Row {
                Button(onClick = {
                    onEdit(estudiante) // Pasar el estudiante al formulario de edición
                }) {
                    Text("Actualizar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    estudiante.id?.let { id ->
                        onDelete(id)
                        onDismiss() // Cerrar la ventana de detalles después de eliminar
                    }
                }) {
                    Text("Eliminar")
                }
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cerrar")
            }
        }
    )
}
